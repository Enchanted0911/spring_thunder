package icu.junyao.acl.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.junyao.acl.entity.AclRole;
import icu.junyao.acl.mapper.AclRoleMapper;
import icu.junyao.acl.req.PageRoleReq;
import icu.junyao.acl.res.AclRoleDetailRes;
import icu.junyao.acl.service.AclRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.common.entity.PageResult;
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
public class AclRoleServiceImpl extends ServiceImpl<AclRoleMapper, AclRole> implements AclRoleService {

    @Override
    public List<String> gainRoleNameListByRoleIdList(List<String> roleIdList) {
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
        aclRoleLambdaQueryWrapper.like(AclRole::getName, pageRoleReq.getRoleName());
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
}
