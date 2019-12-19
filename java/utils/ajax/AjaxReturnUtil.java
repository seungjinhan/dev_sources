package com.chunlab.app.utils.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.chunlab.admin.system.json.JsonMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chunlab.app.system.enums.base.EnumBase;
import com.chunlab.app.system.exception.ExceptionBase;
import com.chunlab.app.utils.ajax.domain.AjaxReturnVo;
import com.chunlab.app.utils.others.LogUtil;


/**
 * Ajax 반환처리 유틸
 * 
 * @package : com.chunlab.app.utils.ajax
 * @file    : AjaxReturnUtil.java
 * @author  : seungjin.han
 * @Date    : 2018. 11. 7.
 * @version : 0.1
 */
public class AjaxReturnUtil {

	private static Logger LOG = LoggerFactory.getLogger(AjaxReturnUtil.class);
	
	/**
	 * AJAX 성공 처리
	 * 
	 * @return
	 */
	public static Map<String, Object> makeSuccess () {

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(AjaxConstants.SUCCESS, true);
		result.put(AjaxConstants.DATA, AjaxConstants.DEFAULT_DATA);
		result.put(AjaxConstants.CODE, AjaxConstants.CODE_AJAX_SUCCESS);
		result.put(AjaxConstants.MESSAGE, AjaxConstants.DEFAULT_MESSAGE);

		return result;
	}

	public static Map<String, Object> makeSuccess ( AjaxReturnVo resultData) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(AjaxConstants.SUCCESS, true);
		result.put(AjaxConstants.DATA, resultData.getData());
		result.put(AjaxConstants.CODE, resultData.getCode());
		result.put(AjaxConstants.MESSAGE, resultData.getMessage());
		
