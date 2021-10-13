package icu.junyao.acl.service;

import icu.junyao.acl.entity.AclRole;
import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.acl.req.AclRoleEditReq;
import icu.junyao.acl.req.AclRoleReq;
import icu.junyao.acl.req.PageRoleReq;
import icu.junyao.acl.res.AclRoleDetailRes;
import icu.junyao.common.entity.PageResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author johnson
 * @since 2021-10-04
 */
public interface AclRoleService extends IService<AclRole> {

    /**
     * 通过角色id集合获取角色名称集合
     *
     * @param roleIdList 角色id集合
     * @return 角色名称集合
     */
    List<String> gainRoleNameListByRoleIdList(List<String> roleIdList);

    /**
     * 分页获取角色信息 还可以根据角色名模糊查询
     *
     * @param pageRoleReq 包含分页信息以及角色名关键字
     * @return 当前页内容以及总记录数
     */
    PageResult<AclRole> pageRole(PageRoleReq pageRoleReq);

    /**
     * 根据角色id获取角色详情
     *
     * @param id 角色id
     * @return {@link AclRoleDetailRes}
     */
    AclRoleDetailRes roleDetails(String id);

    /**
     * 新增一个角色
     *
     * @param aclRoleReq 新增的角色
     */
    void saveRole(AclRoleReq aclRoleReq);

    /**
     * 修改一个角色
     *
     * @param aclRoleEditReq 修改的角色
     */
    void updateRole(AclRoleEditReq aclRoleEditReq);
}
