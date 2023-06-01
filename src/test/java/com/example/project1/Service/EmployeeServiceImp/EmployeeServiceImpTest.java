package com.example.project1.Service.EmployeeServiceImp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.project1.DTO.EmployeeDTO;
import com.example.project1.Entity.Department;
import com.example.project1.Entity.Employee;
import com.example.project1.Repository.EmployeeRepository;
import com.example.project1.Service.EmployeeService;

@SpringBootTest
//@ContextConfiguration(classes = {EmployeeServiceImpTest.TestConfig.class})

class EmployeeServiceImpTest {

	@MockBean
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	@Test
	void AddEmployeeTest() {
		Department department=new Department(451,"asf");
		Set<Department> departments=new HashSet<>();
		departments.add(department);
		Employee employee=new Employee(500,"ASDAS","456145",565,"ROLES_ADMIN",departments);
		Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
		Employee employee2=employeeService.AddEmployee(EmployeeDTO.toDto(employee));
		assertEquals(employee, employee2);
		
	}

//	

}
