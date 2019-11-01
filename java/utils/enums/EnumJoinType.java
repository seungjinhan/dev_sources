package com.chunlab.app.utils.enums;

import com.chunlab.app.system.enums.base.EnumBase;

/**
 * 회원가입 타입
 * 
 * @package : com.chunlab.app.utils.enums
 * @file    : EnumJoinType.java
 * @author  : seungjin.han
 * @Date    : 2018. 11. 13.
 * @version : 0.1
 */
public enum EnumJoinType  implements EnumBase{

	EMAIL("EMAIL" , "EMAIL"), 
	KAKAO("KAKAO" , "KAKAO");

	private String code;
	private String desc;
	
	private EnumJoinType( String code, String desc) {
	
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
