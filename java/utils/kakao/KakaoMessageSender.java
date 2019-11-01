package com.chunlab.app.utils.kakao;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chunlab.app.system.exception.ExceptionBase;
import com.chunlab.app.utils.kakao.enums.EnumKakaoSendTemplate;
import com.chunlab.app.utils.network.RequestWrapper;
import com.chunlab.app.utils.network.enums.EnumAPIMethod;
import com.chunlab.app.utils.network.enums.EnumHttpResponseCode;


/**
 * 
  * @FileName : KakaoMessageSender.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
@Component
public class KakaoMessageSender {

	private final String URL = "";
	
	@Autowired(required=true)
	private HttpServletRequest request;
	
	
	/**
	 * 카카오 전달 body 만들기
	 * @param msg
	 * @param tmpId
	 * @param phone
	 * @return
	 */
	private String makeKakaoBody(String msg, String tmpId, String phone, String buttonTitle, String pcLink, String mobileLink) {
		
		JSONObject obj= new JSONObject();
		obj.put("userId", "");
		obj.put("tmplId", tmpId);
		obj.put("msg", msg);
		obj.put("phn", phone);
		obj.put("profile", "");
		obj.put("message_type", "at");
		
		JSONObject buttonObj= new JSONObject();
		buttonObj.put("type", "WL");
		buttonObj.put("name", buttonTitle);
		buttonObj.put("url_pc", pcLink);
		buttonObj.put("url_mobile", mobileLink);
		obj.put("button1", buttonObj);
		
		obj.put("smsKind","L"); // SMS/LMS 구분(SMS: S, LMS: L)
		obj.put("msgSms",msg);  // LMS 전환발송을 위한 메시지
		obj.put("smsSender",""); // SMS 전환발송 시 발신번호
		obj.put("smsLmsTit",""); // LMS 발송 제목
		obj.put("smsOnly","N");
		
		JSONArray array = new JSONArray();
		array.put(obj);
		
		return array.toString();
	}

	/**
	 * 카카오 전달 body 만들기
	 * @param msg
	 * @param tmpId
	 * @param phone
	 * @return
	 */
	private String makeKakaoBodyOnlyForMobileLink(String msg, String tmpId, String phone, String buttonTitle, String mobileLink) {
		
		JSONObject obj= new JSONObject();
		obj.put("userId", "");
		obj.put("tmplId", tmpId);
		obj.put("msg", msg);
		obj.put("phn", phone);
		obj.put("profile", "");
		obj.put("message_type", "at");
		
		JSONObject buttonObj= new JSONObject();
		buttonObj.put("type", "WL");
		buttonObj.put("name", buttonTitle);
		buttonObj.put("url_mobile", mobileLink);
		obj.put("button1", buttonObj);
		
		obj.put("smsKind","L"); // SMS/LMS 구분(SMS: S, LMS: L)
		obj.put("msgSms",msg);  // LMS 전환발송을 위한 메시지
		obj.put("smsSender",""); // SMS 전환발송 시 발신번호
		obj.put("smsLmsTit",""); // LMS 발송 제목
		obj.put("smsOnly","N");
		
		JSONArray array = new JSONArray();
		array.put(obj);
		
		return array.toString();
	}

	private String makeKakaoBodyNoLink(String msg, String tmpId, String phone) {
		
		JSONObject obj= new JSONObject();
		obj.put("userId", "");
		obj.put("tmplId", tmpId);
		obj.put("msg", msg);
		obj.put("phn", phone);
		obj.put("profile", "");
		obj.put("message_type", "at");
		
		obj.put("smsKind","L"); // SMS/LMS 구분(SMS: S, LMS: L)
		obj.put("msgSms",msg);  // LMS 전환발송을 위한 메시지
		obj.put("smsSender",""); // SMS 전환발송 시 발신번호
		obj.put("smsLmsTit",""); // LMS 발송 제목
		obj.put("smsOnly","N");
		
		JSONArray array = new JSONArray();
		array.put(obj);
		
		return array.toString();
	}
	
	/**
	 * 2019.06.12 추가
	 * 
	 * 카카오 전송 메시지 바디 만들기
	 * @param kakaoSendID
	 * @param linkCode
	 * @param phone
	 * @return
	 */
	private String makeKakaoBody( EnumKakaoSendTemplate kakaoSendID, String linkCode, String phone) {
		
		return this.makeKakaoBody(kakaoSendID.getMsg(), kakaoSendID.getId(), phone, kakaoSendID.getButtonName(), kakaoSendID.getButtonLinkPC()+linkCode, kakaoSendID.getButtonLinkMobile()+linkCode);
	}
	
	/**
	 * 2019.06.12 추가
	 * 
	 * 카카오 전송 메시지 바디 만들기
	 * @param kakaoSendID
	 * @param linkCode
	 * @param phone
	 * @return
	 */
	private String makeKakaoBodyMobileLink( EnumKakaoSendTemplate kakaoSendID, String linkCode, String phone) {
		
		return this.makeKakaoBodyOnlyForMobileLink(kakaoSendID.getMsg(), kakaoSendID.getId(), phone, kakaoSendID.getButtonName(), kakaoSendID.getButtonLinkMobile()+linkCode);
	}
	
	private String makeKakaoBodyNoLink( EnumKakaoSendTemplate kakaoSendID, String phone) {
		
		return this.makeKakaoBodyNoLink(kakaoSendID.getMsg(), kakaoSendID.getId(), phone);
	}
	
	/**
	 * 2019.06.12  추가
	 * 
	 * 카카오 전송 바디 만들기
	 * 
	 * @param kakaoSendID
	 * @param phone
	 * @return
	 */
	private String makeKakaoBody( EnumKakaoSendTemplate kakaoSendID, String phone) {
		return this.makeKakaoBody(kakaoSendID.getMsg(), kakaoSendID.getId(), phone, kakaoSendID.getButtonName(), kakaoSendID.getButtonLinkPC(), kakaoSendID.getButtonLinkMobile());
	}
	
	/**
	 * 카카오전송바디 만들기
	 * 
	 * @param msg
	 * @param tmpId
	 * @param phone
	 * @return
	 */
	private String makeKakaoBody(String msg, String tmpId, String phone) {
		
		JSONObject obj= new JSONObject();
		obj.put("userId", "");
		obj.put("tmplId", tmpId);
		obj.put("msg", msg);
		obj.put("phn", phone);
		obj.put("profile", "");
		obj.put("message_type", "at");
		
		obj.put("smsKind","L"); // SMS/LMS 구분(SMS: S, LMS: L)
		obj.put("msgSms",msg);  // LMS 전환발송을 위한 메시지
		obj.put("smsSender",""); // SMS 전환발송 시 발신번호
		obj.put("smsLmsTit",""); // LMS 발송 제목
		obj.put("smsOnly","N");
		
		JSONArray array = new JSONArray();
		array.put(obj);
		
		return array.toString();
	}
	
	/**
	 * 카카오 메세지 전송
	 * 
	 * @param request
	 * @param body
	 * @param tmpId
	 * @param phone
	 * @return
	 * @throws ExceptionBase
	 * @throws IOException
	 */
	private JSONObject runMessage( String body) throws ExceptionBase, IOException{
		
		String result = RequestWrapper.callOutsideAPI( request, EnumAPIMethod.POST, URL, body);
		
		if( result == null ) {
			throw new ExceptionBase(EnumHttpResponseCode.HTTP_ELSE, result);
		}
		
		JSONArray jsonArray = new JSONArray(result);
		if( jsonArray == null || jsonArray.length() == 0) {
			
			throw new ExceptionBase(EnumHttpResponseCode.HTTP_ELSE, result);
		}
		JSONObject tmpObj = jsonArray.getJSONObject(0);
		
		return tmpObj;

	}
	
	/**
	 * 카카초 전송처리
	 * 
	 * @param kakaoSendID
	 * @param phone
	 * @return
	 * @throws IOException 
	 * @throws ExceptionBase 
	 */
	public JSONObject sendKakao( EnumKakaoSendTemplate kakaoSendID, String phone) throws ExceptionBase, IOException {	
		
		String body = this.makeKakaoBody( kakaoSendID, phone);
		JSONObject result = this.runMessage(body);
		return result;
	}
	
	/**
	 * 카카오 전송처리
	 * 
	 * @param kakaoSendID
	 * @param code
	 * @param phone
	 * @return
	 * @throws ExceptionBase
	 * @throws IOException
	 */
	public JSONObject sendKakao( EnumKakaoSendTemplate kakaoSendID, String code, String phone) throws ExceptionBase, IOException {	
		
		String body = this.makeKakaoBody( kakaoSendID, code, phone);
		JSONObject result = this.runMessage(body);
		return result;
	}
	
	
	/**
	 * 카카오 전송처리_모바일링크만 전송
	 * 
	 * @param kakaoSendID
	 * @param code
	 * @param phone
	 * @return
	 * @throws ExceptionBase
	 * @throws IOException
	 */
	public JSONObject sendKakaoMobileLink( EnumKakaoSendTemplate kakaoSendID, String code, String phone) throws ExceptionBase, IOException {	
		
		String body = this.makeKakaoBodyMobileLink( kakaoSendID, code, phone);
		JSONObject result = this.runMessage(body);
		return result;
	}
	
	/**
	 * 카카오 전송처리_링크 없이 전송
	 * @param kakaoSendID
	 * @param code
	 * @param phone
	 * @return
	 * @throws ExceptionBase
	 * @throws IOException
	 */
	public JSONObject sendKakaoNoLink( EnumKakaoSendTemplate kakaoSendID, String phone) throws ExceptionBase, IOException {	
		
		String body = this.makeKakaoBodyNoLink( kakaoSendID, phone);
		JSONObject result = this.runMessage(body);
		return result;
	}
}
