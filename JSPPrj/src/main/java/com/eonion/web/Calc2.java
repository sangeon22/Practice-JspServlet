package com.eonion.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Application 저장소로 사용 -> Servlet Context를 통한 상태유지, 자원공유?
		//Application 전역에서 사용 가능
		ServletContext application = request.getServletContext();
		
		//Session 객체로 사용 -> 상태를 저장하기 위해 사용
		//Session 범주 내에서 사용 가능(현재 접속한 사용자, 세션마다 공간이 개별적으로 부여)
		HttpSession session = request.getSession();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; Charset=UTF-8");
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
				
		int v = 0;
		if(!v_.equals("")) v = Integer.parseInt(v_);
		
		if(op.equals("=")) {
//			int x = (Integer) application.getAttribute("value");
			int x = (Integer) session.getAttribute("value");
			
			int y = v;
			
//			String operator = (String) application.getAttribute("op");
			String operator = (String) session.getAttribute("op");
			
			int result = 0;
			
			if(operator.equals("+")) result = x + y;
			else result = x - y;
			
			response.getWriter().printf("결과값 = %d", result);
				
		}else {
			//컬렉션프레임워크 Map이라고 생각 (key, value)
//			application.setAttribute("value", v);
//			application.setAttribute("op", op);
			
			session.setAttribute("value", v);
			session.setAttribute("op", op);
		}
		
	
	}

}
