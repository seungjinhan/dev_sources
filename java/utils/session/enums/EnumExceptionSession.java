package com.chunlab.app.utils.session.enums;

import com.chunlab.app.system.enums.base.EnumBase;
import com.chunlab.app.system.enums.base.EnumExceptionBase;

/**
 * 
 * @package : com.chunlab.app.utils.session.enums
 * @file    : EnumSessionException.java
 * @author  : seungjin.han
 * @Date    : 2018. 11. 15.
 * @version : 0.1
 */
public enum EnumExceptionSession implements EnumExceptionBase {

	NO_SESSION("SS_00001" , "Session이 없음");
	
	private String code;
	private String desc;
	
	EnumExceptionSession( String code, String desc){
		
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
