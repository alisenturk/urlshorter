package com.alisenturk.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MainController {
	@RequestMapping(value="/{shortUrl}", method=RequestMethod.GET)
	public RedirectView redirect(@PathVariable String shortUrl){
		
		if(shortUrl==null || shortUrl.equals("ali")){
			shortUrl = "http://localhost:8080//urlshorter/user/allList";
		}
		System.out.println("shortUrl..:" + shortUrl +" ..:" + new Date());
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(shortUrl);
		return redirectView;
	}
}
