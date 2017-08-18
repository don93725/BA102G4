package com.forum.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.don.util.Validation;
import com.forum.dao.Art_typesDAO;
import com.forum.dao.ArticlesDAO;
import com.forum.domain.Art_types;
import com.forum.domain.Articles;
import com.forum.service.ArticlesService;
import com.google.gson.Gson;
import com.members.model.MembersVO;

/**
 * Servlet implementation class ArticlesMakerCtrl
 */
@WebServlet("/forum/ArticlesActionCtrl")
public class ArticlesActionCtrl extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		String forum_no = req.getParameter("forum_no");
		HttpSession session = req.getSession();
		MembersVO user = ((MembersVO) session.getAttribute("user"));
		System.out.println(action);
		if("check".equals(action)){
			res.setContentType("text/html ; charset=utf-8 ");
			PrintWriter out = res.getWriter();
			
			HashMap<String,String> map = new HashMap<String,String>();
			String art_name = req.getParameter("art_name").trim();
			boolean valid = Validation.checkLengthOne2Ten(art_name, "文章標題", map);
			if(!valid){
				Gson gson = new Gson();
				out.write(gson.toJson(map));
				return;
			}
			String art_ctx = req.getParameter("art_ctx").trim();
			valid = Validation.checkLengthTen2ThrH(art_ctx, "文章內文", map);
			if(!valid){
				Gson gson = new Gson();
				out.write(gson.toJson(map));
				return;
			}
			return;
		}
		if (action!=null&&(action.equals("goCreatePage") || action.equals("goUpdatePage")) && forum_no != null) {
			List<Art_types> art_types = new Art_typesDAO()
					.getVOBySQL("select * from art_types where forum_no=" + forum_no, null);
			req.setAttribute("art_types", art_types);
			if (action.equals("goUpdatePage")) {
				String art_no = req.getParameter("art_no");
				Articles articles = (Articles) new ArticlesDAO().getVOByPK(art_no);
				req.setAttribute("articles", articles);
			}
			req.getRequestDispatcher("/front_end/forum/ArticlesMaker.jsp").forward(req, res);
			return;

		}
		if (forum_no != null) {

			if ("create".equals(action)) {
				String art_name = req.getParameter("art_name");
				String art_type_name = req.getParameter("art_type_name");
				String art_ctx = req.getParameter("art_ctx").replace("$ProjectRealPath$", req.getContextPath());
				String mem_no = user.getMem_no();
				ArticlesService articlesSevice = new ArticlesService();
				boolean createResult = articlesSevice.add(mem_no, forum_no, art_type_name, art_name, art_ctx);
				if (createResult) {
					String URL = this.getServletContext().getContextPath() + "/forum/ForumShowCtrl?forum_no="
							+ forum_no;
					res.sendRedirect(URL);
				} else {
					req.setAttribute("msg", "失敗");
					req.getRequestDispatcher("/front_end/forum/ok.jsp").forward(req, res);
				}
			} else if ("update".equals(action)) {
				String art_name = req.getParameter("art_name");
				String art_type_name = req.getParameter("art_type_name");
				String art_no = req.getParameter("art_no");
				String art_ctx = req.getParameter("art_ctx");
				ArticlesService articlesSevice = new ArticlesService();
				boolean createResult = false;
				System.out.println(art_ctx);
				createResult = articlesSevice.update(art_type_name, art_name, art_ctx, art_no);

				if (createResult) {
					String URL = this.getServletContext().getContextPath() + "/forum/ArticleShowCtrl?forum_no="
							+ forum_no + "&art_no=" + art_no;
					res.sendRedirect(URL);
				} else {
					req.setAttribute("msg", "失敗");
					req.getRequestDispatcher("/front_end/forum/ok.jsp").forward(req, res);
				}
			} else if ("delete".equals(action)) {
				String art_no = req.getParameter("art_no");
				boolean result = new ArticlesDAO().executeDelete(art_no);
				if (result) {
					String URL = this.getServletContext().getContextPath() + "/forum/ForumShowCtrl?forum_no="
							+ forum_no;
					res.sendRedirect(URL);
				} else {
					req.setAttribute("msg", "fail to delete article");
					req.getRequestDispatcher("/front_end/forum/ok.jsp").forward(req, res);
				}
			} else if (action.equals("report")) {
				System.out.println("report?");
			} else {

			}

		} else {
			req.setAttribute("msg", "不知哪版怎去?");
			req.getRequestDispatcher("/front_end/forum/ok.jsp").forward(req, res);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
