package com.chunlab.app.utils.session;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.chunlab.app.system.exception.ExceptionBase;
import com.chunlab.app.user.UserVo;
import com.chunlab.app.utils.list.ListUtil;

/**
  * @FileName : SessionUtil.java
  * @Project : app_server
  * @Date : 2019. 9. 26. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 : 세션처리 모듈
 */
public class SessionUtil {

	// 세션에 사용자 정보 추가시 사용할  key
	private static final String SESSION_KEY_USER_INFO = "USER_INFO";

	// 세션에 패스워드 변경 관련 키를 저장
	public static final String SESSION_CODE_PASSWORD_CHANGE_CODE = "SESSION_CODE_PASSWORD_CHANGE_CODE";

	/**
	 * 세션키 찾기
	 * 
	 * @param request
	 * @return
	 */
	public static List<String> getSessionKeys ( HttpServletRequest request) {

		List<String> result = new ArrayList<String>();
		Enumeration<String> keys = request.getAttributeNames();
		while (keys.hasMoreElements()){
			result.add(keys.nextElement());
		}
		return result;
	}

	/**
	 * 사용자 세션을 저장
	 * 
	 * @param request
	 * @param obj
	 */
	public static void setUserSession ( HttpServletRequest request, UserVo obj) {
		request.getSession().setAttribute(SESSION_KEY_USER_INFO, obj);
	}

	/**
	 * 세션에 있는 사용자 세션 반환
	 * 
	 * @param request
	 * @param returnType
	 *            반환타입
	 * @return
	 */
	public static UserVo getUserSession ( HttpServletRequest request) {
		return (UserVo) request.getSession().getAttribute(SESSION_KEY_USER_INFO);
	}

	/**
	 * 사용자 세션제거
	 * 
	 * @param request
	 */
	public static void removeUserSession ( HttpServletRequest request) {
		
		request.getSession().removeAttribute(SESSION_KEY_USER_INFO);
	}

	
	/**
	 * 세션추가
	 * 
	 * @param request
	 * @param key
	 * @param value
	 */
	public static <T> void setSession ( HttpServletRequest request, String key, T value) {

		request.getSession().setAttribute(key, value);
	}


	/**
	 * 세션에서 값 꺼내기
	 * 
	 * @param request
	 * @param key
	 *            세션키
	 * @param returnType
	 *            반환타입
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getSession ( HttpServletRequest request, String key, Class<T> returnType) {

		return (T) request.getSession().getAttribute(key);
	}

	/**
	 * 세션에서 값 지우기
	 * 
	 * @param request
	 * @param key
	 */
	public static void removeSession ( HttpServletRequest request, String key) {

		request.getSession().removeAttribute(key);
	}

	/**
	 * 세션 전체 삭제
	 * 
	 * @param request
	 * @param key
	 */
	public static void removeAllSession ( HttpServletRequest request) {

		Enumeration<String> keys = request.getAttributeNames();
		while (keys.hasMoreElements()){
			request.getSession().removeAttribute(keys.nextElement());
		}
	}

	/**
	 * 세션에 있는 Key 리스트 반환
	 * 
	 * @param request
	 * @return
	 * @throws ExceptionBase 
	 */
	public static List<Object> getSessionKeyList ( HttpServletRequest request) throws ExceptionBase {

		return ListUtil.enumerationToList( request.getSession().getAttributeNames());
	}

}
