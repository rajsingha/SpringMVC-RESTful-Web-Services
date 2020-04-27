package com.webservice.mobile.app;


/*
	This RestAPI is Created By Raj Singha.
	For testing and documentation run the app using docker
	and visit --> http://localhost:8080/mobile-app-ws/swagger-ui.html

*/


import com.webservice.mobile.app.security.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MobileAppWebServicesApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MobileAppWebServicesApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MobileAppWebServicesApplication.class);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SpringApplicationContext springApplicationContext(){
		return new SpringApplicationContext();
	}

	@Bean(name = "AppProperties")
	public AppProperties getAppProperties(){
		return new AppProperties();
	}

}
