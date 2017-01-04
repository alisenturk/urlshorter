package com.alisenturk.interceptor;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.alisenturk.util.Helper;

@Aspect
@Component
public class TokenInterceptor {
	
	
	@Around(value="execution(* com.alisenturk.controller..*.*(..)) && @annotation(com.alisenturk.annotations.TokenCheck))")
	public Object tokenControl(ProceedingJoinPoint joinPoint){
		Object result = null;
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		try{
			result = joinPoint.proceed();
			System.out.println("Token Kontrol..:" + new Date());
		}catch(Throwable e){
			Helper.errorLogger(getClass(), new RuntimeException(e.getMessage()));
		}finally{
			stopWatch.stop();
			System.out.println("Calisma Süresi..:" + stopWatch.getTotalTimeMillis() + " ms");
		}
		
		return (result==null)?"Başarısız":result;
		
	}
}
