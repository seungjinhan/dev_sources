package com.chunlab.app.system.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
  * @FileName : BaseRepository2.java
  * @Project : app_server
  * @Date : 2019. 10. 4. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
@Component
public class BaseRepository2 {

	@Autowired
	private EntityManagerFactory factory;
	
	/**
	 * 
	  * @Method Name : getEntityManager
	  * @작성일 : 2019. 10. 4.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 :
	  * @return
	 */
	public EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

}
