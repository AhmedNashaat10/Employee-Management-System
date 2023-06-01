package com.example.project1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project1.DTO.DepartmentDTO;
import com.example.project1.Entity.Department;
import com.example.project1.Service.DepartmentService;

import java.util.List;

@RestController
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	
	@PostMapping(value = "/department/api")
	public DepartmentDTO AddDepartment(@RequestBody DepartmentDTO departmentDTO)
	{
		return departmentService.AddDepartment(departmentDTO);
	}
	
	@GetMapping(value = "/department/api/get/{id}")
	public DepartmentDTO getDepartmentById(@PathVariable("id") long id)
	{
		return departmentService.getDepartmentById(id);
	}
	@PutMapping(value = "/department/api/update/{id}")
	public DepartmentDTO UpdateDepartment(@PathVariable("id") long id,@RequestBody DepartmentDTO departmentDTO)
	{
		return departmentService.UpdateDepartment(id,departmentDTO);
	}
	@DeleteMapping(value = "/department/api/delete/{id}")
	public String DeleteDepartment(@PathVariable("id") long id)
	{
		return departmentService.DeleteDepartment(id);
	}
	@GetMapping(value = "/department/api/get")
	public Page<Department> GetAllDepartments(@RequestParam int pageNum,@RequestParam int pageSize,@RequestParam boolean isAsc,@RequestParam String orderBy)
	{
		return departmentService.GetAllDepartments(pageNum,pageSize,isAsc,orderBy);
		
	}
	@GetMapping("/department/api/getn/{name}")
	public DepartmentDTO GetDepartmentByName(@PathVariable("name") String name)
	{
		return departmentService.GetDepartmentByName(name);
	}
}
