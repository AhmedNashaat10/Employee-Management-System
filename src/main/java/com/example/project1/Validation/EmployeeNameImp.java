package com.example.project1.Validation;

import java.util.regex.Matcher;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class EmployeeNameImp implements ConstraintValidator<EmployeeName, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value==null || value.isEmpty())
		{
			return true;
		}
		Pattern pattern=Pattern.compile("");// my regex validation for the validation
		Matcher matcher=pattern.matcher(value);
		try {
			if(!matcher.matches()) {
				return false;
			}
			else {
				for(int i=1;i<=4;i++)
				{
					int octet= Integer.valueOf(matcher.group(i));
					if(octet>255)
						return false;
				}
			}
			return true;
		} catch (Exception e) {
		
			return false;
		}
		
	}
	
	

}
