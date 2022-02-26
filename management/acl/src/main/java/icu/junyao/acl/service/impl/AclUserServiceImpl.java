package icu.junyao.acl.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.acl.constant.CommonConstant;
import icu.junyao.acl.entity.AclUser;
import icu.junyao.acl.entity.AclUserRole;
import icu.junyao.acl.mapper.AclUserMapper;
import icu.junyao.acl.mapper.AclUserRoleMapper;
import icu.junyao.acl.req.AclUserEditReq;
import icu.junyao.acl.req.AclUserReq;
import icu.junyao.acl.req.PageUserReq;
import icu.junyao.acl.req.PasswordReq;
import icu.junyao.acl.res.AclUserDetailRes;
import icu.junyao.acl.res.AclUserRes;
import icu.junyao.acl.service.AclUserService;
import icu.junyao.acl.utils.CatchUtil;
import icu.junyao.common.entity.PageResult;
import icu.junyao.common.enums.BusinessResponseEnum;
import icu.junyao.security.entity.JwtUser;
import icu.junyao.security.util.SecurityUtils;
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
    private final PasswordEncoder passwordEncoder;
    private final CatchUtil catchUtil;

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

        // 密码加密
        existUser.setPassword(passwordEncoder.encode(existUser.getPassword()));

        super.save(existUser);
    }

    @Override
    public PageResult<AclUser> pageUser(PageUserReq pageUserReq) {
        IPage<AclUser> aclUserPage = new Page<>(pageUserReq.getPage(), pageUserReq.getPageSize());
        LambdaQueryWrapper<AclUser> aclUserLambdaQueryWrapper = Wrappers.lambdaQuery();

        // 按名称模糊查询
        aclUserLambdaQueryWrapper.like(StrUtil.isNotEmpty(pageUserReq.getUsername()), AclUser::getUsername, pageUserReq.getUsername());

        super.page(aclUserPage, aclUserLambdaQueryWrapper);

        // 如果删除的是最后的数据导致最后一页没有数据了, 此时需要将页数减一
        if (CollUtil.isEmpty(aclUserPage.getRecords()) && aclUserPage.getTotal() > 0) {
            aclUserPage.setCurrent(pageUserReq.getPage() - 1);
            super.page(aclUserPage);
        }
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

        // 更新缓存
        if (super.updateById(existUser)) {
            catchUtil.freshAclUserInfo(existUser);
        }

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

    @Override
    public void updateUserSelf(AclUserEditReq aclUserEditReq) {
        String id = SecurityUtils.getUserId();
        aclUserEditReq.setId(id);
        this.updateUser(aclUserEditReq);
    }

    @Override
    public void updatePassword(PasswordReq passwordReq) {
        String id = SecurityUtils.getUserId();
        AclUser aclUser = super.getById(id);
        // 判断旧密码是否正确
        boolean matches = passwordEncoder.matches(passwordReq.getOldPassword(), aclUser.getPassword());
        BusinessResponseEnum.OLD_PWD_ERROR.assertIsTrue(matches);

        // 旧密码不能和新密码相同
        boolean flag = passwordReq.getOldPassword().equals(passwordReq.getNewPassword());
        BusinessResponseEnum.NEW_PWD_EQUALS_OLD.assertIsFalse(flag);

        // 新密码加密
        aclUser.setPassword(passwordEncoder.encode(passwordReq.getNewPassword()));

        if (super.updateById(aclUser)) {
            catchUtil.freshAclUserInfo(aclUser);
        }

    }

    @Override
    public AclUserDetailRes currentUserInfo() {
        JwtUser user = SecurityUtils.getUser();
        AclUserDetailRes aclUserDetailRes = new AclUserDetailRes();
        BeanUtils.copyProperties(user, aclUserDetailRes);
        return aclUserDetailRes;
    }

    @Override
    public List<AclUserRes> listUser() {
        List<AclUser> aclUserList = super.list();
        return BeanUtil
                .copyToList(aclUserList, AclUserRes.class, CopyOptions.create());
    }
}
