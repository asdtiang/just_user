package org.asdtiang.ju;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);
	

	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().and()
				.formLogin()
				.defaultSuccessUrl("/").loginPage("/login").permitAll().and().logout().permitAll().and()
				.authorizeRequests().antMatchers("/**/**").permitAll()
				.and()	
				.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true)
				.and().and()
				.authorizeRequests().and().csrf()
		        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}
	@Bean
	public HttpSessionIdResolver httpSessionIdResolver() {
		return HeaderHttpSessionIdResolver.xAuthToken(); 
	}
}
