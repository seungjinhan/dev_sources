package com.chunlab.app.utils.others;

/**
 * 
  * @FileName : MathUtil.java
  * @Project : app_server
  * @Date : 2019. 10. 15. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public class MathUtil{
	
	/**
	 * 
	  * @Method Name : getIntgerDigit
	  * @작성일 : 2019. 10. 15.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 : 정수 자리수
	  * @param num
	  * @return
	 */
	public static int getIntgerDigit( Integer num) {
		
		return (int)(Math.log10(num)+1);
	}
}