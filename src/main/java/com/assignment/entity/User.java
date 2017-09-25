package main.java.com.assignment.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="user")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private Long id;  
	
	@Column(name="name")
    private String name;
	
	@Column(name="lastname")
    private String lastName;
	
	@Column(name="email")
    private String email;
	
	@Column(name="active")
    private Boolean active;
	
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<Roles> roles = new ArrayList<Roles>();
	
	public User(){
	}
	
	public User(Long id, String name, String lastName, String email, Boolean active, List<Roles> roles) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.active = active;
		this.roles = roles;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	

}
