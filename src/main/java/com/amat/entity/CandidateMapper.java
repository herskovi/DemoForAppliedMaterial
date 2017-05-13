package com.amat.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

public class CandidateMapper implements Serializable{

	private static final long serialVersionUID = -7788619177798333712L;
	
	
	
	private String email;
	private String fullName;
	private String telephone;
	private String company;
	private String comments;
	private int id;
	private Date createdDate;
	
	
	@JsonSerialize
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@JsonSerialize
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	@JsonSerialize
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@JsonSerialize
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	
	}
	@JsonSerialize
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	@JsonSerialize(using=DateSerializer.class)
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
}