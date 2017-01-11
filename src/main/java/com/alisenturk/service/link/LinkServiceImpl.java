package com.alisenturk.service.link;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alisenturk.dao.link.LinkDao;
import com.alisenturk.model.Link;
import com.alisenturk.model.User;
import com.alisenturk.model.response.ResponseData;
import com.alisenturk.model.response.ResponseStatus;
import com.alisenturk.util.Helper;

@Service
public class LinkServiceImpl implements LinkService {
	
	@Autowired
	LinkDao linkDao;

	@Override
	public User findUserByApiKey(String apiKey) {
		return linkDao.findByApiKey(apiKey);
	}

	@Override
	public ResponseData<String> save(Link link) {
		ResponseData<String> result = new ResponseData<>();
		try{
			result.setData("");
			
			linkDao.save(link);
			
			result.setStatusCode(ResponseStatus.OK.getCode());
			result.setStatusMessage(ResponseStatus.OK.getMessage());
		}catch(Exception e){
			Helper.errorLogger(getClass(), e);
			result.setStatusCode(ResponseStatus.NOK.getCode());
			result.setStatusMessage(e.getMessage());
		}
		return result;
	}

	@Override
	public Link findByShortUrl(String shortUrl) {
		return linkDao.findByShorturl(shortUrl);
	}

}
