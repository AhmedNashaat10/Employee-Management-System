package com.example.project1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project1.DTO.EmployeeDTO;
import com.example.project1.DTO.EmployeeDtoForUsernameAndPassword;
import com.example.project1.Entity.Employee;
import com.example.project1.Error.RecordNotFoundException;
import com.example.project1.HRStatistic.EmployeeProjection;
import com.example.project1.HRStatistic.HRStatisticProject;
import com.example.project1.Service.EmployeeService;
import com.example.project1.Service.JwtService;

@RestController
public class EmployeeController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	EmployeeService employeeService;

	@PostMapping(value = "/employee/api")
	public Employee AddEmployee(@RequestBody EmployeeDTO employeeDTO) {
		return employeeService.AddEmployee(employeeDTO);
	}

	@GetMapping(value = "/employee/api/get/{id}")
	public EmployeeDTO GetEmployeeById(@PathVariable long id) {
		return employeeService.GetEmployeeById(id);
	}

	@PutMapping(value = "/employee/api/update/{id}")
	public EmployeeDTO UpdateEmployee(@PathVariable("id") long id, @RequestBody EmployeeDTO employeeDTO) {
		return employeeService.UpdateEmployee(id, employeeDTO);
	}

	@DeleteMapping(value = "/employee/api/delete/{id}")
	public String DeleteEmployee(@PathVariable long id) {
		return employeeService.DeleteEmployee(id);
	}

	@GetMapping("/employee/api/getids")
	public List<EmployeeProjection> GetAllEmployeesIdProjection(@RequestParam String orderBy,
			@RequestParam boolean isAsc) {
		return employeeService.GetAllEmployeesIdProjection(orderBy, isAsc);
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping(value = "/employee/api/get")
	public List<EmployeeDTO> GetAllEmployees() {
		return employeeService.GetAllEmployees();
	}

	@GetMapping("/employee/api/get/bys/{salary}")
	public List<EmployeeDTO> findBySalaryy(@PathVariable("salary") long salary) {
		return employeeService.findBySalaryy(salary);
	}

	@GetMapping(value = "/employee/api/get/statistic")
	public HRStatisticProject getHRStatistic() {
		return employeeService.getHRStatistic();
	}

	@GetMapping(value = "/employee/api/get/constructorprojection")
	public List<EmployeeDTO> GetAllEmployeesNamesAndIds() {
		return employeeService.GetAllEmployeesNamesAndIds();
	}

	@GetMapping(value = "/employee/api/getb/{name}")
	public EmployeeDTO GetEmployeeByName(@PathVariable("name") String name) {
		return employeeService.GetEmployeeByName(name);
	}
	@PostMapping(value = "/employee/authenticate")
	public String authenticateAndGetToken(@RequestBody EmployeeDtoForUsernameAndPassword employeeDtoForUsernameAndPassword)
	{
		if((authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(employeeDtoForUsernameAndPassword.getEmployeeName(), employeeDtoForUsernameAndPassword.getEmployeePassword()))).isAuthenticated())
		{
			return jwtService.GenerateToken(employeeDtoForUsernameAndPassword.getEmployeeName());
		}
		else {
			throw new RecordNotFoundException("no employee found with that creditals");
		}
		
	}
}
