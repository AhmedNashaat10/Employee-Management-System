package com.example.project1.Security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.project1.Entity.Employee;

public class EntityToUserDetails implements UserDetails {

	private String employeeName;
	private String employeePassword;
	private List<GrantedAuthority> authority;

	public EntityToUserDetails(Employee employee) {
		employeeName = employee.getEmployeeName();
		employeePassword = employee.getEmployeePassword();
		authority = Arrays.stream(employee.getEmployeeRoles().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authority;
	}

	@Override
	public String getPassword() {

		return employeePassword;
	}

	@Override
	public String getUsername() {
		return employeeName;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

}
