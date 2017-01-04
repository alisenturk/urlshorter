package com.alisenturk.controller;

import com.alisenturk.model.User;

public class BaseController {
	
	private User	user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
