package com.tim.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.tim.data.TimConstants;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

	@Override
	public void initialize(Phone constraintAnnotation) {
		// TODO Auto-generated method stub
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.matches(TimConstants.REGEX_PHONE_VN);
	}

}
