package com.bonc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/doc")
public class DocController {
	@RequestMapping("/add")
	public String addDoc(String path) {
		
		return "ok";
	}
}
