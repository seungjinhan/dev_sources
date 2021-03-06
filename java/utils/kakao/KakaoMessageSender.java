package com.chunlab.app.utils.kakao;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.chunlab.app.system.json.JsonList;
import com.chunlab.app.system.json.JsonMap;
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

	private final String URL = "https://alimtalk-api.bizmsg.kr/v1/sender/send";
	
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
		
		JsonMap obj= new JsonMap();
		obj.put("userId", "smilebiome");
		obj.put("tmplId", tmpId);
		obj.put("msg", msg);
		obj.put("phn", phone);
		obj.put("profile", "e79716d2ece4b66f192562ce4781c95d1428826d");
		obj.put("message_type", "at");
		
		JsonMap buttonObj= new JsonMap();
		buttonObj.put("type", "WL");
		buttonObj.put("name", buttonTitle);
		buttonObj.put("url_pc", pcLink);
		buttonObj.put("url_mobile", mobileLink);
		obj.put("button1", buttonObj);
		
		obj.put("smsKind","L"); // SMS/LMS 구분(SMS: S, LMS: L)
		obj.put("msgSms",msg);  // LMS 전환발송을 위한 메시지
		obj.put("smsSender","070-4350-6191"); // SMS 전환발송 시 발신번호
		obj.put("smsLmsTit","스마일바이오미"); // LMS 발송 제목
		obj.put("smsOnly","N");
		
		JsonList array = new JsonList();
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
		
		JsonMap obj= new JsonMap();
		obj.put("userId", "smilebiome");
		obj.put("tmplId", tmpId);
		obj.put("msg", msg);
		obj.put("phn", phone);
		obj.put("profile", "e79716d2ece4b66f192562ce4781c95d1428826d");
		obj.put("message_type", "at");
		
		JsonMap buttonObj= new JsonMap();
		buttonObj.put("type", "WL");
		buttonObj.put("name", buttonTitle);
		buttonObj.put("url_mobile", mobileLink);
		obj.put("button1", buttonObj);
		
		obj.put("smsKind","L"); // SMS/LMS 구분(SMS: S, LMS: L)
		obj.put("msgSms",msg);  // LMS 전환발송을 위한 메시지
		obj.put("smsSender","070-4350-6191"); // SMS 전환발송 시 발신번호
		obj.put("smsLmsTit","스마일바이오미"); // LMS 발송 제목
		obj.put("smsOnly","N");
		
		JsonList array = new JsonList();
		array.put(obj);
		
		return array.toString();
	}

	private String makeKakaoBodyNoLink(String msg, String tmpId, String phone) {
		
		JsonMap obj= new JsonMap();
		obj.put("userId", "smilebiome");
		obj.put("tmplId", tmpId);
		obj.put("msg", msg);
		obj.put("phn", phone);
		obj.put("profile", "e79716d2ece4b66f192562ce4781c95d1428826d");
		obj.put("message_type", "at");
		
		obj.put("smsKind","L"); // SMS/LMS 구분(SMS: S, LMS: L)
		obj.put("msgSms",msg);  // LMS 전환발송을 위한 메시지
		obj.put("smsSender","070-4350-6191"); // SMS 전환발송 시 발신번호
		obj.put("smsLmsTit","스마일바이오미"); // LMS 발송 제목
		obj.put("smsOnly","N");
		
		JsonList array = new JsonList();
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
		
		JsonMap obj= new JsonMap();
		obj.put("userId", "smilebiome");
		obj.put("tmplId", tmpId);
		obj.put("msg", msg);
		obj.put("phn", phone);
		obj.put("profile", "e79716d2ece4b66f192562ce4781c95d1428826d");
		obj.put("message_type", "at");
		
		obj.put("smsKind","L"); // SMS/LMS 구분(SMS: S, LMS: L)
		obj.put("msgSms",msg);  // LMS 전환발송을 위한 메시지
		obj.put("smsSender","070-4350-6191"); // SMS 전환발송 시 발신번호
		obj.put("smsLmsTit","스마일바이오미"); // LMS 발송 제목
		obj.put("smsOnly","N");
		
		JsonList array = new JsonList();
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
	private JsonMap runMessage( String body) throws ExceptionBase, IOException{
		
		String result = RequestWrapper.callOutsideAPI( request, EnumAPIMethod.POST, URL, body);
		
		if( result == null ) {
			throw new ExceptionBase(EnumHttpResponseCode.HTTP_ELSE, result);
		}
		
		JsonList jsonArray = new JsonList(result);
		if( jsonArray == null || jsonArray.length() == 0) {
			
			throw new ExceptionBase(EnumHttpResponseCode.HTTP_ELSE, result);
		}
		JsonMap tmpObj = jsonArray.getJsonMap(0);
		
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
	public JsonMap sendKakao( EnumKakaoSendTemplate kakaoSendID, String phone) throws ExceptionBase, IOException {	
		
		String body = this.makeKakaoBody( kakaoSendID, phone);
		JsonMap result = this.runMessage(body);
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
	public JsonMap sendKakao( EnumKakaoSendTemplate kakaoSendID, String code, String phone) throws ExceptionBase, IOException {	
		
		String body = this.makeKakaoBody( kakaoSendID, code, phone);
		JsonMap result = this.runMessage(body);
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
	public JsonMap sendKakaoMobileLink( EnumKakaoSendTemplate kakaoSendID, String code, String phone) throws ExceptionBase, IOException {	
		
		String body = this.makeKakaoBodyMobileLink( kakaoSendID, code, phone);
		JsonMap result = this.runMessage(body);
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
	public JsonMap sendKakaoNoLink( EnumKakaoSendTemplate kakaoSendID, String phone) throws ExceptionBase, IOException {	
		
		String body = this.makeKakaoBodyNoLink( kakaoSendID, phone);
		JsonMap result = this.runMessage(body);
		return result;
	}
}