		return result;
	}

	/**
	 * AJAX 성공 처리
	 * 
	 * @param data
	 * @return
	 */
	public static Map<String, Object> makeSuccess ( Object data) {

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(AjaxConstants.SUCCESS, true);
		result.put(AjaxConstants.DATA, data);
		result.put(AjaxConstants.CODE, AjaxConstants.CODE_AJAX_SUCCESS);
		result.put(AjaxConstants.MESSAGE, AjaxConstants.DEFAULT_MESSAGE);

		return result;
	}
	
	public static Map<String, Object> makeSuccessOnlyData ( Object data) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(AjaxConstants.SUCCESS, true);
		result.put(AjaxConstants.DATA, data);
		result.put(AjaxConstants.CODE, AjaxConstants.CODE_AJAX_SUCCESS);
		result.put(AjaxConstants.MESSAGE, AjaxConstants.DEFAULT_MESSAGE);
		
		return result;
	}
	
	public static Map<String, Object> makeSuccess ( String code, Object data) {

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(AjaxConstants.SUCCESS, true);
		result.put(AjaxConstants.DATA, data);
		result.put(AjaxConstants.CODE, code);
		result.put(AjaxConstants.MESSAGE, AjaxConstants.DEFAULT_MESSAGE);

		return result;
	}

	/**
	 * AJAX 성곧 처리
	 * 
	 * @param data
	 * @param code
	 * @param message
	 * @return
	 */
	public static Map<String, Object> makeSuccess ( String code, String message, Object data) {

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(AjaxConstants.SUCCESS, true);
		result.put(AjaxConstants.DATA, data);
		result.put(AjaxConstants.CODE, code);
		result.put(AjaxConstants.MESSAGE, message);

		return result;
	}

	/**
	 * AJAX 성공 처리
	 * 
	 * @param code
	 * @return
	 */
	public static Map<String, Object> makeSuccess ( String code) {

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(AjaxConstants.SUCCESS, true);
		result.put(AjaxConstants.DATA, AjaxConstants.DEFAULT_DATA);
		result.put(AjaxConstants.CODE, code);
		result.put(AjaxConstants.MESSAGE, AjaxConstants.DEFAULT_MESSAGE);
		return result;
	}

	/**
	 * AJAX 성공 처리
	 * 
	 * @param code
	 * @return
	 */
	public static Map<String, Object> makeSuccess ( String code, String message) {

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(AjaxConstants.SUCCESS, true);
		result.put(AjaxConstants.DATA, AjaxConstants.DEFAULT_DATA);
		result.put(AjaxConstants.CODE, code);
		result.put(AjaxConstants.MESSAGE, message);
		return result;
	}

	/**
	 * 2019.05.31
	 * 
	 * JsonMap 처리
	 * @param datacode
	 * @return
	 */
	public static Map<String, Object> makeSuccess ( JsonMap datacode) {

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(AjaxConstants.SUCCESS, true);
		result.put(AjaxConstants.DATA, datacode.toString());
		result.put(AjaxConstants.CODE, AjaxConstants.CODE_AJAX_SUCCESS);
		result.put(AjaxConstants.MESSAGE, AjaxConstants.DEFAULT_MESSAGE);
		return result;
	}
	
	/**
	 * AJAX 실패 처리
	 * 
	 * @param data
	 * @return
	 */
	public static Map<String, Object> makeFail () {

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(AjaxConstants.SUCCESS, false);
		result.put(AjaxConstants.DATA, AjaxConstants.DEFAULT_DATA);
		result.put(AjaxConstants.CODE, AjaxConstants.CODE_AJAX_FAIL);
		result.put(AjaxConstants.MESSAGE, AjaxConstants.DEFAULT_MESSAGE);

		LogUtil.logError(LOG , result.toString());
		
		return result;
	}

	/**
	 * AJAX 실패 처리
	 * 
	 * @param data
	 * @return
	 */
	public static Map<String, Object> makeFail ( Object data) {

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(AjaxConstants.SUCCESS, false);
		result.put(AjaxConstants.DATA, data);
		result.put(AjaxConstants.CODE, AjaxConstants.CODE_AJAX_FAIL);
		result.put(AjaxConstants.MESSAGE, AjaxConstants.DEFAULT_MESSAGE);

		LogUtil.logError(LOG , result.toString());
		
		return result;
	}

	/**
	 * AJAX 실패 처리
	 * 
	 * @param data
	 * @param code
	 * @return
	 */
	public static Map<String, Object> makeFail ( String code, Object data) {

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(AjaxConstants.SUCCESS, false);
		result.put(AjaxConstants.DATA, data);
		result.put(AjaxConstants.CODE, code);
		result.put(AjaxConstants.MESSAGE, AjaxConstants.DEFAULT_MESSAGE);

		LogUtil.logError(LOG , result.toString());
		
		return result;
	}
	
	public static Map<String, Object> makeFail ( String code, String message, Object data) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(AjaxConstants.SUCCESS, false);
		result.put(AjaxConstants.DATA, data);
		result.put(AjaxConstants.CODE, code);
		result.put(AjaxConstants.MESSAGE, message);
		
		LogUtil.logError(LOG , result.toString());
		
		return result;
	}

	/**
	 * AJAX 실패 처리
	 * 
	 * @param data
	 * @param code
	 * @param message
	 * @return
	 */
	public static Map<String, Object> makeFail ( String code, Object data, String message) {

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(AjaxConstants.SUCCESS, false);
		result.put(AjaxConstants.DATA, data);
		result.put(AjaxConstants.CODE, code);
		result.put(AjaxConstants.MESSAGE, message);

		LogUtil.logError(LOG , result.toString());
		
		return result;
	}

	/**
	 * AJAX 실패 처리
	 * 
	 * @param code
	 * @return
	 */
	public static Map<String, Object> makeFail ( String code) {

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(AjaxConstants.SUCCESS, false);
		result.put(AjaxConstants.DATA, AjaxConstants.DEFAULT_DATA);
		result.put(AjaxConstants.CODE, code);
		result.put(AjaxConstants.MESSAGE, AjaxConstants.DEFAULT_MESSAGE);
		
		LogUtil.logError(LOG , result.toString());
		
		return result;
	}

	/**
	 * AJAX 실패 처리
	 * 
	 * @param code
	 * @return
	 */
	public static Map<String, Object> makeFail ( String code, String message) {

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(AjaxConstants.SUCCESS, false);
		result.put(AjaxConstants.DATA, AjaxConstants.DEFAULT_DATA);
		result.put(AjaxConstants.CODE, code);
		result.put(AjaxConstants.MESSAGE, message);
		
		LogUtil.logError(LOG , result.toString());
		
		return result;
	}
	
	public static Map<String, Object> makeFail ( String code, String message, String data) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(AjaxConstants.SUCCESS, false);
		result.put(AjaxConstants.DATA, data);
		result.put(AjaxConstants.CODE, code);
		result.put(AjaxConstants.MESSAGE, message);
		
		LogUtil.logError(LOG , result.toString());
		
		return result;
	}

	public static Map<String, Object> makeFail ( String code, String message, String extraMessageName, String extraMessageValue) {

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(AjaxConstants.SUCCESS, false);
		result.put(AjaxConstants.DATA, AjaxConstants.DEFAULT_DATA);
		result.put(AjaxConstants.CODE, code);
		result.put(AjaxConstants.MESSAGE, message + "["+ extraMessageName + ": "+ extraMessageValue +"]");
		
		LogUtil.logError(LOG , result.toString());
		
		return result;
	}
	/**
	 * 정수값 결과에 따른 반환
	 * 
	 * @param result
	 * @return
	 */
	public static Map<String, Object> intAjaxResult ( int result) {

		return (result == 1) ? AjaxReturnUtil.makeSuccess() : AjaxReturnUtil.makeFail();
	}

	/**
	 * 인터셉터에서 Ajax 호출관련 오류로 반환처리 해야 할 경우 사용
	 * 
	 * @param response
	 * @param message
	 * @param logger
	 * @throws IOException
	 */
	public static boolean makeAjaxFailInInterceptor ( HttpServletResponse response, EnumBase enumBase, Logger logger) throws IOException {

		LogUtil.logError(LOG ,"========================================================");
		LogUtil.logError(LOG ,"* Error : " + enumBase.getDesc() + " =======");
		LogUtil.logError(LOG ,"========================================================");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");

		PrintWriter w = response.getWriter();
		w.write("{\"code\":\"" + enumBase.getCode() + "\",\"success\":false,\"message\":\"" + enumBase.getDesc() + "\"}");
		w.flush();
		w.close();

		return false;
	}

	/**
	 * 예외처리 반환
	 * 
	 * @param e
	 * @param message
	 * @return
	 */
	public static Map<String, Object> makeExceptionResult ( Exception e, String message, String code) {

		LogUtil.logError(LOG, e.toString());
		e.printStackTrace();
		return makeFail(code, message);
	}
	
	public static Map<String, Object> makeExceptionResult ( Exception e, String code, String message, Object data) {
		
		LogUtil.logError(LOG, e.toString());
		e.printStackTrace();
		return makeFail(code, message ,data);
	}

	public static Map<String, Object> makeExceptionResult ( Exception e, String message) {

		LogUtil.logError(LOG, e.toString());
		e.printStackTrace();
		return makeFail(message);
	}

	public static Map<String, Object> makeExceptionResult ( String code, String message ) {
		
		return makeFail(code, message);
	}
	
	
	public static Map<String, Object> makeExceptionResult ( String code, String message, String data) {
		
		return makeFail(code, message, data);
	}

	public static Map<String, Object> makeExceptionResult ( String message) {

		return makeFail(message);
	}

	public static Map<String, Object> makeExceptionResult ( Exception e) {

		LogUtil.logError(LOG, e.toString());
		e.printStackTrace();
		return makeFail();
	}
	
	public static Map<String, Object> makeExceptionResult ( ExceptionBase e) {
		
		LogUtil.logError(LOG, e.toString());
		e.printStackTrace();
		return makeFail(e.getCode(), e.getDesc() ,e.getData());
	}

}
