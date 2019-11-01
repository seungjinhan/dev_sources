package com.chunlab.app.token;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.chunlab.app.system.repository.BaseRepository2;

/**
 * 
  * @FileName : TokenRepository2.java
  * @Project : app_server
  * @Date : 2019. 9. 26. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
@Repository
public class TokenRepository2 extends BaseRepository2{

	
	/**
	  * @Method Name : updateAppKey
	  * @작성일 : 2019. 10. 8.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 : app key update
	 */
	public int updateAppKeyNoTx( EntityManager em, String appKey, String email) {
		
		String SQL = "UPDATE TokenVo t " + 
				"			+  SET " + 
				"			+  t.appKey= '"+appKey+"', " + 
				"			+  WHERE t.email= '"+email+"'";
		return em.createQuery(SQL)
				.executeUpdate();
	}


}
