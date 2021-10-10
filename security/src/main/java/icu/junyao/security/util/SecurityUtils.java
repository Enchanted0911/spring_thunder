/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package icu.junyao.security.util;

import icu.junyao.security.entity.JwtUser;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * @author johnson
 * @date 2021-10-02
 */
@UtilityClass
public class SecurityUtils {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户
     *
     * @param authentication 身份验证
     * @return {@link JwtUser}
     */
    public JwtUser getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof JwtUser) {
            return (JwtUser) principal;
        }
        return null;
    }

    /**
     * 获取用户
     *
     * @return {@link JwtUser}
     */
    public JwtUser getUser() {
        Authentication authentication = getAuthentication();
        return getUser(authentication);
    }

    /**
     * 获取用户id
     *
     * @return {@link String}
     */
    public String getUserId() {
        return getUser().getId();
    }

    /**
     * 获取用户名
     *
     * @return {@link String}
     */
    public String getUserName() {
        return getUser().getUsername();
    }
}
