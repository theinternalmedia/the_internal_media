package com.tim.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.tim.data.TimConstants;

public class PasswordValidator implements ConstraintValidator<Password, String>{

	@Override
	public void initialize(Password constraintAnnotation) {
		// TODO Auto-generated method stub
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return value.matches(TimConstants.REGEX_PASSWORD);
	}

}
