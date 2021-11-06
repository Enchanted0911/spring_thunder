package icu.junyao.classroom.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.junyao.classroom.client.OrderClient;
import icu.junyao.classroom.constant.ClassroomConstants;
import icu.junyao.classroom.entity.Course;
import icu.junyao.classroom.entity.CourseChapter;
import icu.junyao.classroom.entity.CourseSubsection;
import icu.junyao.classroom.entity.Teacher;
import icu.junyao.classroom.mapper.*;
import icu.junyao.classroom.req.PageCourseReq;
import icu.junyao.classroom.res.CourseChapterRes;
import icu.junyao.classroom.res.CourseDetailRes;
import icu.junyao.classroom.res.CourseRes;
import icu.junyao.classroom.res.CourseSubsectionRes;
import icu.junyao.classroom.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.common.entity.PageResult;
import icu.junyao.common.enums.BusinessResponseEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
@Service
@RequiredArgsConstructor
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    private final CourseChapterMapper courseChapterMapper;
    private final CourseSubsectionMapper courseSubsectionMapper;
    private final TeacherMapper teacherMapper;
    private final SubjectMapper subjectMapper;
    public final OrderClient orderClient;

    @Override
    public List<CourseRes> list8HotCourse() {
        LambdaQueryWrapper<Course> courseLambdaQueryWrapper = Wrappers.lambdaQuery();
        courseLambdaQueryWrapper.eq(Course::getStatus, ClassroomConstants.PUBLISH_STATUS)
                        .orderByDesc(Course::getBuyCount).last("limit 8");
        List<Course> courseList = super.list(courseLambdaQueryWrapper);
        return BeanUtil.copyToList(courseList, CourseRes.class, CopyOptions.create());
    }

    @Override
    public List<CourseRes> listCourseByTeacherId(String teacherId) {
        LambdaQueryWrapper<Course> courseLambdaQueryWrapper = Wrappers.lambdaQuery();
        courseLambdaQueryWrapper.eq(Course::getTeacherId, teacherId)
                .eq(Course::getStatus, ClassroomConstants.PUBLISH_STATUS);
        List<Course> courseList = super.list(courseLambdaQueryWrapper);
        return BeanUtil.copyToList(courseList, CourseRes.class, CopyOptions.create());
    }

    @Override
    public PageResult<CourseRes> pageCourse(PageCourseReq pageCourseReq) {
        IPage<Course> coursePage = new Page<>(pageCourseReq.getPage(), pageCourseReq.getPageSize());
        LambdaQueryWrapper<Course> courseLambdaQueryWrapper = Wrappers.lambdaQuery();
        courseLambdaQueryWrapper
                .eq(StrUtil.isNotEmpty(pageCourseReq.getSubjectParentId()), Course::getSubjectParentId, pageCourseReq.getSubjectParentId())
                .eq(StrUtil.isNotEmpty(pageCourseReq.getSubjectId()), Course::getSubjectId, pageCourseReq.getSubjectId())
                .eq(Course::getStatus, ClassroomConstants.PUBLISH_STATUS)
                .like(StrUtil.isNotEmpty(pageCourseReq.getCourseName()), Course::getTitle, pageCourseReq.getCourseName())
                .orderByDesc(StrUtil.isNotEmpty(pageCourseReq.getBuyCountSort()), Course::getBuyCount)
                .orderByDesc(StrUtil.isNotEmpty(pageCourseReq.getCreateTimeSort()), Course::getCreatedTime)
                .orderByDesc(StrUtil.isNotEmpty(pageCourseReq.getPriceSort()), Course::getPrice);
        super.page(coursePage, courseLambdaQueryWrapper);

        List<CourseRes> courseResList = BeanUtil.copyToList(coursePage.getRecords(), CourseRes.class, CopyOptions.create());
        return new PageResult<>(coursePage.getTotal(), courseResList);
    }

    @Override
    public List<CourseChapterRes> gainCourseOutline(String id) {

        // 查出课程的所有章节
        LambdaQueryWrapper<CourseChapter> chapterLambdaQueryWrapper = Wrappers.lambdaQuery();
        chapterLambdaQueryWrapper
                .eq(CourseChapter::getCourseId, id)
                .orderByAsc(CourseChapter::getSort);
        List<CourseChapter> courseChapterList = courseChapterMapper.selectList(chapterLambdaQueryWrapper);

        // 对每个章节查出小节, 封装进res
        List<CourseChapterRes> courseChapterResList = new ArrayList<>();
        LambdaQueryWrapper<CourseSubsection> subsectionLambdaQueryWrapper = Wrappers.lambdaQuery();
        courseChapterList.forEach(chapter -> {
            // 查出该章节所有小节
            subsectionLambdaQueryWrapper
                    .eq(CourseSubsection::getChapterId, chapter.getId())
                    .orderByAsc(CourseSubsection::getSort);
            List<CourseSubsection> courseSubsectionList = courseSubsectionMapper
                    .selectList(subsectionLambdaQueryWrapper);

            // 章节属性转移
            CourseChapterRes courseChapterRes = new CourseChapterRes();
            BeanUtils.copyProperties(chapter, courseChapterRes);

            // 小节属性转移
            List<CourseSubsectionRes> subsectionResList = BeanUtil
                    .copyToList(courseSubsectionList, CourseSubsectionRes.class, CopyOptions.create());

            courseChapterRes.setCourseSubsectionResList(subsectionResList);
            courseChapterResList.add(courseChapterRes);
        });

        return courseChapterResList;
    }

    @Override
    public CourseDetailRes courseDetails(String id) {
        Course course = super.getById(id);
        CourseDetailRes courseDetailRes = new CourseDetailRes();
        BeanUtils.copyProperties(course, courseDetailRes);

        // 属性id转name
        Teacher teacher = teacherMapper.selectById(course.getTeacherId());
        String subjectName = subjectMapper.selectById(course.getSubjectId()).getTitle();
        String subjectParentName = subjectMapper.selectById(course.getSubjectParentId()).getTitle();

        courseDetailRes.setSubjectName(subjectName);
        courseDetailRes.setSubjectParentName(subjectParentName);
        courseDetailRes.setTeacherName(teacher.getName());
        courseDetailRes.setTeacherIntro(teacher.getIntro());
        courseDetailRes.setTeacherAvatar(teacher.getAvatar());

        return courseDetailRes;
    }

    @Override
    public Boolean gainIfBuy(String id, DecodedJWT decodedJwt) {
        // 校验解析jwt
        if (decodedJwt == null) {
            throw BusinessResponseEnum.TOKEN_ERROR.newException();
        }

        String userId = decodedJwt.getClaim("id").asString();
        return orderClient.ifBought(userId, id).getData();
    }
}
