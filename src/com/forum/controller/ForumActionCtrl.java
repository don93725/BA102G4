package com.forum.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.don.util.Validation;
import com.forum.domain.Art_types;
import com.forum.domain.Forums;
import com.forum.service.Art_typesService;
import com.forum.service.ForumsService;
import com.google.gson.Gson;
import com.members.model.MembersVO;

/**
 * Servlet implementation class ForumApplyCtrl
 */
@WebServlet("/forum/ForumActionCtrl")
public class ForumActionCtrl extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		MembersVO user = (MembersVO) req.getSession().getAttribute("user");
		res.setContentType("text/html ; charset=utf-8 ");
		PrintWriter out = res.getWriter();
		HashMap<String,String> map = new HashMap<String,String>();

		if ("insert".equals(action)) {
			String mem_no = user.getMem_no();
			String forum_name = req.getParameter("forum_name");
			boolean valid = Validation.checkLengthOne2Ten(forum_name, "板塊名字", map);
			if(!valid){
				Gson gson = new Gson();
				out.write(gson.toJson(map));
				return;
			}
			String forum_desc = req.getParameter("forum_desc");
			valid = Validation.checkLengthOne2Thirty(forum_desc, "板塊敘述", map);
			if(!valid){
				Gson gson = new Gson();
				out.write(gson.toJson(map));
				return;
			}
			String forum_note = req.getParameter("forum_note");
			valid = Validation.checkLengthOne2Thirty(forum_note, "申請原因", map);
			if(!valid){
				Gson gson = new Gson();
				out.write(gson.toJson(map));
				return;
			}
			
			String[] art_type_name = req.getParameterValues("art_type_name");
			

			ForumsService forumsSevice = new ForumsService();
			boolean result = forumsSevice.add(mem_no, forum_name, forum_desc, forum_note, art_type_name);
			if(!result){
				out.write("{\"fail\":\"fail\"}");
				return;
			}
			return;

		} 
		if ("goUpdate".equals(action)) {
			String forum_no = req.getParameter("forum_no");
			ForumsService forumsSevice = new ForumsService();
			Gson gson = new Gson();
			Forums forums = forumsSevice.findByPK(forum_no);
			List<Art_types> art_types = new Art_typesService().getArt_types(forum_no);			
			String jsonStr = gson.toJson(forums);
			System.out.println(jsonStr);
			String jsonStr2 = gson.toJson(art_types);
			out.print(jsonStr+"|"+jsonStr2);
			return;
		}
		if ("update".equals(action)) {
			String forum_no = req.getParameter("forum_no");			
			String forum_desc = req.getParameter("forum_desc");
			boolean valid = Validation.checkLengthOne2Thirty(forum_desc, "板塊描述", map);
			if(!valid){
				Gson gson = new Gson();
				out.write(gson.toJson(map));
				return;
			}
			String[] art_type_name = req.getParameterValues("art_type_name");
			valid = false;
			for(String temp : art_type_name){
				if(temp.length()>0){
					valid = true;					
				}
			}
			if(!valid){
				out.write("{\"文章類型\":\"至少填入一個吧\"}");
				return;
			}
			ForumsService forumsSevice = new ForumsService();
			boolean result = forumsSevice.update(forum_no, forum_desc,art_type_name);			
			
			if(!result){
				System.out.println("進?");
				out.write("{\"fail\":\"fail\"}");
				return;
			}
		}


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
