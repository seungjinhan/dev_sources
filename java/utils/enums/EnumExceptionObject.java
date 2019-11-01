package com.chunlab.app.utils.enums;

import com.chunlab.app.system.enums.base.EnumBase;
import com.chunlab.app.system.enums.base.EnumExceptionBase;


/**
 * 
  * @FileName : EnumExceptionObject.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public enum EnumExceptionObject implements EnumExceptionBase {

	
	OBJECT_NULL("Object_0001" , "Object is NULL");

	private String code;
	private String desc;
	
	EnumExceptionObject( String code, String desc){
		
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
