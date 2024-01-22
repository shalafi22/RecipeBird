package com.recipebird.controller;

import java.util.Map;

import org.apache.tomcat.util.bcel.classfile.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recipebird.dao.UserDAO;
import com.recipebird.model.User;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	@Autowired private UserDAO userDao;
	public static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@PostMapping("/register")
	ResponseEntity<User> registerUser(@RequestBody Map<String, String> requestBody) {
		String username = requestBody.get("username");
		String password = requestBody.get("password");
		User existingUser = userDao.findByUsername(username);
		if (existingUser == null) {
			User user = new User(password, username);
			userDao.save(user);
			return ResponseEntity.status(HttpStatus.OK).body(user);
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		
		
	}
	
	@PostMapping("/login")
	ResponseEntity<Boolean> loginUser(@RequestBody Map<String, String> requestBody){
		String username = requestBody.get("username");
		String password = requestBody.get("password");
		User user = userDao.findByUsername(username);
		if (user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
		} else {
			if (password.equals(user.getPassword())) {return ResponseEntity.status(HttpStatus.OK).body(true);}
			else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
			
		}
	} 
}
