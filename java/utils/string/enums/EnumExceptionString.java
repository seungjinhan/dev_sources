package com.chunlab.app.utils.string.enums;

import com.chunlab.app.system.enums.base.EnumBase;
import com.chunlab.app.system.enums.base.EnumExceptionBase;


/**
 * 
  * @FileName : EnumExceptionString.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public enum EnumExceptionString implements EnumExceptionBase{

	STRING_SIZE_WRONG	("STRING_0001" , "String 길이가 맞지 않음"),
	STRING_NULL			("STRING_9999" , "String is NULL");
	

	private String code;
	private String desc;
	
	EnumExceptionString( String code, String msg){
		
		this.code = code;
		this.desc = msg;
	}

	
	public String getDesc() {
		return desc;
	}


	public String getCode() {
		return code;
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
