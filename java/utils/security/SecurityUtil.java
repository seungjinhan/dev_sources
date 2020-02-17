package com.chunlab.app.utils.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.chunlab.app.system.enums.EnumExceptionOthers;
import com.chunlab.app.system.exception.ExceptionBase;
import com.chunlab.app.utils.security.sha.ShaEncoding;

/**
 * 암호화 모듈
 * 
 * @package : com.chunlab.app.utils.security
 * @file    : SecurityUtil.java
 * @author  : seungjin.han
 * @Date    : 2018. 11. 6.
 * @version : 0.1
 */
public class SecurityUtil {


	/**
	 * SHA512암호화 (현재 패스워드 암호화에 사용중)
	 * 
	 * @param message
	 * @return
	 */
	public static String sha512Encoding ( String message) {
	    
		return ShaEncoding.sha512Encoding( message);
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
	public static String getCryptoHash(String msg, EnumCryptoHashType type) throws ExceptionBase {
		
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
		
		System.out.println( getCryptoHash("1234", EnumCryptoHashType.MD5));
		System.out.println( getCryptoHash("1234", EnumCryptoHashType.SHA1));
		System.out.println( getCryptoHash("1234", EnumCryptoHashType.SHA256));
		System.out.println( getCryptoHash("1234", EnumCryptoHashType.SHA512));
		
//		e4474c55142757985d80cf6c3e94026b
//		ae92fb361ba3b438a6a54fa177d022a7c36447bc
//		c8d970a26a0b439df8f819c10fe58d7c45a65e011dc45493c9c853235e5e3f69
//		4d0ac6583cc3e07dcc8c4ec6aa52f16c3a2d606396f43cb132a093cf8dae5775596f970ee82d71a9ccbdea59cfae8adad00683bf200adcd035289428b5ffc2c6

		System.out.println( sha512Encoding("1234"));
//		1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==		
		
		int a = 0;
		a += 1;
		
	}

}




