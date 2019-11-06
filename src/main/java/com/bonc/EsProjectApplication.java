package com.bonc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EsProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsProjectApplication.class, args);
	}
	@RequestMapping("/test")
	public String addDoc() {
		return "test";
	}
}
