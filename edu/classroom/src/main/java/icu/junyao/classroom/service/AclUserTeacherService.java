package icu.junyao.back.service;


import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.back.entity.AclUserTeacher;

/**
 * <p>
 * 管理员账户关联教师 服务类
 * </p>
 *
 * @author johnson
 * @since 2022-02-27
 */
public interface AclUserTeacherService extends IService<AclUserTeacher> {
    /**
     * 通过教师id获取对应管理员id
     *
     * @param id 教师id
     * @return 管理员id
     */
    String gainAclUserIdByTeacherId(String id);


    /**
     * 通过管理员id获取对应教师id
     *
     * @param id 管理员id
     * @return 教师id
     */
    String gainTeacherIdByAclUserId(String id);
}
