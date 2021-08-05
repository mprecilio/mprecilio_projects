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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name="role")
@Table(name="sn_user_role")
@Cacheable
//@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
@JsonIgnoreProperties(value= {"usersRoles"})
public class UserRole {

	@Id
	@Column(name="sn_role_id")
	int roleId;
	
	@Column(name="sn_role")
	String role;
	
	public UserRole() {
		super();
	}

	public UserRole(int roleId) {
		super();
		this.roleId = roleId;
	}

	public UserRole(String role) {
		super();
		this.role = role;
	}

	public UserRole(int roleId, String role) {
		super();
		this.roleId = roleId;
		this.role = role;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRole [roleId=" + roleId + ", role=" + role + "]";
	}
	
	
	
	
}
