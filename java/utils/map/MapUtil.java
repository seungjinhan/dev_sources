package com.chunlab.app.utils.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapUtil {

	/**
	  * @Method Name : cloneHashMap
	  * @작성일 : 2020. 1. 6.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 : HashMap 복사하기
	  * @param <T>
	  * @param <R>
	  * @param data
	  * @return
	 */
	public static <T, R> Map<T, R> cloneHashMap( Map<T, R> data ){
		
		Map<T, R> result = new HashMap<T, R>();
		Set<T> keys = data.keySet();
		
		for (T key : keys) {
			
			result.put(key,  data.get(key) );
		}
		return result;
	}
	
	public static void main( String[] args) {
		
		Map<String, Object> t1 = new HashMap<String, Object>();
		t1.put("1", "1");
		t1.put("2", "2");
		t1.put("3", "3");
		t1.put("4", "4");
		
		Map<String, Object> t3 = t1;
		
		System.out.println(t1==t3);
		
		Map<String, Object> t2 = cloneHashMap(t1);
		
		System.out.println(t2.toString());
		t2.put("1", "TEST");
		t2.put("5", "TEST1");
		System.out.println(t1==t2);
		System.out.println(t1.toString());
		System.out.println(t2.toString());
		
	}
}
