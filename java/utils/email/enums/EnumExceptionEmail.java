package com.chunlab.app.utils.email.enums;

import com.chunlab.app.system.enums.base.EnumBase;
import com.chunlab.app.system.enums.base.EnumExceptionBase;

/**
 * 
 * @package : com.smilebiome.api.email.enums
 * @file    : EnumEmailException.java
 * @author  : seungjin.han
 * @Date    : 2018. 11. 14.
 * @version : 0.1
 */
public enum EnumExceptionEmail implements EnumExceptionBase{

	NOT_CONTENT_FILE("M_00001", "컨텐츠 메일이 없음"),
	ALEADY_USER_OPEN("M_00003", "이미 활성화 되었음"),
	CANNOT_USER("M_00004", "사용할수 없는 계정"),
	WRONG_CODE("M_00002", "CODE 오류");
	
	private String code;
	private String desc;
	
	EnumExceptionEmail( String code, String desc) {
	
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
