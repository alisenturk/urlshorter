package com.alisenturk.service.users;

import java.io.Serializable;
import java.util.List;

import com.alisenturk.model.User;

public interface UserService extends Serializable{
	public List<User> 	listAllUsers();
	public User			findUserById(Long id);
	public void 		save(User user);
	public User			findUserByIdAndUsername(Long id,String username);
}
