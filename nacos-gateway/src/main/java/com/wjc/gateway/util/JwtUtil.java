package com.wjc.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: wjc
 * @createDate: 2020/9/8 11:24
 * @description:
 */
public class JwtUtil {

    public final static String SECRET_KEY = "abcdef123456kcallme!@";
    public final static long tokenExpireTime = 1000 * 60 * 60;
    public final static long refreshTokenExpireTime = 1000 * 60 * 60 * 24;

    public static String createToken(String userName, Long userId) {
        Map<String, Object> map = new HashMap<String, Object>(){{put("userId", userId);}};
        String token = Jwts.builder()
                .setClaims(map)
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpireTime))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();

        return token;
    }

    public static String createRefreshToekn(String userName){
        String refreshToken = Jwts.builder()
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpireTime))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();

        return refreshToken;
    }

    public static boolean checkJWT(String token) {
        Jws<Claims> jws = parseClaimsJws(token);
        if(null != jws){
            return jws.getBody() == null;
        }
        return false;
    }

    public static String getUserName(String token){
        Jws<Claims> jws = parseClaimsJws(token);
        if(null != jws){
            return jws.getBody().getSubject();
        }
        return "";
    }

    public static boolean isExpiration(String token){
        Jws<Claims> jws = parseClaimsJws(token);
        if(null != jws){
            return jws.getBody().getExpiration().before(new Date());
        }
        return false;
    }

    public static Jws<Claims> parseClaimsJws(String token){
        try {
            Jws<Claims> jws = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return jws;
        }catch (Exception e){
            return null;
        }
    }

    public static String getToken(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getHeader("TOKEN");
    }

    public static String getParam(String paramName){
        String token = getToken();
        Jws<Claims> claims = parseClaimsJws(token);
        return claims == null ? null : claims.getBody().get(paramName).toString();
    }

//    public static void main(String[] args) {
//        String token = createToken("wjceee");
//        System.out.println(token);
//        boolean claims = checkJWT(token);
//        System.out.println(claims);
//        String userName = getUsername(token);
//        System.out.println(userName);
//        boolean expire = isExpiration(token);
//        System.out.println(expire);
//    }
}
