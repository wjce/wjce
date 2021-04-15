package com.wjc.gateway.security.model;

import com.wjc.gateway.entity.AdminUser;
import com.wjc.gateway.entity.AdminUserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author: wjc
 * @createDate: 2020/9/8 15:02
 * @description:
 */
public class SecurityUser implements UserDetails {

    private transient AdminUser currentUserInfo;

    private transient List<AdminUserRole> roleList;

    private transient String roldId;

    private Long userId;

    public SecurityUser() { }

    public SecurityUser(AdminUser user) {
        if (user != null) {
            this.currentUserInfo = user;
        }
    }

    public SecurityUser(AdminUser user, List<AdminUserRole> roleList) {
        if (user != null) {
            this.currentUserInfo = user;
            this.roleList = roleList;
            if (!CollectionUtils.isEmpty(roleList)){
                StringJoiner roleCodes = new StringJoiner(",", "[", "]");
                roleList.forEach(e -> roleCodes.add(e.getRoleId().toString()));
                this.roldId = roleCodes.toString();
            }
        }
    }

    public Long getUserId() {
        return currentUserInfo.getUserId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (!CollectionUtils.isEmpty(this.roleList)) {
            for (AdminUserRole role : this.roleList) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleId().toString());
                authorities.add(authority);
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return currentUserInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return currentUserInfo.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
