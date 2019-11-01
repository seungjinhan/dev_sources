package com.chunlab.app.utils.others;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
  * @FileName : LogUtil.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public class LogUtil {

	private static Logger LOG = LoggerFactory.getLogger(LogUtil.class);
	
	public static void logDebug( Logger log, String msg) {
		log.debug(msg);
	}
	
	public static void logInfo( Logger log, String msg) {
		log.info( msg);
	}
	
	public static void logError( Logger log, String msg) {
		log.error( msg);
	}
	
	public static void logDebug(  String msg) {
		LOG.debug(msg);
	}
	
	public static void logInfo( String msg) {
		LOG.info( msg);
	}
	
	public static void logError( String msg) {
		LOG.error( msg);
	}
}
