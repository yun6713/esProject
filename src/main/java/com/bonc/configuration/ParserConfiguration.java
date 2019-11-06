package com.bonc.configuration;

import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.txt.TXTParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParserConfiguration {
	@Bean
	public AutoDetectParser autoDetectParser() {
		return new AutoDetectParser();
	}
	@Bean
	public TXTParser txtParser() {
		return new TXTParser();
	}
}
