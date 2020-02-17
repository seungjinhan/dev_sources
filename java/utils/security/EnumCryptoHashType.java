package com.chunlab.app.utils.security;

public enum EnumCryptoHashType {

	MD5("MD5"), 
	SHA1("SHA-1"), 
	SHA256("SHA-256"),
	SHA512("SHA-512");
	
	private String type;
	
	EnumCryptoHashType(String type){
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	
}
