package com.chunlab.app.token;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chunlab.app.system.exception.ExceptionBase;
import com.chunlab.app.system.json.JsonMap;
import com.chunlab.app.token.engine.TokenCache;
import com.chunlab.app.user.UserService;
import com.chunlab.app.user.UserVo;
import com.chunlab.app.utils.string.StringUtil;

/**
 * 
  * @FileName : TokenService.java
  * @Project : app_server
  * @Date : 2019. 9. 26. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 : 토큰 처리 서비스
 */
@Service
public class TokenService {

	@Autowired
	private TokenRepository tokenRepository;
	
	@Autowired
	private TokenRepository2 tokenRepository2;
	
	@Autowired
	private TokenCache tokenBox;
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * 토큰 정보 삭제하기
	 * 1. DB에서 삭제
	 * 2. Cache에서 삭제
	 * 
	 * @param email
	 * @throws ExceptionBase 
	 */
	public void removeTokenInfo( String email) throws ExceptionBase {
		
		// DB에 데이터 삭제
		int result = this.tokenRepository.deleteByEmail(email);
		if( result != 1) {
			
			throw new ExceptionBase( EnumExceptionToken.FAIL_REMOVE_DB, email);
		}
		
		// 캐쉬에 데이터 삭제
		boolean resultCache = this.tokenBox.removeTokenDataInCache(email);
		if( resultCache == false) {
			
			throw new ExceptionBase( EnumExceptionToken.FAIL_REMOVE_CACHE, email);
		}
	}

	
	/**
	 * 
	  * @Method Name : getAccessTokenByRefreshToken
	  * @작성일 : 2019. 10. 31.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 :Refresh / Access Token ReSET
	  * @param refreshToken
	  * @return
	  * @throws ExceptionBase
	 */
	public UserVo getAccessTokenByRefreshToken( String refreshToken) throws ExceptionBase {

		// DB에서 Refresh Token으로 조회
		Optional< TokenVo> voOption = this.tokenRepository.findByRefreshToken(refreshToken);
		TokenVo vo = voOption.orElseThrow( ()-> new ExceptionBase( EnumExceptionToken.NOT_EXIST_REFRESHTOKEN));
		
		// Refresh Token 만료 여부 확인
		boolean isRefeshTokenAvailable = this.tokenBox.isAvailableRefreshToken(refreshToken);
		if( isRefeshTokenAvailable == false) {
			
			throw new ExceptionBase( EnumExceptionToken.REFRESH_EXPIRE);
		}
		
		// refresh/ access token 재생성
		TokenVo newVo= this.makeNewRefreshTokenAndAccessToken( vo.getEmail());
		
		UserVo userVo = userService.findByAllEmail( vo.getEmail());
		userVo.setTokenInfo( Optional.ofNullable( newVo));
		
		String oldUserInfoString = userVo.getUserInfo();
		if( StringUtil.isEmpty(oldUserInfoString)) {
			oldUserInfoString = "{}";
		}
		userVo.setAdditionalInfo(new JsonMap(oldUserInfoString).toMap());
		userVo.setUserInfo(null);
		
		return userVo;
	}
	
	/**
	 * 
	  * @Method Name : makeNewRefreshTokenAndAccessToken
	  * @작성일 : 2019. 10. 8.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 :Refresh Token 생성
	  * @param voOption
	  * @param email
	  * @param appKey
	  * @return
	  * @throws ExceptionBase
	 */
	public TokenVo makeNewRefreshTokenAndAccessToken(  String email) throws ExceptionBase {
		
		Optional<TokenVo> voOption = this.tokenRepository.findByEmail(email);
		
		// 수정해야 하는지 여부
		boolean isModify = true;
		
		TokenVo vo = null;
		
		// 토큰 데이터가 없다면 최초 생성
		if( voOption.isPresent() == false) {

			vo = new TokenVo();
			vo.setEmail(email);
		
			// 수정이 아니라 생성
			isModify = false;
		}
		else {
			
			// 이미 있으면 토큰데이터를 가져옴
			vo = voOption.get();
		}
		
		// 새로운 AppDevice 데이터 세팅
		//vo.setAppDeviceNo( appDeviceNo);
		
		// 새 Access / Refresh Token 생성
		TokenVo newVo = this.tokenBox.makeNewRefreshAndAccessToken( vo);
		
		if( isModify) {
			
			// 수정
			this.tokenRepository.modifyByEmail( newVo.getAppDeviceNo(), newVo.getRefreshToken(), newVo.getCreateDate(), newVo.getEmail());
		}
		else {
			
			// 생성
			this.tokenRepository.save(newVo);
		}
		
		// Refresh/Access Token 캐쉬에 저장
		this.tokenBox.putCacheTokenInfo(newVo, vo.getRefreshToken());
		
		return newVo;
		
	}
//	public TokenVo makeNewRefreshTokenAndAccessToken(  String email, long appDeviceNo) throws ExceptionBase {
//		
//		Optional<TokenVo> voOption = this.tokenRepository.findByEmail(email);
//		
//		// 수정해야 하는지 여부
//		boolean isModify = true;
//		
//		TokenVo vo = null;
//		
//		// 토큰 데이터가 없다면 최초 생성
//		if( voOption.isPresent() == false) {
//			
//			vo = new TokenVo();
//			vo.setEmail(email);
//			
//			// 수정이 아니라 생성
//			isModify = false;
//		}
//		else {
//			
//			// 이미 있으면 토큰데이터를 가져옴
//			vo = voOption.get();
//		}
//		
//		// 새로운 AppDevice 데이터 세팅
//		vo.setAppDeviceNo( appDeviceNo);
//		
//		// 새 Access / Refresh Token 생성
//		TokenVo newVo = this.tokenBox.makeNewRefreshAndAccessToken( vo);
//		
//		if( isModify) {
//			
//			// 수정
//			this.tokenRepository.modifyByEmail( newVo.getAppDeviceNo(), newVo.getRefreshToken(), newVo.getCreateDate(), newVo.getEmail());
//		}
//		else {
//			
//			// 생성
//			this.tokenRepository.save(newVo);
//		}
//		
//		// Refresh/Access Token 캐쉬에 저장
//		this.tokenBox.putCacheTokenInfo(newVo, vo.getRefreshToken());
//		
//		return newVo;
//		
//	}
	
