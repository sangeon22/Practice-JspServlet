package com.eonion.web.controller.admin.notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eonion.web.entity.Notice;
import com.eonion.web.entity.NoticeView;
import com.eonion.web.service.NoticeService;

@WebServlet("/admin/board/notice/list")
public class ListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String field_ = request.getParameter("field");
		String query_ = request.getParameter("keyWord");
		String page_ = request.getParameter("page");

		String field = "title";
		if (field_ != null && !field_.equals("")) {
			if (field_.equals("writerId")) {
				field_ = "writer_id";
			}
			field = field_;
		}

		String query = "";
		if (query_ != null && !query_.equals("")) {
			query = query_;
		}

		int page = 1;
		if (page_ != null && !page_.equals("")) {
			page = Integer.parseInt(page_);
		}

		NoticeService noticeService = new NoticeService();
		List<NoticeView> list = noticeService.getNoticeList(field, query, page);
		int count = noticeService.getNoticeCount(field, query);

		request.setAttribute("list", list);
		request.setAttribute("count", count);

		// forward
		request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/list.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] openIds = request.getParameterValues("open-id");
		String[] delIds = request.getParameterValues("del-id");
		String cmd = request.getParameter("cmd");

		switch (cmd) {
		case "일괄공개":
			for (String openId : openIds) {
				System.out.printf("openId = %s\n", openId);
			}
			break;

		case "일괄삭제":
			NoticeService noticeService = new NoticeService();
			int[] ids = new int[delIds.length];
			for (int i = 0; i < delIds.length; i++) {
				ids[i] = Integer.parseInt(delIds[i]);
			}
			int result = noticeService.deleteNoticeAll(ids);
			break;
		}

		response.sendRedirect("list");

	}

}
