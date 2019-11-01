package com.chunlab.app.utils.email;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;

import org.json.JSONObject;

import com.chunlab.app.system.SystemPropertiesInfo;
import com.chunlab.app.system.exception.ExceptionBase;
import com.chunlab.app.utils.email.enums.EnumExceptionEmail;

/**
 * 
  * @FileName : EmailTemplateUtil.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public class EmailTemplateUtil {

	public static String getContent ( EnumEmailTemplateInfo fileName) throws ExceptionBase{ 

		String result = "";

		InputStream inputStream = null;
		StringBuilder sb = new StringBuilder();
		BufferedReader bufferedReader = null;
		try{
			
			inputStream = SystemPropertiesInfo.servletContext.getResourceAsStream("/WEB-INF/mail_html/" + fileName.getFileName() + ".html");
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

			String tempString = "";

			while ((tempString = bufferedReader.readLine()) != null){

				sb.append(tempString);
			}
			 
		}

		catch (IOException e){
			
			throw new ExceptionBase( EnumExceptionEmail.NOT_CONTENT_FILE);
		}
		finally{
			
			if ( inputStream != null){
				try{
					inputStream.close();
				}
				catch (IOException e){
					
					e.printStackTrace();
				}
			}
			if ( bufferedReader != null){
				try{
					bufferedReader.close();
				}
				catch (IOException e){
					
					e.printStackTrace();
				}
			}
		}

		result = sb.toString();

		result = result.replaceAll("__URL__", SystemPropertiesInfo.serverDomain);

		return result;
	}
	
	public static String getContent ( EnumEmailTemplateInfo fileName, JSONObject paramJson) throws ExceptionBase {

		String result = getContent(fileName);
		
		Set<String> keys = paramJson.keySet();
		for (String key : keys){
			String value = paramJson.getString(key);
			result = result.replaceAll(key, value);
		}

		return result;
	}
	
}
