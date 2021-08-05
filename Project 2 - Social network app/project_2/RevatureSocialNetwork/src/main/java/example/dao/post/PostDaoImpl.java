package example.dao.post;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import example.model.LikedPosts;
import example.model.post.Post;
import example.model.post.PostPhoto;


@Transactional
@Repository("postDao")
public class PostDaoImpl implements PostDao {
	
	private SessionFactory ses;
	
	
	@Autowired
	public PostDaoImpl(SessionFactory ses) {
		super();
		this.ses = ses;
	}

	public SessionFactory getSes() {
		return ses;
	}

	public void setSes(SessionFactory ses) {
		this.ses = ses;
	}




	////////////////////////////////////////////Create\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	@Override
	public boolean postNew(Post newPost) {
		
		ses.getCurrentSession().save(newPost);
		
		return true;
	}


	@Override
	public boolean postPhoto(PostPhoto photo) {
		
		ses.getCurrentSession().save(photo);
		
		return true;
	}
	
	@Override
	public void createNewLike(LikedPosts lp) {
		
		
		System.out.println("Your error is here dummy");
//		ses.getCurrentSession().save(lp);
		
	}

	
	
	////////////////////////////////////////////Read\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	@Override
	public List<Post> getAllPosts() {
		return ses.getCurrentSession().createQuery("from Post ORDER BY postId DESC", Post.class).list();
	}
	
	
	@Override
	public List<PostPhoto> getAllPhotos() {
		
		return ses.getCurrentSession().createQuery("from PostPhoto", PostPhoto.class).list();
	}

	@Override
	public Post getPostById(int id) {
		return ses.getCurrentSession().get(Post.class, id);
	}

	@Override
	public List<LikedPosts> getAllLikes() {
		return ses.getCurrentSession().createQuery("from LikedPosts", LikedPosts.class).list();
	}


	
	////////////////////////////////////////////Update\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	
	
	////////////////////////////////////////////Delete\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

}
