package example.model.post;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import example.model.user.User;

@Entity
@Table(name="sn_post_photo")
public class PostPhoto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sn_post_photo_id")
	int postPhotoId;
	
	@Column(name="sn_post_photo_key")
	String postPhotoKey;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="sn_post_id", nullable = false)
	Post postPhoto;
	
	public PostPhoto() {
		super();
	}

	public PostPhoto(String postPhotoKey, Post photoPostId) {
		super();
		this.postPhotoKey = postPhotoKey;
		this.postPhoto = photoPostId;
	}

	public int getPostPhotoId() {
		return postPhotoId;
	}

	public void setPostPhotoId(int postPhotoId) {
		this.postPhotoId = postPhotoId;
	}

	public String getPostPhotoKey() {
		return postPhotoKey;
	}

	public void setPostPhotoKey(String postPhotoKey) {
		this.postPhotoKey = postPhotoKey;
	}
	

	public Post getPostPhoto() {
		return postPhoto;
	}

	public void setPostPhoto(Post postPhoto) {
		this.postPhoto = postPhoto;
	}

	@Override
	public String toString() {
		return "PostPhoto [postPhotoId=" + postPhotoId + ", postPhotoKey=" + postPhotoKey + "]";
	}
	
	


}
