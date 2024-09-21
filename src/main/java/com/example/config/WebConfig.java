package com.example.config;

import com.example.interceptor.SsoInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Autowired
    private SsoInterceptor ssoInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ssoInterceptor)
                .addPathPatterns("/users/**", "/user/**")
                .excludePathPatterns("/login", "/user/new", "/user/save");
    }

    // 필요한 경우에만 다른 설정을 추가할 수 있습니다.
}
