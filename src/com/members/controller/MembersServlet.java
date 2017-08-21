package com.members.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.friends.model.FriendsService;
import com.members.model.MembersService;
import com.members.model.MembersVO;

@WebServlet("/MembersServlet")
public class MembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		System.out.println("(M)action is " + action);
		
			if(action == null) {
				System.out.println("action == null");
				String url = "/front_end/index.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
		
			//來自login.jsp的請求
			if ("login".equals(action)) { 
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//取得使用者輸入的帳號
				String account = req.getParameter("account");
				System.out.println("(MemberServlet)account= "+account);
				//轉交membersservice身分查詢
				MembersService membersSV = new MembersService();
				MembersVO nowLogin_VO = membersSV.select(account);
				String rank = nowLogin_VO.getMem_rank();
				System.out.println("(MemberServlet)rank=" + rank);
			
				//身分為健身者
				if("0".equals(rank)) {
					System.out.println("(MembersServlet)rank in 0");
					req.setAttribute("user", nowLogin_VO);
					String url = "/StudentsServlet";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					return;
				}
				//身分為教練
				else if("1".equals(rank)) {
					System.out.println("(MembersServlet)rank in 1");
					req.setAttribute("user", nowLogin_VO);
					String url = "/CoachesServlet";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					return;
				}
				//身分為健身房業者
				else if("2".equals(rank)) {
					System.out.println("(MembersServlet)rank in 2");
					req.setAttribute("user", nowLogin_VO);
					String url = "/GymsServlet";
					RequestDispatcher failView = req.getRequestDispatcher(url);
					failView.forward(req, res);
					return;
				}
				//身分為員工
				else if("3".equals(rank)) {
					System.out.println("(MembersServlet)rank in 3");
					req.setAttribute("user", nowLogin_VO);
					String url = "/mgr/MgrCtrl";
					RequestDispatcher failView = req.getRequestDispatcher(url);
					failView.forward(req, res);
					return;
				}
				//皆不符合
				else {
					System.out.println("(MeberServlet)帳密錯誤");
					String url = req.getContextPath() + "/front_end/login/login_fail.jsp";
					res.sendRedirect(url);
					return;
				}
			} catch(Exception e) {
				System.out.println("(MemberServlet)例外發生");
				e.printStackTrace();
				errorMsgs.put("Exception",e.getMessage());
				System.out.println(errorMsgs.get("Exception"));
				String url = req.getContextPath() + "/front_end/login/login_fail.jsp";
				res.sendRedirect(url);
				return;
			}
			}
			
			if("update".equals(action)) {
				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsgs", errorMsgs);
				HttpSession session = req.getSession();
				MembersVO membersVO = (MembersVO) session.getAttribute("user");
				try {
					//取得使用者修改的暱稱
					String mem_nickname = req.getParameter("mem_nickname");
					String mem_nicknameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{1,15}$";
					if(mem_nickname == null || mem_nickname.trim().length() == 0){
						errorMsgs.put("mem_nickname","會員暱稱: 請勿空白");
					} else if(!(mem_nickname.matches(mem_nicknameReg))){
						errorMsgs.put("mem_nickname","會員暱稱: 只能是中、英文、數字，且長度必需在1到15之間");
					}
					// 資料有誤就返回form表單
					if(!errorMsgs.isEmpty()) {
						System.out.println("I got the errorMsgs");
						String url = "/front_end/editPage/personal.jsp";
						RequestDispatcher failureView = req.getRequestDispatcher(url);
						failureView.forward(req, res);
						return;
					}
					//開始修改暱稱
					MembersService membersSV = new MembersService();
					membersVO = membersSV.update(membersVO, mem_nickname);

					//轉交membersservice身分查詢
					String rank = membersVO.getMem_rank();
					System.out.println("(MemberServlet)rank=" + rank);
				
					//身分為健身者
					if("0".equals(rank)) {
						System.out.println("(MembersServlet)rank in 0");
						req.setAttribute("user", membersVO);
						String url = "/StudentsServlet";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
						return;
					}
					//身分為教練
					else if("1".equals(rank)) {
						System.out.println("(MembersServlet)rank in 1");
						req.setAttribute("user", membersVO);
						String url = "/CoachesServlet";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
						return;
					}
					//身分為健身房業者
					else if("2".equals(rank)) {
						System.out.println("(MembersServlet)rank in 2");
						req.setAttribute("user", membersVO);
						String url = "/GymsServlet";
						RequestDispatcher failView = req.getRequestDispatcher(url);
						failView.forward(req, res);
						return;
					}
					//身分為員工
					else if("3".equals(rank)) {
						System.out.println("(MembersServlet)rank in 3");
						req.setAttribute("user", membersVO);
						String url = "/ManagerServlet";
						RequestDispatcher failView = req.getRequestDispatcher(url);
						failView.forward(req, res);
						return;
					}
				} catch(Exception e) {
					System.out.println("(MemberServlet)例外發生");
					e.printStackTrace();
					errorMsgs.put("Exception",e.getMessage());
					System.out.println(errorMsgs.get("Exception"));
					String url = req.getContextPath() + "/front_end/login/login_fail.jsp";
					res.sendRedirect(url);
					return;
				}
			}
			if("lookPersonal".equals(action)) {
				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsgs", errorMsgs);
				try {
					
					String mem_no = req.getParameter("mem_no");
					String mem_rank = req.getParameter("mem_rank");
					MembersVO membersVO = new MembersVO();
					FriendsService friendsService = new FriendsService();
					MembersVO user = ((MembersVO)req.getSession().getAttribute("user"));
					
					boolean ifFriend = friendsService.checkFriendShip(mem_no,user.getMem_no());
					req.setAttribute("ifFriend", ifFriend);
					System.out.println("ifFriend="+ifFriend);
					MembersService membersSV = new MembersService();
					if(mem_no != null){
						membersVO = membersSV.look_search_mem(mem_no);
					}
					
					//身分為健身者
					if("0".equals(mem_rank)) {
						System.out.println("(MembersServlet)rank in 0");
						req.setAttribute("user", membersVO);
						String url = "/StudentsServlet";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
						return;
					}
					//身分為教練
					else if("1".equals(mem_rank)) {
						System.out.println("(MembersServlet)rank in 1");
						req.setAttribute("user", membersVO);
						String url = "/CoachesServlet";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
						return;
					}
					//身分為健身房業者
					else if("2".equals(mem_rank)) {
						System.out.println("(MembersServlet)rank in 2");
						req.setAttribute("user", membersVO);
						String url = "/GymsServlet";
						RequestDispatcher failView = req.getRequestDispatcher(url);
						failView.forward(req, res);
						return;
					}
					//身分為員工
					else if("3".equals(mem_rank)) {
						System.out.println("(MembersServlet)rank in 3");
						req.setAttribute("user", membersVO);
						String url = "/ManagerServlet";
						RequestDispatcher failView = req.getRequestDispatcher(url);
						failView.forward(req, res);
						return;
					}
				} catch(Exception e) {
					System.out.println("(MemberServlet)例外發生");
					e.printStackTrace();
					errorMsgs.put("Exception",e.getMessage());
					System.out.println(errorMsgs.get("Exception"));
					String url = req.getContextPath() + "/front_end/login/login_fail.jsp";
					res.sendRedirect(url);
					return;
				}
			}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
