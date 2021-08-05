package example.dao.post;

import java.util.List;

import example.model.LikedPosts;
import example.model.post.Post;
import example.model.post.PostPhoto;

public interface PostDao {
	
	////////////////////////////////////////////Create\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	/**
	 * @author Matthew
	 * 
	 * Accepts a post object and creates adds the post to the database 
	 * 
	 * @param Accept a Post object which will be written to the database
	 * @return boolean return: false = insert failed; true = success
	 */
	boolean postNew(Post newPost);
	
	/**
	 * @author Matthew
	 * 
	 * Adds new like to db
	 * 
	 * @param lp
	 */
	void createNewLike(LikedPosts lp);
	
	boolean postPhoto(PostPhoto photo);
	
	////////////////////////////////////////////Read\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	/**
	 * @author Matthew
	 * 
	 * @return List of all likes
	 */
	List<LikedPosts> getAllLikes();
	
	List<Post> getAllPosts();
	
	List<PostPhoto> getAllPhotos();
	
	Post getPostById(int id);
	////////////////////////////////////////////Update\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	
	
	////////////////////////////////////////////Delete\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

}
