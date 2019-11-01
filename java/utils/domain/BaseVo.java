package com.chunlab.app.utils.domain;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.json.JSONException;
import org.json.JSONObject;

import com.chunlab.app.system.enums.EnumExceptionOthers;
import com.chunlab.app.system.exception.ExceptionBase;


/**
 * 
  * @FileName : BaseVo.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public class BaseVo implements Serializable {

	
	/**
	 * Domain 객체를 JSON으로 변환하기
	 * @return
	 * @throws ExceptionBase 
	 * @throws Exception
	 */
	public JSONObject getJsonObject( ) throws ExceptionBase{
		
		JSONObject j = new JSONObject();
		
		Class cls = this.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			
			field.setAccessible(true);
			
			try {
				if(  field.get(this) == null) {
					
					j.put(field.getName(), "");
				}
				else {
					
					j.put(field.getName(), field.get(this));
				}
			} catch (IllegalArgumentException e) {
				throw new ExceptionBase( EnumExceptionOthers.IllegalArgumentException, e);
			} catch (IllegalAccessException e) {
				throw new ExceptionBase( EnumExceptionOthers.IllegalAccessException, e);
			} catch (JSONException e) {
				throw new ExceptionBase( EnumExceptionOthers.JSONException, e);
			}
		}
		
		return j;
	}
	
//	public <T> void copyData( T oldObj) throws ExceptionBase {
//		
//		Field[] thisFields = this.getClass().getDeclaredFields();
//		Field[] oldFields = oldObj.getClass().getDeclaredFields();
//		
//		for (int i = 0; i < thisFields.length; i++) {
//
//			Field f = thisFields[i];
//			
//			f.setAccessible(true);
//			
//			if( !f.getName().equals("serialVersionUID")) {
//			
//				try {
//					f.set( thisFields, oldFields[i].get( oldObj));
//				} catch (IllegalArgumentException e) {
//					
//					throw new ExceptionBase( EnumExceptionOthers.IllegalArgumentException, e);
//					
//				} catch (IllegalAccessException e) {
//					
//					throw new ExceptionBase( EnumExceptionOthers.IllegalAccessException, e);
//				}
//			}
//			
//		}
//	}
	
	@Override
	public String toString() {
		
		try {
			return this.getJsonObject().toString();
		} catch (ExceptionBase e) {
			return e.toString();
		}
	}
} 
