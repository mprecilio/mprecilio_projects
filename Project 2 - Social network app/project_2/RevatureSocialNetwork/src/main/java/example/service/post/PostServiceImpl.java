package example.service.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.dao.post.PostDao;
import example.model.LikedPosts;
import example.model.post.Post;
import example.model.post.PostPhoto;

@Service
public class PostServiceImpl implements PostService {
	
	private PostDao pDao;
	

	@Autowired
	public PostServiceImpl(PostDao pDao) {
		super();
		this.pDao = pDao;
	}

	////////////////////////////////////////////Create\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	@Override
	public boolean serviceNewPost(Post newPost) {
		pDao.postNew(newPost);
		return true;
	}

	@Override
	public void serviceNewLike(LikedPosts lp) {
		pDao.createNewLike(lp);		
	}


	
	
	////////////////////////////////////////////Read\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	@Override
	public List<Post> getAllPosts(String username) {
		List<Post> postList = pDao.getAllPosts();
		List<PostPhoto> photoList = pDao.getAllPhotos();
		List<LikedPosts> likeList = pDao.getAllLikes();
		for(Post tempPost: postList) {
			for(PostPhoto tempPhoto: photoList) {
				if(tempPost.getPostId()==tempPhoto.getPostPhoto().getPostId()) tempPost.addPhoto(tempPhoto);
			}
			for(LikedPosts tempLike: likeList) {
				if(tempLike.getLikedPostId().getPostId()==tempPost.getPostId())tempPost.addLike();
				if(tempLike.getLikedPostId().getPostId()==tempPost.getPostId() && 
						tempLike.getLikedBy().getUsername().equals(username)) tempPost.setLiked(true);
			}
		}
		return postList;
	}

	@Override
	public boolean serviceNewPhoto(PostPhoto newPhoto) {
		return pDao.postPhoto(newPhoto);
	}

	@Override
	public List<PostPhoto> getAllPhotos() {
		return pDao.getAllPhotos();
	}

	@Override
	public Post getPostById(int id) {
		return pDao.getPostById(id);
	}


	
	
	////////////////////////////////////////////Update\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	
	
	////////////////////////////////////////////Delete\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

}
