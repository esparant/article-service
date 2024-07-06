package com.tak.article.domain.config;

import com.tak.article.domain.home.interceptor.LoginCheckInterceptor;
import com.tak.article.domain.home.interceptor.LoginUserCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/error", "/login");

        registry.addInterceptor(new LoginUserCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/error", "/logout", "/article");
    }
}
