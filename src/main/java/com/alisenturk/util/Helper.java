package com.alisenturk.util;

public class Helper {
	
	
	public static void errorLogger(Class clazz,Exception e){
		errorLogger(clazz, e,null);
	}
	public static void errorLogger(Class clazz,Exception e,String extraInfo){
		System.out.println(e.getMessage());
	}
}
