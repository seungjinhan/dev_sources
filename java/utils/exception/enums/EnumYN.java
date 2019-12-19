package com.chunlab.app.system.enums;

import com.chunlab.app.system.enums.base.EnumBase;
import com.chunlab.app.system.enums.base.EnumExceptionBase;

/**
 * 
  * @FileName : EnumYN.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public enum EnumYN implements EnumExceptionBase {

	N("N","N"), 
	Y("Y","Y");
	
	private String code;
	private String desc;
	
	EnumYN( String code, String desc){
		
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
	
	public static EnumYN get( boolean yn) {
		
		return yn==true?Y:N;
	}
	
	public static boolean get( EnumYN yn) {
		
		return yn==Y?true:false;
	}
	
}
