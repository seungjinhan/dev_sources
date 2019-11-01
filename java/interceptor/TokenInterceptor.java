package com.chunlab.app.system.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.chunlab.app.system.annotation.CheckCall;
import com.chunlab.app.system.annotation.enums.EnumCallType;
import com.chunlab.app.system.annotation.enums.EnumNeedLogin;
import com.chunlab.app.system.exception.ExceptionBase;
import com.chunlab.app.token.EnumExceptionToken;
import com.chunlab.app.token.engine.TokenCache;
import com.chunlab.app.user.UserService;
import com.chunlab.app.user.UserVo;
import com.chunlab.app.utils.ajax.AjaxReturnUtil;
import com.chunlab.app.utils.session.SessionUtil;



/**
 * 
  * @FileName : TokenInterceptor.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

	private Logger LOG = LoggerFactory.getLogger(TokenInterceptor.class);

	@Autowired
	private TokenCache tokenBox;

	@Autowired
	private UserService userService;
	
	@Override
	public void afterCompletion ( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

		super.afterCompletion(request, response, handler, ex);
		LOG.info("============= TokenInterceptor END :: " + request.getRequestURI());
	}

	@Override
	public void afterConcurrentHandlingStarted ( HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	@Override
	public void postHandle ( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

		super.postHandle(request, response, handler, modelAndView);
	}


	@Override
	public boolean preHandle ( HttpServletRequest request, HttpServletResponse response, Object handler) throws ExceptionBase, Exception {

		LOG.info("============= TokenInterceptor START");
		
		CheckCall checkCallAnotation = ((HandlerMethod) handler).getMethodAnnotation(CheckCall.class);
		
		// 로그인이 필요 없는 경우는 그냥 SKIP처리
		if(checkCallAnotation.needLogin() ==  EnumNeedLogin.NO) {
			
			return true;
		}
		
		String accessToken = request.getHeader("Authorization");
		//String accessToken = request.getParameter("access_token");
		
		// 토큰 정보가 없을때 처리
		if( accessToken == null || accessToken.trim().length()==0) {

			// 토큰정보가 없을때 처리
			if(checkCallAnotation.callType() == EnumCallType.AJAX) {
				
				return AjaxReturnUtil.makeAjaxFailInInterceptor(response, EnumExceptionToken.NEED_ACCESS_TOKEN, LOG);
			}
			else {
				
			}
			
		}
		else {
			
			boolean isAvailableToken = false;
			try {
				
				isAvailableToken = this.tokenBox.isAvailableAccessToken(accessToken);
			}
			catch( ExceptionBase ex) {
				
				// Access Token이 없을때처리
				if(ex.getEnumCode().getCode() == EnumExceptionToken.NOT_EXIST_ACCESSTOKEN.getCode()) {
					
					return AjaxReturnUtil.makeAjaxFailInInterceptor(response, EnumExceptionToken.NOT_EXIST_ACCESSTOKEN, LOG);
				}
			}
			
			// Access token 유효시간 체크
			if( isAvailableToken == false) {
				
				if(checkCallAnotation.callType() == EnumCallType.AJAX) {
					
					return AjaxReturnUtil.makeAjaxFailInInterceptor(response, EnumExceptionToken.EXPIRE, LOG);
				}
				else {
					
				}
			}
			
			// Access token 만료시간 재설정
			this.tokenBox.reSetAccessTokenExpire(accessToken);
			
			// 이메일 조회
			String email = this.tokenBox.getEmailFromAccessToken(accessToken);
			
			UserVo userVo = userService.findByEmail(email);
			
			// 세션에 추가 정보와 함께 저장
			userService.addMoreInfoInSession( userVo);
			
		}
		
		return true;
	}


	
}
