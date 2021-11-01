package icu.junyao.back.service;

import icu.junyao.back.entity.CourseComment;
import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.back.req.PageCourseCommentReq;
import icu.junyao.back.res.CourseCommentRes;
import icu.junyao.common.entity.PageCondition;
import icu.junyao.common.entity.PageResult;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
public interface CourseCommentService extends IService<CourseComment> {

    /**
     * 获取课程分页评论
     *
     * @param pageCourseCommentReq {@link PageCourseCommentReq}
     * @return 当前页的课程评论
     */
    PageResult<CourseCommentRes> pageComment(PageCourseCommentReq pageCourseCommentReq);
}
