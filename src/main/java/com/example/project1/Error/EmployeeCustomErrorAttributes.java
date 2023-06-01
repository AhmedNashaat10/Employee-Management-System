package com.example.project1.Error;

import java.util.Map;
import java.util.Arrays;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
@Component
public class EmployeeCustomErrorAttributes extends DefaultErrorAttributes{ //this will customize all the response exception for all the methods
	
	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
		
		Map<String, Object> errorAttributes=super.getErrorAttributes(webRequest, options);
		errorAttributes.put("success", Boolean.FALSE);
		errorAttributes.put("status", errorAttributes.get("error"));
		errorAttributes.put("exception", errorAttributes.get("message"));
		errorAttributes.put("details", Arrays.asList(errorAttributes.get("message")));
		errorAttributes.remove("error");
		errorAttributes.remove("path");
		errorAttributes.remove("trace");
		return errorAttributes;
		
	}

}
