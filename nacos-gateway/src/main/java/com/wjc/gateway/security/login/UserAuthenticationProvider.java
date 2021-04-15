package com.wjc.gateway.security.login;//package com.kacllme.user.security.login;
//
//import com.kacllme.user.security.userdetail.UserDetailsServiceImpl;
//import com.kacllme.user.security.model.SecurityUser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Component;
//
///**
// * @author: wjc
// * @createDate: 2020/9/9 14:02
// * @description:
// */
//@Component
//public class UserAuthenticationProvider implements AuthenticationProvider {
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String userName = authentication.getPrincipal().toString();
//        String password = authentication.getCredentials().toString();
//        SecurityUser userInfo = (SecurityUser) userDetailsService.loadUserByUsername(userName);
//        //判断密码
//        boolean valid = false;
//        if(!valid){
//            throw new BadCredentialsException("密码错误");
//        }
//        return null;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return true;
//    }
//}
