package com.chunlab.app.utils.string;

import com.chunlab.app.system.ApplicationConstans;
import com.chunlab.app.system.exception.ExceptionBase;
import com.chunlab.app.utils.string.enums.EnumSringSizeCompare;


/**
 * 
  * @FileName : StringSizeUtil.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public class StringSizeUtil {

	/**
	 * 이메일 체크
	 * 
	 * @param email
	 * @return
	 * @throws ExceptionBaseNull
	 */
	public static EnumSringSizeCompare checkEmailSize ( String email) throws ExceptionBase {

		return StringUtil.checkStringSizeCompare(email, ApplicationConstans.USER_EMAIL_MAX_SIZE, EnumSringSizeCompare.BIG);
	}

	/**
	 * 비밀번호 사이즈 체크
	 * 
	 * @param email
	 * @return
	 * @throws ExceptionBaseNull
	 */
	public static EnumSringSizeCompare checkPasswordSize ( String email) throws ExceptionBase {

		return StringUtil.checkStringSizeCompare(email, ApplicationConstans.USER_PASSWORD_MAX_SIZE, EnumSringSizeCompare.BIG);
	}

	/**
	 * 스트링 사이트 체크
	 * 
	 * @param string
	 * @param size
	 * @return
	 * @throws ExceptionBaseNull
	 */
	public static EnumSringSizeCompare checkStringSize ( String string, Integer size) throws ExceptionBase {

		return StringUtil.checkStringSizeCompare(string, size, EnumSringSizeCompare.BIG);
	}
}
