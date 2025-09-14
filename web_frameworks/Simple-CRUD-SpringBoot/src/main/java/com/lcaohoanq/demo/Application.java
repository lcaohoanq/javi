package com.lcaohoanq.demo;


import io.github.lcaohoanq.annotations.BrowserLauncher;
import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

//@BrowserLauncher(url = "http://localhost:8080/swagger-ui/index.html", excludeProfiles = "test")
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
