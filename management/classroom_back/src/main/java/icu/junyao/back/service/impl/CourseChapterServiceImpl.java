package icu.junyao.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.back.entity.CourseChapter;
import icu.junyao.back.entity.CourseSubsection;
import icu.junyao.back.mapper.CourseChapterMapper;
import icu.junyao.back.mapper.CourseSubsectionMapper;
import icu.junyao.back.req.CourseChapterEditReq;
import icu.junyao.back.req.CourseChapterReq;
import icu.junyao.back.res.CourseChapterDetailRes;
import icu.junyao.back.service.CourseChapterService;
import icu.junyao.common.enums.BusinessResponseEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
public class CourseChapterServiceImpl extends ServiceImpl<CourseChapterMapper, CourseChapter> implements CourseChapterService {

    private final CourseSubsectionMapper courseSubsectionMapper;

    @Override
    public void saveChapter(CourseChapterReq courseChapterReq) {

        // 去重, 同一个课程不能有相同的章节名称
        LambdaQueryWrapper<CourseChapter> chapterLambdaQueryWrapper = Wrappers.lambdaQuery();
        chapterLambdaQueryWrapper
                .eq(CourseChapter::getCourseId, courseChapterReq.getCourseId())
                .eq(CourseChapter::getTitle, courseChapterReq.getTitle());
        CourseChapter courseChapter = super.getOne(chapterLambdaQueryWrapper);
        if (courseChapter != null) {
            throw BusinessResponseEnum.CHAPTER_EXIST.newException();
        }

        CourseChapter chapter = new CourseChapter();
        BeanUtils.copyProperties(courseChapterReq, chapter);
        super.save(chapter);
    }

    @Override
    public void updateChapter(CourseChapterEditReq courseChapterEditReq) {
        // 去重, 同一个课程不能有相同的章节名称, 排除自身
        LambdaQueryWrapper<CourseChapter> chapterLambdaQueryWrapper = Wrappers.lambdaQuery();
        chapterLambdaQueryWrapper
                .eq(CourseChapter::getCourseId, courseChapterEditReq.getCourseId())
                .eq(CourseChapter::getTitle, courseChapterEditReq.getTitle())
                .ne(CourseChapter::getId, courseChapterEditReq.getId());
        CourseChapter courseChapter = super.getOne(chapterLambdaQueryWrapper);
        if (courseChapter != null) {
            throw BusinessResponseEnum.CHAPTER_EXIST.newException();
        }

        CourseChapter chapter = new CourseChapter();
        BeanUtils.copyProperties(courseChapterEditReq, chapter);
        super.updateById(chapter);
    }

    @Override
    public void deleteChapter(String id) {
        // 先删除小节
        LambdaQueryWrapper<CourseSubsection> subsectionLambdaQueryWrapper = Wrappers.lambdaQuery();
        subsectionLambdaQueryWrapper.eq(CourseSubsection::getChapterId, id);
        courseSubsectionMapper.delete(subsectionLambdaQueryWrapper);

        // 删除章节
        super.removeById(id);
    }

    @Override
    public CourseChapterDetailRes chapterDetails(String id) {
        CourseChapter chapter = super.getById(id);
        CourseChapterDetailRes chapterDetailRes = new CourseChapterDetailRes();
        BeanUtils.copyProperties(chapter, chapterDetailRes);
        return chapterDetailRes;
    }
}
