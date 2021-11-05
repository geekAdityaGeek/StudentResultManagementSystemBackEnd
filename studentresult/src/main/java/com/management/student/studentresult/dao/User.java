package com.management.student.studentresult.dao;

import java.util.Date;

import javax.persistence.*;

import com.management.student.studentresult.service.UserService;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "User")

public class User {
	
	@Id
	@GeneratedValue
	private int userId;
	@OneToOne
	@JoinColumn(name = "auth_id")
	private Auth auth;
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
	private String extId;
	private String name; 
	private String address; 
	private String phone;
	private String status; 
	@Column(name = "created_by")
	private String createdBy; 
	@Column(name = "created_at")
	private Date createdAt; 
	@Column(name = "modified_by")
	private String modifiedBy; 
	@Column(name = "modified_at")
	private Date modifiedAt;

	@PrePersist
	void created_at() {
		this.createdBy = name;
		this.createdAt = this.modifiedAt = new Date(); 
	}
	
	@PreUpdate
	void modified_at() {
		this.modifiedAt = new Date(); 
	}

	public User(Role role, String extId, String name, String address, String phone) {
		this.role = role;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.status = "ACTIVE";
	}

//	public static void main(String args[]) {
//		User user = new User(new Role("STUDENT"),"MT2020022",  "Suvam Das", "Kolkata", "9874695445");
//		UserService userService = new UserService();
//		userService.saveUser(user);
//	}

}















