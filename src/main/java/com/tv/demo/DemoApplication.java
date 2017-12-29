package com.tv.demo;

import com.tv.biz.AppEvent;
import com.tv.controller.hello;
import com.tv.db.ShowJdbcRepository;
import com.tv.models.Show;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = hello.class)
@ComponentScan(basePackageClasses = AppEvent.class)
@ComponentScan(basePackageClasses = ShowJdbcRepository.class)
public class DemoApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
