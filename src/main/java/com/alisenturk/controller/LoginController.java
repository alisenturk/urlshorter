package com.alisenturk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alisenturk.model.JWTUtil;
import com.alisenturk.model.User;
import com.alisenturk.model.response.ResponseData;

@RestController
public class LoginController {

	@Autowired
	JWTUtil jwutil;
	
	@RequestMapping(path="/login",method=RequestMethod.POST)
	public @ResponseBody ResponseData<User> doLogin(@RequestParam String username,@RequestParam String password){
		
		ResponseData<User> result = new ResponseData<>();
		
	
		// jwutil.generateTokenWithJWT(1,"ali"); /Token oluşturma
		
		/* TODO : Mehmet Emin -> Kullanıcı adı ve şifreye sahip bir user'ın olup olmadığı kontrol edilecek. User varsa result'ın data
		 * alanına set edilecek. Aynı zamanda Bu kullanıcı için token oluşturulacak ve bu token da result'taki token alanına set edilecek. 
		 */
		
		
		
		
		return result;
	}
	
}
