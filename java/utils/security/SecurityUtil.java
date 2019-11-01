package com.chunlab.app.utils.security;


/**
 * 암호화 모듈
 * 
 * @package : com.chunlab.app.utils.security
 * @file    : SecurityUtil.java
 * @author  : seungjin.han
 * @Date    : 2018. 11. 6.
 * @version : 0.1
 */
public class SecurityUtil {

	private final static int SHA512 = 512;
	private static SHACodeEncoding shaEncode = new SHACodeEncoding(SHA512);

	/**
	 * SHA512암호화
	 * 
	 * @param message
	 * @return
	 */
	public static String sha512Encoding ( String message) {
	    
		return shaEncode.encode(message);
	}


}
