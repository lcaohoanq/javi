package com.lcaohoanq.demo;


import java.awt.Desktop;
import java.net.URI;
import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		openHomePage("http://localhost:8080/swagger-ui/index.html");
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}

	private static void openHomePage(String url) {
		try {
			if (Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				if (desktop.isSupported(Desktop.Action.BROWSE)) {
					desktop.browse(URI.create(url));
				}
			} else {
				String os = System.getProperty("os.name").toLowerCase();
				if (os.contains("win")) {
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
				} else if (os.contains("mac")) {
					Runtime.getRuntime().exec("open " + url);
				} else if (os.contains("nix") || os.contains("nux")) {
					Runtime.getRuntime().exec("xdg-open " + url);
				} else {
					System.out.println("Unsupported operating system: " + os);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
