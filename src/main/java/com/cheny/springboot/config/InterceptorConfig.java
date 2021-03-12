package com.cheny.springboot.config;

import com.cheny.springboot.Interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] addPath={"/**"};
        String[] excludePath={"/userlogin","/userlogin*","/images/**","/jquery/**",/*"*.js","*.html"*/};
        registry.addInterceptor(new UserInterceptor()).addPathPatterns(addPath).excludePathPatterns(excludePath);
    }
}
