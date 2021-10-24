package icu.junyao.back.service;

import icu.junyao.back.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.back.res.CourseChapterRes;

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
}
