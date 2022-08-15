package com.tim.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 
 * @appName the_internal_media
 *
 */
@Configuration
public class SwaggerConfig {
	
	public static final String AUTHORIZATION_HEADER = "Authorization";

	  private ApiInfo apiInfo() {
	    return new ApiInfo("My REST API",
	        "The Internal Media APIs.",
	        "1.0",
	        "Terms of service",
	        new Contact("minhtuanitk43", "www.minhtuanitk43.com", "minhtuan.itk43@gmail.com"),
	        "License of API",
	        "API license URL",
	        Collections.emptyList());
	  }

	  @Bean
	  public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .apiInfo(apiInfo())
	        .securityContexts(Arrays.asList(securityContext()))
	        .securitySchemes(Arrays.asList(apiKey()))
	        .select()
	        .apis(RequestHandlerSelectors.basePackage("com.tim.restful"))
	        .paths(PathSelectors.ant("/api/*"))
	        .build();
	  }

	  private ApiKey apiKey() {
	    return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	  }

	  private SecurityContext securityContext() {
	    return SecurityContext.builder()
	        .securityReferences(defaultAuth())
	        .build();
	  }

	  List<SecurityReference> defaultAuth() {
	    AuthorizationScope authorizationScope
	        = new AuthorizationScope("global", "accessEverything");
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	    authorizationScopes[0] = authorizationScope;
	    return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	  }

}
