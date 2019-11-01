package com.chunlab.app.utils.json;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.chunlab.app.system.exception.ExceptionBase;
import com.chunlab.app.utils.enums.EnumExceptionObject;
import com.chunlab.app.utils.enums.EnumSortType;
import com.chunlab.app.utils.string.enums.EnumExceptionString;

/**
 * JSON 데이터 처리 유틸
 * 
 * @package : com.chunlab.app.utils.json
 * @file    : JSONUtil.java
 * @author  : seungjin.han
 * @Date    : 2018. 11. 7.
 * @version : 0.1
 */
public class JSONUtil {  

	/**
	 * JSON 배열을 List로 변환
	 * @param jsonArr
	 * @return
	 * @throws ExceptionBase 
	 */
	public static List<Object> jsonArryToList( JSONArray jsonArr) throws ExceptionBase{
	
		if( jsonArr == null) {
			
			throw new ExceptionBase( EnumExceptionObject.OBJECT_NULL);
		}
		
		List<Object> result = new ArrayList<Object>();
		
		for (int i = 0; i < jsonArr.length(); i++) {
			
			result.add( jsonArr.get(i));
		}
		
		return result;
	}
	
	/**
	 * JSONArray를 구분자로 나누는 스트링을 생성
	 * 
	 * @param list
	 * @param separator
	 * @return
	 * @throws ExceptionBase 
	 * @throws ExceptionBase 
	 */
	public static String jsonToString ( JSONArray list, String separator) throws ExceptionBase, ExceptionBase {
		
		if ( list == null){
			
			throw new ExceptionBase( EnumExceptionObject.OBJECT_NULL);
		}
		
		if( separator == null ) {
			
			throw new ExceptionBase( EnumExceptionString.STRING_NULL);
		}
		
		StringBuilder result = new StringBuilder();
		
		for (int i = 0; i < list.length(); i++){
			
			result = result.append(list.get(i));
			if ( i < list.length() - 1){
				
				result = result.append(separator);
			}
		}
		
		return result.toString();
	}
	
	
	/**
	 * JSON을 맵으로 바꾸기
	 * @param jsonObj
	 * @return
	 * @throws ExceptionBase 
	 */
	public static Map<String, String> JsonObjectToMap( JSONObject jsonObj) throws ExceptionBase{
	
		if( jsonObj == null) {
			
			throw new ExceptionBase( EnumExceptionObject.OBJECT_NULL);
		}
		
		Set<String> keys = jsonObj.keySet();
		Map<String, String>  result = new HashMap<String, String>();
		
		for (String key : keys) {
			
			String val = jsonObj.getString(key);
			result.put(key, val);
		}
		
		return result;
	}
	
	/**
	 * 
	  * @Method Name : JsonObjectToMap
	  * @작성일 : 2019. 10. 8.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 :
	  * @param jsonStr
	  * @return
	  * @throws ExceptionBase
	 */
	public static Map<String, String> JsonObjectToMap( String jsonStr) throws ExceptionBase{
		
		if( jsonStr == null) {
			
			throw new ExceptionBase( EnumExceptionObject.OBJECT_NULL);
		}
		
		JSONObject jsonObj = new JSONObject(jsonStr);
		
		Set<String> keys = jsonObj.keySet();
		Map<String, String>  result = new HashMap<String, String>();
		
		for (String key : keys) {
			
			String val = jsonObj.getString(key);
			result.put(key, val);
		}
		
		return result;
	}
	
	/**
	 * Map을 Json으로 변환
	 * 
	 * @param answer
	 * @return
	 * @throws ExceptionBase 
	 */
	public static JSONObject mapToJSONObject ( Map<String, String> answer) throws ExceptionBase {

		if( answer == null) {
			
			throw new ExceptionBase( EnumExceptionObject.OBJECT_NULL);
		}
		
		Set<String> keys = answer.keySet();
		JSONObject  result = new JSONObject();
		
		for (String key : keys) {
			
			String val = answer.get(key);
			
			result.put(key, val);
		}
		
		return result;
	}

	/**
	 * JSON 데이터 내에 Double 데이터 정렬
	 * 
	 * @param data
	 * @param value
	 * @param sortType
	 * @return
	 * @throws ExceptionBase 
	 * @throws ExceptionBase 
	 */
	public static JSONArray sortJSONArrayByDouble ( JSONArray data, final String value, final EnumSortType sortType) throws ExceptionBase, ExceptionBase {

		if( data == null) {
			
			throw new ExceptionBase( EnumExceptionObject.OBJECT_NULL);
		}
		
		if( value == null ) {
			
			throw new ExceptionBase( EnumExceptionString.STRING_NULL);
		}
		
		JSONArray result = new JSONArray();

		List<JSONObject> jsonValues = new ArrayList<JSONObject>();
		for (int i = 0; i < data.length(); i++){

			jsonValues.add(data.getJSONObject(i));
		}

		Collections.sort(jsonValues, new Comparator<JSONObject>() {

			@Override
			public int compare ( JSONObject a, JSONObject b) {

				Double valA = null;
				Double valB = null;

				try{
					valA = a.getDouble(value);
					valB = b.getDouble(value);
				}
				catch (JSONException e){}

				if( sortType == EnumSortType.DESC) {
				    
				    return valB.compareTo(valA);
				}
				else {
				    
				    return valA.compareTo(valB);
				}
			}
		});

		for (int i = 0; i < data.length(); i++){

			result.put(jsonValues.get(i));
		}

		return result;
	}
}

