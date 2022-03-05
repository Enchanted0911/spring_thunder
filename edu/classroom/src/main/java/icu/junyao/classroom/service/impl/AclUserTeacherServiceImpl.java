package icu.junyao.classroom.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.classroom.entity.AclUserTeacher;
import icu.junyao.classroom.mapper.AclUserTeacherMapper;
import icu.junyao.classroom.service.AclUserTeacherService;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 管理员账户关联教师 服务实现类
 * </p>
 *
 * @author johnson
 * @since 2022-02-27
 */
@Service
public class AclUserTeacherServiceImpl extends ServiceImpl<AclUserTeacherMapper, AclUserTeacher> implements AclUserTeacherService {
    @Override
    public String gainAclUserIdByTeacherId(String id) {
        return super.getOne(Wrappers.lambdaQuery(AclUserTeacher.class)
                .eq(AclUserTeacher::getTeacherId, id)).getAclUserId();
    }

    @Override
    public String gainTeacherIdByAclUserId(String id) {
        return super.getOne(Wrappers.lambdaQuery(AclUserTeacher.class)
                .eq(AclUserTeacher::getAclUserId, id)).getTeacherId();
    }
}
