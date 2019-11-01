package com.chunlab.app.utils.enums;

import com.chunlab.app.system.enums.base.EnumBase;

/**
 * 
  * @FileName : EnumSizeCompare.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public enum EnumSizeCompare   implements EnumBase {
	
	SMALL(">" , ""), 
	SAME("=" , ""), 
	BIG("<" , ""), 
	SAME_SMALL(">=" , ""), 
	SAME_BIG("<=" , ""), 
	ERROR("ERROR" , "");
	
	private String code;
	private String desc;
	
	private EnumSizeCompare( String code, String desc) {
	
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

