package icu.junyao.classroom.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import icu.junyao.classroom.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.classroom.req.PageCourseReq;
import icu.junyao.classroom.res.CourseChapterRes;
import icu.junyao.classroom.res.CourseDetailRes;
import icu.junyao.classroom.res.CourseRes;
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
     * 获取前八条热门课程
     *
     * @return 前八条热门课程
     */
    List<CourseRes> list8HotCourse();

    /**
     * 根据教师id获取课程
     *
     * @param teacherId 教师id
     * @return 该教师的课程
     */
    List<CourseRes> listCourseByTeacherId(String teacherId);

    /**
     * 分页获取课程
     *
     * @param pageCourseReq {@link PageCourseReq}
     * @return 课程分页数据
     */
    PageResult<CourseRes> pageCourse(PageCourseReq pageCourseReq);


    /**
     * 获取课程大纲列表
     *
     * @param id 课程id
     * @return 课程大纲列表
     */
    List<CourseChapterRes> gainCourseOutline(String id);

    /**
     * 获取课程详情
     *
     * @param id 课程id
     * @return {@link CourseDetailRes}
     */
    CourseDetailRes courseDetails(String id);

    /**
     * 获取课程是否已购
     *
     * @param id 课程id
     * @param decodedJwt 解析用户信息
     * @return true为已购
     */
    Boolean gainIfBuy(String id, DecodedJWT decodedJwt);
}
