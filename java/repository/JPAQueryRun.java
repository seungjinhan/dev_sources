package com.chunlab.app.system.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.chunlab.app.system.enums.EnumExceptionDatabase;
import com.chunlab.app.system.exception.ExceptionBase;

/**
 * 
  * @FileName : JPAQueryRun.java
  * @Project : app_server
  * @Date : 2019. 10. 4. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 : JPA쿼리 실행 템플릿 ( T:Domain, R:Return값)
 */
public class JPAQueryRun<T, R>{

	/**
	  * @Method Name : run
	  * @작성일 : 2019. 10. 4.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 :
	  * @param em  :EntityManager
	  * @param t   :해당 Domain
	  * @param p   :JPAPredicate
	  * @return R
	  * @throws ExceptionBase
	 */
	public R run(EntityManager em, T t, JPAFunction<T, R> p) throws ExceptionBase {
		
		EntityTransaction tx = em.getTransaction();
		R result = null;
		tx.begin();
		try {
			
			result = p.run(em, t);
			
			tx.commit();
			
		} catch (ExceptionBase e) {
			
			tx.rollback();
			throw new ExceptionBase( e);
			
		} catch (Exception e) {
			
			tx.rollback();
			throw new ExceptionBase( EnumExceptionDatabase.JPA_ERROR, e);
			
		} finally {
			 
			em.close();
		}
		
		return result;
		
	}
}
