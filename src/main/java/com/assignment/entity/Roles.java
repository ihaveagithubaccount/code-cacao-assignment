package main.java.com.assignment.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name="role")
public class Roles implements Serializable {

	//private static final long serialVersionUID = 1L;
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="roleid")
    private Long roleId;  
	
	@Column(name="name")
    private String name;
	
	@ManyToOne
    @JoinColumn(name = "id")
	@JsonBackReference
	private User user;

	Roles(){		
	}

	public Roles(Long roleId, String name, User user) {
		super();
		this.roleId = roleId;
		this.name = name;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
