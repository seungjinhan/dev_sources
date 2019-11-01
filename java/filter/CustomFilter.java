package com.chunlab.app.system.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chunlab.app.system.exception.ExceptionBase;
import com.chunlab.app.system.filter.wapper.CustomHttpServletRequestWapper;
import com.chunlab.app.system.filter.wapper.CustomHttpServletResponseWapper;

/**
 * 
  * @FileName : CustomFilter.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public class CustomFilter implements Filter {

	private static final Logger LOG = LoggerFactory.getLogger(CustomFilter.class);
	private String encoding;
		
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		LOG.info("Filter init");
		
		this.encoding = filterConfig.getInitParameter("encoding");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws  IOException, ServletException {

		request.setCharacterEncoding( this.encoding);
		
		ServletRequest req;
		try {
			req = new CustomHttpServletRequestWapper( (HttpServletRequest) request);
		} catch (ExceptionBase e) {
			e.printStackTrace();
			throw new IOException( e.getData().toString());
		}
		ServletResponse res = new CustomHttpServletResponseWapper( (HttpServletResponse) response);
		
		LOG.info("before doFilter");
		chain.doFilter(req, response);
		LOG.info("after doFilter");
		if( response.isCommitted() == false) {
			response.getWriter().write( res.toString().toUpperCase());
		}
	}

	@Override
	public void destroy() {
		LOG.info("Filter destroy");

	}

}
