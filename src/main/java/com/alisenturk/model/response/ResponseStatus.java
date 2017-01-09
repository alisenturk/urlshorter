package com.alisenturk.model.response;

public enum ResponseStatus {
	OK("OK","İşlem Başarılı"),
	NORECORD("NORECORD","Kayıt bulunamadı!"),
	INVALID("INVALID","Geçersiz işlem!"),
	INVALID_TOKEN("INVALID_TOKEN","Geçersiz token!"),
	NOK("NOK","İşlem Başarısızlıkla sonuçlandı!");
	
	ResponseStatus(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	private String code;
	private String message;
	
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	
}
