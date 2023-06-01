package com.example.project1.Filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.project1.Security.EmployeeUserDetailsService;
import com.example.project1.Service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{

	@Autowired
	private EmployeeUserDetailsService employeeUserDetailsService;
	
	@Autowired
	private JwtService jwtService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader=request.getHeader("Authorization");
		String token=null;
		//Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtb2hhbWVkZCIsImlhdCI6MTY3NjEwODY5NiwiZXhwIjoxNjc2MTEwNDk2fQ.lYYyYzVBPfjq5iL7VgR-s2BT219eAqApHAMzIl_SbVM
		String userName=null;
		if(authHeader!=null && authHeader.startsWith("Bearer "))
		{
			token=authHeader.substring(7);
			userName= jwtService.ExtractUserName(token);
		}
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails userDetails=employeeUserDetailsService.loadUserByUsername(userName);
			if(jwtService.ValidateToken(token, userDetails))
			{
				UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);;
				
			}
		}
		filterChain.doFilter(request, response);
		
		
	}
	

}
