package icu.junyao.acl.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.acl.entity.AclPermission;
import icu.junyao.acl.entity.AclRolePermission;
import icu.junyao.acl.mapper.AclPermissionMapper;
import icu.junyao.acl.req.AclPermissionReq;
import icu.junyao.acl.req.AclPermissionEditReq;
import icu.junyao.acl.res.AclPermissionRes;
import icu.junyao.acl.service.AclPermissionService;
import icu.junyao.acl.service.AclRolePermissionService;
import icu.junyao.acl.service.AclUserRoleService;
import icu.junyao.acl.utils.PermissionUtils;
import icu.junyao.common.enums.BusinessResponseEnum;
import icu.junyao.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author johnson
 * @since 2021-10-04
 */
@Service
@RequiredArgsConstructor
public class AclPermissionServiceImpl extends ServiceImpl<AclPermissionMapper, AclPermission> implements AclPermissionService {

    private final AclUserRoleService aclUserRoleService;
    private final AclRolePermissionService aclRolePermissionService;

    @Override
    public List<String> gainPermissionValueListByAclUser(String aclUserId) {
        // 获取用户的权限id
        List<String> permissionIdList = gainPermissionIdList(aclUserId);

        // 没有权限则返回空集合
        if (CollUtil.isEmpty(permissionIdList)) {
            return new ArrayList<>();
        }

        // 权限id集合转换成权限值集合
        LambdaQueryWrapper<AclPermission> aclPermissionLambdaQueryWrapper = Wrappers.lambdaQuery();
        aclPermissionLambdaQueryWrapper.in(AclPermission::getId, permissionIdList);
        return super.list(aclPermissionLambdaQueryWrapper)
                .stream().map(AclPermission::getPermissionValue)
                .filter(StrUtil::isNotEmpty).collect(Collectors.toList());

    }

    @Override
    public List<AclPermissionRes> gainTreePermissionList() {
        List<String> permissionIdList = gainPermissionIdList(SecurityUtils.getUserId());

        // 权限id集合转换成权限集合
        LambdaQueryWrapper<AclPermission> aclPermissionLambdaQueryWrapper = Wrappers.lambdaQuery();
        aclPermissionLambdaQueryWrapper.in(AclPermission::getId, permissionIdList);
        List<AclPermission> aclPermissionList = super.list(aclPermissionLambdaQueryWrapper);
        List<AclPermissionRes> aclPermissionResList = BeanUtil.copyToList(aclPermissionList, AclPermissionRes.class, CopyOptions.create());

        return PermissionUtils.buildPermission(aclPermissionResList);
    }

    @Override
    public void deleteMenu(String id) {
        List<String> idList = new ArrayList<>();

        // 子菜单加入删除列表
        selectPermissionChildById(id, idList);

        // 加入自身
        idList.add(id);

        super.removeByIds(idList);
    }

    @Override
    public void saveMenu(AclPermissionReq aclPermissionAddReq) {
        // 去重, 权限名不能相同
        LambdaQueryWrapper<AclPermission> aclPermissionLambdaQueryWrapper = Wrappers.lambdaQuery();
        aclPermissionLambdaQueryWrapper.eq(AclPermission::getName, aclPermissionAddReq.getName());
        AclPermission one = super.getOne(aclPermissionLambdaQueryWrapper);
        if (one != null) {
            throw BusinessResponseEnum.PERMISSION_NAME_ALREADY_EXISTS.newException();
        }

        AclPermission aclPermission = new AclPermission();
        BeanUtils.copyProperties(aclPermissionAddReq, aclPermission);
        super.save(aclPermission);
    }

    @Override
    public void updateMenu(AclPermissionEditReq aclPermissionUpdateReq) {
        // 去重, 权限名不能相同, 排除自身
        LambdaQueryWrapper<AclPermission> aclPermissionLambdaQueryWrapper = Wrappers.lambdaQuery();
        aclPermissionLambdaQueryWrapper
                .eq(AclPermission::getName, aclPermissionUpdateReq.getName())
                .ne(AclPermission::getId, aclPermissionUpdateReq.getId());
        AclPermission one = super.getOne(aclPermissionLambdaQueryWrapper);
        if (one != null) {
            throw BusinessResponseEnum.PERMISSION_NAME_ALREADY_EXISTS.newException();
        }

        AclPermission aclPermission = new AclPermission();
        BeanUtils.copyProperties(aclPermissionUpdateReq, aclPermission);
        super.updateById(aclPermission);
    }

    @Override
    public List<JSONObject> gainMenuList() {
        List<AclPermissionRes> aclPermissionList = this.gainTreePermissionList();

        return PermissionUtils.buildMenu(aclPermissionList);
    }

    /**
     * 把当前id的所有子权限id加入到id集合中
     *
     * @param id 当前权限id
     * @param idList 包含当前权限id及子权限id
     */
    private void selectPermissionChildById(String id, List<String> idList) {
        //查询菜单里面子菜单id
        LambdaQueryWrapper<AclPermission> aclPermissionLambdaQueryWrapper = Wrappers.lambdaQuery();
        aclPermissionLambdaQueryWrapper.eq(AclPermission::getPid, id).select(AclPermission::getId);
        List<AclPermission> childIdList = super.list(aclPermissionLambdaQueryWrapper);

        //把childIdList里面菜单id值获取出来，封装idList里面，做递归查询
        childIdList.forEach(item -> {
            //封装idList里面
            idList.add(item.getId());
            //递归查询
            selectPermissionChildById(item.getId(), idList);
        });
    }

    /**
     * 获取用户的权限id集合
     *
     * @param userId 用户id
     * @return 权限id集合
     */
    public List<String> gainPermissionIdList(String userId) {
        // 获取角色id列表
        List<String> roleIdList = aclUserRoleService.gainCurrentUserRoleIdList(userId);
        if (CollUtil.isEmpty(roleIdList)) {
            return new ArrayList<>();
        }

        // 通过角色id集合获取用户的权限id集合
        LambdaQueryWrapper<AclRolePermission> aclRolePermissionLambdaQueryWrapper = Wrappers.lambdaQuery();
        aclRolePermissionLambdaQueryWrapper.in(AclRolePermission::getRoleId, roleIdList);

        return aclRolePermissionService.list(aclRolePermissionLambdaQueryWrapper)
                .stream().map(AclRolePermission::getPermissionId).distinct().collect(Collectors.toList());
    }
}
