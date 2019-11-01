package com.chunlab.app.system.attact;

import com.chunlab.app.system.enums.base.EnumBase;
import com.chunlab.app.system.enums.base.EnumExceptionBase;

/**
 * 
  * @FileName : EnumAttact.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public enum EnumAttact implements EnumExceptionBase {

	SQL_INJECTION("SQL_INJECTION" , "SQL INJECTION"),
	IP_ATTACT("IP_ATTACT" , "IP_ATTACT");
	
	private String code;
	private String desc;
	
	EnumAttact( String code, String desc){
		
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
