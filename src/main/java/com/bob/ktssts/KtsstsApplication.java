package com.bob.ktssts;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com")
@MapperScan("com.bob.ktssts.dao")
public class KtsstsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(KtsstsApplication.class, args);
	}
}
