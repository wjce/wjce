package com.wjc.gateway.filter;

import com.wjc.gateway.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.cloud.gateway.support.DefaultServerRequest;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

/**
 * @author wjc
 * @description
 * @date 2020/11/9
 */
@Component
@Slf4j
public class LogFilter implements GlobalFilter, GatewayFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String method = request.getMethodValue();
        String uri = request.getPath().toString();
        String getParam = request.getQueryParams().toString();


        if (HttpMethod.GET.name().equalsIgnoreCase(method)) {
            String bodyStr = request.getQueryParams().toString();
            saveRequestOperLog(exchange, bodyStr);
        }
            ServerRequest serverRequest = new DefaultServerRequest(exchange);

            StringBuilder sb = new StringBuilder();
            Mono<String> modifiedBody = serverRequest.bodyToMono(String.class).defaultIfEmpty(getParam)
                    .flatMap(body -> {
                        sb.append(body);
                        return Mono.just(body);
                    }).doFinally(t -> {
                        saveRequestOperLog(exchange, sb.toString());
                    });
            BodyInserter bodyInserter = BodyInserters.fromPublisher(modifiedBody, String.class);
            HttpHeaders headers = new HttpHeaders();
            headers.putAll(exchange.getRequest().getHeaders());
            headers.remove(HttpHeaders.CONTENT_LENGTH);

            CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);
            return bodyInserter.insert(outputMessage, new BodyInserterContext())
                    .then(Mono.defer(() -> {
                        ServerHttpRequestDecorator decorator = new ServerHttpRequestDecorator(
                                exchange.getRequest()) {
                            @Override
                            public HttpHeaders getHeaders() {
                                long contentLength = headers.getContentLength();
                                HttpHeaders httpHeaders = new HttpHeaders();
                                httpHeaders.putAll(super.getHeaders());
                                httpHeaders.put("param", new ArrayList<String>(){{add("add param");}});
                                if (contentLength > 0) {
                                    httpHeaders.setContentLength(contentLength);
                                } else {
                                    httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                                }
                                return httpHeaders;
                            }

                            @Override
                            public Flux<DataBuffer> getBody() {
                                return outputMessage.getBody();
                            }
                        };

                        return chain.filter(exchange.mutate().request(decorator).build());
                    }));
    }

    /**
     * 保存请求日志
     *
     * @param exchange
     * @param requestParameters
     * @return
     */
    private String saveRequestOperLog(ServerWebExchange exchange, String requestParameters) {
        if(StringUtils.isNotBlank(requestParameters))
            requestParameters = requestParameters.replaceAll("\r\n","");
        ServerHttpRequest request = exchange.getRequest();

        String ip = IpUtils.getIp(exchange);
        String query = request.getURI().getRawQuery();
        String uri = request.getPath().toString();
        String method = request.getMethodValue();

        log.info(String.format("\nURI: %s \nMETHOD: %s \nQUERY: %s \nBODY: %s \nIP: %s", uri, method, query, requestParameters, ip));
        return requestParameters;
    }

    @Override
    public int getOrder() {
        return -10;
    }



}
