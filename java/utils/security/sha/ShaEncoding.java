package com.chunlab.app.utils.security.sha;


public class ShaEncoding {

	private final static int SHA512 = 512;
	private static SHACodeEncoding shaEncode = new SHACodeEncoding(SHA512);
	
	public static String sha512Encoding ( String message) {
	    
		return shaEncode.encode(message);
	}
	
}
