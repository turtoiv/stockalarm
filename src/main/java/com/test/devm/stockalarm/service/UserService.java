package com.test.devm.stockalarm.service;

import java.util.List;
import java.util.Optional;

import com.test.devm.stockalarm.model.User;

public interface UserService {

	public User saveUser(User user);
	
	public List<User> getUsers();
	
	public Optional<User> getUser(Long userId);
}
