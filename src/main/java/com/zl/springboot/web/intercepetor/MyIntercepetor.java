package com.zl.springboot.web.intercepetor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyIntercepetor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("MyIntercepetor.preHandle");
        return true;
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        logger.info("MyIntercepetor.postHandle");
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        logger.info("MyIntercepetor.afterCompletion");
    }
}
