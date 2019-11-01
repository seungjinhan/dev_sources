package com.chunlab.app.system.attact;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

/**
 * 
  * @FileName : FilterSQLInjection.java
  * @Project : app_server
  * @Date : 2019. 10. 4. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public class SQLInjectionChecker {

	
	private static final List<String> words = Arrays.asList(
						"<SCRIPT>",
						"%3CSCRIPT%3E",
						"INSERTINFO",
						"DELETEFROM",
						"DROPTABLE",
						"'1'='1'",
						"1=1");

	/**
	 * 
	  * @Method Name : isSQLInject
	  * @작성일 : 2019. 10. 7.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 : 체크 문자가 포함이면 true를 반환
	  * @param request
	  * @return
	 */
	public static Optional< List<String[]>> isSQLInject( HttpServletRequest request) {
		
		// request 스트링을 처리 해야 함
		Map<String, String[]> paramData =  request.getParameterMap();
		
		List<String[]> result = paramData.entrySet().stream().filter( t -> {
			
			String[] val = t.getValue();
			if( val.length == 1) {
				
				return words.stream().filter( w -> {
					boolean b = val[0].toUpperCase().trim().contains( w);
					return b;
						})
						.limit(1)
						.count() > 0;
			}
			else {
				
				for (int i = 0; i < val.length; i++) {
					
					final int i2 = i;
					long count2 = words.stream().filter( w -> {
						return val[i2].toUpperCase().trim().contains( w);
					}).limit(1)
					.count();
					if( count2 > 0) {
						
						return true;
					}
					
				}
			}
			return false;
			
		}).limit(1)
		.map(d -> d.getValue())
		.collect( Collectors.toList());
		
		return Optional.ofNullable( result);
	}
}
