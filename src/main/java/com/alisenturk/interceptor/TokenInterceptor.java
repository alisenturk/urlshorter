package com.alisenturk.interceptor;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.alisenturk.annotations.TokenCheck;
import com.alisenturk.controller.BaseController;
import com.alisenturk.dao.users.UserDao;
import com.alisenturk.model.JWTUtil;
import com.alisenturk.model.User;
import com.alisenturk.model.response.ResponseData;
import com.alisenturk.model.response.ResponseStatus;
import com.alisenturk.service.users.UserService;
import com.alisenturk.util.Helper;

@Aspect
@Component
public class TokenInterceptor {
	
	
	@Autowired
	JWTUtil jwtutil;
	
	@Autowired
	UserService userService;
	
	@Value("${urlshorter.openmethod.performans}")
	private boolean openMethodPerformans;
	
	@Around(value="execution(* com.alisenturk.controller..*.*(..)) && @annotation(com.alisenturk.annotations.TokenCheck))")
	public Object tokenControl(ProceedingJoinPoint joinPoint){
		Object result = null;
        StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		String methodName = "";
		String token = "";
        try{ 
        	final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        	
    	    Method method = signature.getMethod();
    	    methodName = joinPoint.getTarget().getClass().getName() + "." + method.getName();
    	    
    	    TokenCheck tokenCtrl = method.getAnnotation(TokenCheck.class);
        	
            if(joinPoint.getArgs()!=null && joinPoint.getArgs().length>0){
            	int tokenIndex = -1;
            	for(String name:signature.getParameterNames()){
            		tokenIndex++;
            		if("token".equals(name)){
            			break;            			
            		}
            	}
            	
            	token =  String.valueOf(joinPoint.getArgs()[tokenIndex]);
            	boolean tokenGecerli = jwtutil.verifyToken(token);
    			if (!tokenGecerli){
    				result = "Invalid token!"; 
    				if(signature.getReturnType().getName().indexOf("ResponseData")>-1){
	    				ResponseData<String> response = new ResponseData<>();
	    				response.setStatusCode(ResponseStatus.INVALID_TOKEN.getCode());
	    				response.setStatusMessage(ResponseStatus.INVALID_TOKEN.getMessage());
	    				result = response;
    				}
    			}else{
    				
    				Object target = joinPoint.getTarget();
                	if(tokenCtrl.memberValidation() && target instanceof BaseController){
                		BaseController bc = (BaseController)target;
                		
                		User user = userService.findUserByIdAndUsername(Long.parseLong(jwtutil.parseToken(token, "userId")),
                														jwtutil.parseToken(token, "username"));
                		
            			if(user==null){
            				result = "Kullanıcı bulunamadı![token]:" + token; 
            				if(signature.getReturnType().getName().indexOf("ResponseData")>-1){
        	    				ResponseData<String> response = new ResponseData<>();
        	    				response.setStatusCode(ResponseStatus.NORECORD.getCode());
        	    				response.setStatusMessage("Kullanıcı bulunamadı![token]:" + token);
        	    				result = response;
            				}
            			}else{
            				bc.setUser(user); 
							result = joinPoint.proceed();
            			}	                		
                	}else{
						result = joinPoint.proceed();
                	}
    				 
    			}
               
            }
        }catch(Throwable e){
            
        }finally{
        	stopWatch.stop();
        	if(openMethodPerformans)
        		System.out.println(methodName + " calisma suresi..:" + stopWatch.getTotalTimeMillis() + " ms");
        }

        return (result==null)?"Basarisiz":result;
		
	}
}
