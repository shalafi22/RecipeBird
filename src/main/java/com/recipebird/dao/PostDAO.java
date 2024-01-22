package com.recipebird.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.recipebird.model.Post;
import com.recipebird.model.User;

public interface PostDAO extends MongoRepository<Post, String> {
	public List<Post> findAll();
	public List<Post> findByAuthor(User author);
}
