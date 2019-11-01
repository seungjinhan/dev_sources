package com.chunlab.app.system.attact;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.chunlab.app.system.SystemPropertiesInfo;


/**
 * 
  * @FileName : AttackCheckerComponent.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 : 공격자에 대한 IP차단을 위한 유틸
 */
public class AttackChecker {

    static Logger LOGGER = LoggerFactory.getLogger(AttackChecker.class);

    static Map<String, Long[]> newData = new HashMap<String, Long[]>();
    
    static Map<String, Long[]> data = new HashMap<String, Long[]>();

    static Map<String, String> blockIP = new HashMap<String, String>(); // IP, URL 셋으로 저장

    /**
     * 공격 체크하기
     * @param ip
     * @param url
     * @return
     */
    public static boolean isAttact(String ip, String url) {

		Long now = System.currentTimeMillis();
		
		String key = makeKey(ip, url);
		if( blockIP.containsKey(ip)) {
			
			LOGGER.info("###############");
			LOGGER.info("Block IP");
			LOGGER.info("ip :" + ip);
			LOGGER.info("url" + url);
			LOGGER.info("###############");
			
			return true;
		}
		// 해당 키가 없으면 false를 반환
		if( newData.containsKey(key) == false) {
			
			// 현재 시간으로 추가함
			newData.put(key, new Long[] { now , 1L});
		} else {
			
			Long[] info = newData.get(key);
			
			// 시간 이내라면 카운트 확인
			if( (now - info[0]) <= SystemPropertiesInfo.attactCheckTime ) {
				
				// 설정한 횟수보다 많다면 공격으로 인지
				if( info[1] >= SystemPropertiesInfo.attactCheckCount) {
					
					LOGGER.info("###############");
					LOGGER.info("Block IP 추가");
					LOGGER.info("ip :" + ip);
					LOGGER.info("시간" + (now - info[0]));
					LOGGER.info("횟수" + info[1]);
					LOGGER.info("###############");
					
					blockIP.put(ip, url);
					return true;
				}
				else {
					newData.put( key, new Long[] { info[0], info[1]+1});
				}
			}
			// 시간이 지났으면 초기화함
			else {
				
				newData.remove(key);
			}
		}
		
		return false;
    }


    private static String makeKey(String ip, String url) {

    	return ip + "," + url;
    }
}
