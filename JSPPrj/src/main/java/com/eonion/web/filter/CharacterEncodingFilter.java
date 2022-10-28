package com.eonion.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {

	@Override
	public void doFilter(
			ServletRequest request,
			ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {
		
//		Servlet Filter는 뭔가 인증권한도 그렇고 Spring Security의 Authentication filter 역할이랑 비슷한것 같다..?
		
//		요청이 오면 흐름을 그냥 넘겨서 다음 필터나 서블릿이 실행되게함
		System.out.println("before filter");
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
		
//		필터가 실행된 후, response가 오면 아래가 실행됨
		System.out.println("after filter");
		
	}

}
