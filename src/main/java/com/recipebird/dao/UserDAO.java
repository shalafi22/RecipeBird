package com.recipebird.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.recipebird.model.User;
import java.util.List;



public interface UserDAO extends MongoRepository<User, String>{
	public User findByUsername(String username);
}
