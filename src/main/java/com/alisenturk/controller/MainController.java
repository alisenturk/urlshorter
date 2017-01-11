package com.alisenturk.controller;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.alisenturk.model.Link;
import com.alisenturk.model.LinkEvent;
import com.alisenturk.service.link.LinkEventService;
import com.alisenturk.service.link.LinkService;
import com.alisenturk.util.Helper;

@Controller
public class MainController {
	
	@Autowired
	LinkService linkService;
	
	@Autowired
	LinkEventService linkEventService;
	
	@RequestMapping(value="/{shortUrl}", method=RequestMethod.GET)
	public RedirectView redirect(@PathVariable(name="shortUrl",required=true) String shortUrl,
			HttpServletRequest request){
		Enumeration<String> heads  = request.getHeaderNames();
		String headName = "";	
		String referrer = request.getHeader("referrer");
		StringBuilder headerValues = new StringBuilder();
		while(heads.hasMoreElements()){
			headName = heads.nextElement();
			System.out.println(headName + ":"+ request.getHeader(headName));
			headerValues.append(headName + ":"+request.getHeader(headName)+"#");
		}
		
		
		
		RedirectView redirectView = new RedirectView();
		String longUrl = "error";
		if(shortUrl==null || shortUrl.equals("ali")){
			longUrl="error";
		}
		Link link = linkService.findByShortUrl(shortUrl);
		if(link!=null){
			LinkEvent event = new LinkEvent();
			event.setIpAddress(Helper.getUserIpAddress(request));
			event.setReferrer(referrer);
			event.setHeaderValues(headerValues.toString());
			event.setUserLink(link);
			event.setCreationDate(new Date());
			
			linkEventService.save(event);
			
			longUrl = link.getLongUrl();
		}	
		
		redirectView.setUrl(longUrl);
		
		return redirectView;
	}
}
