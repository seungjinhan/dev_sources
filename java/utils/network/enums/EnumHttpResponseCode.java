package com.chunlab.app.utils.network.enums;

import com.chunlab.app.system.enums.base.EnumBase;
import com.chunlab.app.system.enums.base.EnumExceptionBase;


/**
 * 
  * @FileName : EnumHttpResponseCode.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public enum EnumHttpResponseCode implements EnumExceptionBase{

	HTTP_401 ("HTTP_401","HTTP_401"),
	HTTP_402 ("HTTP_402","HTTP_402"),
	HTTP_403 ("HTTP_403","HTTP_404"),
	HTTP_406 ("HTTP_406","HTTP_406"),
	HTTP_40X ("HTTP_40X","HTTP_40X"),
	HTTP_202 ("HTTP_202","HTTP_202"),
	HTTP_50X ("HTTP_50X","HTTP_50X"),
	HTTP_500 ("HTTP_500","HTTP_500"),
	HTTP_ELSE ("HTTP_ELSE","HTTP_ELSE");
	
	private String code;
	private String desc;
	
	EnumHttpResponseCode( String code, String desc){
		
		this.code = code;
		this.desc = desc;
	}

	
	@Override
	public EnumBase[] getValues() {
		return values();
	}

	@Override
	public String getDesc() {
		return desc;
	}

	@Override
	public String getCode() {
		return code;
	}
	
	@Override
	public String getName() {
		
		return name();
	}

}
