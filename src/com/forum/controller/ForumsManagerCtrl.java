package com.forum.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forum.domain.Forums;
import com.forum.service.ForumsService;
import com.members.model.MembersVO;
import com.message.model.MessageService;

/**
 * Servlet implementation class ForumsManagerCtrl
 */
@WebServlet("/forum/ForumsManagerCtrl")
public class ForumsManagerCtrl extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		MembersVO user = (MembersVO) req.getSession().getAttribute("user");
		if(user==null){
			//登入管理者葉面
			String URL = this.getServletContext().getContextPath()+"/LoginCtrl";
			res.sendRedirect(URL);
		}
		if ("3".equals(user.getMem_rank())) {			
			ForumsService forumsSevice = new ForumsService();
			//參數列重導此頁面?confirm= 1, 2 (1為審核通過2為不通過)
			if ("confirm".equals(action)) {					
				String forum_no = req.getParameter("forum_no");
				String forum_stat = req.getParameter("forum_stat");
				String rcv_no = req.getParameter("rcv_no");
				System.out.println(forum_no+" "+forum_stat);
				boolean result = forumsSevice.confirm(forum_no, forum_stat);
				
				if(rcv_no != null && result != false){
					MessageService messageSvc = new MessageService();
					System.out.println("rcv_no" + rcv_no);
					messageSvc.add(rcv_no,"0", "審核通過 , 恭喜開版");
				}
			}			
			int thisPage = req.getParameter("thisPage") == null ? 1 : Integer.parseInt(req.getParameter("thisPage"));
			int pageSize = 5;
			int allPageCount = forumsSevice.getApplyNum(pageSize);
			
			List<Forums> forums = forumsSevice.getApplyForums(thisPage, pageSize);
			req.setAttribute("forums", forums);			
			req.setAttribute("allPageCount", allPageCount);
			req.setAttribute("queryStr", "forum/ForumsManagerCtrl?");
			req.setAttribute("thisPage", thisPage);
			req.getRequestDispatcher("/back_end/forum/forumsReport.jsp").forward(req, res);
			//導向哪頁面還沒寫
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
