package com.chunlab.app.system.enums;

import com.chunlab.app.system.enums.base.EnumBase;
import com.chunlab.app.system.enums.base.EnumExceptionBase;


/**
 * 
  * @FileName : EnumExceptionNetwork.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public enum EnumExceptionNetwork implements EnumExceptionBase{

	HTTP_XXX("HTTP_XXX", "HTTP_XXX"),
	HTTP_500("HTTP_500", "HTTP_500"),
	WRONG_PARAMETER("WRONG_PARAMETER", "WRONG_PARAMETER"),
	WRONG_URL("WRONG_URL", "WRONG_URL"),
	CONNECTION_REFUSED("CONNECTION_REFUSED", "CONNECTION_REFUSED");
	
	private String code;
	private String desc;
	
	EnumExceptionNetwork( String code, String desc){
		
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
