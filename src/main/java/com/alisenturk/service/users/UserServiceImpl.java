package com.alisenturk.service.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alisenturk.dao.users.UserDao;
import com.alisenturk.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	@Cacheable("listAllUsers")
	public List<User> listAllUsers() {		
		return (List<User>)userDao.findAll();
	}


	@Override
	public void save(User user) {
		userDao.save(user);
		
	}

	@Override
	public User findUserById(Long id) {
		return userDao.findOne(id);
	}

	@Override
	public User findUserByIdAndUsername(Long id, String username) {
		// TODO : Oğuz -> Kullanıcı adı ve id'ye göre arama yapan query'i UserDao'da yazar mısın?
		return null;
	}
	


}
