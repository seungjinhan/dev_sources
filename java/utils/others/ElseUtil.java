package com.chunlab.app.utils.others;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.chunlab.app.system.json.JsonList;
import com.chunlab.app.system.json.JsonMap;

import com.chunlab.app.auth_code.EnumCodeType;
import com.chunlab.app.system.SystemPropertiesInfo;
import com.chunlab.app.system.enums.EnumExceptionOthers;
import com.chunlab.app.system.exception.ExceptionBase;
import com.chunlab.app.utils.security.SecurityUtil;


/**
 * 
  * @FileName : ElseUtil.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public class ElseUtil {
	
	/**
	 * 30 자 이내 랜덤 문자 생성하기
	 * 
	 * @param email
	 * @return
	 * @throws ExceptionOthers 
	 * @throws ExceptionCustomRun 
	 */
	public static String makeRandomCharWithin30Digit ( int digit) throws ExceptionBase {

		if( digit > 31) {
			
			throw new ExceptionBase(EnumExceptionOthers.DIGIT_OVER , "30자가 초과됨");
		}
		
		String result = "";
		
		result = SecurityUtil.sha512Encoding( ""+ System.nanoTime());
		result = result.substring(0, digit);

		return result;
	}

	/**
	 * 사용자 로그인 유지 키 생성
	 * 
	 * @param email
	 * @return
	 */
	public static String getLoginKeepKey ( String email) {

		String result = "";

		result = SecurityUtil.sha512Encoding(email + System.nanoTime());
		result = result.substring(0, 30);

		return result;
	}

	/**
	 * 시리얼 생성
	 * 
	 * @return
	 */
	public static String getSerial () {

		char ch1 = (char) ((Math.random() * 26) + 65);
		String str = new String("" + ch1 + System.nanoTime() + System.nanoTime());
		
		str = str.substring(0, 4) + "-" 
				+ (char) ((Math.random() * 26) + 65) + str.substring(5, 8) + "-" 
				+ (char) ((Math.random() * 26) + 65) + str.substring(9,	12) + "-" 
				+ (char) ((Math.random() * 26) + 65) + str.substring(13, 16);
		return str;
	}

	/**
	 * 자리수를 받아서 랜덤문자를 생성
	 * @param size
	 * @return
	 */
	public static String getRandomCode ( EnumCodeType codeType, int size) {
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < size; i++) {

			double rd = Math.random();
			
			String ch = "";
			
			if( codeType == EnumCodeType.DIGIT_AND_STRING ) {
				
				if( rd*10 > 5) {
					
					ch = ""+(char) ((rd* 26) + 65);
				}
				else {
					ch = (""+rd*10).substring(0, 1);
				}
			}
			else if( codeType == EnumCodeType.STRING) {
				
				ch = ""+(char) ((rd* 26) + 65);
			}
			else {
				
				ch = (""+rd*10).substring(0, 1);
			}
			sb.append(ch);
		}
		
		/*
		String str = new String( "" + ch + System.nanoTime());
		str = (char) ((Math.random() * 26) + 65) +
				(char) ((Math.random() * 26) + 65) +
					(char) ((Math.random() * 26) + 65) +
						(char) ((Math.random() * 26) + 65) +
							str.substring(0, 4) + 
								(char) ((Math.random() * 26) + 65) + 
									str.substring(5, 8) +
										(char) ((Math.random() * 26) + 65) + 
											str.substring(9,12) + 
												(char) ((Math.random() * 26) + 65) +
													(char) ((Math.random() * 26) + 65) +
														(char) ((Math.random() * 26) + 65);*/
		return sb.toString();
	}
	
	
	public static void logConsole( Object data) {
		
		if( SystemPropertiesInfo.isServer == false) {
			
			LogUtil.logDebug(data.toString());
		}
	}
	
	public static void logConsoleWithBeforeAfter( Object data) {
		
		if(  SystemPropertiesInfo.isServer == false) {
			LogUtil.logDebug("---------------------------------------------------");
			LogUtil.logDebug(data.toString());
			LogUtil.logDebug("---------------------------------------------------");
		}
	}
	
	public static void logConsoleWithBeforeAfter( String title, Object data) {
		
		if( SystemPropertiesInfo.isServer == false ) {
			LogUtil.logDebug("---------------------------------------------------");
			LogUtil.logDebug("["+ title+"]");
			LogUtil.logDebug(data.toString());
			LogUtil.logDebug("---------------------------------------------------");
		}
	}
	
	/**
	 * 상태값 히스토리를 추가 저장
	 * 
	 * @param nowData
	 * @param preStatus
	 * @param postStatus
	 * @param email
	 * @param date
	 * @return
	 */
	public static String addStatusHistory( String nowData, String preStatus, String postStatus, String email, String date) {
		
		
		JsonList logList = null;
		
		if( nowData == null || nowData.trim().length() == 0) {
			
			logList = new JsonList();
		}
		else {
			
			logList = new JsonList( nowData);
		}
		
		JsonMap log = new JsonMap();
		log.put("email", email);
		log.put("date", date);
		log.put("pre_status", preStatus);
		log.put("post_status", postStatus);
		
		logList.put( log);
		
		return logList.toString();
	}
	
	/**
	 * PROGRAM_STOP (프로그램 중지,승인거부)
	 * 상태값 히스토리를 추가 저장
	 * 
	 * @param nowData
	 * @param preStatus
	 * @param postStatus
	 * @param email
	 * @param date
	 * @return
	 */
	public static String addStopStatusHistory( String nowData, String preStatus, String postStatus, String email, String date, String reason, String comment) {
		
		
		JsonList logList = null;
		
		if( nowData == null || nowData.trim().length() == 0) {
			
			logList = new JsonList();
		}
		else {
			
			logList = new JsonList( nowData);
		}
		
		JsonMap log = new JsonMap();
		log.put("email", email);
		log.put("date", date);
		log.put("pre_status", preStatus);
		log.put("post_status", postStatus);
		log.put("reason", reason);
		log.put("comment", comment);
		
		logList.put( log);
		
		return logList.toString();
	}

	/**
	 * 랜덤 숫자 생성하기  5 -> 0~5
	 * @param range
	 * @return
	 */
	public static int getRandomNumber( int range) {
		
 		List<Integer> temp = Arrays.asList( new Integer[]{1,2,3,4,5,6,7,8,9});
		Collections.shuffle( temp);		
		return (int) ((System.nanoTime()-( temp.get(0)+1) ) % (range+1));
	}
	
	
	public static void main( String[] args) {
//		System.out.println(getRandomNumber(9));
//		System.out.println(getRandomNumber(9));
//		System.out.println(getRandomNumber(9));
//		System.out.println(getRandomNumber(9));
//		System.out.println(getRandomNumber(9));
//		System.out.println(getRandomNumber(9));
		
		System.out.println( getRandomCode( EnumCodeType.DIGIT_AND_STRING, 8));
	}
}
