package com.wjc.gateway.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

import java.util.Optional;

/**
 * @author wjc
 * @description
 * @date 2020/10/30
 */
public class IpUtils {

    public static String getIp(ServerWebExchange serverWebExchange){
        ServerHttpRequest request = serverWebExchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        String ip = Optional.ofNullable(headers.get("X-Real-IP")).map(list -> list.get(0)).orElse(null);
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = Optional.ofNullable(headers.get("x-forwarded-for")).map(list -> list.get(0)).orElse(null);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = Optional.ofNullable(headers.get("Proxy-Client-IP")).map(list -> list.get(0)).orElse(null);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = Optional.ofNullable(headers.get("WL-Proxy-Client-IP")).map(list -> list.get(0)).orElse(null);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = Optional.ofNullable(headers.get("HTTP_CLIENT_IP")).map(list -> list.get(0)).orElse(null);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = Optional.ofNullable(headers.get("HTTP_X_FORWARDED_FOR")).map(list -> list.get(0)).orElse(null);
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddress().getAddress().getHostAddress();
            if(ip.equals("0:0:0:0:0:0:0:1")){
                ip= "127.0.0.1";
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ip!=null && ip.length()>15){ //"***.***.***.***".length() = 15
            if(ip.indexOf(",")>0){
                ip = ip.substring(0,ip.indexOf(","));
            }
        }
        return ip;
    }

}
