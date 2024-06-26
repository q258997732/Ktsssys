package com.bob.ktssts;

import com.bob.ktssts.util.RpaExecuter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class TestModule {

	private static final Logger logger = LogManager.getLogger(TestModule.class);
	public static void main(String[] args) throws JsonProcessingException {
		String nonStandardJson = "Name=FlowID, Type=4";
		String standardJson = nonStandardJson.replaceAll("(\\A|, )([^,]+)=([^,]+)", "$1\"$2\":\"$3\"");
		System.out.println(standardJson);
		JsonNode node = new ObjectMapper().readTree(standardJson);

	}
}
