package com.studentManager.gateway.configer;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 1.@Description:
 * 2.@author:liuziyu
 * 3.@Time:2022/11/27
 **/
 @Component
 public class CorsFilter implements Filter {

 @Override
 public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
 HttpServletResponse response = (HttpServletResponse) res;
 response.setHeader("Access-Control-Allow-Origin", "*");
 response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
 response.setHeader("Access-Control-Max-Age", "3600");
 response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
 response.setHeader("Access-Control-Expose-Headers", "Location");
 chain.doFilter(req, res);
 }

 @Override
 public void init(FilterConfig filterConfig) {}

 @Override
 public void destroy() {}

 }
