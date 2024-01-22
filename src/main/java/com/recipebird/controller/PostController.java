package com.recipebird.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recipebird.dao.PostDAO;
import com.recipebird.dao.UserDAO;
import com.recipebird.model.User;
import com.recipebird.model.Post;
import com.recipebird.model.Comment;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
	@Autowired private PostDAO postDao;
	@Autowired private UserDAO userDao;
	
	@PostMapping("/create")
	ResponseEntity<Post> createPost(@RequestBody Map<String, String> requestBody) {
		String author = requestBody.get("author");
		String title= requestBody.get("title");
		String content = requestBody.get("content");
		User authorUser = userDao.findByUsername(author);
		if (authorUser == null) return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		ArrayList<Comment> l = new ArrayList<Comment>();
		Post post = new Post(title, content, 0, 0, authorUser, l);
		postDao.save(post);
		return ResponseEntity.status(HttpStatus.OK).body(post);
	}
	
	@GetMapping("/getall")
	ResponseEntity<List<Post>> getAllPosts() {
		return ResponseEntity.status(HttpStatus.OK).body(postDao.findAll());
	}
	
	
	@PutMapping("/like/{postid}")
	ResponseEntity<Boolean> likePost(@PathVariable String postid) {
		Optional<Post> postopt = postDao.findById(postid);
		Post post = postopt.get();
		post.incrementNumLike();
		postDao.save(post);
		return ResponseEntity.status(HttpStatus.OK).body(true);
	}
	
	@PutMapping("/unlike/{postid}")
	ResponseEntity<Boolean> unlikePost(@PathVariable String postid) {
		Optional<Post> postopt = postDao.findById(postid);
		Post post = postopt.get();
		post.decrementtNumLike();
		postDao.save(post);
		return ResponseEntity.status(HttpStatus.OK).body(true);
	}
	
	
	@PutMapping("/addcomment/{postid}")
	ResponseEntity<Boolean> addComment(@PathVariable String postid, @RequestBody Map<String, String> requestBody ) {
		String author = requestBody.get("author");
		String content = requestBody.get("content");
		Optional<Post> postopt = postDao.findById(postid);
		Post post = postopt.get();
		if (post == null) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		User authorUser = userDao.findByUsername(author);
		if (authorUser == null) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		Comment com = new Comment(content, authorUser);
		post.addComment(com);
		postDao.save(post);
		return ResponseEntity.status(HttpStatus.OK).body(true);
	}
	
	@DeleteMapping("/delete/{postid}/{username}")
	ResponseEntity<Boolean> deletePost(@PathVariable String postid, @PathVariable String username){
		Optional<Post> postopt = postDao.findById(postid);
		Post post = postopt.get();
		if (post == null) return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
		String postAuthorUsername = post.getAuthor().getUsername();
		if (postAuthorUsername.equals(username)) {
			postDao.delete(post);
			return ResponseEntity.status(HttpStatus.OK).body(true);
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
		}
	} 
	
	@GetMapping("/getbyusername/{username}")
	ResponseEntity<List<Post>> getPostByUsername(@PathVariable String username) {
		User user = userDao.findByUsername(username);
		if (user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		return ResponseEntity.status(HttpStatus.OK).body(postDao.findByAuthor(user));
	}
}
