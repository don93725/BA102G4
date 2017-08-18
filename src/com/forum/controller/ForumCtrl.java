package com.forum.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forum.dao.ArticlesDAO;
import com.forum.dao.ForumsDAO;
import com.forum.domain.Articles;
import com.forum.domain.Forums;


@WebServlet("/forum/ForumCtrl")
public class ForumCtrl extends HttpServlet {
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		ForumsDAO forumsDAO = new ForumsDAO();		
		List<Forums> offcialforums = forumsDAO.getAllOfficialForum();
		System.out.println(offcialforums.size());
		Map<String,Integer> countOfficialArticles= forumsDAO.countArticle("3");
		req.setAttribute("countOfficialArticles", countOfficialArticles);
		req.setAttribute("offcialforums", offcialforums);		
		List<Forums> forums = forumsDAO.getAll();
		req.setAttribute("forums", forums);		
		Map<String,Integer> countArticles= forumsDAO.countArticle("1");
		req.setAttribute("countArticles", countArticles);
		req.getRequestDispatcher("/front_end/forum/ForumMain.jsp").forward(req, res);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
