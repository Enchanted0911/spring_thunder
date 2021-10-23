package icu.junyao.back.security;

import icu.junyao.common.enums.BusinessResponseEnum;
import icu.junyao.security.contant.CacheConstants;
import icu.junyao.security.entity.JwtUser;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 自定义userDetailsService - 认证用户详情
 * </p>
 *
 * @author johnson
 * @date 2021-10-02
 */
@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CacheManager cacheManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 先从缓存中取用户
        Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
        if (cache != null && cache.get(username) != null) {
            return cache.get(username, JwtUser.class);
        }
        throw BusinessResponseEnum.LOGIN_EXPIRED.newException();
    }
}
