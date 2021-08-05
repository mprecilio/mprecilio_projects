package example.service.post;

import java.util.List;

import example.model.LikedPosts;
import example.model.post.Post;
import example.model.post.PostPhoto;

public interface PostService {
	
	////////////////////////////////////////////Create\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	/**
	 * @author Matthew
	 * 
	 * Sends a post object to the dao layer to be added to the database
	 * 
	 * @param Accepts a Post object from the controller
	 * @return boolean return: false = insert failed; true = success
	 */
	boolean serviceNewPost(Post newPost);
	
	/**
	 * @author Matthew
	 * 
	 * Creates a new like
	 * 
	 * @param lp
	 */
	void serviceNewLike(LikedPosts lp);
	
	boolean serviceNewPhoto(PostPhoto newPhoto);
	
	////////////////////////////////////////////Read\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	/**
	 * @author Matthew
	 * 
	 * Gets all posts from dao
	 * 
	 * @return List of all posts
	 */
	List<Post> getAllPosts(String username);
	
	List<PostPhoto> getAllPhotos();
	
	Post getPostById(int id);
	
	////////////////////////////////////////////Update\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	
	
	////////////////////////////////////////////Delete\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\


}
