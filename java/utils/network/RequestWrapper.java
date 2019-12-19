package com.chunlab.app.utils.network;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.chunlab.admin.system.json.JsonList;
import com.chunlab.admin.system.json.JsonMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chunlab.app.system.exception.ExceptionBase;
import com.chunlab.app.utils.network.enums.EnumAPIMethod;
import com.chunlab.app.utils.network.enums.EnumHttpResponseCode;


/**
 * 
  * @FileName : RequestWrapper.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public class RequestWrapper {

    private static Logger LOG = LoggerFactory.getLogger(RequestWrapper.class);

    /**
     * 
     * @param request
     * @param method
     * @param url
     * @param header
     * @param body
     * @return
     * @throws IOException
     * @throws ExceptionBase 
     * @throws ErrorCode406Exception
     * @throws ErrorCode401Exception
     * @throws ErrorCode202Exception
     * @throws ExceptionServerCall
     */
    private static HttpServletRequest getNewResponseAttribute(
    						HttpServletRequest request, 
    						String method, 
    						String url,
    						HashMap<String, String> header, 
    						String body) throws ExceptionBase, IOException {
	
		LOG.info("#####################################");
		LOG.info("URL : " + url);
		LOG.info("HEAD : " + header);
		LOG.info("BODY : " + body);
		LOG.info("#####################################");
	
		int status = 0;
	
		URL apiURL = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) apiURL.openConnection();
		connection.setRequestMethod(method);
	
		if (header != null) {
	
		    for (Object key : header.keySet()) {
		    	
		    	connection.setRequestProperty(key.toString(), header.get(key).toString());
		    }
		}
	
		connection.setDoOutput(true);
		connection.connect();
	
		if (body != null) {
	
		    OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
	
		    String strReqBody = "";
	
		    if (body.startsWith("[")) {
		    	
		    	JsonList ja_req_body = new JsonList(body);
		    	strReqBody = ja_req_body.toString();
		    } else if (body.startsWith("{")) {
		    	
		    	JsonMap jo_req_body = new JsonMap(body);
		    	strReqBody = jo_req_body.toString();
		    }
	
		    out.write(strReqBody);
	
		    out.flush();
		    out.close();
		}
	
		status = connection.getResponseCode();
		request.setAttribute("status", status);
	
		if (String.valueOf(status).startsWith("40")) {
			
		    throw new ExceptionBase( EnumHttpResponseCode.HTTP_40X, url +":"+header+":"+body+":"+ status);
		    
		} else if (String.valueOf(status).startsWith("50")) {
	
			throw new ExceptionBase( EnumHttpResponseCode.HTTP_50X, url +":"+header+":"+body+":"+ status);
		} 
		
		StringBuffer sb = new StringBuffer();
	
		InputStream input = new BufferedInputStream(connection.getInputStream());
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	
		String line;
	
		while ((line = reader.readLine()) != null) {
	
		    sb.append(line);
		}
	
		request.setAttribute("body", sb.toString());
	
		Map<String, List<String>> map_header = connection.getHeaderFields();
	
		JsonMap jo_res_header = new JsonMap();
		for (String key : map_header.keySet()) {
	
		    if (key != null) {
	
				List<String> entry = map_header.get(key);
				jo_res_header.put(key, entry);
		    }
		}
	
		request.setAttribute("header", jo_res_header.toString());
	
		return request;
    }



    /**
     * 외부 API를 호출할때 사용
     * 
     * @param request
     * @param method
     * @param url
     * @param body
     * @return
     * @throws IOException
     * @throws ExceptionBase 
     * @throws ErrorCode406Exception
     * @throws ErrorCode401Exception
     * @throws ErrorCode202Exception
     * @throws ExceptionServerCall
     */
    public static String callOutsideAPI(HttpServletRequest request, EnumAPIMethod method, String url, String body) throws IOException, ExceptionBase{
    	
		String api_method = method.name();
		String api_url = url;
	
		HashMap<String, String> api_header = new HashMap<String, String>();
	
		api_header.put("Content-Type", "application/json; charset=UTF-8");
	   	
		request = getNewResponseAttribute(request, api_method, api_url, api_header, body);
	
		return request.getAttribute("body").toString();
    }
	
}
