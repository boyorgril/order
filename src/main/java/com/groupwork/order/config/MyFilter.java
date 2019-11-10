package com.groupwork.order.config;



import org.springframework.core.annotation.Order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


//注册器名称为MyFilter,拦截的url为所有
@WebFilter(filterName="MyFilter",urlPatterns={"/*"})
@Order(1)
public class MyFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(MyFilter.class);
    //需要忽视的url,不进行过滤
    private static final String[] ignoreUrl = {".js", ".css", ".jpg", ".jpeg", ".ico", "loginCheck", "404", "logout", "register", "checkAccount", "registerAccount", "druid"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        Long userId = (Long) request.getSession().getAttribute("userId");
        boolean flag = false;
        for (String pattern : ignoreUrl){
            if(request.getRequestURI().contains(pattern)){
                flag =true;
                break;
            }
        }
        if(flag){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if(StringUtils.isEmpty(userId)){
            request.getRequestDispatcher("/login").forward(servletRequest,servletResponse);
            return;
        }
    }

    @Override
    public void destroy() {

    }
}
