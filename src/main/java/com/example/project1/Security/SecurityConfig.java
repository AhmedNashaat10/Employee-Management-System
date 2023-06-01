package com.example.project1.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.project1.Filter.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable().authorizeHttpRequests().requestMatchers("/employee/api","/employee/authenticate","/department/api").permitAll().and()
				.authorizeHttpRequests().requestMatchers("/employee/**","/department/**").authenticated().and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

//	@Bean
//	public WebSecurityCustomizer webSecurityCustomizer()
//	{
//		return (web) -> web.ignoring().requestMatchers("/employee/api/get");
//	}

//	public InMemoryUserDetailsManagerConfigurer userDetailsService()
//	{
//		UserDetails userDetails=User.withDefaultPasswordEncoder()
//				.username("ahmed")
//				.password("nashaat")
//				.roles("USER")
//				.build();
//		return InMemoryUserDetailsManagerConfigurer(userDetails);
//	}
	// authentication
	@Bean
	public UserDetailsService users() {
////		UserBuilder users=User.withDefaultPasswordEncoder();
//		UserDetails user = User.withUsername("user").password(passwordEncoder.encode("userpassword")).roles("USER")
//				.build();
//		UserDetails admin = User.withUsername("admin").password(passwordEncoder.encode("adminpassword"))
//				.roles("USER", "ADMIN").build();
		return new EmployeeUserDetailsService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); // we need to give
																							// authenticationProvider
																							// userdetailservice and
																							// passwordencoder so
																							// authenticationProvider
																							// use
																							// userdetails and generate
																							// userdetails object and
																							// will set to
																							// authentication object
		authenticationProvider.setUserDetailsService(users());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
	{
		return authenticationConfiguration.getAuthenticationManager();
	}
}
