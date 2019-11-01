package com.chunlab.app.utils.list;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
  * @FileName : ListManager.java
  * @Project : app_server
  * @Date : 2019. 10. 28. 
  * @작성자 : deepplin
  * @변경이력 :
  * @프로그램 설명 : 리스트를 관리
 */
public class ListManager {

	/**
	 * 
	  * @Method Name : filterForSingle
	  * @작성일 : 2019. 10. 28.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 : 리스트에서 해당 조건의 한가지만 찾기
	  * @param <T>
	  * @param list
	  * @param t
	  * @return
	 */
	public static <T> T filterForSingle( List<T> list, Predicate<T> t) {
		
		for (T _t : list) {
			
			if(t.test(_t)) {
				return _t;
			}
		}
		
		return null;
	}
	
	/**
	 * 
	  * @Method Name : filterForMulti
	  * @작성일 : 2019. 10. 28.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 : 리스트에서 해당 조건의 여러개
	  * @param <T>
	  * @param list
	  * @param t
	  * @return
	 */
	public static <T> List<T> filterForMulti( List<T> list, Predicate<T> t){

		List<T> result = new ArrayList<T>();
		
		for (T _t : list) {
			
			if(t.test(_t)) {
				result.add(_t);
			}
		}
		return result;
	}
	
}
