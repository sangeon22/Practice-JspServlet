package com.eonion.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/spag")
public class Spag extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = 0;

		String num_ = request.getParameter("n");
		if(num_!=null && !num_.equals(""))
			num = Integer.parseInt(num_);
		
		String result;
		
		if(num % 2 != 0)
			result = "홀수";
		else
			result = "짝수";
		request.setAttribute("result", result);
		
		String[] names = {"eoni", "minsu"};
		request.setAttribute("names", names);
		
		Map<String, Object> notice = new HashMap<>();
		notice.put("id", 1);
		notice.put("title", "EL 표기");
		request.setAttribute("notice", notice);
		
		// forward - 현재 작업을 하던 것을 JSP에서 이어가도록
		RequestDispatcher dispatcher = request.getRequestDispatcher("spag.jsp");
		dispatcher.forward(request, response);
	}
}
