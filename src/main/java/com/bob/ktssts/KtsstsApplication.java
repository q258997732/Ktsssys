package com.bob.ktssts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.logging.Logger;

@SpringBootApplication(scanBasePackages = "com.bob.ktssts")
@EnableScheduling
public class KtsstsApplication {

	static Logger LOGGER = Logger.getLogger("com.bob.ktssts");

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(KtsstsApplication.class, args);
	}
}
