package com.mindtree.mcse.mobilemall.web.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mindtree.mcse.mobilemall.domain.Account;

public class AccountValidator implements Validator {

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return Account.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "firstName", "FIRST_NAME_REQUIRED", "First name is required.");
		ValidationUtils.rejectIfEmpty(errors, "lastName", "LAST_NAME_REQUIRED", "Last name is required.");
		ValidationUtils.rejectIfEmpty(errors, "email", "EMAIL_REQUIRED", "Email address is required.");
		ValidationUtils.rejectIfEmpty(errors, "phone", "PHONE_REQUIRED", "Phone number is required.");
		ValidationUtils.rejectIfEmpty(errors, "address1", "ADDRESS_REQUIRED", "Address (1) is required.");
		ValidationUtils.rejectIfEmpty(errors, "city", "CITY_REQUIRED", "City is required.");
		ValidationUtils.rejectIfEmpty(errors, "state", "STATE_REQUIRED", "State is required.");
		ValidationUtils.rejectIfEmpty(errors, "zip", "ZIP_REQUIRED", "ZIP is required.");
		ValidationUtils.rejectIfEmpty(errors, "country", "COUNTRY_REQUIRED", "Country is required.");
	}
}
