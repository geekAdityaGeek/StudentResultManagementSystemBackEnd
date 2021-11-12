package com.management.student.studentresult.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;

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
	@Column(name = "ext_id", unique = true)
	private String extId;
	private String name; 
	private String address;
	@Column(unique = true)
	private String phone;
	private Date dob;
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
		this.status = "ACTIVE";
		this.createdAt = this.modifiedAt = new Date(); 
	}
	
	@PreUpdate
	void modified_at() {
		this.modifiedAt = new Date(); 
	}

	public User(Auth auth, Role role, String extId, String name, String address, String phone, Date dob){
		this.auth = auth;
		this.role = role;
		this.extId = extId;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.dob = dob;
		this.status = "ACTIVE";
	}

	public User() {
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Auth getAuth() {
		return auth;
	}

	public void setAuth(Auth auth) {
		this.auth = auth;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getExtId() {
		return extId;
	}

	public void setExtId(String extId) {
		this.extId = extId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
}















