package com.bob.ktssts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.logging.Logger;

@SpringBootApplication(scanBasePackages = "com.bob.ktssts")
public class KtsstsApplication {

	static Logger LOGGER = Logger.getLogger("com.bob.ktssts");

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(KtsstsApplication.class, args);

//		logger.log(Level.parse("INFO"),run.getBean("apiUserDao").toString());
	}
}
