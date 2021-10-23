package icu.junyao.acl.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.acl.entity.AclRole;
import icu.junyao.acl.entity.AclUserRole;
import icu.junyao.acl.mapper.AclUserRoleMapper;
import icu.junyao.acl.req.AclUserRoleReq;
import icu.junyao.acl.res.AllAclRoleInfoRes;
import icu.junyao.acl.service.AclRoleService;
import icu.junyao.acl.service.AclUserRoleService;
import icu.junyao.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author johnson
 * @since 2021-10-04
 */
@Service
@RequiredArgsConstructor
public class AclUserRoleServiceImpl extends ServiceImpl<AclUserRoleMapper, AclUserRole> implements AclUserRoleService {

    private final AclRoleService aclRoleService;

    @Override
    public List<String> gainCurrentUserRoleNameList() {
        if (null == SecurityUtils.getUser()) {
            throw new BadCredentialsException("当前没有登录用户");
        }

        LambdaQueryWrapper<AclUserRole> aclUserRoleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        aclUserRoleLambdaQueryWrapper.eq(AclUserRole::getUserId, SecurityUtils.getUserId());
        List<String> roleIdList = super.list(aclUserRoleLambdaQueryWrapper)
                .stream().map(AclUserRole::getRoleId).collect(Collectors.toList());
        return aclRoleService.gainRoleNameListByRoleIdList(roleIdList);
    }

    @Override
    public List<String> gainCurrentUserRoleIdList(String aclUserId) {
        // 获取用户的角色id集合
        LambdaQueryWrapper<AclUserRole> aclUserRoleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        aclUserRoleLambdaQueryWrapper.eq(AclUserRole::getUserId, aclUserId);
        return super.list(aclUserRoleLambdaQueryWrapper)
                .stream().map(AclUserRole::getRoleId).collect(Collectors.toList());
    }

    @Override
    public List<AllAclRoleInfoRes> gainRoleInfoByUserId(String id) {
        // 获取所有角色数据, 属性转移到有选择标记的角色类上
        List<AclRole> allAclRoleList = aclRoleService.list();
        List<AllAclRoleInfoRes> allAclRoleInfoResList = BeanUtil.copyToList(allAclRoleList, AllAclRoleInfoRes.class, CopyOptions.create());

        // 获取用户的角色id列表
        LambdaQueryWrapper<AclUserRole> aclUserRoleLambdaQueryWrapper = Wrappers.lambdaQuery();
        aclUserRoleLambdaQueryWrapper.eq(AclUserRole::getUserId, id);
        List<String> roleId = super.list(aclUserRoleLambdaQueryWrapper)
                .stream().map(AclUserRole::getRoleId).collect(Collectors.toList());

        // 对所有角色进行标记
        allAclRoleInfoResList.forEach(r -> {
            if (roleId.contains(r.getId())) {
                r.setChecked(true);
            }
        });
        return allAclRoleInfoResList;
    }

    @Override
    public void assignRoleToUser(AclUserRoleReq aclUserRoleReq) {
        // 先删除用户现有的角色
        LambdaQueryWrapper<AclUserRole> aclUserRoleLambdaQueryWrapper = Wrappers.lambdaQuery();
        aclUserRoleLambdaQueryWrapper.eq(AclUserRole::getUserId, aclUserRoleReq.getUserId());
        super.remove(aclUserRoleLambdaQueryWrapper);

        // 给用户分配新角色
        List<AclUserRole> aclUserRoleList = new ArrayList<>();
        aclUserRoleReq.getRoleIdList().forEach(roleId -> {
            AclUserRole aclUserRole = new AclUserRole();
            aclUserRole.setRoleId(roleId);
            aclUserRole.setUserId(aclUserRoleReq.getUserId());
            aclUserRoleList.add(aclUserRole);
        });
        super.saveBatch(aclUserRoleList);
    }
}
