package icu.junyao.acl.service;

import icu.junyao.acl.entity.AclUser;
import icu.junyao.acl.entity.AclUserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.acl.req.AclUserRoleReq;
import icu.junyao.acl.res.AllAclRoleInfoRes;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author johnson
 * @since 2021-10-04
 */
public interface AclUserRoleService extends IService<AclUserRole> {
    /**
     * 获取当前登录用户的角色名称集合
     *
     * @return 角色id集合
     */
    List<String> gainCurrentUserRoleNameList();

    /**
     * 通过用户获取用户的角色id集合
     *
     * @param aclUserId 用户id
     * @return 角色id集合
     */
    List<String> gainCurrentUserRoleIdList(String aclUserId);

    /**
     * 获取用户的角色信息, 获取所有的角色, 其中对该用户的角色标记已选择
     *
     * @param id 用户id
     * @return 角色信息列表
     */
    List<AllAclRoleInfoRes> gainRoleInfoByUserId(String id);

    /**
     * 给用户分配角色
     *
     * @param aclUserRoleReq {@link AclUserRoleReq}
     */
    void assignRoleToUser(AclUserRoleReq aclUserRoleReq);
}
