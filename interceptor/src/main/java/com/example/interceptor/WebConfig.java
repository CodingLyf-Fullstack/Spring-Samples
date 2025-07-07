package com.example.interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Configuration class to register interceptors
@Configuration
public class WebConfig implements WebMvcConfigurer {

 // Injecting the custom interceptor
 @Autowired
 private BetaAccessInterceptor betaAccessInterceptor;

 // This method is used to register interceptors and specify which paths they apply to
 @Override
 public void addInterceptors(InterceptorRegistry registry) {
     registry.addInterceptor(betaAccessInterceptor)
             .addPathPatterns("/search"); // Apply this interceptor only to /search API
 }
}

//This config tells Spring to run BetaAccessInterceptor only when /search is called.

//Other APIs won’t be affected.

