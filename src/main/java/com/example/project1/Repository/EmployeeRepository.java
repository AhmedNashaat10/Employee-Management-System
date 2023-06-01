package com.example.project1.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.project1.Entity.Employee;
import com.example.project1.HRStatistic.EmployeeProjection;
import com.example.project1.HRStatistic.HRStatisticProject;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public Optional<Employee> findByEmployeeName(String name);

	@Modifying
	@Transactional(readOnly = true) // the default is false= updateable or true just readonly
	public List<Employee> findByEmployeeSalary(@Param("TT") long salary);

	@Query(value = "select(select count(*) from department) AS deptCount,(select count(*) from employee) AS empCount", nativeQuery = true)
	HRStatisticProject getHRStatistic();

	@Query(value = "SELECT emp FROM Employee emp")
	List<EmployeeProjection> findAli(Sort sort); // or pageable has pagination and sort

	@Query(value = "SELECT emp FROM Employee emp ORDER BY employeeID DESC")
	List<Employee> findAll();

	@Query(value = "Select NEW Employee(emp.employeeID,emp.employeeName) FROM Employee emp")
	List<Employee> GetAllEmployeesUsingConstructorProj(); // the rest of the fields will be null or zeros

}
