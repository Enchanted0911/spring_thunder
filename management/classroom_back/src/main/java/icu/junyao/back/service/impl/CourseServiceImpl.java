package icu.junyao.back.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.junyao.back.constant.ClassroomConstants;
import icu.junyao.back.entity.Course;
import icu.junyao.back.entity.CourseChapter;
import icu.junyao.back.entity.CourseDescription;
import icu.junyao.back.entity.CourseSubsection;
import icu.junyao.back.mapper.*;
import icu.junyao.back.req.CourseBaseInfoEditReq;
import icu.junyao.back.req.CourseBaseInfoReq;
import icu.junyao.back.req.PageCourseReq;
import icu.junyao.back.res.*;
import icu.junyao.back.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.common.entity.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final CourseDescriptionMapper courseDescriptionMapper;
    private final TeacherMapper teacherMapper;
    private final SubjectMapper subjectMapper;

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
    public PageResult<CourseRes> pageCourse(PageCourseReq pageCourseReq) {
        // 构建分页条件
        IPage<Course> coursePage = new Page<>(pageCourseReq.getPage(), pageCourseReq.getPageSize());
        LambdaQueryWrapper<Course> courseLambdaQueryWrapper = Wrappers.lambdaQuery();
        courseLambdaQueryWrapper.eq(Course::getStatus, pageCourseReq.getStatus())
                .like(Course::getTitle, pageCourseReq.getTitle())
                .orderByDesc(Course::getCreatedTime);
        super.page(coursePage, courseLambdaQueryWrapper);

        // 属性转移
        List<CourseRes> courseResList = BeanUtil
                .copyToList(coursePage.getRecords(), CourseRes.class, CopyOptions.create());

        return new PageResult<>(coursePage.getTotal(), courseResList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveCourseBaseInfo(CourseBaseInfoReq courseBaseInfoReq) {

        // 新增课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseBaseInfoReq, course);
        course.setCover(course.getCover() == null ? ClassroomConstants.DEFAULT_COURSE_COVER : course.getCover());
        super.save(course);

        // 保存课程描述
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseBaseInfoReq.getDescription());
        courseDescription.setId(course.getId());
        courseDescriptionMapper.insert(courseDescription);

        return course.getId();
    }

    @Override
    public CourseBaseInfoRes gainCourseBaseInfo(String id) {
        // 获取课程信息
        Course course = super.getById(id);
        CourseBaseInfoRes courseBaseInfoRes = new CourseBaseInfoRes();
        BeanUtils.copyProperties(course, courseBaseInfoRes);

        // 获取课程描述信息
        CourseDescription courseDescription = courseDescriptionMapper.selectById(id);
        courseBaseInfoRes.setDescription(courseDescription.getDescription());

        return courseBaseInfoRes;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCourseBaseInfo(CourseBaseInfoEditReq courseBaseInfoEditReq) {
        // 更新课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseBaseInfoEditReq, course);
        super.updateById(course);

        // 更新课程描述
        CourseDescription courseDescription = new CourseDescription();
        BeanUtils.copyProperties(courseBaseInfoEditReq, courseDescription);
        courseDescriptionMapper.updateById(courseDescription);
    }

    @Override
    public CoursePublishInfoRes gainCoursePublishInfo(String id) {

        // 获取课程信息
        Course course = super.getById(id);
        CoursePublishInfoRes coursePublishInfoRes = new CoursePublishInfoRes();
        BeanUtils.copyProperties(course, coursePublishInfoRes);

        // 教师id转教师名称
        String teacherName = teacherMapper.selectById(course.getTeacherId()).getName();
        coursePublishInfoRes.setTeacherName(teacherName);

        // 学科id转学科名称
        String subjectOneName = subjectMapper.selectById(course.getSubjectParentId()).getTitle();
        String subjectTwoName = subjectMapper.selectById(course.getSubjectId()).getTitle();
        coursePublishInfoRes.setSubjectLevelOne(subjectOneName);
        coursePublishInfoRes.setSubjectLevelTwo(subjectTwoName);

        return coursePublishInfoRes;
    }

    @Override
    public void publishCourse(String id) {
        Course course = new Course();
        course.setId(id);
        course.setStatus(ClassroomConstants.PUBLISH_STATUS);
        super.updateById(course);
    }

    @Override
    public void removeCourse(String id) {
        
    }
}
