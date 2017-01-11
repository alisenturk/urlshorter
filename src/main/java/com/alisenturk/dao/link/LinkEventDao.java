package com.alisenturk.dao.link;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alisenturk.model.LinkEvent;

@Transactional
public interface LinkEventDao extends JpaRepository<LinkEvent, Long> {
	
	
}