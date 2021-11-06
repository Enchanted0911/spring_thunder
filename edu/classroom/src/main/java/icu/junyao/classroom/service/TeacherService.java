package icu.junyao.classroom.service;

import icu.junyao.classroom.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.classroom.req.PageTeacherReq;
import icu.junyao.classroom.res.TeacherRes;
import icu.junyao.common.entity.PageResult;
import jdk.dynalink.linker.LinkerServices;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
public interface TeacherService extends IService<Teacher> {

    /**
     * 获取前四个热门教师
     *
     * @return 前四个热门教师
     */
    List<TeacherRes> list4HotTeacher();

    /**
     * 分页获取教师
     *
     * @param pageTeacherReq {@link PageTeacherReq}
     * @return 教师分页数据
     */
    PageResult<TeacherRes> pageTeacher(PageTeacherReq pageTeacherReq);

    /**
     * 获取教师详情
     *
     * @param id 教师id
     * @return {@link TeacherRes}
     */
    TeacherRes teacherDetails(String id);
}
