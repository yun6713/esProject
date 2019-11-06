package com.bonc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
@RestController
public class EsProjectApplication extends WebMvcConfigurationSupport{

	public static void main(String[] args) {
		System.setProperty("es.set.netty.runtime.available.processors", "false");
		SpringApplication.run(EsProjectApplication.class, args);
	}
	@Override
	protected void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/test");
	}
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**")
			.addResourceLocations("classpath:static/");
	}
	@RequestMapping("/test")
	public String test() {
		return "test ok";
	}
}
