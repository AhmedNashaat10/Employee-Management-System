package com.example.project1.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.project1.DTO.DepartmentDTO;
import com.example.project1.Entity.Department;
import com.example.project1.Repository.DepartmentRepository;

@Service
public class DepartmentServiceImp implements DepartmentService{

	@Autowired
	DepartmentRepository departmentRepository;
	
	public DepartmentDTO getDepartmentById(long id)
	{
		return DepartmentDTO.toDto(departmentRepository.findById(id).get());
	}
	
	public DepartmentDTO AddDepartment(DepartmentDTO departmentDTO)
	{
		return DepartmentDTO.toDto(departmentRepository.save(DepartmentDTO.toEntity(departmentDTO)));
	}
	
	public DepartmentDTO UpdateDepartment(long id,DepartmentDTO departmentDTO)
	{
		Department department = departmentRepository.findById(id).get();
//		department.setDepartmentName(departmentDTO.getDepartmentName());
//		return DepartmentDTO.toDto(departmentRepository.save(department));
		DepartmentDTO.UpdateDepartment(departmentDTO, department);
		return DepartmentDTO.toDto(departmentRepository.save(department));
		
	}
	
	public String DeleteDepartment(long id)
	{
		if(departmentRepository.findById(id).isPresent())
		{
			departmentRepository.deleteById(id);
			return "the department has been deleted successfully";
		}
		
		else
			return "there is no department with that id";
	}
	
	public Page<Department> GetAllDepartments(int pageNum,int pageSize,boolean isAsc,String orderBy)
	{
		Pageable page =PageRequest.of(pageNum, pageSize, Sort.by(isAsc ? Direction.ASC : Direction.DESC, orderBy));
		return departmentRepository.findAli(page);
	}
	
	public DepartmentDTO GetDepartmentByName(String name)
	{
		return DepartmentDTO.toDto(departmentRepository.findByDepartmentName(name));
	}
}
