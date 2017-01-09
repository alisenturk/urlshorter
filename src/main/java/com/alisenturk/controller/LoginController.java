package com.alisenturk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alisenturk.model.JWTUtil;

@RestController
public class LoginController {

	@Autowired
	JWTUtil jwutil;
	
	@RequestMapping(path="/login",method=RequestMethod.POST)
	public @ResponseBody String doLogin(@RequestParam String username,@RequestParam String password){
		String result = "Selam " + username;
	
		result = jwutil.generateTokenWithJWT(1,"ali");		
		
		
		return result;
	}
	
}
