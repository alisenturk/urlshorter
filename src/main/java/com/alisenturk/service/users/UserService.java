package com.alisenturk.service.users;

import java.io.Serializable;
import java.util.List;

import com.alisenturk.model.User;

public interface UserService extends Serializable{
	public List<User> listAllUsers();
	public User		findMemberById(Long id);
	public void save(User user);
}
