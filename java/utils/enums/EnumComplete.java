package com.chunlab.app.utils.enums;

import com.chunlab.app.system.enums.base.EnumBase;

/**
 * 미완료, 완료, 실패
 * @package : com.chunlab.app.utils.enums
 * @file    : EnumComplete.java
 * @author  : seungjin.han
 * @Date    : 2018. 12. 6.
 * @version : 0.1
 */
public enum EnumComplete implements EnumBase{

	NO_COMPLETE( "NO_COMPLETE" , "미완료"),  
	COMPLETE("COMPLETE" , "성공"), 
	FAIL("FAIL" , "실패"),
	TEST("TEST" , "테스트");
	
	private String code;
	private String desc;
	
	private EnumComplete( String code, String desc) {
	
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
