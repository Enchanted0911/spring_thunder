package icu.junyao.acl.service;

import icu.junyao.acl.res.AclUserInfoRes;

/**
 * @author johnson
 * @since 2021-10-04
 */
public interface AclService {

    /**
     * 获取当前登录用户信息
     * @return {@link AclUserInfoRes}
     */
    AclUserInfoRes gainUserInfo();
}
