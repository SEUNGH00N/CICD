package com.fullstack.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 글로벌 CORS 설정을 정의하는 클래스입니다.
 * <p>
 * 이 클래스는 모든 HTTP 요청에 대해 CORS 정책을 정의합니다.
 * </p>
 * 
 * @version 1.0_0725
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * CORS 정책을 설정하는 메서드입니다.
     * 
     * @param registry CORS 정책을 설정할 수 있는 CORS 레지스트리
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**") // 모든 경로에 대해 CORS를 설정합니다.
                .allowedOrigins("http://localhost:3000") // 허용할 오리진을 지정합니다.
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드를 지정합니다.
                .allowedHeaders("*"); // 모든 헤더를 허용합니다.
    }
}
