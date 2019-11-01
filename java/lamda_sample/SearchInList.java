package com.chunlab.app.system.cache.box;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chunlab.app.device.AppDeviceService;
import com.chunlab.app.device.AppDeviceUtil;
import com.chunlab.app.device.AppDeviceVo;
import com.chunlab.app.device.box.AppPushReceiverVo;
import com.chunlab.app.system.SystemPropertiesInfo;
import com.chunlab.app.system.enums.EnumYN;
import com.chunlab.app.system.exception.ExceptionBase;
import com.chunlab.app.utils.list.ListManager;

/**
  * @FileName : SearchInList.java
  * @Project : dev_module
  * @Date : 2019. 10. 28. 
  * @작성자 : deepplin
  * @변경이력 :
  * @프로그램 설명 : ListManager를 사용하여 검색하기
 */
@Component
public class CachePushInfoComponent {

	
	//////////// List 데이터 
	public static List<AppPushReceiverVo> pushReceiverList = null;

	
	
	/**
	 * 한가지만 조회를 해야 할때
	 * 
	 * @param p
	 * @return
	 */
	private AppPushReceiverVo filterForSingle(PushInfoPredicate p){
	
		AppPushReceiverVo vo = ListManager.filterForSingle(
				pushReceiverList,
				(AppPushReceiverVo t) -> t.getAppDeviceNo() == appDeviceNo);
		
		return null;
	}
	
	
	/**
	 * 여러개를 조회해야 할때
	 * 
	 * @param p
	 * @return
	 */
	private List<AppPushReceiverVo> filterForMulti(PushInfoPredicate p){
		
		List<AppPushReceiverVo> result = new ArrayList<AppPushReceiverVo>();
		
		List< AppPushReceiverVo> list = ListManager.filterForMulti(
				pushReceiverList,
				(AppPushReceiverVo t) -> t.getPushSendHour() == time);
		
		return result;
	}
	
	
}
