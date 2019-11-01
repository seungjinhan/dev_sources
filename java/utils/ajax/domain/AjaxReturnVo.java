package com.chunlab.app.utils.ajax.domain;

import com.chunlab.app.utils.ajax.AjaxConstants;
import com.chunlab.app.utils.domain.BaseVo;

import lombok.Data;

/**
 * 
  * @FileName : AjaxReturnVo.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
@Data
public class AjaxReturnVo extends BaseVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7788001732823200318L;
	
	private String code = AjaxConstants.DEFAULT_CODE;
	private String message = AjaxConstants.DEFAULT_MESSAGE;
	private Object data = AjaxConstants.DEFAULT_DATA;

	public AjaxReturnVo() {
	}
	
	public AjaxReturnVo(String code) {
		this.code = code;
	}

	public AjaxReturnVo(String code, Object data) {
		this.code = code;
		this.data = data;
	}
	
	public AjaxReturnVo(String code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	
	
	
}
