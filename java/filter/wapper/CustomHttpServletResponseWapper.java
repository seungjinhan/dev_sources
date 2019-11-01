package com.chunlab.app.system.filter.wapper;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 
  * @FileName : CustomHttpServletResponseWapper.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public class CustomHttpServletResponseWapper extends HttpServletResponseWrapper {

	protected CharArrayWriter charWriter;
	
	public CustomHttpServletResponseWapper(HttpServletResponse response) {
		super(response);
	}
	
	@Override
	public PrintWriter getWriter() throws IOException {
		return new PrintWriter(charWriter);
	}
	
	@Override
	public String toString() {
		return charWriter.toString();
	}
}
