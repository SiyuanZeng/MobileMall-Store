package com.mindtree.mcse.mobilemall.web.validators;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mindtree.mcse.mobilemall.domain.hibernateannotation.HReview;

public class ReviewValidator implements Validator {
	private Logger logger = Logger.getLogger(ReviewValidator.class);
	
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return HReview.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		logger.debug("validate() method to validate " + obj.getClass().toString() + obj + "************************************* ");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", "name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemId", "required.itemId", "ItemId is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "required.title", "Title is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "required.description", "Description is required.");
		
		logger.debug("Add Review Errors: " + errors);
	}
}		
