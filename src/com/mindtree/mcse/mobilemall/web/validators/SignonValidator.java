package com.mindtree.mcse.mobilemall.web.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mindtree.mcse.mobilemall.web.dto.LoginDto;

public class SignonValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return LoginDto.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "USERNAME_REQUIRED", "Username is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "PASSWORD_REQUIRED", "Password is required.");

	}

}
