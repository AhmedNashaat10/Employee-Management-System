package com.example.project1.Security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.project1.Entity.Employee;
import com.example.project1.Error.RecordNotFoundException;
import com.example.project1.Repository.EmployeeRepository;

@Component
public class EmployeeUserDetailsService implements UserDetailsService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Employee> employee = employeeRepository.findByEmployeeName(username);
//		EntityToUserDetails entityToUserDetails=new EntityToUserDetails(employee);
//		return entityToUserDetails;
		return employee.map(EntityToUserDetails::new).orElseThrow(() -> new RecordNotFoundException("user not found"));

	}
}
