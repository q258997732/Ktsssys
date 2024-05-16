package com.bob.ktssts;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestModule {
	private static final Logger logger = LogManager.getLogger(TestModule.class);
	public static void main(String[] args) {
		logger.log(Level.valueOf("INFO"),"test");
	}
}
