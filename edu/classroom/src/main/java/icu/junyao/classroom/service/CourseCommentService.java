package icu.junyao.classroom.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import icu.junyao.classroom.entity.CourseComment;
import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.classroom.req.CourseCommentReq;
import icu.junyao.classroom.req.PageCourseCommentReq;
import icu.junyao.classroom.res.CourseCommentRes;
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

    /**
     * 添加课程评论
     *
     * @param courseCommentReq {@link CourseCommentReq}
     * @param decodedJwt {@link DecodedJWT}
     */
    void saveComment(CourseCommentReq courseCommentReq, DecodedJWT decodedJwt);
}
