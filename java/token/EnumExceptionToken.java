package com.chunlab.app.token;

import com.chunlab.app.system.enums.base.EnumBase;
import com.chunlab.app.system.enums.base.EnumExceptionBase;

/**
 * 
  * @FileName : EnumExceptionToken.java
  * @Project : app_server
  * @Date : 2019. 9. 26. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 : 토튼 처리 예외 리스트
 */
public enum EnumExceptionToken implements EnumExceptionBase{

	NEED_ACCESS_TOKEN		("NEED_ACCESS_TOKEN" 			, "Access Token이 필요함"),
	NOT_EXIST_ACCESSTOKEN	("NOT_EXIST_ACCESSTOKEN" 		, "Access Token이 없음"),
	NOT_EXIST_REFRESHTOKEN	("NOT_EXIST_REFRESHTOKEN" 		, "Refresh Token이 없음"),
	NOT_EXIST_APPKEY		("NOT_EXIST_APPKEY" 			, "앱키데이터가 없음"),
	NOT_EXIST_EMAIL			("NOT_EXIST_EMAIL" 				, "이메일이 없음"),
	NOT_EXIST_TOKEN			("NOT_EXIST_TOKEN" 				, "토큰 정보가 없음"),
	FAIL_REMOVE_DB			("FAIL_REMOVE_DB" 				, "db 데이터 삭제 오류"),
	FAIL_REMOVE_CACHE		("FAIL_REMOVE_CACHE" 			, "CACHE 데이터 삭제 오류"),
	REFRESH_EXPIRE			("REFRESH_EXPIRE" 				, "REFRESH_만료됨"),
	EXPIRE					("EXPIRE" 						, "만료됨");
	
	private String code;
	private String desc;
	
	EnumExceptionToken( String code, String desc){
		
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	@Override
	public EnumBase[] getValues() {
		return values();
	}

	@Override
	public String getName() {
		
		return name();
	}
}
