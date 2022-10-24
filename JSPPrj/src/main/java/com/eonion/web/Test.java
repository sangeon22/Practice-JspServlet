package com.eonion.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("hello")
public class Test extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 사용자에게 보내는 방식이 UTF-8
		// 브라우저에서 읽을 수 있도록 Response Header에 심는다.
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
//		out.println("안녕 hi !!");
		
		
		// 사용자 입력 - GET 요청, QueryString
		String cnt_ = request.getParameter("cnt");
		int cnt = 100;
		if (cnt_ != null && !cnt_.equals(""))
			cnt = Integer.parseInt(cnt_);
		
		for (int i = 0; i < cnt; i++) {
			out.println((i+1) + "안녕 Servlet!! <br >");
		}
		
		
	}
}
