package com.mysite.reactfileupload;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {    // 교차출처 정책 처리시 설정
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**").allowedOrigins("*");
    }
}
