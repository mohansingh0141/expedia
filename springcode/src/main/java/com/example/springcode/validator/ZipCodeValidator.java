package com.example.springcode.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.springcode.domain.ZipCode;


public class ZipCodeValidator implements Validator {

	private static Pattern pattern;
	
	static{
		pattern =	Pattern.compile("^\\d{5}$");
	}
	
	@Override
	public boolean supports(Class clazz) {
		
		return ZipCode.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors,"zipValue", "zipValue.cannotBeEmpty");
		
		ZipCode zipcode = (ZipCode) target;
		
		if(!isValidZip(zipcode.getZipValue())){
			errors.rejectValue("zipValue", "zipValue.invalidZip");
		}
		
		
	}
	
	public static boolean isValidZip(String zipcode){
		
		try{
			
			Matcher matcher = pattern.matcher(zipcode);
			return matcher.matches();
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	

}
