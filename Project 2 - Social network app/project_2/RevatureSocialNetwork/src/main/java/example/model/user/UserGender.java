package example.model.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="sn_gender")
@Cacheable
//@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class UserGender {
	
	
	@Id
	@Column(name="sn_gender_id")
	int genderId;
	
	@Column(name="sn_gender")
	String gender;
	
	@Column(name="sn_pronouns")
	String pronouns;
	

	public UserGender() {
		super();
	}

	public UserGender(int genderId) {
		super();
		this.genderId = genderId;
	}

	public UserGender(String gender, String pronouns) {
		super();
		this.gender = gender;
		this.pronouns = pronouns;
	}

	public UserGender(int genderId, String gender, String pronouns) {
		super();
		this.genderId = genderId;
		this.gender = gender;
		this.pronouns = pronouns;
	}

	public int getGenderId() {
		return genderId;
	}

	public void setGenderId(int genderId) {
		this.genderId = genderId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPronouns() {
		return pronouns;
	}

	public void setPronouns(String pronouns) {
		this.pronouns = pronouns;
	}

	@Override
	public String toString() {
		return "UserGender [genderId=" + genderId + ", gender=" + gender + ", pronouns=" + pronouns + "]";
	} 
	
	
	


}
