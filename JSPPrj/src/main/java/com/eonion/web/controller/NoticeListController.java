package com.eonion.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eonion.web.entity.Notice;
import com.eonion.web.service.NoticeService;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String field_ = request.getParameter("field");
		String query_ = request.getParameter("keyWord");
		
		String field = "title";
		if(field_ != null) {
			if(field_.equals("writerId")) {
				field_ = "writer_id";
				System.out.println("============== ifififiif");
			}
			field = field_;				
		}
		System.out.println("============== "+field);
		String query = "";
		if (query_ != null) {
			query = query_;
		}
		
		NoticeService noticeService = new NoticeService();
		List<Notice> list = noticeService.getNoticeList(field, query, 1);
		
		request.setAttribute("list", list);
		
		//forward
		request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response);
		
	}

}
