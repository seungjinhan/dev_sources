package com.chunlab.app.token.engine;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import com.chunlab.app.system.json.JsonMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chunlab.app.system.enums.EnumExceptionOthers;
import com.chunlab.app.system.exception.ExceptionBase;
import com.chunlab.app.utils.date.DateUtil;
import com.chunlab.app.utils.date.enums.EnumDateType;
import com.chunlab.app.utils.others.ElseUtil;

/**
  * @FileName : TokenEngine.java
  * @Project : app_server
  * @Date : 2019. 9. 26. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 : 토큰처리 엔진
 */
public class TokenEngine extends TokenEngingSupport {

	private static Logger LOG = LoggerFactory.getLogger(TokenEngine.class);
	

    /**
     * 
      * @Method Name : getAccessTokenExpire
      * @작성일 : 2019. 9. 26.
      * @작성자 : seungjin.han
      * @변경이력 : 
      * @Method 설명 : Access Token 만료일 생성해서 리턴
      * @return
      * @throws ExceptionBase
     */
    public static long makeAccessTokenExpire() throws ExceptionBase {
    	
    	try {
    		
    		return Long.parseLong( DateUtil.addMiniutesFromNow(EnumDateType.YYYYMMDDHHmm , ACCESS_TOKEN_EXPIRE));
    	}
    	 catch (ParseException e) {
 			
 			throw new ExceptionBase(EnumExceptionOthers.ParseException, e.getMessage() );
 		}
    }
    


    /**
     * 
      * @Method Name : getAccessTokenNewExpire
      * @작성일 : 2019. 9. 26.
      * @작성자 : seungjin.han
      * @변경이력 : 
      * @Method 설명 : AccessToken에 만료일 업데이트
      * @param accessToken
      * @return
      * @throws ExceptionBase
     */
    public static String getAccessTokenWithNewExpire( String accessToken) throws ExceptionBase {
    	
    	LOG.debug("Access token Expire 재설정 요청 START:" + accessToken);
    	
    	// AccessToken Decoding하기
    	TokenCoreData accessTokenObject = decodingFromToken( accessToken);
    	
    	try {
    		
    		// 새로운 expire 조회
    		Long expire = makeAccessTokenExpire();// DateUtil.addMiniutesFromNow(EnumDateType.YYYYMMDDHHmm , ACCESS_TOKEN_EXPIRE);
    		
    		//accessTokenObject.put( TokenEngine.MAP_KEY_EXPIRE, expire);
    		accessTokenObject.expire = expire;
    		
    		String newAccessToken = encode(accessTokenObject, expire);    	
    		
    		LOG.debug("Access token Expire 재설정 요청  END :" + newAccessToken);
    		
    		return newAccessToken;
    		
		}  catch (UnsupportedEncodingException e) {
			
			throw new ExceptionBase(EnumExceptionOthers.UnsupportedEncodingException, e.getMessage() );
		}
    }

    
    /**
     * 
      * @Method Name : decode
      * @작성일 : 2019. 9. 26.
      * @작성자 : seungjin.han
      * @변경이력 : 
      * @Method 설명 : 토큰 데이터 디코딩
      * @param token
      * @return
      * @throws UnsupportedEncodingException
     */
    public static TokenCoreData decode(String token) throws UnsupportedEncodingException {
        
    	// String 특수문자 디코딩
        token = specialStringDecoding(token);
        
        String[] tokenArr = token.split(CHANGE_STR_CONNECTOR);
        
        String token1 = tokenArr[0];
        int token2 = Integer.parseInt(tokenArr[1]);
        String token3 = tokenArr[2];
        String token4 = tokenArr[3];
        int machingIndex =  Integer.parseInt( tokenArr[4]);
        token1 = String.valueOf( decodingNumber(token1, machingIndex)/token2);
        token4 = String.valueOf( decodingNumber(token4, machingIndex)/token2/2);
        
        int iCode = token2%2;
        iCode = iCode==0? iCode+1 : iCode;
        
        byte[] token3Byte = token3.getBytes("UTF-8");
        
        int index = 0;
        
        byte[] data = new byte[token3Byte.length];
        
        for (byte b : token3Byte) {
            
            byte temp = (byte) (b - (byte)iCode);
        
            data[index] = temp;
            
            index++;
        }
        token3 = new String( data,"UTF-8");
        
//        JsonMap result = new JsonMap();
//        
//        result.put( MAP_KEY_CREATE, token1);
//        result.put( MAP_KEY_DATA, token3.replace('(','{').replace(')','}'));
//        result.put( MAP_KEY_EXPIRE, token4);
//        
        TokenCoreData tokenCoreData = new TokenCoreData();
        tokenCoreData.createDate = Long.valueOf( token1);
        tokenCoreData.data = token3.replace('(','{').replace(')','}');
        tokenCoreData.expire = Long.valueOf( token4);
        
        return tokenCoreData;
    }
    


