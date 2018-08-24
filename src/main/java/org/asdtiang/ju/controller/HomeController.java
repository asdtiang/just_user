package org.asdtiang.ju.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class HomeController {

	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	
	
	
	
	@RequestMapping("/api/v1/apiDoc")
	public String apiDoc() {
		return "swagger";
	}
	
	
	
	@RequestMapping("/demo")
	public String demo() {
		return "demo";
	}
	
}
