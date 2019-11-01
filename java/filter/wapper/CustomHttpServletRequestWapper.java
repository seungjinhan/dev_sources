package com.chunlab.app.system.filter.wapper;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chunlab.app.system.enums.EnumExceptionOthers;
import com.chunlab.app.system.exception.ExceptionBase;
import com.chunlab.app.utils.http.HttpUtil;

/**
 * 
  * @FileName : CustomHttpServletRequestWapper.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public class CustomHttpServletRequestWapper extends HttpServletRequestWrapper {

	private Map<String, String[]> params = new HashMap<String, String[]>();
	
	public CustomHttpServletRequestWapper(HttpServletRequest request) throws ExceptionBase {
		super(request);
		
		this.params.putAll( request.getParameterMap());
		
		// Body String을 가져옴
		String jsonBody = HttpUtil.getBodyDataByRequest(request);

		if( jsonBody  == null) {
			
			return;
		}
		
		try {

			JSONObject obj = new JSONObject(jsonBody);
			Set<String> keys = obj.keySet();
			for (String key : keys) {
				
				setParameter(key, ""+obj.get(key));
			}
		} catch (Exception e) {
			
			throw new ExceptionBase( EnumExceptionOthers.JSON_ERROR, jsonBody);
		} 

	}

	@Override
	public String getParameter(String name) {
		
		String[] paramArr = getParameterValues(name);
		
		if( paramArr != null && paramArr.length > 0) {
			return paramArr[0];
		}
		else {
			return null;
		}
	}
	
	@Override
	public Map<String, String[]> getParameterMap() {
		return Collections.unmodifiableMap( params);
	}
	
	@Override
	public Enumeration<String> getParameterNames() {
		return Collections.enumeration( params.keySet());
	}
	
	@Override
	public String[] getParameterValues(String name) {
		
		String[] result = null;
		String[] value = params.get(name);
		
		if( value != null) {
			
			result = new String[ value.length];
			System.arraycopy(value, 0, result, 0, value.length);
		}
		
		return result;
	}
	
	public void setParameter( String name, String value) {
		
		String[] param = {value};
		params.put(name, param);
	}
	
	public void setParameter( String name, String[] values) {
		params.put(name, values);
	}

	
//	
//
//	@Override 
//	public String getParameter(String name) {
//		
//		String value = super.getParameter(name);
//		
//		if(value == null) {
//			value = ""; 
//		}
//		return value.replaceAll("[<>]", ""); 
//	}
}