    /**
     * 
      * @Method Name : encode
      * @작성일 : 2019. 9. 26.
      * @작성자 : seungjin.han
      * @변경이력 : 
      * @Method 설명 : 토큰 데이터 인코딩 처리
      * @param msg
      * @param cDate
      * @return
      * @throws UnsupportedEncodingException
     */
    public static String encode( TokenCoreData msg, long cDate) throws UnsupportedEncodingException {
        
        long a1 = msg.createDate;// getLong("create");
        String a2 = msg.data.replace('{', '(').replace('}', ')');
        long a3 = msg.expire;
        
        int sum = String.valueOf(cDate).chars().distinct().sum();
        
        long token1 = a1*sum;
        long token3 = a3*sum*2;
        
        int machingIndex = ElseUtil.getRandomNumber(9);
        
        String token1Str = encodingNumber( String.valueOf(token1), machingIndex);
        String token3Str = encodingNumber( String.valueOf(token3), machingIndex);
        
        byte[] a = a2.getBytes("UTF-8");
        int iCode = sum%2;
        iCode = iCode==0? iCode+1 : iCode;
        
        byte[] data = new byte[a.length];
        
        int index = 0;
        for (byte b : a) {
                
            data[index] = (byte) (b + (byte)iCode);
            
            index++;
        }
        
        String token2Str = new String( data, "UTF-8");
        LOG.debug("BeforeEncoding: " + token2Str);
        token2Str = specialStringEncoding(token2Str);
        
        LOG.debug("BeforeEncoding END : " + token2Str);
        return token1Str+CHANGE_STR_CONNECTOR+sum+CHANGE_STR_CONNECTOR+token2Str+CHANGE_STR_CONNECTOR+token3Str+CHANGE_STR_CONNECTOR+machingIndex;
    }
    
    /**
     * 
      * @Method Name : makeAccessToken
      * @작성일 : 2019. 9. 26.
      * @작성자 : seungjin.han
      * @변경이력 : 
      * @Method 설명 : Access Token 생성
      * @param email
      * @return { 토큰, 만료일 , 이메일}
      * @throws ExceptionBase
     */
    public static TokenObjectInfo makeAccessToken( String email) throws ExceptionBase {
    	
    	LOG.debug("Access token 생성 요청 START:" + email);
    	
    	String token = "";
    	Long expire = null;
		try {
			
			// 만료일 가져오기
			expire = makeAccessTokenExpire();
			
//	    	JsonMap obj = new JsonMap();
//	    	obj.put( MAP_KEY_CREATE, System.nanoTime());
//	    	
	    	TokenCoreData coreData = new TokenCoreData();
	    	coreData.createDate = System.nanoTime();
	    	
	    	JsonMap data = new JsonMap();
	    	data.put( MAP_KEY_EMAIL, email);
	    	
	    	System.out.println("------------ makeAccessToken : token data ------------");
	    	System.out.println(data.toString());
	    	
//	    	obj.put( MAP_KEY_DATA, data.toString());
//	    	obj.put( MAP_KEY_EXPIRE, makeAccessTokenExpire());
	    	
	    	coreData.data = data.toString();
	    	coreData.expire = makeAccessTokenExpire();
	    	
			token = encode(coreData, expire);
			
			LOG.debug("Access token 생성 요청 END :" + token);
				
		}  catch (UnsupportedEncodingException e) {
			
			throw new ExceptionBase(EnumExceptionOthers.UnsupportedEncodingException, e.getMessage() );
		}
    	
    	return new TokenObjectInfo(token, expire, email);
    }

