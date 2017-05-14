package com.amat.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="candidates")
public class Candidate implements Serializable
{

		

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	@Column(name = "EMAIL", nullable=false)
	private String email;
	@Column(name = "NAME", nullable=false)
	private String fullName;
	@Column(name = "TELEPHONE", nullable=false)
	private String telephone;
	@Column(name = "COMPANY")
	private String company;
	@Column(name = "COMMENTS")
	private String comments;
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
	
	
	
	public Candidate( String email, String fullName, String telephone, String company, String comments,
			Date creationDate) {
		super();
		this.email = email;
		this.fullName = fullName;
		this.telephone = telephone;
		this.company = company;
		this.comments = comments;
		this.creationDate = creationDate;
	}
	
	public Candidate() 
	{
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
}