package com.recipebird.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "posts")
public class Post {
	public Post() {
		super();
	}
	public Post(String postTitle, String postContent, int numComments, int numLikes, User author,
			List<Comment> comments) {
		super();
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.numComments = numComments;
		this.numLikes = numLikes;
		this.author = author;
		this.comments = comments;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public int getNumComments() {
		return numComments;
	}
	public void setNumComments(int numComments) {
		this.numComments = numComments;
	}
	public int getNumLikes() {
		return numLikes;
	}
	public void setNumLikes(int numLikes) {
		this.numLikes = numLikes;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public void incrementNumLike() {
		this.numLikes++;
	}
	public void addComment(Comment com) {
		comments.add(com);
	}
	
	@Id
	private String id;
	
	private String postTitle;
	private String postContent;
	private int numComments;
	private int numLikes;
	private User author;
	private List<Comment> comments;
	public void decrementtNumLike() {
		this.numLikes--;
		
	}
	
}


