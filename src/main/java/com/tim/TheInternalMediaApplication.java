package com.tim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 * @appName the_internal_media
 *
 */
@SpringBootApplication
public class TheInternalMediaApplication {

	public static void main(String[] args) {

		SpringApplication.run(TheInternalMediaApplication.class, args);
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOriginPatterns("/**")
                .allowedMethods("*");
            }
        };
    }
}
