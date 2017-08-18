package com.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.album.domain.Albums;
import com.album.service.AlbumsService;
import com.board.domain.Message_board;
import com.board.service.Message_boardService;
import com.friends.model.FriendsDAO;
import com.friends.model.FriendsService;
import com.google.gson.Gson;
import com.members.model.MembersVO;

/**
 * Servlet implementation class BoardShowCtrl
 */
@WebServlet("/board/BoardShowCtrl")
public class BoardShowCtrl extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String mem_no = req.getParameter("mem_no");
		String type = req.getParameter("type");
		String friend = req.getParameter("friend");
		String bd_msg_no = req.getParameter("bd_msg_no");
		MembersVO user = (MembersVO) req.getSession().getAttribute("user");
		if (mem_no == null && bd_msg_no==null &&friend==null) {
			// 沒選擇個人版動態
			String referer = (String) req.getSession().getAttribute("referer");
			if (referer != null) {
				res.sendRedirect(referer);
			} else {
				res.sendRedirect(req.getContextPath() + "/index.jsp");
			}
			return;
		}
		
		int thisPage = (req.getParameter("thisPage") == null) ? 1 : Integer.valueOf(req.getParameter("thisPage"));
		int pageSize = 8;
		String condition = null;
		if(friend==null){
			if (user == null) {
				// 非本人
				condition = "bd_prvt=1";
			} else {
				String user_no = user.getMem_no();
				if (mem_no.equals(user_no)) {
					condition = "bd_prvt=0 or bd_prvt=1 or bd_prvt=2";
				} else {
					if (new FriendsService().checkFriendShip(mem_no, user_no)) {
						condition = "bd_prvt=1 or bd_prvt=0";
					}else{
						condition = "bd_prvt=1";
					}
				}
				
			}			
		}
		Message_boardService message_boardService = new Message_boardService();
		if(bd_msg_no!=null){
			List<Message_board> message_board = message_boardService.getSingleBoard(thisPage, pageSize, bd_msg_no, condition);
			req.setAttribute("message_board", message_board);
			req.getRequestDispatcher("/front_end/board/SingleBoard.jsp").forward(req, res);	
			return;
		}
		int allPageCount = 0;
		List<Message_board> message_board = null;
		System.out.println(thisPage+" "+pageSize);
		if(friend!=null){
			//進朋友頁面
			String user_no = user.getMem_no();
			allPageCount = message_boardService.getFriendsBoardNum(user_no, pageSize);			
			message_board = message_boardService.getFriendsPageAndRank(thisPage, pageSize, user_no);
		}else{
			allPageCount = message_boardService.getBoardNum(mem_no, condition, pageSize);			
			message_board = message_boardService.getPageAndRank(thisPage, pageSize, mem_no, condition);
		}
		if (thisPage > allPageCount) {
			thisPage = allPageCount;
		}
		req.setAttribute("message_board", message_board);
		req.setAttribute("thisPage", thisPage);
		req.setAttribute("allPageCount", allPageCount);
//		List<Message_board> message_board = message_boardService.getPageAndRank(thisPage, pageSize, mem_no, condition);
//		req.setAttribute("message_board", message_board);
		if("json".equals(type)){
			res.setContentType("text/html ; charset=utf-8 ");
			PrintWriter out = res.getWriter();
			req.getRequestDispatcher("/front_end/board/Board2.jsp").forward(req, res);			
		}else{
			req.getRequestDispatcher("/front_end/board/Board.jsp").forward(req, res);			
		}
		return;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
