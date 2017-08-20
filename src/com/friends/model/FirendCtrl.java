package com.friends.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.members.model.MembersVO;

/**
 * Servlet implementation class FirendCtrl
 */
@WebServlet("/friends/FirendCtrl")
public class FirendCtrl extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		String mem_no = req.getParameter("mem_no");
		MembersVO user = (MembersVO) req.getSession().getAttribute("user");

		if (user==null) {
			//非會員想做
			return;		
		}
		if(action==null){		
			FriendsService friendsService = new FriendsService();
			int thisPage = (req.getParameter("thisPage")==null)? 1:Integer.parseInt(req.getParameter("thisPage"));
			int pageSize = 8;
			List<Friends> friendPageList = friendsService.getPageFriendList(thisPage, pageSize, mem_no);			
			int allPageCount = friendsService.getFriendNum(mem_no);
			req.setAttribute("friendPageList", friendPageList);
			req.setAttribute("thisPage", thisPage);
			req.setAttribute("friendsService", friendsService);
			req.setAttribute("queryStr", "/friends/FirendCtrl?mem_no="+mem_no);
			req.getRequestDispatcher("/front_end/friends/Friends.jsp").forward(req, res);
			return;
		}
		if ("insert".equals(action)) {
			// 新增
			FriendsService friendsService = new FriendsService();
			boolean result = friendsService.add(mem_no, user.getMem_no());
			PrintWriter out =res.getWriter();
			if(result){
			}else{
				out.print("{\"添加\":\"失敗\"}");		
			}
			return;
		}

		if ("delete".equals(action)) {
			// 刪除
			FriendsService friendsService = new FriendsService();
			boolean result = friendsService.delete(mem_no, user.getMem_no());
			PrintWriter out =res.getWriter();
			if(result){
			}else{
				out.print("{\"刪除\":\"失敗\"}");		
			}
			return;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
