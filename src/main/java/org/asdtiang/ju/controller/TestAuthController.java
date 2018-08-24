package org.asdtiang.ju.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;
@ApiIgnore
@Controller
public class TestAuthController {
	
	@RequestMapping("/testAuth")
	public String test() {
		return "index";
	}
	
}
