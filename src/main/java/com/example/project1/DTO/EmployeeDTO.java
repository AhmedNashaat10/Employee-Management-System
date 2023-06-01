package com.example.project1.DTO;

import java.util.Set;

import com.example.project1.Entity.Department;
import com.example.project1.Entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {

	private long employeeID;
	private String employeeName;
	private String employeePassword;
	private long employeeSalary;
	private String employeeRoles;
	private Set<Department> departments;

	public static EmployeeDTO toDto(Employee employee) {
		return EmployeeDTO.builder().employeeID(employee.getEmployeeID()).employeeName(employee.getEmployeeName())
				.employeeSalary(employee.getEmployeeSalary()).departments(employee.getDepartments())
				.employeePassword(employee.getEmployeePassword()).build();
	}

	public static Employee toEntity(EmployeeDTO employeeDTO) {
		return Employee.builder().employeeID(employeeDTO.getEmployeeID()).employeeName(employeeDTO.getEmployeeName())
				.employeeSalary(employeeDTO.getEmployeeSalary()).departments(employeeDTO.getDepartments())
				.employeePassword(employeeDTO.getEmployeePassword()).employeeRoles(employeeDTO.getEmployeeRoles())
				.build();
	}

	public static Employee UpdateEntity(EmployeeDTO employeeDTO, Employee employee) {
		return employee.builder().employeeID(employeeDTO.getEmployeeID()).employeeName(employeeDTO.getEmployeeName())
				.employeeSalary(employeeDTO.getEmployeeSalary()).departments(employeeDTO.getDepartments())
				.employeePassword(employeeDTO.getEmployeePassword()).build();
	}
}
