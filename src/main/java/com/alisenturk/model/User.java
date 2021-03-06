package com.alisenturk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User extends BaseEntity  implements BaseInterface{

	private String	username;
	private String	name;
	private String	surname;
	private String	email;
	private String	apiKey;
	private String	domainURL = "http://url5.xyz";
	
	@Column(length=20,nullable=false, unique=true)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(length=60,nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(length=60,nullable=false)
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@Column(length=60,nullable=false,unique=true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(length=512)
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", name=" + name + ", surname=" + surname + ", email=" + email
				+ ", apiKey=" + apiKey + ", domainURL=" + domainURL + "]";
	}
	
	@Column(length=120)
	public String getDomainURL() {
		return domainURL;
	}
	public void setDomainURL(String domainURL) {
		this.domainURL = domainURL;
	}
	
	
	
}
