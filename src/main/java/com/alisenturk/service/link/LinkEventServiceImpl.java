package com.alisenturk.service.link;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alisenturk.dao.link.LinkEventDao;
import com.alisenturk.model.LinkEvent;
import com.alisenturk.model.response.ResponseData;
import com.alisenturk.model.response.ResponseStatus;
import com.alisenturk.util.Helper;

@Service
public class LinkEventServiceImpl implements LinkEventService{

	@Autowired
	LinkEventDao linkEventDao;
	
	@Override
	public ResponseData<String> save(LinkEvent linkEvent) {
		ResponseData<String> result = new ResponseData<>();
		try{
			result.setData("");
			
			linkEventDao.save(linkEvent);
			
			result.setStatusCode(ResponseStatus.OK.getCode());
			result.setStatusMessage(ResponseStatus.OK.getMessage());
		}catch(Exception e){
			Helper.errorLogger(getClass(), e);
			result.setStatusCode(ResponseStatus.NOK.getCode());
			result.setStatusMessage(e.getMessage());
		}
		return result;
	}

	
}
