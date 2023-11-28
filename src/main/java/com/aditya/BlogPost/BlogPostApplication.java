package com.aditya.BlogPost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication

public class BlogPostApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogPostApplication.class, args);
	}

}
