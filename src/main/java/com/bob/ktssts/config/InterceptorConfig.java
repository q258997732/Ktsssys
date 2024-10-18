package com.bob.ktssts.config;

import com.bob.ktssts.jwt.JWTInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnProperty(name = "usrmgt.enable", havingValue = "true")
public class InterceptorConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new JWTInterceptor())
				.addPathPatterns("/api/updateTaskExecLog")
				.addPathPatterns("/api/updateAllXciUserStatus")
				.addPathPatterns("/api/resetAutoRpaTask")
				.addPathPatterns("/api/startXciTask")
				.addPathPatterns("/api/stopXciTask");

	}
}

