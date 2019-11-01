package com.chunlab.app.utils.security;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * 
  * @FileName : SHACodeEncoding.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
class SHACodeEncoding implements PasswordEncoder {

	private ShaPasswordEncoder passwordEncoder;
	private Object salt = null;

	public SHACodeEncoding() {
		this.passwordEncoder = new ShaPasswordEncoder();
	}

	public SHACodeEncoding(int sha) {
		this.passwordEncoder = new ShaPasswordEncoder(sha);
		this.passwordEncoder.setEncodeHashAsBase64(true);
	}

	public void setSalt ( Object salt) {
		this.salt = salt;
	}

	@Override
	public String encode ( CharSequence arg0) {
		return this.passwordEncoder.encodePassword(arg0.toString(), salt);
	}

	@Override
	public boolean matches ( CharSequence arg0, String arg1) {
		return this.passwordEncoder.isPasswordValid(arg1, arg0.toString(), salt);
	}

}
