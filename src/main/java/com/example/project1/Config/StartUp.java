package com.example.project1.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.project1.DTO.DepartmentDTO;
import com.example.project1.DTO.EmployeeDTO;
import com.example.project1.Entity.Department;
import com.example.project1.Entity.Employee;
import com.example.project1.Service.DepartmentService;
import com.example.project1.Service.EmployeeService;

//this class will execute in the moment the Application start
//@Component
public class StartUp implements CommandLineRunner{

	@Autowired
	DepartmentService departmentService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Override
	public void run(String... args) throws Exception {
		//create Employees and Department or anything
		DepartmentDTO department=new DepartmentDTO();
		department.setDepartmentName("Net");
		departmentService.AddDepartment(department);
		
		
		EmployeeDTO employee=new EmployeeDTO();
		employee.setEmployeeName("Tantawy");
		employee.setEmployeeSalary(15000);
		//employee.setDepartment(DepartmentDTO.toEntity(department)); //here is the error
		employeeService.AddEmployee(employee);
		
	}

	
}
