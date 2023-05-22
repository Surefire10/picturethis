package com.picturethis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ComponentScan
@SpringBootApplication(scanBasePackages = "com.picturethis")
public class PictureThisApplication {

	public static void main(String[] args) {

		SpringApplication.run(PictureThisApplication.class, args);

	}






}