package com.recipebird.model;

public class Comment {
	private String content;
	private User authorUser;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getAuthorUser() {
		return authorUser;
	}
	public void setAuthorUser(User authorUser) {
		this.authorUser = authorUser;
	}
	public Comment(String content, User authorUser) {
		super();
		this.content = content;
		this.authorUser = authorUser;
	}
	public Comment() {
		super();
	}
}
