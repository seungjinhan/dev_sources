package com.chunlab.app.utils.network.enums;

import com.chunlab.app.system.enums.base.EnumBase;


/**
 * 
  * @FileName : EnumAPICall.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public enum EnumAPICall implements EnumBase {

	RETURN_FALSE("AC0001" , "API 호출 FALSE");
	
	private String code;
	private String desc;
	
	EnumAPICall( String code, String desc){
		
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
