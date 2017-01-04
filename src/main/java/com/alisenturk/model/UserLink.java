package com.alisenturk.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class UserLink extends BaseEntity {
	
	private String	longUrl;
	private String	shortUrl;
	private String	title;
	private String	tags;
	
	private List<LinkEvent> linkEvents = new ArrayList<>();
	
	@Column(length=512,nullable=false)
	public String getLongUrl() {
		return longUrl;
	}
	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}
	
	@Column(length=60,nullable=false)
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	@Column(length=512)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(length=512)
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	@OneToMany(mappedBy = "userLink")
	public List<LinkEvent> getLinkEvents() {
		return linkEvents;
	}
	public void setLinkEvents(List<LinkEvent> linkEvents) {
		this.linkEvents = linkEvents;
	}
	
	
	
	
}
