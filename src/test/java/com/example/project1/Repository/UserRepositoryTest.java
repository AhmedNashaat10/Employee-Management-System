package com.example.project1.Repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project1.Entity.Employee;

@SpringBootTest
class UserRepositoryTest {


	@Autowired
	EmployeeRepository employeeRepository;
	
	@Test
	void GetAllEmployeesUsingConstructorProjection_FoundTest()
	{
		List<Employee> empList= employeeRepository.GetAllEmployeesUsingConstructorProj();
		assertEquals(true, empList!=null);
	}
	
	@Test
	void GetAllEmployeesUsingConstructorProjection_NotFoundTest()
	{
		List<Employee> empList= employeeRepository.GetAllEmployeesUsingConstructorProj();
		assertEquals(false, empList.isEmpty());
	}

}
