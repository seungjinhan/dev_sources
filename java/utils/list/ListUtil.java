package com.chunlab.app.utils.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.json.JSONArray;

import com.chunlab.app.system.exception.ExceptionBase;
import com.chunlab.app.utils.enums.EnumExceptionObject;
import com.chunlab.app.utils.string.enums.EnumExceptionString;


/**
 * List처리 유틸리티
 * 
 * @package : com.chunlab.app.utils.list
 * @file    : ListUtil.java
 * @author  : seungjin.han
 * @Date    : 2018. 11. 7.
 * @version : 0.1
 */
public class ListUtil {

	
	/**
	 * 앞리스트에서 두번째 리스트를 빼기 (중복된 항목을 제거)
	 * 
	 * @param preList
	 * @param postList
	 * @return
	 * @throws ExceptionBase 
	 */
	public static List<String> subtractList ( List<String> preList, List<String> postList) throws ExceptionBase{
		
		if( preList == null || postList == null) {
			
			throw new ExceptionBase( EnumExceptionObject.OBJECT_NULL);
		}
		
		List<String> result = new ArrayList<String>();

		boolean check = false;
		for (String data : preList) {
			
			check = false;
			for (String data2 : postList) {
				
				if( data.equals(data2)) {
					
					check = true;
					continue;
				}
			}
			if( check == false) {
				
				result.add(data);
			}
		}
		
		return result;
	}
	
	/**
	 * 앞리스트에서 뒤에 리스트 데이터 제거
	 * 
	 * @param preList
	 * @param postList
	 * @return
	 * @throws ExceptionBase 
	 */
	public static List<Long> subtractLongList( List<Long> preList, List<Long> postList) throws ExceptionBase{
		
		if( preList == null || postList == null) {
			
			throw new ExceptionBase( EnumExceptionObject.OBJECT_NULL);
		}
		
		List<Long> result = new ArrayList<Long>();
		
		boolean check = false;
		for (Long data : preList) {
			
			check = false;
			for (Long data2 : postList) {
				
				if( data == data2) {
					
					check = true;
					continue;
				}
			}
			if( check == false) {
				
				result.add(data);
			}
		}
		
		return result;
	}
	
	
	/**
	 * 
	 * Enumeraton을 List로 변환하기
	 * 
	 * @param list
	 * @return
	 * @throws ExceptionBase 
	 */
	public static List<Object> enumerationToList ( Enumeration<String> list) throws ExceptionBase {

		if( list == null) {
			
			throw new ExceptionBase( EnumExceptionObject.OBJECT_NULL);
		}
		
		List<Object> result = new ArrayList<Object>();

		while (list.hasMoreElements()){

			result.add(list.nextElement());
		}
		
		return result;
	}

	/**
	 * 리스트를 구분자로 연결한 스트링으로 반환
	 * 
	 * @param list
	 * @param seperator
	 * @return
	 * @throws ExceptionBase 
	 * @throws ExceptionBase 
	 */
	public static String listToString ( List<String> list, String seperator) throws ExceptionBase, ExceptionBase {

		if( list == null) {
			
			throw new ExceptionBase( EnumExceptionObject.OBJECT_NULL);
		}
		
		if( seperator == null) {
			
			throw new ExceptionBase( EnumExceptionString.STRING_NULL);
		}
		
		StringBuilder sb = new StringBuilder();
		int index = 1;
		for (String kitNo : list){

			sb.append(kitNo);
			if ( index < list.size()){

				sb.append(seperator);
			}
			index = index + 1;
		}

		return sb.toString();
	}
	
	/**
	 * Long 타입의 리스트를 스트링으로 변환
	 * 
	 * @param list
	 * @param seperator
	 * @return
	 * @throws ExceptionBase 
	 * @throws ExceptionBase 
	 */
	public static String listLongToString ( List<Long> list, String seperator) throws ExceptionBase, ExceptionBase {
		
		if( list == null) {
			
			throw new ExceptionBase( EnumExceptionObject.OBJECT_NULL);
		}
		
		if( seperator == null) {
			
			throw new ExceptionBase( EnumExceptionString.STRING_NULL);
		}
		
		StringBuilder sb = new StringBuilder();
		int index = 1;
		for ( Long kitNo : list){
			
			sb.append(kitNo);
			if ( index < list.size()){
				
				sb.append(seperator);
			}
			index = index + 1;
		}
		
		return sb.toString();
	}

