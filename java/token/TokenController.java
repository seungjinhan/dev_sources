package com.chunlab.app.token;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chunlab.app.device.AppDeviceService;
import com.chunlab.app.device.AppDeviceVo;
import com.chunlab.app.system.annotation.CheckCall;
import com.chunlab.app.system.annotation.enums.EnumAccessType;
import com.chunlab.app.system.annotation.enums.EnumCallType;
import com.chunlab.app.system.annotation.enums.EnumNeedLogin;
import com.chunlab.app.system.exception.ExceptionBase;
import com.chunlab.app.system.exception.ExceptionError;
import com.chunlab.app.token.engine.TokenCache;
import com.chunlab.app.user.UserVo;
import com.chunlab.app.utils.ajax.AjaxReturnUtil;

/**
 * 
  * @FileName : TokenController.java
  * @Project : app_server
  * @Date : 2019. 9. 26. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 : 토큰처리 컨트롤러
 */
@Controller
@RequestMapping("token")
public class TokenController {


	@Autowired
	private TokenService refreshTokenService;
	
	@Autowired
	private AppDeviceService appDeviceService;
	
	@Autowired
	private TokenCache tokenBox;
	

	@CheckCall(accessType = EnumAccessType.EVERYONE, needLogin = EnumNeedLogin.YES, callType = EnumCallType.PAGE)
	@RequestMapping(value = "/detail")
	public ModelAndView test( HttpServletRequest request, Device device) {
		
		ModelAndView view =  new ModelAndView( "/page/pc/detail");
		return view;
	}

	/**
	 * 
	  * @Method Name : app_token_check
	  * @작성일 : 2019. 9. 26.
	  * @작성자 : seungjin.han
	  * @변경이력 : 
	  * @Method 설명 : appkey로 토큰 정보를 조회함
	  * @param request
	  * @param appKey
	  * @return
	  * @throws ExceptionError
	  * @throws ExceptionBase
	  * @throws Exception
	 */
	@CheckCall(accessType = EnumAccessType.EVERYONE, needLogin = EnumNeedLogin.NO, callType = EnumCallType.AJAX)
	@RequestMapping(value = "/app_token_check", method= RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> app_token_check( 
			HttpServletRequest request,
			@RequestParam("app_key") String appKey)  throws ExceptionError, ExceptionBase, Exception{
		
		AppDeviceVo deviceVo = appDeviceService.findByAppKey(appKey);
		TokenVo vo = this.refreshTokenService.findByAppDeviceNo(deviceVo.getNo() );
		
		// access token 정보 세팅
		vo.setAccessToken( this.tokenBox.getAccessTokenByEmail(vo.getEmail()));
		
		return AjaxReturnUtil.makeSuccess( vo);
	}
	

	/**
	 * 
	  * @Method Name : get_access_token
	  * @작성일 : 2019. 9. 26.
	  * @작성자 : seungjin.han
	  * @변경이력 : 
	  * @Method 설명 : Access Token 요청
	  * @param request
	  * @param refreshToken
	  * @return
	  * @throws ExceptionError
	  * @throws ExceptionBase
	  * @throws Exception
	 */
	@CheckCall(accessType = EnumAccessType.EVERYONE, needLogin = EnumNeedLogin.NO, callType = EnumCallType.AJAX)
	@RequestMapping(value = "/get_access_token", method= RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> get_access_token( 
			HttpServletRequest request,
			@RequestParam("refresh_token") String refreshToken)  throws ExceptionError, ExceptionBase, Exception{
		
		UserVo userVo = this.refreshTokenService.getAccessTokenByRefreshToken(refreshToken);
		userVo.setPassword("");
		return AjaxReturnUtil.makeSuccessOnlyData( userVo);
	}

}