	/**
	 * 저장
	 * @param vo
	 * @return
	 */
	public TokenVo save( TokenVo vo) {
		
		return this.tokenRepository.save(vo);
	}
	
	/**
	 * 전체 조회
	 * @return
	 */
	public List< TokenVo> findAll(){
		
		return this.tokenRepository.findByNoGreaterThanEqual(0L).orElse( new ArrayList<TokenVo>());
	}
	
	/**
	 * appKey로 조회
	 * 
	 * @param appKey
	 * @return
	 * @throws ExceptionBase 
	 */
	public TokenVo findByAppDeviceNo( long appDeviceNo) throws ExceptionBase {
		
		Optional< TokenVo> vo = this.tokenRepository.findByAppDeviceNo(appDeviceNo);
		
		// 해당 앱키 데이터가 없음 -> 로그인 요청을 해야 함
		return vo.orElseThrow( () -> new ExceptionBase( EnumExceptionToken.NOT_EXIST_APPKEY, appDeviceNo));
	}
	
	/**
	  * @Method Name : findByEmail
	  * @작성일 : 2019. 10. 8.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 : 이메일로 조회하기
	  * @param email
	  * @return
	  * @throws ExceptionBase
	 */
	public TokenVo findByEmail( String email) throws ExceptionBase {
		
		Optional< TokenVo> vo = this.tokenRepository.findByEmail( email);
		
		// 해당 앱키 데이터가 없음 -> 로그인 요청을 해야 함
		return vo.orElseThrow( () -> new ExceptionBase( EnumExceptionToken.NOT_EXIST_APPKEY, email));
	}
	
	/**
	 * WEB용 토큰
	 * RT/AT 있으면 기존꺼 그대로 주고
	 * 없거나 만료 됐으면 새로 생성해서 준다
	 * @param email
	 * @작성자 : jiyeon
	 * @return
	 * @throws ExceptionBase 
	 */
	public TokenVo getRefreshTokenAndAccessToken( String email) throws ExceptionBase {
		
		Optional<TokenVo> voOption = this.tokenRepository.findByEmail(email);
		
		// 있으면 꺼내서 주고
		// 없거나 만료 됐으면 새로 생성해서 준다
		
		TokenVo vo = null;
		
		// 토큰 데이터가 없다면 최초 생성
		if( voOption.isPresent() == false) {

			vo = new TokenVo();
			vo.setEmail(email);
		
			// 새 Access / Refresh Token 생성
			vo = this.tokenBox.makeNewRefreshAndAccessToken( vo);
			// 생성
			this.tokenRepository.save(vo);
		}
		else {
			
			// 이미 있으면 토큰데이터를 가져옴
			vo = voOption.get();
			
			// RT 토큰 만료됐으면 각각 생성
			boolean isRefeshTokenAvailable = this.tokenBox.isAvailableRefreshToken(vo.getRefreshToken());
			if( isRefeshTokenAvailable == false) {
				vo =  this.tokenBox.makeNewRefreshToken(vo);
			}
			
			// AT 토큰 만료됐으면 각각 생성
			boolean isAccessTokenAvailable = this.tokenBox.isAvailableAccessToken(vo.getAccessToken());
			if( isAccessTokenAvailable == false ) {
				vo =  this.tokenBox.makeNewAccessToken(vo);
			} else {
				String accessToken = this.tokenBox.getAccessTokenByEmail( vo.getEmail());
				vo.setAccessToken( accessToken);
				vo.setAccessTokenExpire( String.valueOf( this.tokenBox.getAccessTokenExpire(accessToken)));
			}
		}
		
		// Refresh/Access Token 캐쉬에 저장
		this.tokenBox.putCacheTokenInfo(vo, vo.getRefreshToken());
		
		return vo;
		
	}
}
