package com.example.project1.HRStatistic;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeProjection {

	
	long getEmployeeID(); //Interface close Projection
	
	@Value("#{target.employeeID + ' ' + target.employeeSalary}")
	long getEemployeeNameAndHisSalary(); //Interface open Projection
	
	String getEmployeeName();
	
}
