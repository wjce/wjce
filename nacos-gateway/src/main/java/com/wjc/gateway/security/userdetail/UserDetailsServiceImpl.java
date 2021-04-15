package com.wjc.gateway.security.userdetail;

import com.wjc.gateway.entity.AdminUser;
import com.wjc.gateway.entity.AdminUserRole;
import com.wjc.gateway.security.model.SecurityUser;
import com.wjc.gateway.service.IAdminUserRoleService;
import com.wjc.gateway.service.IAdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: wjc
 * @createDate: 2020/9/9 13:58
 * @description:
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IAdminUserService adminUserService;
    @Autowired
    private IAdminUserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AdminUser user = adminUserService.queryByUserName(s);

        if(null == user){
            throw new UsernameNotFoundException("用户名不存在！");
        }

        List<AdminUserRole> userRoleList = userRoleService.queryListByUserId(user.getUserId());
        return new SecurityUser(user, userRoleList);
    }
}
