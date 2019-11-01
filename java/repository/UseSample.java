package com.chunlab.app.device;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.chunlab.app.system.enums.EnumExceptionDatabase;
import com.chunlab.app.system.exception.ExceptionBase;
import com.chunlab.app.system.repository.BaseRepository2;
import com.chunlab.app.system.repository.JPAFunction;
import com.chunlab.app.system.repository.JPAQueryRun;

/**
 * 
  * @FileName : AppDeviceRepository2.java
  * @Project : app_server
  * @Date : 2019. 10. 1. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
@Repository
public class UseSample extends BaseRepository2{

	/**
	 * 
	  * @Method Name : update
	  * @작성일 : 2019. 10. 4.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 : 업데이트하기
	  * @param vo
	  * @return
	  * @throws ExceptionBase
	 */
	public AppDeviceVo update( AppDeviceVo deviceVo) throws ExceptionBase {
		
		JPAQueryRun<AppDeviceVo, AppDeviceVo> sqlRunObj = new JPAQueryRun<AppDeviceVo, AppDeviceVo>();
		
		AppDeviceVo result = sqlRunObj.run( 
				getEntityManager(), 
				deviceVo, 
				new JPAFunction<AppDeviceVo, AppDeviceVo>() {
			
					@Override
					public AppDeviceVo run(EntityManager em, AppDeviceVo t) throws ExceptionBase {
						
						try {
							
							AppDeviceVo dbVo = em.find( AppDeviceVo.class, t.getNo());
							
							dbVo.setAppVersion( t.getAppVersion());
							dbVo.setDeviceOS( t.getDeviceOS());
							dbVo.setUpdateDate( t.getUpdateDate());
							dbVo.setAppKey( t.getAppKey());
							dbVo.setPushOption( t.getPushOption());
							dbVo.setPushToken( t.getPushToken());
							dbVo.setPushYN( t.getPushYN());
							dbVo.setUpdateDate( t.getUpdateDate());
							
							em.persist( dbVo);
							
							return dbVo;
						} catch (Exception e) {
							
							throw new ExceptionBase( EnumExceptionDatabase.UPDATE_ERROR, e);
						} 
					}
		});
		
		return result;
	}
	


}
