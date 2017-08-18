package com.forum.controller;

import java.io.IOException;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.don.inteface.DAOInterface;
import com.don.util.TransData;
import com.don.util.Validation;
import com.forum.dao.Article_commentsDAO;
import com.forum.domain.Article_comments;
import com.forum.service.Article_commentsService;
import com.google.gson.Gson;
import com.members.model.MembersVO;

@WebServlet("/forum/ArtCmtActionCtrl")
@MultipartConfig(fileSizeThreshold = 100, maxFileSize = 10 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024)
public class ArtCmtActionCtrl extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		MembersVO user = ((MembersVO) session.getAttribute("user"));
		String art_no = req.getParameter("art_no");
		String action = req.getParameter("action");
		if("check".equals(action)){
			res.setContentType("text/html ; charset=utf-8 ");
			PrintWriter out = res.getWriter();			
			HashMap<String,String> map = new HashMap<String,String>();
			String art_cmt_ctx = req.getParameter("art_cmt_ctx");	
			boolean valid = Validation.checkLengthTen2FakeThrH(art_cmt_ctx, "留言內文", map);
			if(!valid){
				Gson gson = new Gson();
				out.write(gson.toJson(map));
				return;
			}
			return;
		}
		if (art_no != null) {
			if ("create".equals(action)) {
				Part part = req.getPart("file");
				String mem_no = user.getMem_no();
				String art_cmt_ctx = req.getParameter("art_cmt_ctx");
				Article_commentsService article_commentsSevice = new Article_commentsService();
				boolean result = article_commentsSevice.add(art_no, mem_no, part, art_cmt_ctx, req.getContextPath());
				if (result) {
					String forum_no = req.getParameter("forum_no");
					String URL = getServletContext().getContextPath() + "/forum/ArticleShowCtrl?forum_no=" + forum_no
							+ "&art_no=" + art_no;
					res.sendRedirect(URL);
				} else {
					req.setAttribute("msg", "fail create comment");
					req.getRequestDispatcher("/front_end/forum/ok.jsp").forward(req, res);
				}
			} else if (action.equals("delete")) {
				Article_commentsDAO article_commentsDAO = new Article_commentsDAO();
				String art_cmt_no = req.getParameter("art_cmt_no");
				boolean result = article_commentsDAO.executeDelete(art_cmt_no);
				if (result) {
					String forum_no = req.getParameter("forum_no");
					String URL = getServletContext().getContextPath() + "/forum/ArticleShowCtrl?forum_no=" + forum_no
							+ "&art_no=" + art_no;
					res.sendRedirect(URL);
				} else {
					req.setAttribute("msg", "fail delete comment");
					req.getRequestDispatcher("/front_end/forum/ok.jsp").forward(req, res);
				}
			} else {
				req.setAttribute("msg", "from cmt els");
				req.getRequestDispatcher("/front_end/forum/ok.jsp").forward(req, res);
			}

		} else {
			req.setAttribute("msg", "from cmtMaker");
			req.getRequestDispatcher("/front_end/forum/ok.jsp").forward(req, res);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
