package com.chunlab.app.utils.string;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;

import com.chunlab.app.system.json.JsonList;

import com.chunlab.app.system.enums.EnumYN;
import com.chunlab.app.system.exception.ExceptionBase;
import com.chunlab.app.utils.string.enums.EnumSringSizeCompare;
import com.chunlab.app.utils.string.enums.EnumExceptionString;


/**
 * 
  * @FileName : StringUtil.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public class StringUtil {

	/**
	 * 리스트를 구분자로 하나의 스트링으로 만들기
	 * 
	 * @param list
	 * @param separator
	 * @return
	 */
	public static <T> String makeStringFromList ( List<T> list, String separator) {
		
		if ( list == null){
			
			throw new NullPointerException();
		}
		
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < list.size(); i++){
			
			result = result.append(list.get(i));
			if ( i < list.size() - 1){
				
				result = result.append(separator);
			}
		}
		
		return result.toString();
	}
	
	/**
	 * SQL WHERE 조건에 들어갈 스트링 리스트생성 '로 감싸기
	 * @param list
	 * @param separator
	 * @return
	 */
	public static <T> String makeStringFromListForSQL ( List<T> list, String separator) {
		
		if ( list == null){
			
			throw new NullPointerException();
		}
		
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < list.size(); i++){
			
			result = result.append("'").append(list.get(i)).append("'");
			if ( i < list.size() - 1){
				
				result = result.append(separator);
			}
		}
		
		return result.toString();
	}
	
	
	public static <T> String makeStringFromListForSQL ( String whereArr, String separator) {
		
		if ( whereArr == null){
			
			throw new NullPointerException();
		}
		StringTokenizer st = new StringTokenizer(whereArr, ",");
		StringBuilder result = new StringBuilder();
		
		while (st.hasMoreElements()) {
			
			String object = (String) st.nextElement();
			result = result.append("'").append( object).append("'");
			
			if ( st.hasMoreElements()){
				
				result = result.append(separator);
			}
		}
		
		return result.toString();
	}
	
	/**
	 * String이 비어있는지 아닌지 확인
	 * 
	 * @param str
	 * 
	 * @retur: null 이거나 공백이면 true, 공백이 아니면 false
	 */
	public static boolean isEmpty ( String str) throws ExceptionBase {
		return str == null || str.length() == 0 ? true : false;
	}
	
	public static boolean isNotEmpty ( String str) throws ExceptionBase {
		return !isEmpty(str);
	}
	
	public static boolean isNull ( String str) throws ExceptionBase {
		return str == null;
	}
	
	public static boolean isNotNull ( String str) throws ExceptionBase {
		return !isNull(str);
	}

	/**
	 * 스트링을 비교
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 * @throws ExceptionBase
	 */
	public static boolean stringEquals ( String str1, String str2) throws ExceptionBase {

		if ( str1 == null || str2 == null){

			throw new ExceptionBase( EnumExceptionString.STRING_NULL);
		}

		return str1.equals(str2);
	}

	/**
	 * 스트링을 구분자로 나누기
	 * 
	 * @param str
	 * @param delim
	 * @return
	 * @throws ExceptionBase
	 */
	public static List<String> makeListFromString ( String str, String delim) throws ExceptionBase {

		if ( str == null){

			throw new ExceptionBase( EnumExceptionString.STRING_NULL);
		}
		
		if ( delim == null){

			throw new ExceptionBase( EnumExceptionString.STRING_NULL);
		}

		List<String> result = new ArrayList<String>();
		
		StringTokenizer st = new StringTokenizer(str, delim);
		
		while (st.hasMoreTokens()){
			
			result.add(st.nextToken());
		}
		
		return result;
	}

	/**
	 * 스트링을 long List로 변경
	 * @param str
	 * @param delim
	 * @return
	 * @throws ExceptionBase
	 */
	public static List<Long> makeListLongFromString ( String str, String delim) throws ExceptionBase {

		if ( str == null){

			throw new ExceptionBase( EnumExceptionString.STRING_NULL);
		}
		if ( delim == null){

			throw new ExceptionBase( EnumExceptionString.STRING_NULL);
		}

		List<Long> result = new ArrayList<Long>();
		StringTokenizer st = new StringTokenizer(str, delim);
		while (st.hasMoreTokens()){
			result.add( Long.parseLong(st.nextToken()));
		}
		return result;
	}
	
	/**
	 * 스트링의 사이즈를 기준에 맞는지 확인
	 * 
	 * @param str
	 * @param size
	 * @param standard
	 * @return
	 * @throws ExceptionBase
	 */
	public static EnumSringSizeCompare checkStringSizeCompare ( String str, int size, EnumSringSizeCompare standard) throws ExceptionBase {

		if ( str == null){

			throw new ExceptionBase( EnumExceptionString.STRING_NULL);
		}

		EnumSringSizeCompare result = 
				str.length() == size ? EnumSringSizeCompare.SAME 
						: (str.length() > size ? 
								EnumSringSizeCompare.BIG : EnumSringSizeCompare.SMALL);

		return result;
	}
	
	/**
	 * 데이터에 스트링 포함여부 확인
	 * 
	 * @param data
	 * @param compareString
	 * @return
	 * @throws ExceptionBase
	 */
	public static EnumYN checkStringContain(String data, String compareString) throws ExceptionBase {
		
		if( data == null || compareString == null) {
			
			throw new ExceptionBase( EnumExceptionString.STRING_NULL);
		}
		
		if( data.length() > data.replaceAll(compareString, "").length()) {
			
			return EnumYN.Y;
		}
		else {
			
			return EnumYN.N;
		}
	}
	
	/**
	 * String을 구분자로 끊어서 리스트로 반환
	 * 
	 * @param str
	 * @param del
	 * @return
	 * @throws ExceptionBase 
	 * @throws ExceptionBase 
	 */
	public static List<String> stringToList ( String str, String del) throws ExceptionBase {

		if( str == null || del == null ) {
			
			throw new ExceptionBase( EnumExceptionString.STRING_NULL);
		}
		
		List<String> result = new ArrayList<String>();

		StringTokenizer st = new StringTokenizer(str, del);
		while (st.hasMoreTokens()){
			result.add(st.nextToken());
		}
		return result;
	}
	

	/**
	 * String -> JsonList
	 * 
	 * @param str
	 * @param del
	 * @return
	 * @throws ExceptionBase 
	 */
	public static JsonList stringToJsonList ( String str, String del) throws ExceptionBase {

		if( str == null || del == null ) {
			
			throw new ExceptionBase( EnumExceptionString.STRING_NULL);
		}

		JsonList result = new JsonList();

		StringTokenizer st = new StringTokenizer(str, del);
		while (st.hasMoreTokens()){
			
			result.put(st.nextToken());
		}
		return result;
	}
	
	/**
	 * String에서 구분자 기준으로 특정 String을 제거함
	 * 
	 * @param stringArr
	 * @param removeStringList
	 * @param delim
	 * @return
	 * @throws ExceptionBaseNull 
	 */
	public static String removeListEqualsStringArr( String stringArr, String delim, List<String> removeStringList) throws ExceptionBase {
		
		if( stringArr == null || removeStringList == null || delim == null) {
			
			throw new ExceptionBase( EnumExceptionString.STRING_NULL);
		}
		
		String result = delim + stringArr + delim;
		
		for (Object removeStr : removeStringList) {
			
			result = result.replaceAll( delim +removeStr + delim , ",");
		}
		
		return removeBothEndChar(result);
	}
	
	/**
	 * 양쪽끝 문자 하나씩 제거
	 * @param txt
	 * @return
	 */
	public static String removeBothEndChar( String txt) {
		
		return txt.substring(1, txt.length()-1);
	}
	
	/**
	 * 콤마 구분한 스트링 만들기
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String makeStringWithComma( String str1, String str2 ) {
		
		String makeString = str1 + "," + str2 + ",";
		return makeString;
	}
	
	
}
