package com.chunlab.app.utils.exception.enums;

import com.chunlab.app.system.enums.base.EnumBase;
import com.chunlab.app.system.enums.base.EnumExceptionBase;

/**
 * 
  * @FileName : EnumException.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public enum EnumException implements EnumExceptionBase {

	PARSING_ERROR("EX_0001" , "PARSING ERROR"),
	WRONG_ACCESS("EX_0002" , "잘못된 접근"),
	TIME_OVER("EX_0003" , "시간 초과됨"),
	WRONG_ENUM_NAME("EX0001", "해당하는 enum이 없습니다"),
	SYSTEM_ERROR("EX_9999" , "SYSTEM ERROR");
	
	private String code;
	private String desc;
	
	EnumException( String code, String msg){
		
		this.code = code;
		this.desc = msg;
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
