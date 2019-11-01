package com.chunlab.app.utils.email;

/**
 * 이메일 전송을 위한 ENUM 코드
 * 
 *  형식: 메일명("파일명","제목")
 * 
 * @package : com.chunlab.system.util.mail
 * @file    : EnumMailSenderInfo.java
 * @author  : seungjin.han
 * @Date    : 2018. 5. 14.
 * @version : 0.1
 */
public enum EnumEmailTemplateInfo  {

	TEST					("test", 						"TEST"),
	AUTH					("auth", 						"이메일 인증 안내 메일입니다."),
	forget_password			("forget_password",				"패스워드 찾기"),
	WELCOME					("welcome", 					"가입을 환영합니다."),
	REQUEST_PASSWORD_CHANGE	("request_password_change", 	"비밀번호를 재설정하세요.");
	
	private String fileName;
	private String subject;
	
	EnumEmailTemplateInfo( String fileName, String subject) {
	
		this.fileName = fileName;
		this.subject = subject;
	}
	
	public String getSubject() {
		
		return "[스마일바이오미]" + this.subject;
	}
	
	public String getFileName() {
		
		return this.fileName;
	}
}
