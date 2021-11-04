package icu.junyao.back.service;

import icu.junyao.back.entity.CourseSubsection;
import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.back.req.CourseSubsectionEditReq;
import icu.junyao.back.req.CourseSubsectionReq;
import icu.junyao.back.res.CourseSubsectionRes;
import icu.junyao.common.entity.R;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
public interface CourseSubsectionService extends IService<CourseSubsection> {

    /**
     * 根据课程小节id获取课程小节
     *
     * @param id 小节id
     * @return {@link CourseSubsectionRes}
     */
    CourseSubsectionRes gainCourseSubsection(String id);

    /**
     * 新增小节
     *
     * @param courseSubsectionReq {@link CourseSubsectionReq}
     */
    void saveCourseSubsection(CourseSubsectionReq courseSubsectionReq);

    /**
     * 修改小节信息
     *
     * @param courseSubsectionEditReq {@link CourseSubsectionEditReq}
     */
    void updateCourseSubsection(CourseSubsectionEditReq courseSubsectionEditReq);

    /**
     * 删除课程小节
     *
     * @param id 课程小节id
     */
    void removeCourseSubsection(String id);
}
