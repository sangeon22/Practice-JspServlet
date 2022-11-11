package com.eonion.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; Charset=UTF-8");
		
		//1. Application 저장소로 사용 -> Servlet Context를 통한 상태유지, 자원공유?
		//   Application 전역에서 사용 가능
		ServletContext application = request.getServletContext();
		
		//2. Session 객체로 사용 -> 상태를 저장하기 위해 사용
		//   Session 범주 내에서 사용 가능(현재 접속한 사용자, 세션마다 공간이 개별적으로 부여)
		HttpSession session = request.getSession();
		
		//3. 사용자에게 생성해 보냈던 쿠키를 읽는 배열
		Cookie[] cookies = request.getCookies();
	
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
				
		int v = 0;
		if(!v_.equals("")) v = Integer.parseInt(v_);
		
		if(op.equals("=")) {
//			int x = (Integer) application.getAttribute("value");
//			int x = (Integer) session.getAttribute("value");
			
			// Cookies에서 하나씩 쿠키를 꺼내면서 키값에 맞는 밸류를 꺼냄
			int x = 0;
			for (Cookie c : cookies) {
				if(c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue());
					break;
				}
			}
			
			String operator = "";
			for (Cookie c : cookies) {
				if(c.getName().equals("op")) {
					operator = c.getValue();
					break;
				}
			}
			
				
			int y = v;
//			String operator = (String) application.getAttribute("op");
//			String operator = (String) session.getAttribute("op");
			
			int result = 0;
			
			if(operator.equals("+")) result = x + y;
			else result = x - y;
			
			response.getWriter().printf("결과값 = %d", result);
				
		}else {
			//컬렉션프레임워크 Map이라고 생각 (key, value)
//			application.setAttribute("value", v);
//			application.setAttribute("op", op);
			
//			session.setAttribute("value", v);
//			session.setAttribute("op", op);
			
			// 쿠키를 만들어서 response Header에 심어서 클라이언트에게 전달
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie opCookie = new Cookie("op", op);
			
			// 쿠키 옵션 - URL에 맞는 경우에만 쿠키를 가져오도록
			valueCookie.setPath("/calc2");
			opCookie.setPath("/calc2");
			
			// 쿠키의 만료시간을 주는 옵션(브라우저를 닫아도 유효)
			valueCookie.setMaxAge(24*60*60);
			
			response.addCookie(valueCookie);
			response.addCookie(opCookie);
		}
		
	
	}

}
