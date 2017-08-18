package com.forum.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.members.model.MembersVO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/forum/Login2")
public class Login2 extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		MembersVO members = new MembersVO();
		members.setMem_no("2");
		members.setMem_nickname("壞東");
		members.setMem_rank("2");
		session.setAttribute("user",members);
		res.setContentType("text/html; charset=utf-8");
		res.getWriter().println("login");
		System.out.println(((MembersVO)session.getAttribute("user")).getMem_nickname()+"登入了");
		req.getRequestDispatcher("/forum/ForumCtrl").forward(req, res);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
