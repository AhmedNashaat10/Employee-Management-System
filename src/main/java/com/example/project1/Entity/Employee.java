package com.example.project1.Entity;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQuery(name = "Employee.findByEmployeeSalary", query = "select emp from Employee emp where emp.employeeSalary >= :TT")
@Builder
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private long employeeID;
	//@EmployeeName
	private String employeeName;
	private String employeePassword;
	private long employeeSalary;
	private String employeeRoles;
	@JsonManagedReference
	@OneToMany(targetEntity = Department.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "EmployeeDepartmentFK", referencedColumnName = "employeeID")
	private Set<Department> departments;
	
	public Employee(long id, String name) {
		employeeID = id;
		employeeName = name;
	}

}
