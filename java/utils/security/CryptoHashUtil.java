package com.chunlab.app.utils.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.chunlab.app.system.enums.EnumExceptionOthers;
import com.chunlab.app.system.exception.ExceptionBase;

/**
 * 
  * @FileName : CryptoHashUtil.java
  * @Project : app_server
  * @Date : 2019. 12. 19. 
  * @작성자 : deepplin
  * @변경이력 :
  * @프로그램 설명 :
 */
public class CryptoHashUtil {

	enum  CRYPTOHASH_TYPE {
		
		MD5("MD5"), 
		SHA1("SHA-1"), 
		SHA256("SHA-256"),
		SHA512("SHA-512");
		
		private String type;
		
		CRYPTOHASH_TYPE(String type){
			this.type = type;
		}
		
		public String getType() {
			return this.type;
		}
		
	}
	
	/**
	  * @Method Name : getCryptoHash
	  * @작성일 : 2019. 12. 19.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 :
	  * @param msg
	  * @param type
	  * @return
	  * @throws ExceptionBase
	 */
	public static String getCryptoHash(String msg, CRYPTOHASH_TYPE type) throws ExceptionBase {
		
		try {
			
		    MessageDigest msgDigest = MessageDigest.getInstance(type.getType());
		
		    byte[] inputDigest = msgDigest.digest(msg.getBytes());
		
		    BigInteger inputDigestBigInt = new BigInteger(1, inputDigest);
		
		    String hashtext = inputDigestBigInt.toString(16);
		
		    while (hashtext.length() < 32) {
		        hashtext = "0" + hashtext;
		    }
		    return hashtext;
		}
		catch (NoSuchAlgorithmException e) {
			
		    throw new ExceptionBase( EnumExceptionOthers.NoSuchAlgorithmException, e.getMessage());
		}
	}
	
	public static void main( String[] args) throws ExceptionBase {
		
		System.out.println( CryptoHashUtil.getCryptoHash("1234", CRYPTOHASH_TYPE.SHA512));
	}
}
