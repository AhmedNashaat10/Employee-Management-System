package com.example.project1.DTO;

import com.example.project1.Entity.Department;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDTO {

	private long departmentID;
	private String departmentName;
	
	public static DepartmentDTO toDto(Department department)
	{
		return DepartmentDTO.builder()
				.departmentID(department.getDepartmentID())
				.departmentName(department.getDepartmentName())
				.build();
	}
	
	public static Department toEntity(DepartmentDTO departmentDTO)
	{
		return Department.builder()
				.departmentID(departmentDTO.getDepartmentID())
				.departmentName(departmentDTO.getDepartmentName())
				.build();
	}
	
	public static DepartmentDTO UpdateDepartment(DepartmentDTO departmentDTO,Department department)
	{
		department.setDepartmentName(departmentDTO.getDepartmentName());
		return departmentDTO;
	}
}
