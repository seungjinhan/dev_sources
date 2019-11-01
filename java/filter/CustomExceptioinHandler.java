package com.chunlab.app.system.exception;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import com.chunlab.app.system.ApplicationConstans;
import com.chunlab.app.system.SystemPropertiesInfo;
import com.chunlab.app.system.annotation.CheckCall;
import com.chunlab.app.system.email_sender_queue.EmailSenderQueueService;
import com.chunlab.app.system.email_sender_queue.EmailSenderQueueVo;
import com.chunlab.app.user.UserVo;
import com.chunlab.app.utils.ajax.AjaxReturnUtil;
import com.chunlab.app.utils.network.NetworkUtil;
import com.chunlab.app.utils.session.SessionUtil;


/**
 * 
  * @FileName : CustomExceptioinHandler.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :예외처리 핸들러
 */
@ControllerAdvice
public class CustomExceptioinHandler {

	private Logger LOG = LoggerFactory.getLogger(CustomExceptioinHandler.class);
	
	
	@Autowired
	private EmailSenderQueueService emailSenderQueueService;
	
	/**
	 * 커스텀된 예외를 공통으로 처리 
	 * 
	 * @param request
	 * @param ex
	 * @param data
	 * @return
	 */
	private Map<String, Object> customExceptionRun( ExceptionBase ex, String data, String apiErrorCode){
		
		return AjaxReturnUtil.makeExceptionResult( ex);

	}

	
	/**
	 * 시스템 예외 발생시 
	 * @param request
	 * @param ex
	 * @return
	 * @throws ExceptionDataSendingToLogCenter
	 */
	@ExceptionHandler( Exception.class)
	@ResponseBody
	public Map<String, Object>  handlerException( HttpServletRequest request, Exception ex) {
		
		
		
		StackTraceElement[] exST =  ex.getStackTrace();
		StringBuilder sb = new StringBuilder();
		for (StackTraceElement st : exST) {
			
			sb = sb.append( st.toString()).append("<br>");			
		}
		
		String remoteIP = NetworkUtil.getRemoteIP(request);
		String queryAndParam = NetworkUtil.getQueryAndParameterString(request);
		
		UserVo userVo = SessionUtil.getUserSession(request);
		String email = "No Session";
		if( userVo != null) {
			email = userVo.getEmail();
		}
		
		EmailSenderQueueVo vo = new EmailSenderQueueVo();
		vo.setSubject("[ERROR]"+ (SystemPropertiesInfo.isServer==true?"운영":"개발")+"서버 - Exception Error");
		vo.setContents( "<ul>" + 
						"<li>" + ex.getMessage() + "</li>" + 
						"<li>" + ex.toString() + "</li>" +
						"<li>" + sb.toString() + "</li>" + 
						"<li>IP :" + remoteIP + "</li>" + 
						"<li>URL: " + request.getRequestURI() + "</li>" + 
						"<li>Params:" + queryAndParam + "</li>" + 
						"<li>email: " + email + "</li>" + 
						"</ul>");
		
		vo.setDescriptoin("ERROR 메세지");
		
		emailSenderQueueService.save(vo, SystemPropertiesInfo.systemEmailList);
		
		LOG.info( ex.getMessage());
		ex.printStackTrace();
		
		return AjaxReturnUtil.makeExceptionResult( ApplicationConstans.SYSTE_ERROR_CODE, ex.getMessage());
	}
	

	/**
	 * 
	 * @param request
	 * @param ex
	 * @return
	 */
	@ExceptionHandler( ExceptionBase.class)
	@ResponseBody
	public Map<String, Object>  handlerException( HttpServletRequest request, ExceptionBase ex) throws Exception{
		
		LOG.info( ex.getCode() + ":" + ex.getData().toString() + " [ " + ex.getDesc() + " ]");
		ex.printStackTrace();
		return this.customExceptionRun( ex, ex.getData().toString(), ex.getCode());
	}

	
	/**
	 * 에러메세지로 전송해야 하는 예외
	 * @param request
	 * @param ex
	 * @return
	 */
	@ExceptionHandler( ExceptionError.class)
	@ResponseBody
	public Map<String, Object>  handlerException( HttpServletRequest request, ExceptionError ex) {
		
		StackTraceElement[] exST =  ex.getStackTrace();
		StringBuilder sb = new StringBuilder();
		for (StackTraceElement st : exST) {
			
			sb = sb.append( st.toString()).append("<br>");			
		}
		
		String remoteIP = NetworkUtil.getRemoteIP(request);
		String queryAndParam = NetworkUtil.getQueryAndParameterString(request);
		UserVo userVo = SessionUtil.getUserSession(request);
		String email = "No Session";
		if( userVo != null) {
			email = userVo.getEmail();
		}
		
		EmailSenderQueueVo vo = new EmailSenderQueueVo();
		vo.setSubject("[ERROR]"+ (SystemPropertiesInfo.isServer==true?"운영":"개발")+"서버 - "+ ex.getCode());
		vo.setContents( "<ul>" + 
						"<li>" + ex.getData().toString() + " </li>" + 
						"<li>" + ex.getDesc() + " </li/>" + 
						"<li>" + ex.getMessage() + "</li>" + 
						"<li>" + ex.toString() + "</li>" +
						"<li>" + ex.getE().getMessage() + "</li>" +
						"<li>" + sb.toString() + "</li>" +
						"<li>IP :" + remoteIP + "</li>" + 
						"<li>URL: " + request.getRequestURI() + "</li>" + 
						"<li>Params:" + queryAndParam + "</li>" + 
						"<li>email: " + email + "</li>" + 
						"</ul>");
		
		vo.setDescriptoin("ERROR 메세지");
		
		emailSenderQueueService.save(vo, SystemPropertiesInfo.systemEmailList);
		
		ex.getLOG().info( ex.getCode() + ":" + ex.getData().toString() + " [ " + ex.getDesc() + " ]");
		
		
		return this.customExceptionRun( ex, ex.getData().toString(), ex.getCode());
	}
	
}
	
