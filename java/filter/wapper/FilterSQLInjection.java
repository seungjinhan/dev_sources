package com.chunlab.app.system.filter.wapper;

import java.util.Arrays;
import java.util.List;

/**
 * 
  * @FileName : FilterSQLInjection.java
  * @Project : app_server
  * @Date : 2019. 10. 4. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public class FilterSQLInjection {

	
	private static final List<String> words = Arrays.asList(
						"<SCRIPT>",
						"%3CSCRIPT%3E",
						"INSERTINFO",
						"DELETEFROM",
						"DROPTABLE",
						"'1'='1'",
						"1=1");
			
	public static boolean isSQLInject(String str) {
		
		str = str.toUpperCase().replaceAll(" ", "");
		
		for (String w : words) {
			
			if( str.contains( w)) {
				return true;
			}
			
		}
		return false;
	}
}
