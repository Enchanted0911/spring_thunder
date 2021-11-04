package icu.junyao.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.back.entity.Teacher;
import icu.junyao.back.req.PageTeacherReq;
import icu.junyao.back.req.TeacherEditReq;
import icu.junyao.back.req.TeacherReq;
import icu.junyao.back.res.TeacherRes;
import icu.junyao.common.entity.PageResult;

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
     * 获取所有教师列表
     *
     * @return 所有教师列表
     */
    List<TeacherRes> listTeacher();

    /**
     * 删除教师, 若该教师存在课程则不能删除
     *
     * @param id 教师id
     */
    void removeTeacher(String id);

    /**
     * 分页获取教师
     *
     * @param pageTeacherReq {@link PageTeacherReq}
     * @return
     */
    PageResult<TeacherRes> pageTeacher(PageTeacherReq pageTeacherReq);

    /**
     * 新增教师
     *
     * @param teacherReq {@link TeacherReq}
     */
    void saveTeacher(TeacherReq teacherReq);

    /**
     * 修改教师
     *
     * @param teacherEditReq {@link TeacherEditReq}
     */
    void updateTeacher(TeacherEditReq teacherEditReq);

    /**
     * 获取单个教师详情
     *
     * @param id 教师id
     * @return {@link TeacherRes}
     */
    TeacherRes teacherDetails(String id);
}
