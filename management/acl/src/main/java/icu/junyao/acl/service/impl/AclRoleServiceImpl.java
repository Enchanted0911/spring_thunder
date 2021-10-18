package icu.junyao.acl.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.junyao.acl.entity.AclRole;
import icu.junyao.acl.entity.AclRolePermission;
import icu.junyao.acl.entity.AclUserRole;
import icu.junyao.acl.mapper.AclRoleMapper;
import icu.junyao.acl.mapper.AclRolePermissionMapper;
import icu.junyao.acl.mapper.AclUserRoleMapper;
import icu.junyao.acl.req.AclRoleEditReq;
import icu.junyao.acl.req.AclRoleReq;
import icu.junyao.acl.req.PageRoleReq;
import icu.junyao.acl.res.AclRoleDetailRes;
import icu.junyao.acl.service.AclRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.common.entity.PageResult;
import icu.junyao.common.enums.BusinessResponseEnum;
import icu.junyao.common.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author johnson
 * @since 2021-10-04
 */
@Service
@RequiredArgsConstructor
public class AclRoleServiceImpl extends ServiceImpl<AclRoleMapper, AclRole> implements AclRoleService {

    private final AclUserRoleMapper aclUserRoleMapper;
    private final AclRolePermissionMapper aclRolePermissionMapper;

    @Override
    public List<String> gainRoleNameListByRoleIdList(List<String> roleIdList) {
        // id集合为空直接返回空结果
        if (CollUtil.isEmpty(roleIdList)) {
            return new ArrayList<>();
        }

        LambdaQueryWrapper<AclRole> aclRoleLambdaQueryWrapper = Wrappers.lambdaQuery();
        aclRoleLambdaQueryWrapper.in(AclRole::getId, roleIdList);
        return super.list(aclRoleLambdaQueryWrapper).stream().map(AclRole::getName).collect(Collectors.toList());
    }

    @Override
    public PageResult<AclRole> pageRole(PageRoleReq pageRoleReq) {
        IPage<AclRole> aclRolePage = new Page<>(pageRoleReq.getPage(), pageRoleReq.getPageSize());
        LambdaQueryWrapper<AclRole> aclRoleLambdaQueryWrapper = Wrappers.lambdaQuery();

        // 按名称模糊查询
        aclRoleLambdaQueryWrapper.like(StrUtil.isNotEmpty(pageRoleReq.getRoleName()), AclRole::getName, pageRoleReq.getRoleName());

        super.page(aclRolePage, aclRoleLambdaQueryWrapper);
        return new PageResult<>(aclRolePage.getTotal(), aclRolePage.getRecords());
    }

    @Override
    public AclRoleDetailRes roleDetails(String id) {
        AclRole aclRole = super.getById(id);
        AclRoleDetailRes aclRoleDetailRes = new AclRoleDetailRes();
        BeanUtils.copyProperties(aclRole, aclRoleDetailRes);
        return aclRoleDetailRes;
    }

    @Override
    public void saveRole(AclRoleReq aclRoleReq) {
        // 去重, 角色名和角色编码不能重复
        LambdaQueryWrapper<AclRole> aclRoleLambdaQueryWrapper = Wrappers.lambdaQuery();
        aclRoleLambdaQueryWrapper.eq(AclRole::getName, aclRoleReq.getName())
                .or().eq(AclRole::getCode, aclRoleReq.getCode());
        List<AclRole> aclRoleTmpList = super.list(aclRoleLambdaQueryWrapper);
        if (CollUtil.isNotEmpty(aclRoleTmpList)) {
            throw BusinessResponseEnum.ROLE_INFO_EXIST.newException();
        }

        AclRole aclRole = new AclRole();
        BeanUtils.copyProperties(aclRoleReq, aclRole);
        super.save(aclRole);
    }

    @Override
    public void updateRole(AclRoleEditReq aclRoleEditReq) {
        // 去重, 角色名和角色编码不能重复, 不包括自身
        LambdaQueryWrapper<AclRole> aclRoleLambdaQueryWrapper = Wrappers.lambdaQuery();
        aclRoleLambdaQueryWrapper.eq(AclRole::getName, aclRoleEditReq.getName())
                .or().eq(AclRole::getCode, aclRoleEditReq.getCode());
        aclRoleLambdaQueryWrapper.and(wrapper -> wrapper.ne(AclRole::getId, aclRoleEditReq.getId()));

        List<AclRole> aclRoleTmpList = super.list(aclRoleLambdaQueryWrapper);
        if (CollUtil.isNotEmpty(aclRoleTmpList)) {
            throw BusinessResponseEnum.ROLE_INFO_EXIST.newException();
        }

        AclRole aclRole = new AclRole();
        BeanUtils.copyProperties(aclRoleEditReq, aclRole);
        super.updateById(aclRole);
    }

    @Override
    public void removeRole(String id) {
        // 查找是否有用户在使用该角色
        LambdaQueryWrapper<AclUserRole> aclUserRoleLambdaQueryWrapper = Wrappers.lambdaQuery();
        aclUserRoleLambdaQueryWrapper.eq(AclUserRole::getRoleId, id);
        List<AclUserRole> aclUserRoleList = aclUserRoleMapper.selectList(aclUserRoleLambdaQueryWrapper);
        if (CollUtil.isNotEmpty(aclUserRoleList)) {
            throw BusinessResponseEnum.ROLE_BRING_USED.newException();
        }

        // 删除角色关联的权限
        LambdaQueryWrapper<AclRolePermission> aclRolePermissionLambdaQueryWrapper = Wrappers.lambdaQuery();
        aclRolePermissionLambdaQueryWrapper.eq(AclRolePermission::getRoleId, id);
        aclRolePermissionMapper.delete(aclRolePermissionLambdaQueryWrapper);

        // 删除角色
        super.removeById(id);
    }

    @Override
    public void removeBatchRole(List<String> idList) {
        // 查找是否有用户在使用该角色
        LambdaQueryWrapper<AclUserRole> aclUserRoleLambdaQueryWrapper = Wrappers.lambdaQuery();
        aclUserRoleLambdaQueryWrapper.in(AclUserRole::getRoleId, idList);
        List<AclUserRole> aclUserRoleList = aclUserRoleMapper.selectList(aclUserRoleLambdaQueryWrapper);
        if (CollUtil.isNotEmpty(aclUserRoleList)) {
            throw BusinessResponseEnum.ROLE_BRING_USED.newException();
        }

        // 删除角色关联的权限
        LambdaQueryWrapper<AclRolePermission> aclRolePermissionLambdaQueryWrapper = Wrappers.lambdaQuery();
        aclRolePermissionLambdaQueryWrapper.in(AclRolePermission::getRoleId, idList);
        aclRolePermissionMapper.delete(aclRolePermissionLambdaQueryWrapper);

        // 删除角色
        super.removeByIds(idList);
    }
}
