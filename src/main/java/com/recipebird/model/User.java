package com.recipebird.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="users")
public class User {
	@Id
	private String id;
	
	private String username;
	private String password;
	
	public User() {}
	public User(String _password, String _username) {
		this.username = _username;
		this.password = _password;
	} 
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String _id) {
		this.id = _id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String _password) {
		this.password = _password;
	}
	
	public void setUsername(String _username) {
		this.username = _username;
	} 
}
