package icu.junyao.back.service;

import icu.junyao.back.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.back.req.CourseBaseInfoEditReq;
import icu.junyao.back.req.CourseBaseInfoReq;
import icu.junyao.back.req.PageCourseReq;
import icu.junyao.back.res.CourseBaseInfoRes;
import icu.junyao.back.res.CourseChapterRes;
import icu.junyao.back.res.CoursePublishInfoRes;
import icu.junyao.back.res.CourseRes;
import icu.junyao.common.entity.PageResult;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
public interface CourseService extends IService<Course> {

    /**
     * 获取课程大纲列表
     *
     * @param id 课程id
     * @return 课程大纲列表
     */
    List<CourseChapterRes> gainCourseOutline(String id);

    /**
     * 获取课程分页列表
     *
     * @param pageCourseReq {@link PageCourseReq}
     * @return 课程分页信息
     */
    PageResult<CourseRes> pageCourse(PageCourseReq pageCourseReq);

    /**
     * 新增课程-基本信息
     *
     * @param courseBaseInfoReq {@link CourseBaseInfoReq}
     * @return 新增课程的id
     */
    String saveCourseBaseInfo(CourseBaseInfoReq courseBaseInfoReq);

    /**
     * 获取课程基本信息
     *
     * @param id 课程id
     * @return {@link CourseBaseInfoRes}
     */
    CourseBaseInfoRes gainCourseBaseInfo(String id);

    /**
     * 修改课程基本信息
     *
     * @param courseBaseInfoEditReq {@link CourseBaseInfoEditReq}
     */
    void updateCourseBaseInfo(CourseBaseInfoEditReq courseBaseInfoEditReq);

    /**
     * 获取课程发布信息
     *
     * @param id 课程id
     * @return {@link CoursePublishInfoRes}
     */
    CoursePublishInfoRes gainCoursePublishInfo(String id);

    /**
     * 发布课程
     *
     * @param id 课程id
     */
    void publishCourse(String id);

    /**
     * 删除课程
     *
     * @param id 课程id
     */
    void removeCourse(String id);
}
