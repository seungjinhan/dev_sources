package com.chunlab.app.system.enums;

/**
 * 
  * @FileName : EnumProgramStopType.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :프로그램 중단 사유
 */
public enum EnumProgramStopType {

	NONE, USER_BEFORE_AGREE, USER_AFTER_AGREE, ADMIN_BEFORE_AGREE, ADMIN_AFTER_AGREE;

	public static EnumProgramStopType getNameByIndex( int index) {
		
		return EnumProgramStopType.values()[index];
	}
}
