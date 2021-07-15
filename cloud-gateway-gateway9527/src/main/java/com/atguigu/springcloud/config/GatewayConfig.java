package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    // 路由配置类
    @Bean
    public RouteLocator getCustomRoutLocator(RouteLocatorBuilder builder) {
        return builder.routes().route("net", r -> r.path("/internet").uri("https://news.baidu.com"))
                .route("tech", r -> r.path("/tech").uri("https://news.baidu.com"))
                .build();
    }

    // 自定义全局过滤器
    @Bean
    public GlobalFilter getCustomGlobalFilter() {
        return new CustomGlobalFilter();
    }
}
