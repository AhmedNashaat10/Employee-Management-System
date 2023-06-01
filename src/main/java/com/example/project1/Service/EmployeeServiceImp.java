package com.example.project1.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.project1.DTO.EmployeeDTO;
import com.example.project1.Entity.Department;
import com.example.project1.Entity.Employee;
import com.example.project1.Error.RecordNotFoundException;
import com.example.project1.HRStatistic.EmployeeProjection;
import com.example.project1.HRStatistic.HRStatisticProject;
import com.example.project1.Repository.EmployeeRepository;

@Service
public class EmployeeServiceImp implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Employee AddEmployee(EmployeeDTO employeeDTO) {
		employeeDTO.setEmployeePassword(passwordEncoder.encode(employeeDTO.getEmployeePassword()));
		return employeeRepository.save(EmployeeDTO.toEntity(employeeDTO));
	}

	public EmployeeDTO GetEmployeeById(long id) {

		return EmployeeDTO.toDto(employeeRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("no record found for this id")));
	}

	public EmployeeDTO UpdateEmployee(long id, EmployeeDTO employeeDTO) {
//		Employee employee=employeeRepository.findById(id).get();
//		employee.setEmployeeName(employeeDTO.getEmployeeName());
//		employee.setEmployeeSalary(employeeDTO.getEmployeeSalary());
//		employee.setDepartment(employeeDTO.getDepartment());
//		return EmployeeDTO.toDto(employeeRepository.save(employee1));
		Employee employee = employeeRepository.findById(id).get();
		if (Objects.nonNull(employeeDTO.getEmployeeName())) {
			employee.setEmployeeName(employeeDTO.getEmployeeName());
		}
		if (Objects.nonNull(employeeDTO.getEmployeeSalary())) {
			employee.setEmployeeSalary(employeeDTO.getEmployeeSalary());
		}
		if (Objects.nonNull(employeeDTO.getDepartments())) {
			employee.setDepartments(employeeDTO.getDepartments());
		}
		if (Objects.nonNull(employeeDTO.getEmployeePassword())) {
			employee.setEmployeePassword(employeeDTO.getEmployeePassword());
		}
		return EmployeeDTO.toDto(employeeRepository.save(employee));

	}

	public String DeleteEmployee(long id) {
		if (employeeRepository.findById(id).isPresent()) {
			employeeRepository.deleteById(id);
			return "The employee has been removed Successfully";
		}

		return "there is no Employee with that id";

	}

	public List<EmployeeProjection> GetAllEmployeesIdProjection(String orderBy, boolean isAsc) {

		return employeeRepository.findAli(Sort.by(isAsc ? Direction.ASC : Direction.DESC, orderBy));
	}

	public List<EmployeeDTO> GetAllEmployees() {
		return employeeRepository.findAll().stream().map(EmployeeDTO::toDto).collect(Collectors.toList());
	}

	public List<EmployeeDTO> findBySalaryy(long salary) {
		return employeeRepository.findByEmployeeSalary(salary).stream().map(EmployeeDTO::toDto)
				.collect(Collectors.toList());
	}

	public HRStatisticProject getHRStatistic() {
		return employeeRepository.getHRStatistic();
	}

	public List<EmployeeDTO> GetAllEmployeesNamesAndIds() {
		return employeeRepository.GetAllEmployeesUsingConstructorProj().stream().map(EmployeeDTO::toDto)
				.collect(Collectors.toList());
	}

	public EmployeeDTO GetEmployeeByName(String name) {
		return EmployeeDTO.toDto(employeeRepository.findByEmployeeName(name).get());
	}

}
