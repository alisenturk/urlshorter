package com.alisenturk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alisenturk.annotations.TokenCheck;
import com.alisenturk.model.User;
import com.alisenturk.model.response.ResponseData;
import com.alisenturk.model.response.ResponseStatus;
import com.alisenturk.service.users.UserService;
import com.alisenturk.util.Helper;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService	userService;
	
	@RequestMapping(value="/save",method=RequestMethod.POST, produces = {"application/json","application/xml"})
	public @ResponseBody ResponseData<String> saveMember(@RequestBody User user){
		ResponseData<String> response = new ResponseData<>();
		try{			
			userService.save(user);
			response.setStatusCode(ResponseStatus.OK.getCode());
			response.setStatusMessage(ResponseStatus.OK.getMessage());
			response.setData("");
		}catch(Exception e){
			Helper.errorLogger(getClass(), e);
			response.setStatusCode(ResponseStatus.NOK.getCode());
			response.setStatusMessage(e.getMessage());
			response.setData("");
		}
		return response;
	}
	
	@RequestMapping(path="/allList")
	@TokenCheck(memberValidation=true)
	public @ResponseBody ResponseData<List<User>> getAllUsers(){
		ResponseData<List<User>> response = new ResponseData<>();
		try{			
			
			List<User> list = userService.listAllUsers();
			response.setData(list);
			response.setStatusCode(ResponseStatus.OK.getCode());
			response.setStatusMessage(ResponseStatus.OK.getMessage());
			if(response.getData().isEmpty()){
				response.setStatusCode(ResponseStatus.NOK.getCode());
				response.setStatusMessage(ResponseStatus.NOK.getMessage());
			}
			
		}catch(Exception e){
			Helper.errorLogger(getClass(), e);
		}
		return response;
	}
	
}
