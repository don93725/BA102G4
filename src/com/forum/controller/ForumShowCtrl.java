package com.forum.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forum.dao.Art_typesDAO;
import com.forum.dao.ArticlesDAO;
import com.forum.dao.ForumsDAO;
import com.forum.domain.Art_types;
import com.forum.domain.Articles;
import com.forum.service.Art_typesService;
import com.forum.service.ArticlesService;
import com.forum.service.ForumsService;

/**
 * Servlet implementation class ForumShowCtrl
 */
@WebServlet("/forum/ForumShowCtrl")
public class ForumShowCtrl extends HttpServlet {
	@Override

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String forum_no = req.getParameter("forum_no");
		
		if(forum_no!=null){
			ArticlesDAO articlesDAO=new ArticlesDAO();
			String thisPage = req.getParameter("thisPage");
			if(thisPage==null){
				thisPage="1";
			}
			int pageSize = 8;
			String art_type_no = req.getParameter("art_type_no");
			int allPageCount=0;
			String art_type_name = null;
			String where = forum_no;
			String queryStr ="ForumShowCtrl?forum_no="+forum_no;
			if(art_type_no!=null) {
				queryStr = queryStr +"&art_type_no="+art_type_no;
				Object[] param = {art_type_no};
				art_type_name = (String)new Art_typesDAO().getCol("art_type_name", param)[0];
				where = where +" and art_type='"+art_type_name+"'";
				allPageCount = articlesDAO.countBySQL("select count(*) from articles where forum_no="+where);
			}else {
				allPageCount = articlesDAO.countBySQL("select count(*) from articles where forum_no='"+forum_no+"'");
			}
			List<Art_types> art_types = new Art_typesService().getArt_types(forum_no);
			req.setAttribute("art_types", art_types);
			allPageCount = (allPageCount-1)/pageSize+1;
			req.setAttribute("allPageCount", allPageCount);
			Object[] param = {forum_no};
			String forum_name= (String)new ForumsDAO().getCol("forum_name",param )[0];			
			req.setAttribute("forum_name", forum_name);
			req.setAttribute("queryStr", queryStr);				
			req.setAttribute("thisPage", thisPage);	
			ForumsService forumsSevice = new ForumsService();
			forumsSevice.increaseViews(forum_no);
			String mem_no = forumsSevice.getMem(forum_no);
			req.setAttribute("mem_no", mem_no);
			List<Articles> articles=new ArticlesService().getPageData(Integer.parseInt(thisPage), pageSize, where);
			req.setAttribute("articles", articles);
			req.getRequestDispatcher("/front_end/forum/Forum.jsp").forward(req, res);
		}else{
			req.setAttribute("msg", "不知哪版怎去?");
			req.getRequestDispatcher("/front_end/forum/ok.jsp").forward(req, res);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
