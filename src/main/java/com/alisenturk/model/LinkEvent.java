package com.alisenturk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class LinkEvent extends BaseEntity {
	
	private UserLink	userLink;
	private String		referrer;
	private String		location;
	private String		ipAddress;
	
	@NotNull
	@ManyToOne	
	public UserLink getUserLink() {
		return userLink;
	}
	public void setUserLink(UserLink userLink) {
		this.userLink = userLink;
	}
	
	@Column(length=512)
	public String getReferrer() {
		return referrer;
	}
	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}
	
	@Column(length=512)
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Column(length=20)
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	
	
	
}