    /**
     * 
      * @Method Name : reSetAccessToken
      * @작성일 : 2019. 9. 26.
      * @작성자 : seungjin.han
      * @변경이력 : 
      * @Method 설명 : 새로운 토큰 생성
      * @param token
      * @return
      * @throws ExceptionBase
     */
    public static TokenObjectInfo reSetAccessToken( String token) throws ExceptionBase {
    	
    	LOG.debug("Access token 재 생성 요청 START:" + token);
    	
    	// 재생성을 원하는 access token에서 email을 꺼내서 새롭게 생성함
    	TokenCoreData accessTokenObject = decodingFromToken( token);
    	String email = new JsonMap( accessTokenObject.data).getString( TokenEngine.MAP_KEY_EMAIL);
    	
    	// 재생성
    	TokenObjectInfo newTokenInfo = makeAccessToken(email);
    	
    	LOG.debug("Access token 재 생성 요청  END :" + newTokenInfo);
    	
    	return newTokenInfo;
    }


    /**
      * @Method Name : decodingFromToken
      * @작성일 : 2019. 9. 26.
      * @작성자 : seungjin.han
      * @변경이력 : 
      * @Method 설명 : 토큰에서 데이터 꺼내기
      * @param token
      * @return
      * @throws ExceptionBase
     */
    public static TokenCoreData decodingFromToken( String token) throws ExceptionBase {
    	
    	TokenCoreData result = null;
    	
    	try {
    		
			result = decode(token);
			
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
			throw new ExceptionBase(EnumExceptionOthers.UnsupportedEncodingException, e.getMessage() );
		}
    	
    	return result;
    }
    

    /**
     * 
      * @Method Name : makeRefreshToken
      * @작성일 : 2019. 9. 26.
      * @작성자 : seungjin.han
      * @변경이력 : 
      * @Method 설명 : Refresh Token 생성
      * @param email
      * @return
      * @throws ExceptionBase
     */
    public static String makeRefreshToken( String email) throws ExceptionBase {
    	
    	String result = "";
    	String now = DateUtil.getDatabaseCreateDate();
    	Long expire;
    	try {
    		
    		LOG.debug("Refresh token 생성 요청  START :" + email);
    		
    		expire = DateUtil.addDaysFromToday(REFRESH_TOKEN_EXPIRE);
    		
//    		JsonMap obj = new JsonMap();
//    		obj.put( MAP_KEY_CREATE, now);
    		
    		JsonMap data = new JsonMap();
    		data.put( MAP_KEY_EMAIL, email);
    		
//    		obj.put( MAP_KEY_DATA, data.toString());
//    		obj.put( MAP_KEY_EXPIRE, expire);
    		
    		TokenCoreData coreData = new TokenCoreData();
    		coreData.data = data.toString();
    		coreData.expire = expire;
    		coreData.createDate = Long.parseLong(now);
    		
    		result = encode( coreData, expire);
    		
    		LOG.debug("Refresh token 생성 요청  END :" + result);
    		
    	} catch (ParseException e) {
			
			throw new ExceptionBase(EnumExceptionOthers.ParseException, e.getMessage() );
		} catch (UnsupportedEncodingException e) {
    		
    		throw new ExceptionBase(EnumExceptionOthers.UnsupportedEncodingException, e.getMessage() );
    	}
    	
    	return result;
    }


    /**
     * 
      * @Method Name : checkExpireAccessToken
      * @작성일 : 2019. 9. 26.
      * @작성자 : seungjin.han
      * @변경이력 : 
      * @Method 설명 : Access Token 만료일 확인
      * @param accessToken
      * @return
      * @throws ExceptionBase
     */
    public static boolean checkExpireAccessToken( String accessToken) throws ExceptionBase {
    	
    	try {
    		
    		LOG.debug("Access token 만료일 체크 :" + accessToken);
    		
    		TokenCoreData tokenDecode = decode(accessToken);
   			Long expire= tokenDecode.expire;
   			Long expireLong = expire;
   			Long now = Long.parseLong( DateUtil.now( EnumDateType.YYYYMMDDHHmm));
   			
   			LOG.debug("Access token 만료일 :" + expireLong);
   			
   			// 지금 시간보다 만료일이 더 크면 아직 유효시간 남음
   			if( now <= expireLong) {
   				
   				return true ;
   			}
   			else {
   				
   				return false;
   			}
		} catch (UnsupportedEncodingException e) {
			
			throw new ExceptionBase(EnumExceptionOthers.UnsupportedEncodingException, e.getMessage() );
		}
    }


