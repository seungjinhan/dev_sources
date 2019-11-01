package com.chunlab.app.utils.enums;

import com.chunlab.app.system.enums.base.EnumBase;

/**
 * 
 * @package : com.chunlab.app.utils.enums
 * @file    : EnumOpen.java
 * @author  : seungjin.han
 * @Date    : 2018. 11. 14.
 * @version : 0.1
 */
public enum EnumOpen implements EnumBase{

	CLOSE ("CLOSE","CLOSE"), 
	OPEN ("OPEN","OPEN");

	private String code;
	private String desc;
	
	private EnumOpen( String code, String desc) {
	
		this.code = code;
		this.desc = desc;
	}
	

	@Override
	public String getCode() {
		return code;
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
	public String getName() {
		
		return name();
	}
}
