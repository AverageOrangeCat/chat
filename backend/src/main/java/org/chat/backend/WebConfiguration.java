package org.chat.backend;

import org.chat.backend.interceptors.VerificationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private VerificationInterceptor verificationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(verificationInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/credentials/create")
                .excludePathPatterns("/session/login");
    }

}
