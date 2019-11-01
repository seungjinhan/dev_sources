package com.chunlab.app.utils.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 
 * @package : com.chunlab.smilebiome.security
 * @file : PasswordEncoding.java
 * @author : seungjin.han
 * @Date : 2017. 9. 19.
 * @version : 0.1
 */
class PasswordEncoding implements PasswordEncoder {

	private PasswordEncoder passwordEncoder = null;

	protected PasswordEncoding() {
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	protected PasswordEncoding(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public String encode ( CharSequence arg0) {
		return this.passwordEncoder.encode(arg0);
	}

	@Override
	public boolean matches ( CharSequence arg0, String arg1) {
		return this.passwordEncoder.matches(arg0, arg1);
	}

}
