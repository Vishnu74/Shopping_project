package com.shopme.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

	@Autowired
	private UserService userservice;
	
	@PostMapping("/users/check_email")
	 public String CheckDuplicateEmail(@Param("email") String email) {
		return userservice.isEmailUnique(email) ? "OK" : "Duplicated";
	 }
}
