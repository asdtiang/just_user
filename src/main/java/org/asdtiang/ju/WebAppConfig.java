package org.asdtiang.ju;

import javax.annotation.PostConstruct;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebMvc
@ComponentScan(basePackages = {"org.asdtiang.ju"})
@Configuration
@EnableSwagger2
public class WebAppConfig implements WebMvcConfigurer {
	private static final Logger log = LoggerFactory.getLogger(WebAppConfig.class);
	
	@PostConstruct
	public void init() {
		 //h2Server();
	}


	/*
	@Bean
	Server h2Server() {	
			Server server = new Server();
			try {
				server.runTool("-tcp");
				server.runTool("-tcpAllowOthers");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return server;
	}*/

}
