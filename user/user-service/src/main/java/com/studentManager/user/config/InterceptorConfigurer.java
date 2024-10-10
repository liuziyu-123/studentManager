package com.studentManager.user.config;


import com.studentManager.user.intercepter.GlobelInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfigurer implements WebMvcConfigurer {

    @Bean
    public GlobelInterceptor initAuthInterceptor() {
        return new GlobelInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(initAuthInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/user/login","/user/loginOut","/user/verification");
        //.excludePathPatterns("/user/**");
    }
}