	/**
	 * 
	 * @param data
	 * @return
	 * @throws ExceptionBase
	 */
	public static <T> List<T> iterableToList ( Iterable<T> data) throws ExceptionBase {

		if( data == null) {
			
			throw new ExceptionBase( EnumExceptionObject.OBJECT_NULL);
		}
		
		List<T> result = new ArrayList<T>();

		for (T t : data){

			result.add(t);
		}

		return result;
	}

	/**
	 * Set 리스트를 List 로 변환하여 반환
	 * 
	 * @param set
	 * @return
	 * @throws ExceptionBase 
	 */
	public static List<String> setToList ( Set<String> set) throws ExceptionBase {

		if( set == null) {
			
			throw new ExceptionBase( EnumExceptionObject.OBJECT_NULL);
		}
		
		List<String> result = new ArrayList<String>();

		for (String s : set){

			result.add(s);
		}

		return result;
	}

	/**
	 * 맵 키를 리스트 형태로 반환
	 * 
	 * @param map
	 * @return
	 * @throws ExceptionBase 
	 */
	@SuppressWarnings("unchecked")
	public static List<String> mapToList ( @SuppressWarnings("rawtypes") Map map) throws ExceptionBase {

		if( map == null) {
			
			throw new ExceptionBase( EnumExceptionObject.OBJECT_NULL);
		}
		
		return setToList(map.keySet());
	}

	/**
	 * List를 JSONArray 로 변환
	 * @param list
	 * @return
	 * @throws ExceptionBase 
	 */
	public static JSONArray listToJSONArray( List<Object> list) throws ExceptionBase {
		
		if( list == null) {
			
			throw new ExceptionBase( EnumExceptionObject.OBJECT_NULL);
		}
		
		JSONArray result = new JSONArray();
		
		for (int i = 0; i < list.size(); i++) {
			
			result.put( list.get(i));
		}
		
		return result;
	}
	
	/**
	 * String을 JSONArray로 변환
	 */
	public static JSONArray convertJSONArrayFromString ( String str, String del) {

		JSONArray result = new JSONArray();

		StringTokenizer st = new StringTokenizer(str, del);
		while (st.hasMoreTokens()){
			
			result.put(st.nextToken());
		}
		return result;
	}

	/**
	 * 리스트 내림차순 정렬
	 * @param data
	 * @return
	 * @throws ExceptionBase 
	 */
	public static <T> List<T> sortDecending ( List<T> data) throws ExceptionBase {

		if( data == null) {
			
			throw new ExceptionBase( EnumExceptionObject.OBJECT_NULL);
		}
		
		Collections.sort(data, new Descending<T>());
		return data;
	}

	/**
	 * 리스트 오름차순 정렬
	 * 
	 * @param data
	 * @return
	 * @throws ExceptionBase 
	 */
	public static <T> List<T> sortAscecding ( List<T> data) throws ExceptionBase {

		if( data == null) {
			
			throw new ExceptionBase( EnumExceptionObject.OBJECT_NULL);
		}
		
		Collections.sort(data, new Ascending<T>());
		return data;
	}

	/**
	 * 내림차순 내부 클래스
	 * 
	 * @package : com.chunlab.system.util
	 * @file    : ListUtil.java
	 * @author  : seungjin.han
	 * @Date    : 2018. 8. 20.
	 * @version : 0.1
	 * @param <T>
	 */
	static class Descending<T> implements Comparator<T> {

		@Override
		public int compare ( T o1, T o2) {

			if ( o1 instanceof String){

				return ((String) o2).compareTo((String) o1);
			}
			else if ( o1 instanceof Integer){

				return ((Integer) o2).compareTo((Integer) o1);
			}
			else if ( o1 instanceof Float){

				return ((Float) o2).compareTo((Float) o1);
			}
			else if ( o1 instanceof Double){

				return ((Double) o2).compareTo((Double) o1);
			}
			else{
				return 0;
			}

		}

	}

	/**
	 * 오름차순 처리 내부 클래스
	 * 
	 * @package : com.chunlab.system.util
	 * @file    : ListUtil.java
	 * @author  : seungjin.han
	 * @Date    : 2018. 8. 20.
	 * @version : 0.1
	 * @param <T>
	 */
	static class Ascending<T> implements Comparator<T> {

		@Override
		public int compare ( T o2, T o1) {

			if ( o1 instanceof String){

				return ((String) o2).compareTo((String) o1);
			}
			else if ( o1 instanceof Integer){

				return ((Integer) o2).compareTo((Integer) o1);
			}
			else if ( o1 instanceof Float){

				return ((Float) o2).compareTo((Float) o1);
			}
			else if ( o1 instanceof Double){

				return ((Double) o2).compareTo((Double) o1);
			}
			else{
				return 0;
			}

		}

	}
}
