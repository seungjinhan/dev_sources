package com.chunlab.app.system.enums;

import com.chunlab.app.system.enums.base.EnumBase;
import com.chunlab.app.system.enums.base.EnumExceptionBase;

/**
 * 
  * @FileName : EnumExceptionDatabase.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public enum EnumExceptionDatabase implements EnumExceptionBase{

	INSERT_ERROR("INSERT_ERROR", "INSERT_ERROR"),
	UPDATE_ERROR("UPDATE_ERROR", "UPDATE_ERROR"),
	SELECT_ERROR("SELECT_ERROR", "SELECT_ERROR"),
	JPA_ERROR("JPA_ERROR", "SELECT_ERROR"),
	DELETE_ERROR("DELETE_ERROR", "DELETE_ERROR");
	
	private String code;
	private String desc;
	
	EnumExceptionDatabase( String code, String desc){
		
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
	public EnumBase[] getValues() {
		return values();
	}
	
	@Override
	public String getName() {
		
		return name();
	}
}
