package com.alisenturk.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@RequestMapping("/login")
	public @ResponseBody String doLogin(@RequestParam String username,@RequestParam String password){
		String result = "Selam " + username;
		
		return result;
	}
	
}
