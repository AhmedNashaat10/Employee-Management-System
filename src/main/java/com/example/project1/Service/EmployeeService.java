package com.example.project1.Service;

import java.util.List;

import com.example.project1.DTO.EmployeeDTO;
import com.example.project1.Entity.Employee;
import com.example.project1.HRStatistic.EmployeeProjection;
import com.example.project1.HRStatistic.HRStatisticProject;

public interface EmployeeService {

	public Employee AddEmployee(EmployeeDTO employeeDTO);

	public EmployeeDTO GetEmployeeById(long id);

	public EmployeeDTO UpdateEmployee(long id, EmployeeDTO employeeDTO);

	public String DeleteEmployee(long id);

	public List<EmployeeProjection> GetAllEmployeesIdProjection(String orderBy, boolean isAsc);

	public List<EmployeeDTO> GetAllEmployees();

	public List<EmployeeDTO> findBySalaryy(long salary);

	HRStatisticProject getHRStatistic();

	public List<EmployeeDTO> GetAllEmployeesNamesAndIds();

	public EmployeeDTO GetEmployeeByName(String name);
}
