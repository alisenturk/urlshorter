package com.alisenturk.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alisenturk.annotations.TokenCheck;
import com.alisenturk.model.JWTUtil;
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
	
	@Autowired
	JWTUtil jwtutil;
	 
	@Autowired
	PasswordEncoder passwordEncoder;
	
	private String generateApiKey(String kullaniciAdi){
		UUID uuid = UUID.randomUUID();
		String key = uuid.toString()+"#"+kullaniciAdi; 
		String token = passwordEncoder.encode(key);
		
		return token;
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST, produces = {"application/json","application/xml"})
	public @ResponseBody ResponseData<String> saveMember(@RequestBody User user){
		ResponseData<String> response = new ResponseData<>();
		try{
			
			user.setApiKey(generateApiKey(user.getUsername()));
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
	public @ResponseBody ResponseData<List<User>> getAllUsers(@RequestHeader(name="token",defaultValue="bos") String token){
		ResponseData<List<User>> response = new ResponseData<>();
		try{			
			
			List<User> list = userService.listAllUsers();
			response.setData(list);
			response.setStatusCode(ResponseStatus.OK.getCode());
			response.setStatusMessage(ResponseStatus.OK.getMessage());
			if(response.getData().isEmpty()){
				response.setStatusCode(ResponseStatus.NORECORD.getCode());
				response.setStatusMessage(ResponseStatus.NORECORD.getMessage());
			}
			
		}catch(Exception e){
			Helper.errorLogger(getClass(), e);
		}
		return response;
	}
	
	@RequestMapping(path="/check/token/valid")	
	public @ResponseBody ResponseData<String> isTokenValid(@RequestParam(name="token",required=true) String token){
		ResponseData<String> response = new ResponseData<>();
		try{			
			response.setStatusCode(ResponseStatus.OK.getCode());
			response.setStatusMessage("Token geçerlidir.");
			
			boolean isValid = jwtutil.verifyToken(token);
			
			if(!isValid){
				response.setStatusCode(ResponseStatus.INVALID.getCode());
				response.setStatusMessage("Geçersiz Token!!");
			}
			
		}catch(Exception e){
			Helper.errorLogger(getClass(), e);
			response.setStatusCode(ResponseStatus.NOK.getCode());
			response.setStatusMessage(e.getMessage());
		}
		return response;
	}
	
}
