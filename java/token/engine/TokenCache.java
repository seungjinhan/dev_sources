package com.chunlab.app.token.engine;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import com.chunlab.admin.system.json.JsonMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chunlab.app.system.exception.ExceptionBase;
import com.chunlab.app.token.EnumExceptionToken;
import com.chunlab.app.token.TokenService;
import com.chunlab.app.token.TokenVo;
import com.chunlab.app.token.engine.TokenEngine.TokenCoreData;
import com.chunlab.app.token.engine.TokenEngine.TokenObjectInfo;
import com.chunlab.app.utils.date.DateUtil;
import com.chunlab.app.utils.date.enums.EnumDateType;
import com.chunlab.app.utils.list.ListManager;

/**
 * 
  * @FileName : TokenCache.java
  * @Project : app_server
  * @Date : 2019. 9. 26. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 : 토큰 캐쉬
 */
@Component
public class TokenCache {

	private static Logger LOG = LoggerFactory.getLogger(TokenCache.class);
	
	@Autowired
	private TokenService refreshTokenService;
	
	///////////////////////////////////////////////////////////
	///  토큰 정보를 저장하는 리스트
	private static List<TokenVo> tokenList = null;
	///////////////////////////////////////////////////////////
	
	/**
	 * AccessToken으로 List index 찾기
	 * 
	 * @param accessToken
	 * @return
	 */
	private int finterIndexByAccessToken( String accessToken){
		
		for (int i = 0; i < tokenList.size(); i++) {
			
			if( tokenList.get(i).getAccessToken().equals(accessToken)) {
				
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * Refresh Token으로 캐쉬데이터 조회
	 * 
	 * @param refreshToken
	 * @return
	 */
	private TokenVo getTokenInfoInCacheByRefreshToken( String refreshToken) {
		
		TokenVo vo = ListManager.filterForSingle( tokenList, (TokenVo t) -> t.getRefreshToken().equals( refreshToken));
		
		return vo;
	}
	
	/**
	 * Access Token으로 캐쉬데이터 조회
	 * 
	 * @param accessToken
	 * @return
	 */
	private TokenVo getTokenInfoInCacheByAccessToken( String accessToken) {
		
		TokenVo vo = ListManager.filterForSingle( tokenList, (TokenVo t) -> t.getAccessToken().equals( accessToken));
		return vo;
	}
	
	/**
	 * 이메일로 캐쉬데이터 조회
	 * 
	 * @param email
	 * @return
	 */
	private TokenVo getTokenInfoInCacheByEmail( String email) {
		
		TokenVo vo = ListManager.filterForSingle( tokenList, (TokenVo t) -> t.getEmail().equals( email));
		return vo;
	}
	
	/**
	 * 캐쉬 데이터 삭제
	 * @param email
	 */
	public boolean removeTokenDataInCache( String email) {
		
		TokenVo vo = this.getTokenInfoInCacheByEmail(email);
		return tokenList.remove(vo);
	}
	
	/**
	 * 토근을 초기화 함
	 * @throws ExceptionBase 
	 */
	public void initCache() throws ExceptionBase {
		
		tokenList = this.refreshTokenService.findAll();
		
		for (TokenVo tokenVo : tokenList) {
			
			TokenObjectInfo accessTokenInfo = TokenEngine.makeAccessToken( tokenVo.getEmail());
			tokenVo.setAccessToken( accessTokenInfo.token);
			tokenVo.setAccessTokenExpire( String.valueOf( accessTokenInfo.expire));
			
			// refresh token expire 저장
			TokenCoreData refreshTokenInfo = TokenEngine.decodingFromToken( tokenVo.getRefreshToken());
			//tokenVo.setRefreshTokenExpire( refreshTokenInfo. getString( TokenEngine.MAP_KEY_EXPIRE));
			tokenVo.setRefreshTokenExpire( String.valueOf(  refreshTokenInfo.expire));
		}
	}
	
	/**
	 * 유효기간 만료된건 삭제처리한다.
	 */
	public void removeAccessTokenByExpire() {
		
		LOG.info("유효기간 만료된 Access Token 제거  : START");
		Long now = Long.parseLong( DateUtil.now( EnumDateType.YYYYMMDDHHmm));
		
		// 만료시간이 지금보다 큰것을 조회함
		
		List< TokenVo> expired = ListManager.filterForMulti( tokenList, (TokenVo t) -> Long.parseLong(t.getAccessTokenExpire()) < now );

		tokenList.removeAll(expired);
		
		LOG.info("유효기간 만료된 Access Token 제거  : END");
	}
	
	/**
	 * Access token 디코딩하기
	 * 
	 * @param accessToken
	 * @return
	 * @throws ExceptionBase
	 */
	public TokenCoreData decodeAccessToken( String accessToken) throws ExceptionBase {
		
		LOG.info("Access Token Decoding START :" + accessToken );
		
		if( this.isAvailableAccessToken(accessToken) == true) {
			
			TokenCoreData tokenJson = TokenEngine.decodingFromToken(accessToken);
			
			LOG.info("Access Token Decoding END " + tokenJson);
			
			return tokenJson;
			
		}
		else {
			
			LOG.info("Access Token 만료됨 ");
			throw new ExceptionBase( EnumExceptionToken.EXPIRE);
		}
		
		
	}
	
	/**
	 * Access Token ReSet
	 * 
	 * @param token
	 * @throws ExceptionBase
	 */
	public void reSetAccessToken( String token) throws ExceptionBase {
		
		LOG.info("Access Token 재 생성 START :" + token );
		
		// 새토큰 생성
		TokenObjectInfo newTokenInfo = TokenEngine.reSetAccessToken(token);
		
		this.putTokenInfo(newTokenInfo);
		
		LOG.info("Access Token 재 생성 END :" + newTokenInfo );
	}
	
	/**
	 * Access toekn Expire 시간 재설정
	 * 
	 * @param accessToken
	 * @throws ExceptionBase
	 */
	public void reSetAccessTokenExpire( String accessToken) throws ExceptionBase {
		
		LOG.info("Access Token Expire 재 설정 START :" + accessToken );
		
		Integer index = this.finterIndexByAccessToken(accessToken);
		
		tokenList.get(index).setAccessTokenExpire( String.valueOf( TokenEngine.makeAccessTokenExpire()));
		
		LOG.info("Access Token Expire 재 설정 END " );
	}
	
	/**
	 * Access Token 조회
	 * 1. Refresh Token 유효성 검토
	 * 2. Email로 조회
	 * 
	 * @param refreshToken
	 * @return
	 * @throws ExceptionBase
	 */
	public TokenVo makeAccessTokenByRefreshToken( String refreshToken) throws ExceptionBase {
	
		TokenVo vo = this.getTokenInfoInCacheByRefreshToken(refreshToken);// this.filterForSingle( (TokenVo t) -> t.getRefreshToken().equals( refreshToken));
		
		// 리프레쉬 토큰이 만료됨 -> APP.Key를 받아서 재 생성해야 함
		if( vo == null) {
			
			throw new ExceptionBase( EnumExceptionToken.EXPIRE);
		}

		// 새로운 RefreshToken, AccessToken 가져옴
		TokenVo newVo = this.makeNewRefreshAndAccessToken(vo);
		
		
		// 기준 RefreshToken 정보를 제거하고 새로운 토큰정보 캐쉬에 저장
		this.putCacheTokenInfo(newVo, vo.getRefreshToken());
		
		return vo;
	}
	

	/**
	 * Access Token의 만료시간 조회
	 * 
	 * @param accessToken
	 * @return
	 * @throws ExceptionBase 
	 */
	public Long getAccessTokenExpire( String accessToken) throws ExceptionBase {
		
		TokenVo vo = this.getTokenInfoInCacheByAccessToken(accessToken);// filterForSingle((TokenVo t)->t.getAccessToken().contentEquals(accessToken));
		
		if( vo == null) {
			
			throw new ExceptionBase( EnumExceptionToken.NOT_EXIST_ACCESSTOKEN);
		}
		LOG.info("Access Token 만료 시간 조회 END : " + vo.getAccessTokenExpire() );
		
		return Long.parseLong( vo.getAccessTokenExpire());
	}
	
	/**
	  * @Method Name : getRefreshTokenExpire
	  * @작성일 : 2019. 10. 31.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 : Refresh Token 조회
	  * @param refreshToken
	  * @return
	  * @throws ExceptionBase
	 */
	public Long getRefreshTokenExpire( String refreshToken) throws ExceptionBase {
		
		TokenVo vo = this.getTokenInfoInCacheByRefreshToken( refreshToken);
		
		if( vo == null) {
			
			throw new ExceptionBase( EnumExceptionToken.NOT_EXIST_REFRESHTOKEN);
		}
		
		LOG.info("Refresh Token 만료 시간 조회 END : " + vo.getRefreshTokenExpire() );
		
		return Long.parseLong( vo.getRefreshTokenExpire());
	}
	
	
	/**
	 * Access token 유효성 확인
	 * 
	 * @param accessToken
	 * @return
	 * @throws ExceptionBase
	 */
	public boolean isAvailableAccessToken( String accessToken) throws ExceptionBase{
		
		Long expire = this.getAccessTokenExpire(accessToken);
		
		return TokenEngine.isAvailableAccessToken( expire);
	}
	
	/**
	  * @Method Name : isAvailableRefreshToken
	  * @작성일 : 2019. 10. 31.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 : Refresh token 유호성 체크
	  * @param refreshToken
	  * @return
	  * @throws ExceptionBase
	 */
	public boolean isAvailableRefreshToken( String refreshToken) throws ExceptionBase{
		
		Long expire = this.getRefreshTokenExpire(refreshToken);
		
		return TokenEngine.isAvailableRefreshToken(expire);
	}
	
	/**
	 * Access token 저장 ( token , 만료시간)
	 * 
	 * @param token
	 * @param expire
	 * @throws ExceptionBase 
	 */
	public void putTokenInfo( TokenObjectInfo tokenInfo) throws ExceptionBase {

		LOG.info("Access Token 생성 START :" + tokenInfo.toString() );
		
		String email =  tokenInfo.email;
		
		///////////////////////////////////////////////////////////
		//   이미 해당 email에 해당하는 A-Token이 있다면 해당 Token 을 제거해야 함
		/////////////////////////////////////////////////////////////
		TokenVo token = this.getTokenInfoInCacheByEmail(email);// this.filterForSingle( (TokenVo t) -> t.getEmail().equals(email));

		if( token == null) {
			
			LOG.info( email + " Token이 없음");
			
			throw new ExceptionBase( EnumExceptionToken.NOT_EXIST_TOKEN, email);
		}
		else {
			token.setAccessToken( tokenInfo.token);
			token.setAccessTokenExpire( String.valueOf( tokenInfo.expire));
		}
		
		LOG.info( "Cache 에 Access Token 저장 ");
		LOG.info( "token Info : "+token.toString());
		
		LOG.info("Access Token 생성 END " );
		
	}
	
	/**
	 * 이메일로 AccessToken 조회하ㅣ
	 * 
	 * @param email
	 * @return
	 * @throws ExceptionBase 
	 */
	public String getAccessTokenByEmail( String email) throws ExceptionBase {
		LOG.info("Email로 Access Token 조회 START: " + email );
		
		TokenVo vo = this.getTokenInfoInCacheByEmail(email);//filterForSingle((TokenVo t) -> email.equals( t.getEmail()));
		
		if( vo == null) {
			throw new ExceptionBase( EnumExceptionToken.NOT_EXIST_TOKEN);
		}
		
		LOG.info("Email로 Access Token 조회 END : " + vo.getAccessToken() );
		
		return vo.getAccessToken();
	}
	
	/**
	 * 토큰 리스트 
	 * 
	 * @return
	 */
	public List<TokenVo> getTokenList(){
	
		return tokenList;
	}
	
	
	/**
	 * AccessToken에서 이메일 조회
	 * @param token
	 * @return
	 * @throws ExceptionBase
	 */
	public String getEmailFromAccessToken( String token) throws ExceptionBase {
		
		LOG.info("Access Token 에서 Email 가져오기 START : " + token );
		TokenCoreData tokenJson = this.decodeAccessToken(token);
		LOG.info("dcoding data : " + tokenJson );
		//String email =new JsonMap( tokenJson.getString( TokenEngine.MAP_KEY_DATA)).getString( TokenEngine.MAP_KEY_EMAIL);
		
		String email =new JsonMap( tokenJson.data).getString( TokenEngine.MAP_KEY_EMAIL);
		
		LOG.info("Access Token 에서 Email 가져오기 END : " + email );
		return email;
	}
	
	/**
	 * AccessTokenList
	 * @return
	 */
	public List<String> getAccessTokenList() {
		
		List<String> accessTokenList = tokenList.stream().map(TokenVo::getAccessToken).collect( toList());
		
		return accessTokenList;
	}

	/**
	 * Refresh Token 생성 (access token 포함)
	 * 
	 * @param vo
	 * @param email
	 * @param appKey
	 * @return
	 * @throws ExceptionBase
	 */
	public TokenVo makeNewRefreshAndAccessToken(TokenVo vo) throws ExceptionBase {
		
		LOG.info("get New Refresh / new Access token Start ");
	
		TokenObjectInfo accessToken = TokenEngine.makeAccessToken( vo.getEmail());
		String refreshToken = TokenEngine.makeRefreshToken( vo.getEmail());
		
		TokenVo newVo = new TokenVo();
		
		newVo.setEmail( vo.getEmail());
		newVo.setAppDeviceNo( vo.getAppDeviceNo());
		newVo.setAccessToken( accessToken.token);
		newVo.setAccessTokenExpire( String.valueOf( accessToken.expire));
		newVo.setRefreshToken(refreshToken);
		newVo.setCreateDate( DateUtil.getDatabaseCreateDate());
		
		LOG.info("get New Refresh  / new Access token End : " + newVo.toString());
	
		return newVo;
	}
	
	/**
	 * 캐쉬에 앱키랑 정보를 저장 (기존 RefreshToken 정보는 리스트에서 우선 제거함)
	 * 
	 * @param vo
	 * @param email
	 * @param appKey
	 * @return
	 * @throws ExceptionBase
	 */
	public TokenVo putCacheTokenInfo( TokenVo vo, String oldRefreshToken) throws ExceptionBase {
	
		LOG.info("put cache token info Start : " + vo.toString());

		// 오래된 Refresh Token이 있다면 제거한다.
		TokenVo removeVo =  this.getTokenInfoInCacheByRefreshToken(oldRefreshToken);
		if( removeVo != null) {
			
			LOG.info("remove old info for refresh token: " + oldRefreshToken);
			tokenList.remove(removeVo);
		}
		
		// 새로운 정보를 추가
		LOG.info("add new token for refresh token: " + vo.getRefreshToken());
		
		TokenCoreData refreshTokenInfo = TokenEngine.decodingFromToken( vo.getRefreshToken());
		vo.setRefreshTokenExpire( String.valueOf( refreshTokenInfo.expire));
		
		tokenList.add(vo);
		
		LOG.info("put cache token info End");
	
		return vo;
	
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	// 토큰 테스트
	////////////////////////////////////////////////////////////////////////////////////////
	private List<String> appKeyList = new ArrayList<String>();
	
	private String makeAppKey( int i) {
		
		while(true) {
		
			String appKey = new String( ""+( Math.random() * 1000000 + (i*45)));
			if( appKeyList.contains(appKey) == false) {

				appKeyList.add(appKey);
				return appKey;
			}
		}
	}

	/**
	 * 토큰 정보 출력하기
	 * 
	 */
	public void showTokenList() {
		LOG.info("######### 토큰 정보 In Cache START############");
		tokenList.stream().forEach(System.out::println);
		LOG.info("######### 토큰 정보 In Cache END ############");
	}
	
	public static void main( String[] args) throws ExceptionBase {
//		
//		TokenCache tb = new TokenCache();
//		
//		for (int i = 0; i < 10; i++) {
//			
//			TokenVo vo = new TokenVo();
//			Long appDeviceNo = 1L;
//			String email = appDeviceNo+"@k.com";
//			String refreshToken = TokenEngine.makeRefreshToken(email);
//			String[] accessToken = TokenEngine.makeAccessToken(email);
//			
//			vo.setNo(i * 1L);
//			vo.setAppDeviceNo( appDeviceNo);
//			vo.setRefreshToken(refreshToken);
//			vo.setAccessToken(accessToken[0]);
//			vo.setAccessTokenExpire(accessToken[1]);
//			vo.setEmail(email);
//			vo.setCreateDate( DateUtil.getDatabaseCreateDate());
//
//			tb.tokenList.add(vo);
//		}
//		
//		TokenVo vo = new TokenVo();
//		
//		String appKey = "1234";
//		String email = appKey+"@k.com";
//		String refreshToken = TokenEngine.makeRefreshToken(email);
//		String[] accessToken = TokenEngine.makeAccessToken(email);
//		
//		vo.setNo(100L);
//		vo.setAppKey( appKey);
//		vo.setRefreshToken(refreshToken);
//		vo.setAccessToken(accessToken[0]);
//		vo.setAccessTokenExpire(accessToken[1]);
//		vo.setEmail(email);
//		vo.setCreateDate( DateUtil.getDatabaseCreateDate());
//		
//		tb.tokenList.add(vo);
//		
//		tb.showTokenList();
//		
//		System.out.println("__________________ TEST  ");
//		
//		String token = tb.getAccessTokenByEmail(email);
//		System.out.println(token);
//		System.out.println( tb.isAvailableAccessToken( token));
//		TokenVo token2 = tb.makeAccessTokenByRefreshToken(refreshToken);
//		
//		System.out.println(token);
//		System.out.println(token2);
//		if( token.equals( token2)) {
//			System.out.println("true");
//		}
//		else {
//			System.out.println(false);
//		}
//		System.out.println( tb.isAvailableAccessToken(token2.getAccessToken()));
//		System.out.println( tb.getEmailFromAccessToken(token2.getAccessToken()));
//		System.out.println( tb.decodeAccessToken(token2.getAccessToken()));
//		System.out.println( tb.getAccessTokenExpire(token2.getAccessToken()));
//		System.out.println( tb.getAccessTokenList());
//		
//		tb.showTokenList();
	}
}
