package com.alisenturk.dao.link;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alisenturk.model.Link;
import com.alisenturk.model.User;

@Transactional
public interface LinkDao extends JpaRepository<Link, Long> {
	
	@Query("select u from User u where u.apiKey = ?1")
	User findByApiKey(String apiKey);
	
	@Query("select u from Link u where u.shortUrl = ?1")
	Link findByShorturl(String shortUrl);
}