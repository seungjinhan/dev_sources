package com.chunlab.app.system.attact;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chunlab.app.system.SystemPropertiesInfo;
import com.chunlab.app.system.annotation.CheckCall;
import com.chunlab.app.system.annotation.enums.EnumAccessType;
import com.chunlab.app.system.annotation.enums.EnumCallType;
import com.chunlab.app.system.annotation.enums.EnumNeedLogin;
import com.chunlab.app.system.exception.ExceptionBase;
import com.chunlab.app.user.UserVo;
import com.chunlab.app.utils.ajax.AjaxReturnUtil;
import com.chunlab.app.utils.session.SessionUtil;

/**
 * 
  * @FileName : AttactController.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
@Controller
@RequestMapping("/attact")
public class AttactController {

	/**
	 * 클록 아이피 조회
	 * @param request
	 * @return
	 * @throws ExceptionBase
	 * @throws Exception
	 */
	@CheckCall(accessType = EnumAccessType.EVERYONE, needLogin = EnumNeedLogin.YES, callType = EnumCallType.AJAX)
	@RequestMapping(value = "/get_block_info")
	public Map<String,Object> get_block_info( 
			HttpServletRequest request)  throws ExceptionBase, Exception{
		
		UserVo userVo = SessionUtil.getUserSession(request);
		if(SystemPropertiesInfo.systemEmailList.contains( userVo.getEmail()) ) {
			
			return AjaxReturnUtil.makeSuccess( AttackChecker.blockIP);
		}
		return AjaxReturnUtil.makeFail();
	}
	
	/**
	 * 블록 아이피 초기화
	 * 
	 * @param request
	 * @return
	 * @throws ExceptionBase
	 * @throws Exception
	 */
	@CheckCall(accessType = EnumAccessType.EVERYONE, needLogin = EnumNeedLogin.YES, callType = EnumCallType.AJAX)
	@RequestMapping(value = "/reset_block_info")
	public Map<String,Object> reset_block_info( 
			HttpServletRequest request)  throws ExceptionBase, Exception{
		
		UserVo userVo = SessionUtil.getUserSession(request);
		if(SystemPropertiesInfo.systemEmailList.contains( userVo.getEmail()) ) {
			
			AttackChecker.blockIP = new HashMap<String, String>();
			return AjaxReturnUtil.makeSuccess( AttackChecker.blockIP);
		}
		return AjaxReturnUtil.makeFail();
	}
	
}
