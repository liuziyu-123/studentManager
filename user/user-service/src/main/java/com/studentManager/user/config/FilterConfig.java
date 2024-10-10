package com.studentManager.user.config;

import com.studentManager.user.filter.GlobleFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public GlobleFilter getGlobleFilter() {
        return new GlobleFilter();
    }


    @Bean
    public FilterRegistrationBean GlobelFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(getGlobleFilter());
        registrationBean.addUrlPatterns("/*");

        registrationBean.setName("globle");
        registrationBean.setOrder(2);
        return registrationBean;
    }


}