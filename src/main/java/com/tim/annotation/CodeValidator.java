package com.tim.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.tim.data.TimConstants;

public class CodeValidator implements ConstraintValidator<Code, String> {

	@Override
	public void initialize(Code constraintAnnotation) {
		// TODO Auto-generated method stub
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.matches(TimConstants.REGEX_CODE);
	}

}
