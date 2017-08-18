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


@WebServlet("/forum/ForumCtrl2")
public class ForumCtrl2 extends HttpServlet {
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		ForumsDAO forumsDAO = new ForumsDAO();		
		List<Forums> list = forumsDAO.getAll();
		req.setAttribute("forums", list);
		List<Forums> rankList = forumsDAO.pageAndRank(1, 5, "forum_mviews desc", "forum_stat=1" );
		req.setAttribute("rankList", rankList);
		List<Articles> articlesRankList = new ArticlesDAO().pageAndRank(1, 5, "art_mviews desc", null );
		req.setAttribute("articlesRankList", articlesRankList);
		List<Articles> newestRankList = new ArticlesDAO().pageAndRank(1, 5, "art_add_date desc", null );
		req.setAttribute("newestRankList", newestRankList);
		Map<String,Integer> countArticles= forumsDAO.countArticle();
		req.setAttribute("countArticles", countArticles);
		req.getRequestDispatcher("/front_end/forum/ForumMain.jsp").forward(req, res);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
