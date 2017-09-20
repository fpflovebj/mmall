package com.zl.springboot.web.config;

import com.zl.springboot.web.intercepetor.MyIntercepetor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 注册所有的intercepetor
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{

    /**
     * 静态资源文件映射
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 将static请求映射到static目录下
         */
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:static/");
    }
    /**
     * 添加 intercepetor
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyIntercepetor()).addPathPatterns("/*");
        //配置静态 资源文件映射
        super.addInterceptors(registry);
    }
}
