package icu.junyao.acl.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.acl.constant.CommonConstant;
import icu.junyao.acl.entity.AclRole;
import icu.junyao.acl.entity.AclUser;
import icu.junyao.acl.entity.AclUserRole;
import icu.junyao.acl.mapper.AclUserMapper;
import icu.junyao.acl.mapper.AclUserRoleMapper;
import icu.junyao.acl.req.AclUserEditReq;
import icu.junyao.acl.req.AclUserReq;
import icu.junyao.acl.req.PageUserReq;
import icu.junyao.acl.res.AclUserDetailRes;
import icu.junyao.acl.service.AclUserService;
import icu.junyao.common.entity.PageResult;
import icu.junyao.common.enums.BusinessResponseEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    private final AclUserRoleMapper aclUserRoleMapper;

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
        if (StrUtil.isEmpty(existUser.getAvatar())) {
            existUser.setAvatar(CommonConstant.DEFAULT_AVATAR);
        }
        super.save(existUser);
    }

    @Override
    public PageResult<AclUser> pageUser(PageUserReq pageUserReq) {
        IPage<AclUser> aclUserPage = new Page<>(pageUserReq.getPage(), pageUserReq.getPageSize());
        LambdaQueryWrapper<AclUser> aclUserLambdaQueryWrapper = Wrappers.lambdaQuery();

        // 按名称模糊查询
        aclUserLambdaQueryWrapper.like(StrUtil.isNotEmpty(pageUserReq.getUsername()), AclUser::getUsername, pageUserReq.getUsername());

        super.page(aclUserPage, aclUserLambdaQueryWrapper);
        return new PageResult<>(aclUserPage.getTotal(), aclUserPage.getRecords());
    }

    @Override
    public void updateUser(AclUserEditReq aclUserEditReq) {
        // 去重判断
        LambdaQueryWrapper<AclUser> aclUserLambdaQueryWrapper = Wrappers.lambdaQuery();
        aclUserLambdaQueryWrapper.eq(AclUser::getUsername, aclUserEditReq.getUsername())
                .ne(AclUser::getId, aclUserEditReq.getId());
        AclUser existUser = super.getOne(aclUserLambdaQueryWrapper);
        if (existUser != null) {
            throw BusinessResponseEnum.USER_NAME_REPEAT.newException();
        }

        existUser = new AclUser();
        BeanUtils.copyProperties(aclUserEditReq, existUser);
        super.updateById(existUser);
    }

    @Override
    public void removeUser(String id) {
        // 删除用户和角色的绑定
        LambdaQueryWrapper<AclUserRole> aclUserRoleLambdaQueryWrapper = Wrappers.lambdaQuery();
        aclUserRoleLambdaQueryWrapper.eq(AclUserRole::getUserId, id);
        aclUserRoleMapper.delete(aclUserRoleLambdaQueryWrapper);

        // 删除用户
        super.removeById(id);
    }

    @Override
    public void removeBatchUser(List<String> idList) {
        // 删除用户和角色的绑定
        LambdaQueryWrapper<AclUserRole> aclUserRoleLambdaQueryWrapper = Wrappers.lambdaQuery();
        aclUserRoleLambdaQueryWrapper.in(AclUserRole::getUserId, idList);
        aclUserRoleMapper.delete(aclUserRoleLambdaQueryWrapper);

        // 删除用户
        super.removeByIds(idList);
    }

    @Override
    public AclUserDetailRes userDetails(String id) {
        AclUser aclUser = super.getById(id);
        AclUserDetailRes aclUserDetailRes = new AclUserDetailRes();
        BeanUtils.copyProperties(aclUser, aclUserDetailRes);
        return aclUserDetailRes;
    }
}
