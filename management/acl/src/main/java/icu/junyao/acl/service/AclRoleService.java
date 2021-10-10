package icu.junyao.acl.service;

import icu.junyao.acl.entity.AclRole;
import com.baomidou.mybatisplus.extension.service.IService;
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
     * 
     * @param pageRoleReq
     * @return
     */
    PageResult<AclRole> pageRole(PageRoleReq pageRoleReq);

    AclRoleDetailRes roleDetails(String id);
}
