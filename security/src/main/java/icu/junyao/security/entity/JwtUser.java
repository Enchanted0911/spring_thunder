package icu.junyao.security.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author johnson
 * @date 2021-10-02
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class JwtUser implements UserDetails {
    private String id;

    private String username;

    private String nickName;

    private String avatar;

    private String password;

    private Collection<GrantedAuthority> authorities;


    /**
     * 账号是否过期
     */
    private boolean isAccountNonExpired = true;

    /**
     * 账号是否锁定
     */
    private boolean isAccountNonLocked = true;

    /**
     * 证书是否过期
     */
    private boolean isCredentialsNonExpired = true;

    /**
     * 账号是否有效
     */
    private boolean isEnabled = true;

    /**
     * 获得用户权限
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }

    /**
     * 判断账号是否过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    /**
     * 判断账号是否锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    /**
     * 判断证书是否过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    /**
     * 判断账号是否有效
     */
    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
