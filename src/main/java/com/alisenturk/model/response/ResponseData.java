package com.alisenturk.model.response;

import java.io.Serializable;
import java.util.List;

public class ResponseData<T> implements Serializable {
	
	private static final long serialVersionUID = 3213924808186128070L;
	
	private String 	statusCode;
	private String 	statusMessage;
	private T		data;
	private int		dataCount=-1;
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public int getDataCount() {
		if(dataCount==-1 && getData()!=null){
			if(getData() instanceof List){
				dataCount = ((List)getData()).size();
			}
		}
		return dataCount;
	}
	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}
	

}
