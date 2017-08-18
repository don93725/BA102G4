package com.album.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.album.domain.Albums;
import com.album.service.AlbumsService;
import com.don.util.Validation;
import com.google.gson.Gson;
import com.members.model.MembersVO;

/**
 * Servlet implementation class AlbumActionCtrl
 */
@WebServlet("/album/AlbumsActionCtrl")
public class AlbumsActionCtrl extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");
		String mem_no = req.getParameter("mem_no");
		MembersVO user = (MembersVO) req.getSession().getAttribute("user");
		HashMap<String,String> map = new HashMap<String,String>();
		if (!mem_no.equals(user.getMem_no())||action==null) {
			//非會員想做其他操作
			String referer = (String) req.getSession().getAttribute("referer");
			req.getSession().removeAttribute("referer");
			if(referer!=null){						
				res.sendRedirect(referer);
			}else{
				res.sendRedirect(req.getContextPath()+"/index.jsp");
			}	
			return;		
		}

		if ("insert".equals(action)) {
			// 新增
			String al_name = req.getParameter("al_name");
			boolean valid = Validation.checkLengthOne2Ten(al_name, "al_name", map);
			if(!valid){
				Gson gson = new Gson();
				out.write(gson.toJson(map));
				return;
			}
			String al_prvt = req.getParameter("al_prvt");
			AlbumsService albumsService = new AlbumsService();
			boolean result = albumsService.add(user.getMem_no(), al_name, al_prvt);
			if (!result) {
				out.write("{\"fail\":\"fail\"}");
			} 
			return;
		}
		if ("update".equals(action)) {
			// 更新
			String al_no = req.getParameter("al_no");
			String al_name = req.getParameter("al_name");
			boolean valid = Validation.checkLengthOne2Ten(al_name, "al_name", map);
			if(!valid){
				Gson gson = new Gson();
				out.write(gson.toJson(map));
				return;
			}
			String al_prvt = req.getParameter("al_prvt");
			System.out.println(al_no+","+al_name+","+al_prvt);
			AlbumsService albumsService = new AlbumsService();
			boolean result = albumsService.update(al_no, al_name, al_prvt);
			
			if (!result) {
				out.write("{\"fail\":\"fail\"}");
			} 
			return;
		}
		if ("delete".equals(action)) {
			// 刪除
			String[] al_no = req.getParameterValues("al_no");
			AlbumsService albumsService = new AlbumsService();
			boolean result = albumsService.delete(al_no);
			if (!result) {
				out.write("{\"fail\":\"fail\"}");
			} 
			return;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
