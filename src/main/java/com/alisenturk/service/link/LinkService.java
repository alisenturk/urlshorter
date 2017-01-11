package com.alisenturk.service.link;

import java.io.Serializable;

import com.alisenturk.model.Link;
import com.alisenturk.model.User;
import com.alisenturk.model.response.ResponseData;

public interface LinkService  extends Serializable{

	public User findUserByApiKey(String apiKey);
	public ResponseData<String> save(Link link);
	public Link	findByShortUrl(String shortUrl);
}
