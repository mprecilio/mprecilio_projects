package example.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import example.model.post.Post;
import example.model.user.User;


@Entity
@Table(name = "sn_post_likes")
public class LikedPosts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sn_like_id")
	Integer likeID;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="sn_like_user_id", nullable = false)
	User likedBy;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="sn_liked_post_id", nullable = false)
	Post likedPostId;

	public LikedPosts() {
		super();
	}
	

	public LikedPosts(User likedBy, Post likedPostId) {
		super();
		this.likedBy = likedBy;
		this.likedPostId = likedPostId;
	}


	public LikedPosts(Integer likeID, User likedBy, Post likedPostId) {
		super();
		this.likeID = likeID;
		this.likedBy = likedBy;
		this.likedPostId = likedPostId;
	}

	public Integer getLikeID() {
		return likeID;
	}

	public void setLikeID(Integer likeID) {
		this.likeID = likeID;
	}

	public User getLikedBy() {
		return likedBy;
	}

	public void setLikedBy(User likedBy) {
		this.likedBy = likedBy;
	}

	public Post getLikedPostId() {
		return likedPostId;
	}

	public void setLikedPostId(Post likedPostId) {
		this.likedPostId = likedPostId;
	}

	@Override
	public String toString() {
		return "LikedPosts [likeID=" + likeID + ", likedBy=" + likedBy + ", likedPostId=" + likedPostId + "]";
	}

	
	
	

}