    /**
     * 
      * @Method Name : checkExpireRefreshToken
      * @작성일 : 2019. 9. 26.
      * @작성자 : seungjin.han
      * @변경이력 : 
      * @Method 설명 : Refresh Token 만료일 체크
      * @param refreshToken
      * @return
      * @throws ExceptionBase
     */
    public static boolean checkExpireRefreshToken( String refreshToken) throws ExceptionBase {
    	
    	try {
    		
    		LOG.debug("Refresh token 만료일 체크 :" + refreshToken);
    		
    		TokenCoreData tokenDecode = decode(refreshToken);
   			Long expire= tokenDecode.expire;
   			Long expireLong = expire;
   			Long today = Long.parseLong( DateUtil.now( EnumDateType.YYYYMMDD));
   			
   			LOG.debug("Refresh token 만료일 :" + expireLong);
   			
   			if(  today <= expireLong ) {
   				
   				return true;
   			}
   			
   			return false;
		} catch (UnsupportedEncodingException e) {
			
			throw new ExceptionBase(EnumExceptionOthers.UnsupportedEncodingException, e.getMessage() );
		}
    	
    }

    /**
     * 
      * @Method Name : isAvailableAccessToken
      * @작성일 : 2019. 9. 26.
      * @작성자 : seungjin.han
      * @변경이력 : 
      * @Method 설명 : AccessToken 만료일 확인
      * @param accessExpire
      * @return
     */
    public static boolean isAvailableAccessToken( long accessExpire) {
    		
		Long now = Long.parseLong( DateUtil.now( EnumDateType.YYYYMMDDHHmm));
		
    	LOG.info("Today: " + now);
    	LOG.info("AccessToken Expire: " + accessExpire);
    	
		// 지금 시간보다 만료일이 더 크면 아직 유효시간 남음
		if( now <= accessExpire) {
			
			return true ;
		}
		else {
			
			return false;
		}
    }
    
    /**
      * @Method Name : isAvailableRefreshToken
      * @작성일 : 2019. 10. 31.
      * @작성자 : deepplin
      * @변경이력 : 
      * @Method 설명 :
      * @param refreshExpire
      * @return
     */
    public static boolean isAvailableRefreshToken( long refreshExpire) {
    	
    	Long now = Long.parseLong( DateUtil.now( EnumDateType.YYYYMMDD));
    	
    	LOG.info("Today: " + now);
    	LOG.info("RefreshToken Expire: " + refreshExpire);
    	
    	// 지금 시간보다 만료일이 더 크면 아직 유효시간 남음
    	if( now <= refreshExpire) {
    		
    		return true ;
    	}
    	else {
    		
    		return false;
    	}
    }

    /**
     * 
      * @FileName : TokenEngine.java
      * @Project : app_server
      * @Date : 2019. 11. 1. 
      * @작성자 : deepplin
      * @변경이력 :
      * @프로그램 설명 :토큰을 구성하는 핵심데이터
     */
    static class TokenCoreData{
    	
    	public long createDate;
    	public String data;
    	public long expire;
    	
    	
		public TokenCoreData() {
			super();
		}

		public TokenCoreData(Long createDate, String data, long expire) {
			super();
			this.createDate = createDate;
			this.data = data;
			this.expire = expire;
		}
    	
    }
    
    /**
     * 
      * @FileName : TokenEngine.java
      * @Project : app_server
      * @Date : 2019. 11. 1. 
      * @작성자 : deepplin
      * @변경이력 :
      * @프로그램 설명 : 토큰을 구성하는 정보
     */
    static class TokenObjectInfo{
    	public String token;
    	public long expire;
    	public String email;
		public TokenObjectInfo(String token, long expire, String email) {
			super();
			this.token = token;
			this.expire = expire;
			this.email = email;
		}
		@Override
		public String toString() {
			return "TokenInfo [token=" + token + ", expire=" + expire + ", email=" + email + "]";
		}
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException, ExceptionBase {
        
        TokenObjectInfo token = makeAccessToken("hyungseok.seo@chunlab.com");
        
        System.out.println(">  " + token.toString());
        System.out.println(">> " + decode(token.token));
        System.out.println(">>>" +  checkExpireAccessToken(token.token));

        String newToken = getAccessTokenWithNewExpire(token.token);
        
        System.out.println(">  " + newToken);
        System.out.println(">> " + decode(newToken));
        System.out.println(">>>" +  checkExpireAccessToken(newToken));
        
        String token2 = makeRefreshToken("hyungseok.seo@chunlab.com");
        System.out.println(token2);
        System.out.println(decode(token2));
        System.out.println(checkExpireRefreshToken(token2));
    }

}
