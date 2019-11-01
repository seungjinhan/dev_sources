package com.chunlab.app.utils.string.enums;

import com.chunlab.app.system.enums.base.EnumBase;

/**
 * 스트링 사이즈 비교 관련
 * 
 * @package : com.chunlab.enums
 * @file : EnumSringSizeCompare.java
 * @author : seungjin.han
 * @Date : 2018. 2. 6.
 * @version : 0.1
 */
public enum EnumSringSizeCompare implements EnumBase {

	SMALL ("",""), 
	SAME ("",""), 
	BIG ("",""), 
	SAME_SMALL ("",""), 
	SAME_BIG ("","");
	

	private String code;
	private String desc;
	
	EnumSringSizeCompare( String code, String msg){
		
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
