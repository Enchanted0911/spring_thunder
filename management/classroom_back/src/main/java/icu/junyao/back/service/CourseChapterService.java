package icu.junyao.back.service;

import icu.junyao.back.entity.CourseChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.back.req.CourseChapterEditReq;
import icu.junyao.back.req.CourseChapterReq;
import icu.junyao.back.res.CourseChapterDetailRes;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
public interface CourseChapterService extends IService<CourseChapter> {

    /**
     * 新增章节
     *
     * @param courseChapterReq {@link CourseChapterReq}
     */
    void saveChapter(CourseChapterReq courseChapterReq);

    /**
     * 修改章节
     *
     * @param courseChapterEditReq {@link CourseChapterEditReq}
     */
    void updateChapter(CourseChapterEditReq courseChapterEditReq);

    /**
     * 删除章节
     *
     * @param id 待删除章节id
     */
    void deleteChapter(String id);

    /**
     * 获取单个章节详情
     *
     * @param id 待获取章节id
     * @return {@link CourseChapterDetailRes}
     */
    CourseChapterDetailRes chapterDetails(String id);
}
