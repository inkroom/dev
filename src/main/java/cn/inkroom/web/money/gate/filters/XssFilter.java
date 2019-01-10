package cn.inkroom.web.money.gate.filters;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class XssFilter extends OncePerRequestFilter {
	
	private final Log log = LogFactory.getLog(getClass());

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try{
			XSSRequestWrapper xssRequestWrapper = new XSSRequestWrapper(request);
			filterChain.doFilter(xssRequestWrapper, response);
		}catch(Exception e){
			log.error(e.getMessage());
		}
		
	}

	

	
}
