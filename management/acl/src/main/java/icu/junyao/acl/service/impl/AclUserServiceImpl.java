package icu.junyao.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.acl.entity.AclUser;
import icu.junyao.acl.mapper.AclUserMapper;
import icu.junyao.acl.req.AclUserReq;
import icu.junyao.acl.service.AclUserService;
import icu.junyao.common.enums.BusinessResponseEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author johnson
 * @since 2021-10-04
 */
@Service
@RequiredArgsConstructor
public class AclUserServiceImpl extends ServiceImpl<AclUserMapper, AclUser> implements AclUserService {

    @Override
    public void saveAclUser(AclUserReq aclUserReq) {
        // 去重判断
        LambdaQueryWrapper<AclUser> aclUserLambdaQueryWrapper = Wrappers.lambdaQuery();
        aclUserLambdaQueryWrapper.eq(AclUser::getUsername, aclUserReq.getUsername());
        AclUser existUser = super.getOne(aclUserLambdaQueryWrapper);
        if (existUser != null) {
            throw BusinessResponseEnum.USER_NAME_REPEAT.newException();
        }

        existUser = new AclUser();
        BeanUtils.copyProperties(aclUserReq, existUser);
        super.save(existUser);
    }
}
