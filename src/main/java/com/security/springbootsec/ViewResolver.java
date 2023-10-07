package com.security.springbootsec;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ViewResolver implements WebMvcConfigurer {

@Override
    public void addViewControllers(ViewControllerRegistry registry){
    registry.addViewController("/mylogin").setViewName("mylogin");
}
}
