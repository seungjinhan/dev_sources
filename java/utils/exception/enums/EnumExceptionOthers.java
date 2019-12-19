package com.chunlab.app.system.enums;

import com.chunlab.app.system.enums.base.EnumBase;
import com.chunlab.app.system.enums.base.EnumExceptionBase;


/**
 * 
  * @FileName : EnumExceptionOthers.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public enum EnumExceptionOthers implements EnumExceptionBase{

	NoSuchAlgorithmException("NoSuchAlgorithmException" , "NoSuchAlgorithmException"),
	JSON_ERROR("JSON_ERROR" , "JSON 변환 오류"),
	SQL_INJECTION("SQL_INJECTION" , "SQL_INJECTION"),
	DIGIT_OVER("DIGIT_OVER" , "숫가가 범위를 넘어섬"),
	JSONException("JSONException" , "JSONException"),
	IllegalAccessException("IllegalAccessException" , "IllegalAccessException"),
	IllegalArgumentException("IllegalArgumentException" , "IllegalArgumentException"),
	UnsupportedEncodingException("UnsupportedEncodingException" , "UnsupportedEncodingException"),
	ParseException("ParseException" , "ParseException");
	
	private String code;
	private String desc;
	
	EnumExceptionOthers( String code, String desc){
		
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
	public String getName() {
		
		return name();
	}
	
	@Override
	public EnumBase[] getValues() {
		return values();
	}
}
