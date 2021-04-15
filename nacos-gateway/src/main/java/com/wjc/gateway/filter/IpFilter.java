package com.wjc.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.wjc.gateway.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @author wjc
 * @description
 * @date 2020/10/16
 */
@Slf4j
@Configuration
public class IpFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        try {
            ServerHttpRequest serverHttpRequest = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            String url = serverHttpRequest.getURI().toString();
            String uri = serverHttpRequest.getPath().toString();
            String ip = IpUtils.getIp(exchange);
            String method = serverHttpRequest.getMethodValue();
            String bodyStr = serverHttpRequest.getQueryParams().toString();

            if(ip.equals("123.456.789.0")){
                return responseError(ip, 401, "ip被限制", response, HttpStatus.UNAUTHORIZED);
            }

            if (HttpMethod.POST.name().equalsIgnoreCase(method)) {
                return DataBufferUtils.join(exchange.getRequest().getBody()).map(dateBuffer -> {
                    if(dateBuffer == null){
                        String param = serverHttpRequest.getQueryParams().toString();
                        dateBuffer.write(param.getBytes(StandardCharsets.UTF_8));
                    }
                    return dateBuffer;
                }).flatMap(dataBuffer -> {
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    try {
                        String bodyString = new String(bytes, "utf-8");
                        exchange.getAttributes().put("POST_BODY", bodyString);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    DataBufferUtils.release(dataBuffer);
                    Flux<DataBuffer> cachedFlux = Flux.defer(() -> {
                        DataBuffer buffer = exchange.getResponse().bufferFactory()
                                .wrap(bytes);
                        return Mono.just(buffer);
                    });

                    ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(
                            exchange.getRequest()) {
                        @Override
                        public Flux<DataBuffer> getBody() {
                            return cachedFlux;
                        }
                    };
                    return chain.filter(exchange.mutate().request(mutatedRequest)
                            .build());
                });
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -9;
    }


    private Mono<Void> responseError(String errorLog, int code, String msg, ServerHttpResponse response, HttpStatus status){
        log.error(errorLog);
        JSONObject message = new JSONObject();
        message.put("code", code);
        message.put("message", msg);
        byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.setStatusCode(status);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }



}