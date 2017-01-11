package com.alisenturk.service.link;

import java.io.Serializable;

import com.alisenturk.model.LinkEvent;
import com.alisenturk.model.response.ResponseData;

public interface LinkEventService extends Serializable{

	public ResponseData<String> save(LinkEvent linkEvent);
}
