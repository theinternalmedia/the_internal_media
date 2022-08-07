package com.tim.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;

/**
 * This is configurations of application's beans
 * 
 * @appName the_internal_media
 *
 */
@Configuration
public class AppConfig {

	/**
	 * MessageSource Bean.
	 * 
	 * @author minhtuanitk43
	 * @return MessageSource
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = 
				new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	/**
	 * LocalValidatorFactoryBean Bean.
	 * 
	 * @author minhtuanitk43
	 * @return LocalValidatorFactoryBean
	 */
	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

	/**
	 * RestTemplate Bean.
	 * 
	 * @author minhtuanitk43
	 * @return RestTemplate
	 */
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	/**
	 * ModelMapper Bean.
	 * 
	 * @author minhtuanitk43
	 * @return ModelMapper
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
