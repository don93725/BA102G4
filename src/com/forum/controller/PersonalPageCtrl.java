package com.forum.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PersonalPageCtrl
 */
@WebServlet("/PersonalPageCtrl")
public class PersonalPageCtrl extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.getWriter().print("這裡會員頁面喔");
		String mem_no = req.getParameter("mem_no");
		String mem_rank = req.getParameter("mem_rank");
		String url = req.getContextPath() +"/MembersServlet?mem_rank="+mem_rank+"&mem_no="+mem_no+"&action=lookPersonal";
		req.getRequestDispatcher(url).forward(req, res);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
