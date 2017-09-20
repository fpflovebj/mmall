package com.zl.springboot.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "myFilter",urlPatterns = "/*")
public class MyFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("MyFilter.init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("MyFilter.doFilter");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        logger.info("MyFilter.destroy");
    }
}
