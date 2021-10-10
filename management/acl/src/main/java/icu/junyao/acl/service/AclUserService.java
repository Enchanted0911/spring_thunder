package icu.junyao.acl.service;

import icu.junyao.acl.entity.AclUser;
import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.acl.req.AclUserReq;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author johnson
 * @since 2021-10-04
 */
public interface AclUserService extends IService<AclUser> {

    /**
     * 新增管理用户
     *
     * @param aclUserReq {@link AclUserReq}
     */
    void saveAclUser(AclUserReq aclUserReq);
}
