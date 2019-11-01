package com.chunlab.app.system.interceptor;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.chunlab.app.logs.LogsService;
import com.chunlab.app.logs.box.LogsBoxApiCallVo;
import com.chunlab.app.system.SystemPropertiesInfo;
import com.chunlab.app.system.annotation.CheckCall;
import com.chunlab.app.system.annotation.enums.EnumCallType;
import com.chunlab.app.system.attact.AttackChecker;
import com.chunlab.app.system.attact.EnumAttact;
import com.chunlab.app.system.attact.SQLInjectionChecker;
import com.chunlab.app.system.email_sender_queue.EmailSenderQueueService;
import com.chunlab.app.system.email_sender_queue.EmailSenderQueueVo;
import com.chunlab.app.user.UserVo;
import com.chunlab.app.utils.ajax.AjaxReturnUtil;
import com.chunlab.app.utils.date.DateUtil;
import com.chunlab.app.utils.network.NetworkUtil;


/**
 * 
  * @FileName : ServiceInterceptor.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public class ServiceInterceptor extends HandlerInterceptorAdapter {

	private Logger LOG = LoggerFactory.getLogger(ServiceInterceptor.class);

	private String loginPage = "/page/user/login.do";
	
	private String ipAttactPage = "/attact/page/attact.do";
	private String sqlAttactPage = "/attact/page/sql_injection.do";
	
	
	@Autowired
	private LogsService logsService;
	
	@Autowired
	private EmailSenderQueueService emailSenderQueueService;
	
	private UserVo sessionUser = null;
	private String remoteIP = null;
	private String queryAndParam = null;
	
	private void sendMailQueue( HttpServletRequest request,String subject, String contents, String desc) {
		
		String ip = NetworkUtil.getRemoteIP(request);
		String url = request.getServletPath();
		
		// 메일 전송
		EmailSenderQueueVo vo = new EmailSenderQueueVo();
		vo.setSubject("[ERROR]"+ (SystemPropertiesInfo.isServer==true?"운영":"개발")+"서버 - ");
		vo.setContents( 
						"CONTENTS:" + contents + "\n"
						+"IP:" + ip + "\n"
						+"URL:" + url + "\n"
						+"TIME:" + DateUtil.getDatabaseCreateDate()
						);
		vo.setDescriptoin("SQL INJECTION");
		emailSenderQueueService.save(vo, SystemPropertiesInfo.systemEmailList);
	}
	
	/**
	 * 호출에 대한 프로토콜확인
	 * 
	 * @param checkCallAnotation
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
//	private boolean checkCallProtocal( CheckCall checkCallAnotation, HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
//		
//		sessionUser = SessionUtil.getUserSession(request, UserVo.class);
//		
//		// 로그인해야 함
//		if( checkCallAnotation.needLogin() == EnumNeedLogin.YES) {
//			
//			// 로그인이 안된경우
//			if( sessionUser == null) {
//				
//				// AJAX 호출인경우
//				if( checkCallAnotation.callType() == EnumCallType.AJAX) {
//					
//					return AjaxReturnUtil.makeAjaxFailInInterceptor(response, EnumExceptionSession.NO_SESSION, LOG);
//				}
//				// 페이지 호출인 경우 로그인 페이지로 이동
//				else {
//					
//					response.sendRedirect(loginPage);
//				}
//			}
//		}
//		
//		return true;
//	}
	
	/**
	 * 공격 여부 처리
	 * @param checkCallAnotation
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	private boolean checkAttack( CheckCall checkCallAnotation, HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		
		String ip = NetworkUtil.getRemoteIP(request);
		String url = request.getServletPath();
		
		if( AttackChecker.isAttact(ip, url)) {
			
			this.sendMailQueue(request, "IP 공격" , SystemPropertiesInfo.attactCheckTime+"s [횟수:"+ SystemPropertiesInfo.attactCheckCount+"]" , "IP 공격" );
			
			if( checkCallAnotation.callType() == EnumCallType.PAGE) {
				
				response.sendRedirect(ipAttactPage);
				return false;
			}
			else {
				
				return AjaxReturnUtil.makeAjaxFailInInterceptor(response, EnumAttact.IP_ATTACT, LOG);
			}
		}
		
		return true;
	}
	
	
	/**
	 * 
	  * @Method Name : sqlInjectionAttack
	  * @작성일 : 2019. 10. 4.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 :
	  * @param checkCallAnotation
	  * @param request
	  * @param response
	  * @param handler
	  * @return
	  * @throws Exception
	 */
	private boolean checkSqlInjectionAttack( CheckCall checkCallAnotation, HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		
		Optional< List<String[]>> isSQLInjection = SQLInjectionChecker.isSQLInject( request);
		
		// 데이터가 있다는건 SQL Injection 문자가 있다는 의미
		if( isSQLInjection.isPresent() == true && isSQLInjection.get().size()>0) {
			
			StringBuilder sb = new StringBuilder();
			for ( String[] arr: isSQLInjection.get()) {
				
				for (String s : arr) {
					sb.append(s);
				}
			}
			
			// 메일 큐에 저장
			this.sendMailQueue(request, "SQL INJECTION ERROR" , sb.toString() , "SQL INJECTION ERROR");
			
			if( checkCallAnotation.callType() == EnumCallType.PAGE) {
				
				response.sendRedirect(sqlAttactPage);
				return false;
			}
			else {
				
				return AjaxReturnUtil.makeAjaxFailInInterceptor(response, EnumAttact.SQL_INJECTION, LOG);
			}
		}
		
		return true;
	}

	/**
	 * 호출 정보 로그 저장
	 * 
	 * @return
	 * @throws Exception
	 */
	private boolean saveApiCall() throws Exception {
		
		LogsBoxApiCallVo vo = new LogsBoxApiCallVo();
		vo.setDate( DateUtil.getDatabaseCreateDate());
		vo.setEmail( sessionUser==null?"":sessionUser.getEmail());
		vo.setIp(remoteIP);
		vo.setParams(queryAndParam);
		
		logsService.insertApiCall(vo);
		
		return true;
	}
	@Override
	public void afterCompletion ( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

		super.afterCompletion(request, response, handler, ex);
		
		// 호출 로그 정보 저장하기
		//saveApiCall();
		
		LOG.info("============= END :: " + request.getRequestURI());
	}

	@Override
	public void afterConcurrentHandlingStarted ( HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	@Override
	public void postHandle ( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

		super.postHandle(request, response, handler, modelAndView);


	}



	/**
	 * 
	 */
	@Override
	public boolean preHandle ( HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		remoteIP = NetworkUtil.getRemoteIP(request);
		queryAndParam = NetworkUtil.getQueryAndParameterString(request);
		CheckCall checkCallAnotation = ((HandlerMethod) handler).getMethodAnnotation(CheckCall.class);
		
		LOG.info("============= START :: " + remoteIP + ":" + request.getRequestURI() + ", query:: " + queryAndParam + ", call:: " + checkCallAnotation.toString());
		
		boolean result = this.checkSqlInjectionAttack(checkCallAnotation, request, response, handler);

		if( result == true) {
			
			result = this.checkAttack(checkCallAnotation, request, response, handler);
		}
		
		
		return result;
	}

}
