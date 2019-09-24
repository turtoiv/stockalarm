package com.test.devm.stockalarm.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.devm.stockalarm.model.User;
import com.test.devm.stockalarm.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public User register(@RequestBody User newUser) {
		System.out.println("use registration called");
		return userService.saveUser(newUser);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Optional<User> getUser(@PathVariable("id") Long id) {
		System.out.println("user get method called");
		return userService.getUser(id);
	}
}
