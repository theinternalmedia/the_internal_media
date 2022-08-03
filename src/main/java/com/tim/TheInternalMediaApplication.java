package com.tim;

import com.tim.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.validation.constraints.Size;
import java.lang.reflect.Field;

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

}
