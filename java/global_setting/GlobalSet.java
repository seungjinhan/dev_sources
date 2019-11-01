package com.chunlab.app.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
  * @FileName : GlobalSet.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 : 서버별로 필요한 세팅( Tomcat에 파일을 넣고 읽어 들임)file 위칠: /tomcat/conf/global.properties
 */
public class GlobalSet {

	private Logger LOG = LoggerFactory.getLogger(GlobalSet.class);

	public GlobalSet(){

		File configDir = new File(System.getProperty("catalina.base"), "conf");
		File configFile = new File(configDir, "global.properties");

		InputStream stream = null;
		try{
			
			stream = new FileInputStream(configFile);
		}
		catch (FileNotFoundException e){
			
			e.printStackTrace();
		}
		
		Properties props = new Properties();
		
		try{
			
			props.load(stream);
		}
		catch (IOException e){
			
			e.printStackTrace();
		}
		finally{

			try{
				
				stream.close();
			}
			catch (IOException e){
				
				e.printStackTrace();
			}
		}

		SystemPropertiesInfo.serverDomain = props.getProperty("server.domain");
		SystemPropertiesInfo.supportServerDomain = props.getProperty("support.server");
		SystemPropertiesInfo.serverName = props.getProperty("server.name");
		SystemPropertiesInfo.isServer = props.getProperty("is.server").equals("true")?true:false;

		System.out.println("############# TOMCAT GLOBAL PROPERTIES START #############");
		System.out.println("server.domain 		: " + SystemPropertiesInfo.serverDomain);
		System.out.println("support.server 		: " + SystemPropertiesInfo.supportServerDomain);
		System.out.println("server.name 		: " + SystemPropertiesInfo.serverName);
		System.out.println("is.server 		: " + SystemPropertiesInfo.isServer);
		System.out.println("############# TOMCAT GLOBAL PROPERTIES END #############");
		
	}
	

}
