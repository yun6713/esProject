package com.bonc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bonc.service.DocService;

@RestController
@RequestMapping("/doc")
public class DocController {
	@Autowired
	DocService ds;
	@RequestMapping("/add")
	public String addDoc(@RequestParam String path) {
		ds.add(path);
		return "ok";
	}
}
