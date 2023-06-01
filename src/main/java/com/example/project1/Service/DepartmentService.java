package com.example.project1.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.project1.DTO.DepartmentDTO;
import com.example.project1.Entity.Department;

public interface DepartmentService {

	public DepartmentDTO getDepartmentById(long id);
	public DepartmentDTO AddDepartment(DepartmentDTO departmentDTO);
	public DepartmentDTO UpdateDepartment(long id,DepartmentDTO departmentDTO);
	public String DeleteDepartment(long id);
	public Page<Department> GetAllDepartments(int pageNum,int pageSize,boolean isAsc,String orderBy);
	public DepartmentDTO GetDepartmentByName(String name);
}
