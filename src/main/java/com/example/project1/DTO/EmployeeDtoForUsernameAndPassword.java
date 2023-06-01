package com.example.project1.DTO;

import com.example.project1.Entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDtoForUsernameAndPassword {

	private String employeeName;
	private String employeePassword;
	
	
	public EmployeeDtoForUsernameAndPassword toDto(Employee employee)
	{
		return EmployeeDtoForUsernameAndPassword.builder()
				.employeeName(employee.getEmployeeName())
				.employeePassword(employee.getEmployeePassword())
				.build();
	}
	public Employee toEntity(EmployeeDtoForUsernameAndPassword employeeDtoForUsernameAndPassword)
	{
		return Employee.builder().employeeName(employeeDtoForUsernameAndPassword.getEmployeePassword())
				.employeePassword(employeeDtoForUsernameAndPassword.getEmployeePassword())
				.build();
		
	}
}
