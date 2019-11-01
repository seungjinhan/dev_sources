package com.chunlab.app.utils.test;

import com.chunlab.app.auth_code.EnumCodeType;
import com.chunlab.app.utils.others.ElseUtil;


/**
 * 
  * @FileName : UtilTest.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public class UtilTest {

	public static void main(String[] args) {
		
		String s = ElseUtil.getRandomCode(EnumCodeType.STRING, 10);
		System.out.println(s);
		s = ElseUtil.getRandomCode(EnumCodeType.DIGIT, 10);
		System.out.println(s);
		s = ElseUtil.getRandomCode(EnumCodeType.DIGIT_AND_STRING, 10);
		System.out.println(s);

	}

}
