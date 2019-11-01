package com.chunlab.app.utils.network;

import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;


/**
 * 
  * @FileName : NetworkUtil.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public class NetworkUtil {

	public static boolean isAllowIP ( String allowIpList, String ip, String delim) {

		StringTokenizer st = new StringTokenizer( allowIpList, delim);
		
		while (st.hasMoreTokens()){
			
			String _ip = st.nextToken();
			
			if( _ip.equals( ip)) {
				
				return true;
			}
		}

		return false;
	}
	public static boolean isAllowIP ( HttpServletRequest request , String allowIpList,  String delim) {
		
		String ip = getRemoteIP(request);
		
		return isAllowIP(allowIpList, ip, delim);
	}
	
	public static String getRemoteIP ( HttpServletRequest request) {


		String ip = request.getHeader("X-Forwarded-For");

		if ( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){

			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if ( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = "unknown";
		}
		else{

			if ( ip.indexOf(",") > -1){ // ,가 있다면
				StringTokenizer st = new StringTokenizer(ip, ",");
				if ( st.countTokens() > 0){
					ip = st.nextToken(); // 가장 왼쪽의 값(Client IP)을 가져온다.
				}
			}
		}

		return ip;
	}
	
	public static String getParameterString( HttpServletRequest request) {
		
		StringBuilder result = new StringBuilder();
		
		Enumeration<String> kkk2 = request.getParameterNames();
		
		boolean isStart = false;
		while (kkk2.hasMoreElements()) {
			String string = (String) kkk2.nextElement();
			if(isStart == true) {
				result.append(",");
			}
			result.append(string).append(":").append(request.getParameter(string));
			isStart = true;
		}
		
		return result.toString();
	}

	public static String getQueryAndParameterString( HttpServletRequest request) {
		
		StringBuilder result = new StringBuilder();
		
		String getQuery = request.getQueryString();
		if( getQuery != null && "".equals( getQuery)==false) {
			
			result.append(getQuery);
		}
		
		String postQuery = getParameterString(request);
		if( postQuery != null && "".equals( postQuery) == false ) {
			
			if( getQuery != null && "".equals( getQuery)==false) {
				
				result.append("|");
			}
			result.append(postQuery);
		}
		return result.toString();
	}
	
}
