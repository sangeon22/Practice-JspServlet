package com.eonion.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add2")
public class Add2 extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; Charset=UTF-8");
		
		String[] nums = request.getParameterValues("num");
		
		
		int result = 0;
				
		for (String n : nums) {
			int num = Integer.parseInt(n);
			result += num;
		}
		
		response.getWriter().printf("결과값 = %d", result);
	
	
	
	}

}
