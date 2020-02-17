package com.chunlab.app.utils.database;

import com.chunlab.app.system.json.JsonList;
import com.chunlab.app.system.json.JsonMap;

import com.chunlab.app.system.exception.ExceptionBase;


/**
 * 
  * @FileName : SQLUtil.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public class SQLUtil {

	/**
	 * Where 조건 In 에  ''로 감싼 String생성하기
	 * @param strArr
	 * @return
	 * @throws ExceptionBase
	 * @throws ExceptionBase
	 */
	public static String makeWhereInString( String stringList){
		
		stringList = "'"+stringList.replaceAll(" ", "").replaceAll(",", "','")+"'";
		
		return stringList;
	}
	
	/**
	 * DB에 상태값 변경에 따른 히스토리 데이터 추가하기
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
	
}
