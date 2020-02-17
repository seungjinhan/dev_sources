package com.chunlab.app.utils.date.enums;

import com.chunlab.app.system.enums.base.EnumBase;

/**
 * 
  * @FileName : EnumDateType.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public enum EnumDayType  implements EnumBase{


	CUSTOM				("CUSTOM", "CUSTOM"), 
	YEAR				("YEAR"	, "년"), 
	DAY					("DAY"	, "일"), 
	MONTH				("MONTH", "월"), 
	WEEK				("WEEK"	, "주"); 


	private String code;
	private String desc;
	
	EnumDayType( String code, String msg){
		
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
