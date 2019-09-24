package com.test.devm.stockalarm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.devm.stockalarm.model.User;
import com.test.devm.stockalarm.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repository;
	
	@Override
	public User saveUser(User user) {
		return repository.save(user);
	}

	@Override
	public List<User> getUsers() {
		return (List<User>) repository.findAll();
	}
	
	@Override
	public Optional<User> getUser(Long userId) {
		return repository.findById(userId);
	}

}
