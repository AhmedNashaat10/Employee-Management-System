package com.example.project1.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project1.DTO.DepartmentDTO;
import com.example.project1.Entity.Department;
import com.example.project1.Entity.Employee;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

	public Department findByDepartmentName(String departmentName);
	
	@Query(value = "SELECT dept FROM Department dept")
	Page<Department> findAli(Pageable pageable);
}
