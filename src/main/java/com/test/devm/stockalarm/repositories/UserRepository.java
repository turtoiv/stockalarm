package com.test.devm.stockalarm.repositories;

import org.springframework.data.repository.CrudRepository;

import com.test.devm.stockalarm.model.User;

public interface UserRepository extends CrudRepository<User,Long> {

}
