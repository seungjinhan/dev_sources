package com.chunlab.app.system.exception;


import org.slf4j.Logger;

import com.chunlab.app.system.enums.base.EnumExceptionBase;

import lombok.Data;

/**
 * 
  * @FileName : ExceptionError.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
@Data
public class ExceptionError extends ExceptionBase {


	private Logger LOG;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4445808836802966306L;

	private ExceptionError(EnumExceptionBase code) {
		super(code);
	}

	private ExceptionError(EnumExceptionBase code, Object data) {
		super(code, data);
	}

	private ExceptionError(Exception e, EnumExceptionBase code, Object data) {
		super(e, code, data);
	}

	private ExceptionError(Exception e, EnumExceptionBase code) {
		super(e, code);
	}
	
	public ExceptionError(Exception e, EnumExceptionBase code, Logger LOG, String data) {
		super(e, code, data);		
		this.LOG = LOG;
	}
}
