package com.bob.ktssts;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication(scanBasePackages = "com")
@MapperScan("com.bob.ktssts.dao")
public class KtsstsApplication {

	static Logger logger = Logger.getLogger("com.bob.ktssts");

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(KtsstsApplication.class, args);
//		logger.log(Level.parse("INFO"),run.getBean("apiUserDao").toString());
	}
}
