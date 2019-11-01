package com.chunlab.app.system.exception;


import com.chunlab.app.system.enums.base.EnumExceptionBase;

import lombok.Data;

/**
 * 
  * @FileName : ExceptionBase.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
@Data
public class ExceptionBase extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4893402395748023439L;
	
	private String code = "";
	private String desc = "";
	
	private Object data = "";
	
	private Exception  e;
	
	private EnumExceptionBase enumCode;
	
	public ExceptionBase( ExceptionBase e) {
		
		this.code = e.getCode();
		this.desc = e.getDesc();
		this.enumCode = e.getEnumCode();
		this.data = e.data;
		this.e = e;
	}
	
	public ExceptionBase( EnumExceptionBase code) {
		this.code = code.getCode();
		this.desc = code.getDesc();
		this.enumCode = code;
	}
	
	public ExceptionBase( EnumExceptionBase code, Object data) {
		this.code = code.getCode();
		this.desc = code.getDesc();
		this.data = data;
		this.enumCode = code;
	}
	
	public ExceptionBase( Exception e, EnumExceptionBase code, Object data) {
		this.e = e;
		this.code = code.getCode();
		this.desc = code.getDesc();
		this.data = data;
		this.enumCode = code;
	}
	
	public ExceptionBase( Exception e, EnumExceptionBase code) {
		this.e = e;
		this.code = code.getCode();
		this.desc = code.getDesc();
		this.enumCode = code;
	}

	@Override
	public String toString() {
		return "ExceptionBase [code=" + code + ", desc=" + desc + ", data=" + data + ", e=" + e + ", enumCode="	+ enumCode + "]";
	}
	
}
