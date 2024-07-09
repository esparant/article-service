package com.tak.article.domain.config;

import com.tak.article.domain.home.interceptor.LoginCheckInterceptor;
import com.tak.article.domain.home.interceptor.LoginUserCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final String[] GUEST_PATTERNS = {
            "/", "/error", "/login", "/signup", "/sign-up/check", "/**.ico"};
    private static final String[] MEMBER_PATTERNS = {
            "/", "/error", "/logout", "/article", "/post",
            "/post/**", "/**.ico"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(GUEST_PATTERNS);

        registry.addInterceptor(new LoginUserCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns(MEMBER_PATTERNS);
    }
}
