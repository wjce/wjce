package com.wjc.gateway.security.interceptor;

import com.oracrle.common.constants.SysConstants;
import com.wjc.gateway.config.PermissionsPerproties;
import com.wjc.gateway.entity.AdminMenu;
import com.wjc.gateway.entity.AdminRoleMenu;
import com.wjc.gateway.service.IAdminMenuService;
import com.wjc.gateway.service.IAdminRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @author: wjc
 * @createDate: 2020/9/8 20:55
 * @description:
 */
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private PermissionsPerproties permissionsPerproties;
    @Autowired
    private IAdminMenuService adminMenuService;
    @Autowired
    private IAdminRoleMenuService adminRoleMenuService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        if(requestUrl.contains("?")){
            requestUrl = requestUrl.substring(0, requestUrl.indexOf("?"));
        }

        for (String ignoreUrl : permissionsPerproties.getIgnoreUrls()) {
            if(!ignoreUrl.equals("**") && !ignoreUrl.equals("/**")){
                if(ignoreUrl.endsWith("/**") && ignoreUrl.indexOf("*") == ignoreUrl.length() - 2){
                    ignoreUrl = ignoreUrl.substring(0, ignoreUrl.length() - 3);
                    if(requestUrl.contains(ignoreUrl)){
                        return null;
                    }
                } else if(ignoreUrl.equals(requestUrl)){
                    return null;
                }
            }
        }

        AdminMenu menu = adminMenuService.queryByUrl(requestUrl);
        if(null != menu){
            List<AdminRoleMenu> roleMenuList = adminRoleMenuService.queryListByMenuId(menu.getMenuId());
            String[] roleArr = roleMenuList.stream().map(AdminRoleMenu::getRoleId).toArray(String[]::new);
            return SecurityConfig.createList(roleArr);
        }
        return SecurityConfig.createList(SysConstants.LOGIN_ROLE);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
