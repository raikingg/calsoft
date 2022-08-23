package com.shivan.calsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.shivan.calsoft.model.UserData;
import com.shivan.calsoft.service.UserService;

@RestController
@RequestMapping("/app")
public class AppController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/echo")
	public String greetMe() {
		return "Hello there";
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserData>> fetchAllUsers(){
		List<UserData> listOfAllUsers = userService.fetchAllUsers();
		if(!listOfAllUsers.isEmpty()) {
			return new ResponseEntity<List<UserData>>(listOfAllUsers,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<UserData>>(HttpStatus.NO_CONTENT);
		}
	}
	
	
	@PostMapping("/users")
	public ResponseEntity<UserData> addUser(@RequestBody UserData user){
		try {
		
		return new ResponseEntity<UserData>(userService.saveUser(user),HttpStatus.CREATED);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PutMapping("/users/{userId}")
	public ResponseEntity<UserData> updateUser(@PathVariable int userId, @RequestBody UserData userData){
		try {
			UserData user = userService.getUserByUserId(userId);
			if(!userData.getDesignation().isBlank()) {
				user.setDesignation(userData.getDesignation());
			}
			if(!userData.getName().isBlank()) {
				user.setName(userData.getName());
			}
			if(!String.valueOf(userData.getSalary()).isBlank()) {
				user.setSalary(userData.getSalary());
			}
			userService.saveUser(user);
			return new ResponseEntity<UserData>(user,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<UserData>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable int userId){
		List<UserData> userData = userService.fetchAllUsers();
		for(UserData user : userData) {
			if(user.getId() == userId) {
				userService.deleteUser(userId);
				return new ResponseEntity<String>("User Deleted",HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("Delete Failed!",HttpStatus.NOT_FOUND);
				
	}
}
