package com.alisenturk.controller;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alisenturk.model.Link;
import com.alisenturk.model.User;
import com.alisenturk.model.response.ResponseData;
import com.alisenturk.model.response.ResponseStatus;
import com.alisenturk.service.link.LinkService;
import com.alisenturk.util.Helper;

@RestController
@RequestMapping("/api")
public class UrlController {

	@Autowired
	LinkService linkService;
	
	@Value("${server.context-path}")
	private String appContextPath;
	
	@RequestMapping(path="/shorturl",method=RequestMethod.POST)
	public ResponseData<String> generateShortUrl(@RequestBody(required=true) Link link,
												@RequestHeader(name="apiKey",required=true,defaultValue="bos") String apiKey){
		ResponseData<String> response = new ResponseData<>();
		try{
			response.setData("");
			
			User user = linkService.findUserByApiKey(apiKey);
			if(user==null){
				throw new RuntimeException("Kullanıcı bulunamadı!");
			}
			String shortUrl = RandomStringUtils.randomAlphanumeric(8);;
			
			link.setDomainURL(user.getDomainURL());
			link.setCreatedBy(user.getId());			
			link.setShortUrl(shortUrl);
			
			response = linkService.save(link);
			if(response.getStatusCode().equals(ResponseStatus.OK.getCode())){
				response.setData(user.getDomainURL() + appContextPath + shortUrl);
			}
		}catch(Exception e){
			response.setStatusCode(ResponseStatus.NOK.getCode());
			response.setStatusMessage(ResponseStatus.NOK.getMessage());
			Helper.errorLogger(getClass(), e);
		}
		
		return response;
	}
}
