package com.chunlab.app.token;

//@Component
public class _TokenBox {

//	private static Logger LOG = LoggerFactory.getLogger(TokenBox.class);
//	
//	@Autowired
//	private TokenService refreshTokenService;
//	
//	// token : expire
//	private Map<String, Long> accessTokenMap = new HashMap<String, Long>();
//	
//	// email : token
//	private Map<String, String> accessTokenMapKeyEmail = new HashMap<String, String>();
//	
//	// token : tokenInfo
//	private Map<String, TokenVo> refreshTokenMap = new HashMap<String, TokenVo>();
//	
//	// email : token
//	private Map<String, String> refreshTokenMapKeyEmail = new HashMap<String, String>();
//
//	/**
//	 * 유효기간 만료된건 삭제처리한다.
//	 */
//	public void removeAccessTokenByExpire() {
//		
//		LOG.info("유효기간 만료된 Access Token 제거  : START");
//		
//		Set<String> tokenKey = accessTokenMap.keySet();
//		for (String key : tokenKey) {
//			
//			try {
//
//				// 유효기간 만료된건 삭제처리한다.
//				if(checkAccessTokenExpire(key) == false) {
//					
//					accessTokenMap.remove(key);
//					String email = getEmailFromAccessToken(key);
//					accessTokenMapKeyEmail.remove(email);
//					
//					LOG.info("유효기간 만료된 Access Token : " + email);
//				}
//				
//				
//			} catch (Exception e) {
//				// 예외가 발생해도 그냥 진행해야 한다.
//			}
//		}
//		
//		LOG.info("유효기간 만료된 Access Token 제거  : END");
//	}
//	
//	/**
//	 * Access token 디코딩하기
//	 * 
//	 * @param accessToken
//	 * @return
//	 * @throws ExceptionBase
//	 */
//	public JsonMap decodeAccessToken( String accessToken) throws ExceptionBase {
//		
//		LOG.info("Access Token Decoding START :" + accessToken );
//		
//		if( this.checkAccessTokenExpire(accessToken) == true) {
//			
//			JsonMap tokenJson = TokenEngine.decodingFromToken(accessToken);
//			
//			LOG.info("Access Token Decoding END " + tokenJson);
//			
//			return tokenJson;
//			
//		}
//		else {
//			
//			LOG.info("Access Token 만료됨 ");
//			throw new ExceptionBase( EnumExceptionToken.EXPIRE);
//		}
//		
//		
//	}
//	/**
//	 * Access Token ReSet
//	 * 
//	 * @param token
//	 * @throws ExceptionBase
//	 */
//	public void reSetAccessToken( String token) throws ExceptionBase {
//		
//		LOG.info("Access Token 재 생성 START :" + token );
//		
//		// 새토큰 생성
//		String newToken = TokenEngine.reSetAccessToken(token)[0];
//		
//		this.putAccessToken(newToken);
//		
//		LOG.info("Access Token 재 생성 END :" + newToken );
//	}
//	
//	/**
//	 * Access toekn Expire 시간 재설정
//	 * @param token
//	 * @throws ExceptionBase
//	 */
//	public void reSetAccessTokenExpire( String token) throws ExceptionBase {
//		
//		LOG.info("Access Token Expire 재 설정 START :" + token );
//		
//		this.accessTokenMap.put(token, Long.parseLong( TokenEngine.getAccessTokenExpire()));
//		
//		LOG.info("Access Token Expire 재 설정 END " );
//	}
//	
//	/**
//	 * Access Token 조회
//	 * 1. Refresh Token 유효성 검토
//	 * 2. Email로 조회
//	 * 
//	 * @param refreshToken
//	 * @return
//	 * @throws ExceptionBase
//	 */
//	public String getAccessTokenByRefreshToken( String refreshToken) throws ExceptionBase {
//	
//		TokenVo vo = this.refreshTokenMap.get(refreshToken);
//		
//		// 리프레쉬 토큰이 만료됨 -> APP.Key를 받아서 재 생성해야 함
//		if( vo == null) {
//			
//			throw new ExceptionBase( EnumExceptionToken.EXPIRE);
//		}
//		
//		// 해당 R-Token에 A-Token이 있는지 확인
//		// 있으면 기존 A-Token 제거
//		
//		
//		// 새로운 access token 생성
//		String newAccessToken = TokenEngine.makeAccessToken( vo.getEmail())[0];
//		
//		// AccessToken 저장
//		putAccessToken( newAccessToken);
//		
//		return newAccessToken;
//	}
//	
//	/**
//	 * Access Token의 만료시간 조회
//	 * 
//	 * @param accessToken
//	 * @return
//	 * @throws ExceptionBase 
//	 */
//	public Long getAccessTokenExpire( String accessToken) throws ExceptionBase {
//		
//		System.out.println("getAccessTokenExpire" + this.getAccessTokenMapKeyList());
//		System.out.println( accessToken);
//		Long expire = this.accessTokenMap.get(accessToken);
//		if( expire == null) {
//			
//			throw new ExceptionBase( EnumExceptionToken.NOT_EXIST_ACCESSTOKEN);
//		}
//		return expire;
//	}
//	
//	/**
//	 * Access token 유효성 확인
//	 * 
//	 * @param accessToken
//	 * @return
//	 * @throws ExceptionBase
//	 */
//	public boolean checkAccessTokenExpire( String accessToken) throws ExceptionBase{
//		
//		Long expire = this.getAccessTokenExpire(accessToken);
//		return TokenEngine.isAvailableAccessToken( expire);
//	}
//	
//	public void putAccessTokenUpdateExpire( String token) {
//		
//		LOG.info("Access Token 만료일 업데이트 START :" + token );
//		
//		
//		
//		LOG.info("Access Token 만료일 업데이트 END :" + token );
//	}
//	
//	/**
//	 * Access token 저장 ( token , 만료시간)
//	 * 
//	 * @param token
//	 * @param expire
//	 * @throws ExceptionBase 
//	 */
//	public void putAccessToken( String token) throws ExceptionBase {
//
//		LOG.info("Access Token 생성 START :" + token );
//		
//		JsonMap data = TokenEngine.decodingFromToken(token);
//		
//		LOG.info("decoding data :" + data.toString() );
//		
//		String email =  new JsonMap(data.getString(TokenEngine.MAP_KEY_DATA)).getString( TokenEngine.MAP_KEY_EMAIL);
//		LOG.info("email :" + email);
//		
//		/////////////////////////////////////////////////////////////
//		//   이미 해당 email에 해당하는 A-Token이 있다면 해당 Token 을 제거해야 함
//		/////////////////////////////////////////////////////////////
//		if( this.accessTokenMapKeyEmail.containsKey(email)) {
//			
//			LOG.info( email + " Token 이미 존재 -> 제거");
//			this.accessTokenMap.remove( this.accessTokenMapKeyEmail.get(email));
//		}
//		
//		LOG.info( "Cache 에 Access Token 저장 ");
//		LOG.info( "email: "+email);
//		LOG.info( "token: "+token);
//		
//		this.accessTokenMap.put(token, data.getLong( TokenEngine.MAP_KEY_EXPIRE));
//		this.accessTokenMapKeyEmail.put( email, token);
//		
//		LOG.info("Access Token 생성 END " );
//		
//	}
//	
//	/**
//	 * 이메일로 AccessToken 조회하ㅣ
//	 * 
//	 * @param email
//	 * @return
//	 */
//	public String getAccessTokenByEmail( String email) {
//		LOG.info("Email로 Access Token 조회 START: " + email );
//		String token = this.accessTokenMapKeyEmail.get(email); 
//		LOG.info("Email로 Access Token 조회 END : " + token );
//		return token;
//	}
//	
//	/**
//	 * AccessToken에서 이메일 조회
//	 * @param token
//	 * @return
//	 * @throws ExceptionBase
//	 */
//	public String getEmailFromAccessToken( String token) throws ExceptionBase {
//		
//		LOG.info("Access Token 에서 Email 가져오기 START : " + token );
//		JsonMap tokenJson = this.decodeAccessToken(token);
//		LOG.info("dcoding data : " + tokenJson );
//		String email =new JsonMap( tokenJson.getString( TokenEngine.MAP_KEY_DATA)).getString( TokenEngine.MAP_KEY_EMAIL); 
//		LOG.info("Access Token 에서 Email 가져오기 END : " + email );
//		return email;
//	}
//	
//	/**
//	 * AccessTokenList
//	 * @return
//	 */
//	public JsonList getAccessTokenMapKeyList() {
//		
//		JsonList accessKeyList = new JsonList();
//		
//		Set<String> accessTokenSet = this.accessTokenMap.keySet();
//		for (String key : accessTokenSet) {
//			accessKeyList.put(key);
//		}
//		
//		return accessKeyList;
//	}
//	
//	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//	
//	
//	/**
//	 * 리프레쉬토큰 메모리에 세팅
//	 * @throws ExceptionBase 
//	 */
//	public void putCacheRefreshTokenMap() throws ExceptionBase {
//		
//		LOG.info("db 에서 읽어서 refresh token 메모리에 저장  Start");
//		
//		List< TokenVo> refreshTokens =  refreshTokenService.findAll();
//		for (TokenVo vo : refreshTokens) {
//			
//			
//			this.putCacheRefreshTokenAndNewAccessToken(vo);
//		}
//		LOG.info("db 에서 읽어서 refresh token 메모리에 저장  end");
//	}
//
//	/**
//	 * Refresh 토큰 캐쉬에 저장
//	 * @param vo
//	 * @throws ExceptionBase 
//	 */
//	public void putCacheRefreshTokenAndAccessToken( TokenVo vo) throws ExceptionBase {
//	
//		LOG.info("Refresh / Access token save Cache Start :" + vo.toString());
//		
//		refreshTokenMap.put( vo.getRefreshToken(), vo);
//		refreshTokenMapKeyEmail.put( vo.getEmail(), vo.getRefreshToken());
//		
//		putAccessToken( vo.getAccessToken());
//		
//		LOG.info("Refresh / new Access token save Cache End : " + vo.toString());
//	}
//
//	
//	/**
//	 * 리프레쉬토큰, 액세스토큰(생성) 캐쉬에 저장 
//	 * 
//	 * @param vo
//	 * @throws ExceptionBase 
//	 */
//	public TokenVo putCacheRefreshTokenAndNewAccessToken( TokenVo vo) throws ExceptionBase {
//		
//		LOG.info("Refresh / new Access token save Cache Start :" + vo.toString());
//		
//		refreshTokenMap.put( vo.getRefreshToken(), vo);
//		refreshTokenMapKeyEmail.put( vo.getEmail(), vo.getRefreshToken());
//		
//		// Access Token 생성
//		String accessToken = TokenEngine.makeAccessToken( vo.getEmail())[0];
//		// Access token 저장
//		putAccessToken(accessToken);
//		
//		vo.setAccessToken(accessToken);
//		
//		LOG.info("Refresh / new Access token save Cache End : " + vo.toString());
//		
//		return vo;
//		
//	}
}
