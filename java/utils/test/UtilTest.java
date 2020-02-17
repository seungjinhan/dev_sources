package com.chunlab.app.utils.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;


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
		
//		String s = ElseUtil.getRandomCode(EnumCodeType.STRING, 10);
//		System.out.println(s);
//		s = ElseUtil.getRandomCode(EnumCodeType.DIGIT, 10);
//		System.out.println(s);
//		s = ElseUtil.getRandomCode(EnumCodeType.DIGIT_AND_STRING, 10);
//		System.out.println(s);

		List<String> list3 = CollectionUtils.arrayToList( new String[]{"1","2"});
		
		List<String> list = new ArrayList<String>();
		List<String> list2 = null;
		list.add("test1");
		list.add("test2");
		list.add("test3");
		list.add("test4");
		list.add("test5");
//		
		List<String> list4 = new ArrayList<String>();
		list4.add("test0");
		list4.add("test2");
		list4.add("test1");
		list4.add("test4");
		
		System.out.println( CollectionUtils.isEmpty( list));
		System.out.println( CollectionUtils.isEmpty( list2));
		System.out.println( CollectionUtils.isEmpty( new ArrayList<String>()));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put( "1" , "1");
		Map<String, String> map2 = new HashMap<String, String>();
		Map<String, String> map3 = null;
		System.out.println( CollectionUtils.isEmpty( map));
		System.out.println( CollectionUtils.isEmpty( map2));
		System.out.println( CollectionUtils.isEmpty( map3));
		
//		CollectionUtils.mergeArrayIntoCollection( list2, list);
//		System.out.println(">"+ list);
//	
		System.out.println( CollectionUtils.contains(list.iterator(), "test1"));
		System.out.println( CollectionUtils.containsInstance(list , "test1"));
		System.out.println( CollectionUtils.containsAny(list , list4));
		System.out.println( CollectionUtils.findFirstMatch( list , list4));
		
		System.out.println( CollectionUtils.findValueOfType(list, List.class));
		
		System.out.println( CollectionUtils.hasUniqueObject(list));
		
		System.out.println( CollectionUtils.findCommonElementType(list));
		System.out.println( CollectionUtils.lastElement(list));
	}

}
