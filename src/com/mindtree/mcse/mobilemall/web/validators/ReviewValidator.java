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
		logger.debug("validate() method to validate " + obj.getClass().toString() + " ************************************* ");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required.id", "id is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", "name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemId", "required.itemId", "ItemId is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "required.title", "Title is required.");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "timeStamp", "required.timeStamp", "TimeStamp is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "required.description", "Description is required.");
	}
}		
		//		private String ;
		//		private String itemId;
		//		private String ;
		//		private Date ;
		//		private String title;
		//		private String ;
		//		
		//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName",
		//				"required.userName", "Field name is required.");
		//		
		//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address",
		//				"required.address", "Field name is required.");
				
		//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
		//				"required.password", "Field name is required.");
					
		//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
		//				"required.confirmPassword", "Field name is required.");
				
		//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sex", 
		//				"required.sex", "Field name is required.");
				
		//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "favNumber", 
		//				"required.favNumber", "Field name is required.");
				
		//		ValidationUtils.rejectIfEmptyOrWhitespace(
		//				errors, "javaSkills", "required.javaSkills","Field name is required.");
				
		//		Customer cust = (Customer)target;
				
		//		if(!(cust.getPassword().equals(cust.getConfirmPassword()))){
		//			errors.rejectValue("password", "notmatch.password");
		//		}
		//		
		//		if(cust.getFavFramework().length==0){
		//			errors.rejectValue("favFramework", "required.favFrameworks");
		//		}
		//
		//		if("NONE".equals(cust.getCountry())){
		//			errors.rejectValue("country", "required.country");
		//		}

