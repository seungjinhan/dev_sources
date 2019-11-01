package com.chunlab.app.utils.date.enums;

/**
 * 
  * @FileName : EnumDateType.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public enum EnumDateType {


    YYYY("yyyy"), 
    YYYYMM("yyyyMM"), 
    YYYYMMDD("yyyyMM"),
    MM("MM"), 
    MMDD("MMdd"), 
    DD("dd"), 
    YYYYMMDDHHmm("yyyyMMddHHmm"), 
    YYYYMMDDHHmmss("yyyyMMddHHmmss");

	private String type;
	
	private EnumDateType( String type) {

		this.type = type;		
	}
	
	public String getType() {
		return this.type;
	}
}
