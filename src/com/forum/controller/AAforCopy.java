package com.forum.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.album.domain.Albums;
import com.album.service.AlbumsService;
import com.members.model.MembersVO;

@WebServlet("/forCopy")
public class AAforCopy extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		String mem_no = req.getParameter("mem_no");
		MembersVO user = (MembersVO) req.getSession().getAttribute("user");

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
			return;
		}
		if ("update".equals(action)) {
			// 修改
			return;
		}
		if ("delete".equals(action)) {
			// 刪除
			return;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
