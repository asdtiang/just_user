package org.asdtiang.ju;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ServletComponentScan
@EnableCaching
@EnableJpaRepositories(basePackages = "org.asdtiang.ju.repository")
@EntityScan(basePackages = "org.asdtiang.ju.domain")
public class JustUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(JustUserApplication.class, args);
	}
}
