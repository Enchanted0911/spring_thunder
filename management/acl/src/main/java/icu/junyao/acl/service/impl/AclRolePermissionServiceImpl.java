package icu.junyao.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.acl.entity.AclPermission;
import icu.junyao.acl.entity.AclRolePermission;
import icu.junyao.acl.mapper.AclPermissionMapper;
import icu.junyao.acl.mapper.AclRolePermissionMapper;
import icu.junyao.acl.req.AclRolePermissionAddReq;
import icu.junyao.acl.res.AclPermissionRes;
import icu.junyao.acl.service.AclRolePermissionService;
import icu.junyao.acl.utils.PermissionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色权限 服务实现类
 * </p>
 *
 * @author johnson
 * @since 2021-10-04
 */
@Service
@RequiredArgsConstructor
public class AclRolePermissionServiceImpl extends ServiceImpl<AclRolePermissionMapper, AclRolePermission> implements AclRolePermissionService {

    private final AclPermissionMapper aclPermissionMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveRolePermissionRelationShip(AclRolePermissionAddReq aclRolePermissionAddReq) {
        // 新建关系前先删除原来的关系
        LambdaQueryWrapper<AclRolePermission> aclRolePermissionLambdaQueryWrapper = Wrappers.lambdaQuery();
        aclRolePermissionLambdaQueryWrapper.eq(AclRolePermission::getRoleId, aclRolePermissionAddReq.getRoleId());
        super.removeById(aclRolePermissionAddReq.getRoleId());

        List<AclRolePermission> aclRolePermissionList = new ArrayList<>();
        aclRolePermissionAddReq.getPermissionIdList().forEach(i -> {
            AclRolePermission aclRolePermission = new AclRolePermission();
            aclRolePermission.setRoleId(aclRolePermissionAddReq.getRoleId());
            aclRolePermission.setPermissionId(i);
            aclRolePermissionList.add(aclRolePermission);
        });
        super.saveBatch(aclRolePermissionList);
    }

    @Override
    public List<AclPermissionRes> selectRolePermissionRelationShip(String roleId) {
        List<AclPermission> allPermissionList = aclPermissionMapper.selectList(Wrappers.lambdaQuery());

        //根据角色id获取角色权限
        List<AclRolePermission> rolePermissionList = super.list(Wrappers.lambdaQuery(AclRolePermission.class)
                .eq(AclRolePermission::getRoleId, roleId));

        // 属性转移 标记角色的权限
        List<AclPermissionRes> aclPermissionResList = new ArrayList<>();
        allPermissionList.forEach(i -> {
            AclPermissionRes aclPermissionRes = new AclPermissionRes();
            BeanUtils.copyProperties(i, aclPermissionRes);
            rolePermissionList.forEach(r -> {
                if (r.getPermissionId().equals(i.getId())) {
                    aclPermissionRes.setSelected(true);
                }
            });
            aclPermissionResList.add(aclPermissionRes);
        });

        return PermissionUtils.buildPermission(aclPermissionResList);

    }
}
