package example.model.post;

import java.util.Date;
import java.sql.Timestamp;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import example.model.user.User;


@Entity
@Table(name="sn_post")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sn_post_id")
	int postId;
	
	@Column(name="sn_post_text_content", nullable = false)
	String postWrittenContent;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="sn_post_time_created")
	Date postTimeCreated;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="sn_user_id", nullable = false)
	User postedBy;
	
	@Transient
	private String createdByUser;
	@Transient
	private int numLikes = 0;
	@Transient
	private boolean isLiked = false;
	@Transient
	private List<PostPhoto> myPhotos = new ArrayList<>();
	
	
	public Post() {
		super();
	}

	public Post(String postWrittenContent, User postedBy) {
		super();
		this.postWrittenContent = postWrittenContent;
		this.postedBy = postedBy;
	}
	
	public Post(int postId) {
		super();
		this.postId = postId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getPostWrittenContent() {
		return postWrittenContent;
	}

	public void setPostWrittenContent(String postWrittenContent) {
		this.postWrittenContent = postWrittenContent;
	}

	public Date getPostTimeCreated() {
		return postTimeCreated;
	}

	public void setPostTimeCreated(Date postTimeCreated) {
		this.postTimeCreated = postTimeCreated;
	}

	public User getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(User postedBy) {
		this.postedBy = postedBy;
	}
	
	public void addPhoto(PostPhoto photo) {
		myPhotos.add(photo);
	}

	public String getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(String createdByUser) {
		this.createdByUser = createdByUser;
	}
	
	public List<PostPhoto> getMyPhotos() {
		return myPhotos;
	}

	public void setMyPhotos(List<PostPhoto> myPhotos) {
		this.myPhotos = myPhotos;
	}
	

	public int getNumLikes() {
		return numLikes;
	}

	public void setNumLikes(int numLikes) {
		this.numLikes = numLikes;
	}
	public void addLike() {
		this.numLikes++;
	}
	

	public boolean isLiked() {
		return isLiked;
	}

	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}

	@Override
	public String toString() {
		return "Post [postWrittenContent=" + postWrittenContent + ", postTimeCreated="
				+ /*postTimeCreated +*/ ", postedBy=" + postedBy.getUsername() + "]";
	}
	
	//postId=" + postId + ", 
}
