package icu.junyao.back.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import icu.junyao.back.entity.Course;
import icu.junyao.back.entity.CourseChapter;
import icu.junyao.back.entity.CourseSubsection;
import icu.junyao.back.mapper.CourseChapterMapper;
import icu.junyao.back.mapper.CourseMapper;
import icu.junyao.back.mapper.CourseSubsectionMapper;
import icu.junyao.back.res.CourseChapterRes;
import icu.junyao.back.res.CourseSubsectionRes;
import icu.junyao.back.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
}
