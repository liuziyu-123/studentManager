package com.studentManager.user.filter;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter("/**")
@Slf4j
public class GlobleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("过滤器执行了........");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("过滤器执行了");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //Filter 过滤器跨域处理
  //      String map=propotiesConfiger.getLogin();
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "token,Origin, X-Requested-With, Content-Type,Accept,Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        //获取Header中的token
//        String uri = request.getRequestURI();
//        for (String item : propotiesConfiger.getList().values()) {
//            if (uri.equals(item) || uri.endsWith(".jpg")) {
//                filterChain.doFilter(servletRequest, servletResponse);
//                return;
//            }
//        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        System.out.println("过滤器结束了");
    }


//    public List<String> map(ApplicationContext ctx) {
//        List<String> mapList = new ArrayList<>();
//        String login = ctx.getEnvironment().getProperty("white.list.login");
//        String upload = ctx.getEnvironment().getProperty("white.list.upload");
//        String fileUUID = ctx.getEnvironment().getProperty("white.list.fileUUID");
//        String outEx = ctx.getEnvironment().getProperty("white.list.outEx");
//        mapList.add(login);
//        mapList.add(upload);
//        mapList.add(fileUUID);
//        mapList.add(outEx);
//        return mapList;
//    }
}
