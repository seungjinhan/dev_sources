package com.chunlab.app.utils.network;


import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.chunlab.app.system.exception.ExceptionBase;
import com.chunlab.app.utils.network.enums.EnumAPIMethod;


/**
 * 
  * @FileName : OutServerCall.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
@Component
public class OutServerCall {

	@Autowired(required=true)
	private HttpServletRequest request;
	
//	public String call( EnumOutUrlBase url, EnumAPIMethod method) throws IOException, ExceptionBase{
//		
//		String result = null;
//		
//		result = OutServerUtil.call(request, method, url.getCode()); 
//		
//		return result;
//	}
//	
//	public String call( EnumOutUrlBase url, EnumAPIMethod method, JSONObject param) throws IOException, ExceptionBase{
//		
//		String result = null;
//		
//		result = OutServerUtil.call(request, method, url.getCode(), param); 
//		
//		return result;
//	}
//	
	public String call( String url, EnumAPIMethod method) throws ExceptionBase{
		
		String result = null;
		
		result = OutServerUtil.call(request, method, url); 
		
		return result;
	}
	
	public String call( String url, EnumAPIMethod method, JSONObject param) throws ExceptionBase{
		
		String result = null;
		
		result = OutServerUtil.call(request, method, url, param); 
		
		return result;
	}
	
	public String callWithHeader( String url, EnumAPIMethod method, JSONObject param, HttpHeaders headers) throws ExceptionBase{
		
		String result = null;
		
		result = OutServerUtil.callWithHeader(request, method, url, param, headers); 
		
		return result;
	}
	

	
}
