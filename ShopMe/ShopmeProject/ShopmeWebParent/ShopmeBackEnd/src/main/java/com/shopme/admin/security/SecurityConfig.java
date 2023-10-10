package com.shopme.admin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

	@Bean
public UserDetailsService userDetailsService(PasswordEncoder encoder)
{
	UserDetails admin =User.withUsername("Vishnu")
			.password(encoder.encode("Vishnu@0802"))
			.roles("ADMIN")
			.build();
	UserDetails users =User.withUsername("John")
			.password("Vishnu@0803")
			.roles("USER")
			.build();
	return new InMemoryUserDetailsManager(admin,users);
}
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http)
//	{
//    http.csrf().disable();
//    .AuthorizeHttpRequestsConfigurer<HttpSecurityBuilder<H>>
//	}
	@Bean
	public PasswordEncoder paswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}
