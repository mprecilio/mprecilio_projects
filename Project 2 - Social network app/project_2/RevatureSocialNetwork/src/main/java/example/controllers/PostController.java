package example.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import example.model.LikedPosts;
import example.model.post.Post;
import example.model.post.PostPhoto;
import example.model.user.User;
import example.service.post.PostService;
import example.service.user.UserService;

@RestController
@RequestMapping(value="/post")
@CrossOrigin("*")
public class PostController {
	
	private PostService ps;
	private UserService us;
	static private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	public PostController(PostService ps, UserService us) {
		super();
		this.ps = ps;
		this.us = us;
	}


	private int postId; 

	final static Logger loggy = Logger.getLogger(PostController.class);
	
		{
			loggy.setLevel(Level.ALL);
		}
	////////////////////////////////////////////Create\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	@PostMapping(value="/new-post")
	public String controllerNewPost(HttpSession ses, @RequestBody Post newPost) {
		System.out.println("-------------Creating Post--------------");
		User postUser = new User();
		postUser.setUsername(newPost.getCreatedByUser());
		User postUserWInfo = us.searchUser(postUser);
		newPost.setPostedBy(postUserWInfo);
		System.out.println(newPost);
		ps.serviceNewPost(newPost);
		postId = newPost.getPostId();
		
		
		
		loggy.info(postUser.getUsername() + " has created a post!!!");
		
		return "Success";
		
	}
	
	@PostMapping(value="/like-photo")
	public String controllerNewLike(HttpSession ses, @RequestBody Post newPost) {
		System.out.println("-------------Creating Post--------------");
		User postUser = new User();
		postUser.setUsername(newPost.getCreatedByUser());
//		User postUserWInfo = us.searchUserTwo(postUser);
		User postUserWInfo = us.searchUserTwo(postUser);
		System.out.println(postUserWInfo);
		Post postWInfo = ps.getPostById(newPost.getPostId());
		LikedPosts lp = new LikedPosts(postUserWInfo,postWInfo);
		ps.serviceNewLike(lp);
		
		
		
		loggy.info(postUser.getUsername() + " has created a post!!!");
		
		return "Success";
		
	}

	
	@PostMapping(value="/postphoto")
	public void controllerPostPhoto(HttpServletRequest req, HttpServletResponse res, @RequestBody User photoKey) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("-------------------Adding A Post--------------------------------");
		
//		String key = mapper.readValue(req.getInputStream(), Object.class).toString();
		String key = photoKey.getProfilePhoto();
		if(key==null) {
			System.out.println("no photo added");
			return;
		}
		System.out.println(key);
		
		int length = key.length();
//		key = key.substring(5, length-1);
		System.out.println("this is my key: " + key);
		System.out.println("and here is my postId: " + postId);
		Post myPost = ps.getPostById(postId);
		ps.serviceNewPhoto(new PostPhoto(key, myPost));
		
		if((ps.serviceNewPhoto(new PostPhoto(key, myPost)))==true) {
			loggy.info("Posting photo was successful!!!");
		}
		else {
			loggy.info("Posting photo was unsuccessful!!!");
		}
	}
	
	
	////////////////////////////////////////////Read\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	@GetMapping(value="/get-posts/{username}")
	public List<Post> controllerGetPosts(HttpSession ses, @PathVariable(value="username") String username) {
		System.out.println("-------------Getting all Posts--------------");
		
		loggy.info("All posts have beem sent over to the front-end!!");
		return ps.getAllPosts(username);
		
	}
	
	
	////////////////////////////////////////////Update\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	
	
	////////////////////////////////////////////Delete\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	
}
