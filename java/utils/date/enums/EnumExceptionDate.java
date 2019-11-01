package com.chunlab.app.utils.date.enums;

import com.chunlab.app.system.enums.base.EnumBase;
import com.chunlab.app.system.enums.base.EnumExceptionBase;

/**
 * 
  * @FileName : EnumExceptionPush.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public enum EnumExceptionDate implements EnumExceptionBase {
	
	CANNOT_MAKE_DATA								("CANNOT_MAKE_DATA" 						, "데이터를 만들수 없음"),
	CHANGE_DATE_ERROR								("CHANGE_DATE_ERROR" 						, "날짜 변환 에러"),
	WRONG_DATE_FORMAT_ERROR							("WRONG_DATE_FORMAT_ERROR" 					, "날짜 포멧 에러");

	private String code;
	private String desc;
	
	EnumExceptionDate( String code, String desc){
		
		this.code = code;
		this.desc = desc;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
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
