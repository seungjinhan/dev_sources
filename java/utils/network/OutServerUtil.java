package com.chunlab.app.utils.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.chunlab.admin.system.json.JsonMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.chunlab.app.system.exception.ExceptionBase;
import com.chunlab.app.utils.network.enums.EnumAPIMethod;
import com.chunlab.app.utils.others.LogUtil;


/**
 * Api Server Util
 * @package : com.chunlab.system.util.api_server
 * @file    : ApiServerUtil.java
 * @author  : seungjin.han
 * @Date    : 2018. 11. 28.
 * @version : 0.1
 */
class OutServerUtil {

	
	/**
	 * API 요청
	 * @param request
	 * @param method
	 * @param url
	 * @param body
	 * @return
	 * @throws ExceptionBase 
	 * @throws IOException
	 * @throws ExceptionBase
	 */
	public static String call( HttpServletRequest request, EnumAPIMethod method, String url, JsonMap body){
		
		LogUtil.logInfo("#####################################################################");
		LogUtil.logInfo("URL : " + url);
		LogUtil.logInfo("METHOD : " + method);
		LogUtil.logInfo("BODY : " + body);
		LogUtil.logInfo("#####################################################################");
	
	    // REST API 호출
//	    try {
//		    	
		    List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		    converters.add(new FormHttpMessageConverter());
		    converters.add(new StringHttpMessageConverter() );
		    //converters.add( new MappingJackson2HttpMessageConverter());
		    
		    RestTemplate restTemplate = new RestTemplate();
		    restTemplate.setMessageConverters(converters);
		    String result = null;
		    
		    if( method == EnumAPIMethod.POST) {

			    // parameter 세팅
			    MultiValueMap<String, String> map = null;
			    
			    if( body != null) {
			    	
			    	map = new LinkedMultiValueMap<String, String>();
			    	
			    	Set<String> keys = body.keySet();
			    	for (String key : keys) {
			    		
			    		map.add( key, body.getString(key));
			    	}
			    }
			    
		    	result = restTemplate.postForObject( url, map, String.class);
		    }
		    else if( method == EnumAPIMethod.GET) {
		    	
		    	StringBuilder sb = new StringBuilder("?");
		    	if( body != null) {
			    	
			    	Set<String> keys = body.keySet();
			    	for (String key : keys) {
			    		
			    		sb.append(key).append("=").append(body.getString(key));
			    	}
			    }
			    
		    	result = restTemplate.getForObject(url + sb.toString(), String.class);
		    }
		    
			LogUtil.logDebug("######################  result  ###################################");
			LogUtil.logDebug(result);
			LogUtil.logDebug("#####################################################################");
			
		    return result;

//	    }	   
//	    catch( ResourceAccessException e) {
//	    	
//	    	throw new ExceptionBase( EnumExceptionNetwork.CONNECTION_REFUSED, url+"?"+body);
//	    	
//	    }
//	    catch( HttpServerErrorException | HttpClientErrorException e ) {
//
//	    	if( e.getRawStatusCode() == 404) {
//	    		
//	    		throw new ExceptionBase( EnumExceptionNetwork.WRONG_URL, url+"?"+body);
//	    	}
//	    	
//	    	else if( e.getRawStatusCode() == 400) {
//	    		
//	    		throw new ExceptionBase( EnumExceptionNetwork.WRONG_PARAMETER, url+"?"+body);
//	    	}
//	    	
//	    	else if( e.getRawStatusCode() == 500) {
//	    		
//	    		throw new ExceptionBase( EnumExceptionNetwork.HTTP_500, url+"?"+body);
//	    	}
//	    	
//	    	throw new ExceptionBase( EnumExceptionNetwork.HTTP_XXX, url+"?"+body + " : RESPONSE ERROR CODE : " + e.getRawStatusCode());
//	    }

	}
	
	/**
	 * API 요청
	 * @param request
	 * @param method
	 * @param url
	 * @param body
	 * @return
	 * @throws ExceptionBase 
	 * @throws IOException
	 * @throws ExceptionBase
	 */
	public static String callWithHeader( HttpServletRequest request, EnumAPIMethod method, String url, JsonMap body, HttpHeaders headers) throws ExceptionBase{
		
		String headerString = headers.toString();
		LogUtil.logInfo("#####################################################################");
		LogUtil.logInfo("URL : " + url);
		LogUtil.logInfo("METHOD : " + method);
		LogUtil.logInfo("HEADER : " + headerString);
		LogUtil.logInfo("#####################################################################");
	
	    // REST API 호출
//	    try {
		    	
		    List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		    converters.add(new FormHttpMessageConverter());
		    converters.add(new StringHttpMessageConverter() );
		    //converters.add( new MappingJackson2HttpMessageConverter());
		    
		    RestTemplate restTemplate = new RestTemplate();
		    restTemplate.setMessageConverters(converters);
		    String result = null;
		    
		    if( method == EnumAPIMethod.POST) {

			    // parameter 세팅
			    MultiValueMap<String, String> map = null;
			    
			    if( body != null) {
			    	
			    	map = new LinkedMultiValueMap<String, String>();
			    	
			    	Set<String> keys = body.keySet();
			    	for (String key : keys) {
			    		
			    		map.add( key, body.getString(key));
			    	}
			    	
			    	
			    }
			    
			    HttpEntity<MultiValueMap<String, String>> requestBody = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		    	result = restTemplate.postForObject( url, requestBody, String.class);
		    }
		    else if( method == EnumAPIMethod.GET) {
		    	
		    	StringBuilder sb = new StringBuilder("?");
		    	if( body != null) {
			    	
			    	Set<String> keys = body.keySet();
			    	for (String key : keys) {
			    		
			    		sb.append(key).append("=").append(body.getString(key));
			    	}
			    }
		    	HttpEntity<MultiValueMap<String, String>> requestBody = new HttpEntity<MultiValueMap<String,String>>(null, headers);// new HttpEntity<MultiValueMap<String, String>>(headers);
		    	result = restTemplate.getForObject(url + sb.toString(), String.class, requestBody);
		    }
		    
			LogUtil.logDebug("######################  result  ###################################");
			LogUtil.logDebug(result);
			LogUtil.logDebug("#####################################################################");
			
		    return result;

//	    }	   
//	    catch( ResourceAccessException e) {
//	    	
//	    	throw new ExceptionBase( EnumExceptionNetwork.CONNECTION_REFUSED, url+"?"+headerString);
//	    	
//	    }
//	    catch( HttpServerErrorException | HttpClientErrorException e ) {
//
//	    	if( e.getRawStatusCode() == 404) {
//	    		
//	    		throw new ExceptionBase( EnumExceptionNetwork.WRONG_URL, url+"?"+headerString);
//	    	}
//	    	
//	    	else if( e.getRawStatusCode() == 400) {
//	    		
//	    		throw new ExceptionBase( EnumExceptionNetwork.WRONG_PARAMETER, url+"?"+headerString);
//	    	}
//	    	
//	    	else if( e.getRawStatusCode() == 500) {
//	    		
//	    		throw new ExceptionBase( EnumExceptionNetwork.HTTP_500, url+"?"+headerString);
//	    	}
//	    	
//	    	throw new ExceptionBase( EnumExceptionNetwork.HTTP_XXX, url+"?"+headerString + " : RESPONSE ERROR CODE : " + e.getRawStatusCode());
//	    }

	}
	
	public static String call( HttpServletRequest request, EnumAPIMethod method, String url) throws ExceptionBase{

		return call(request, method, url, null);
	}
	
    
	
}
