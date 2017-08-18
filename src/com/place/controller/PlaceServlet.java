package com.place.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.coaches.model.CoachesService;
import com.members.model.MembersService;
import com.members.model.MembersVO;
import com.place.model.PlaceService;
import com.place.model.PlaceVO;
import com.students.model.StudentsService;
import com.students.model.StudentsVO;
import com.tools.Tools;

@WebServlet("/PlaceServlet")
public class PlaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		System.out.println("(P)action= " + action);
		PrintWriter out = res.getWriter();
			
			if(action == null) {
				System.out.println("action == null");
				String url = "/front_end/index.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
		
			if ("insert".equals(action)) { 
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = req.getSession();
			MembersVO membersVO = (MembersVO) session.getAttribute("user");

			try {
				//1.接收請求參數 - 輸入格式的錯誤處理
				String p_name = req.getParameter("p_name");
				String p_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{1,18}$";
				if (p_name == null || p_name.trim().length() == 0) {
					errorMsgs.put("p_name","健身房名稱: 請勿空白");
				}
				if(!(p_name.matches(p_nameReg))) {
					errorMsgs.put("p_name","健身房名稱: 只能是中、英文字母 ,且長度必需在1到18之間");
	            }
				
				String cap = req.getParameter("p_cap");
				String p_capReg = "^([1-9][0-9]*){1,2}$";
				if (cap == null || cap.trim().length() == 0) {
					errorMsgs.put("p_cap","可容納人數: 請勿空白");
				}
				if(!(cap.matches(p_capReg))) {
					errorMsgs.put("p_cap","可容納人數: 只能是0-99");
	            }
				Integer p_cap = Integer.valueOf(cap);
				
				String p_add = req.getParameter("p_add");
				String p_addReg = "^[(\u4e00-\u9fa5)(a-zA-Z)(0-9)(-)]{1,50}$";
				if(p_add == null || p_add.trim().length() == 0) {
					errorMsgs.put("p_add", "場地地址: 請勿空白");
				}
				if(!(p_add.matches(p_addReg)) || p_add.length() > 50) {
					errorMsgs.put("p_add", "場地地址: 只能是中文大小寫英數字，且長度必需在1到50之間");
				}
				Tools tools = new Tools();
				String p_latlng = tools.getLATLNG(p_add);
				if(p_latlng == null) {
					errorMsgs.put("p_add", "場地地址: 請不要玩弄Google地圖");
				}

				String p_into = req.getParameter("p_into");
				if(p_into == null || p_into.trim().length() == 0) {
					errorMsgs.put("gym_into", "場地介紹: 請勿空白");
				}else if(p_into.length() > 500) {
					errorMsgs.put("gym_into", "場地介紹: 只能輸入1-500個字元");
				}
				
				// 資料有誤就返回form表單
				if(!errorMsgs.isEmpty()) {
					out.write("false");
					String url = "/front_end/rentManagement/include/pleaceList.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
				
				//2.開始新增資料
				PlaceService placeSV = new PlaceService();
				placeSV.addPlace(membersVO, p_name, p_into, p_add, p_latlng, p_cap);
				System.out.println("insert is complete");
				String url = req.getContextPath() + "/front_end/rentManagement/placePublish.jsp";
				res.sendRedirect(url);
				return;

			//其他可能的錯誤處理
			} catch(Exception e) {
				e.printStackTrace();
				errorMsgs.put("Exception",e.getMessage());
				System.out.println(errorMsgs.get("Exception"));
				String url = "/front_end/rentManagement/pleacePublish.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
			}
			if("placeList".equals(action)) {
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				HttpSession session = req.getSession();
				MembersVO membersVO = (MembersVO) session.getAttribute("user");
				
				try {
					String placeList_acc = membersVO.getMem_acc();
					PlaceService placeSV = new PlaceService();
					List<PlaceVO> placeList = placeSV.placeList(placeList_acc);
					System.out.println("size= " + placeList.size());
					if (placeList.size() == 0) {
						errorMsgs.add("尚未新增場地");
					}

					req.setAttribute("placeList", placeList);
					String url = "/front_end/rentManagement/placePublish.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					return;
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("");
					failureView.forward(req, res);
					return;
				}
			}
			
			if("delete".equals(action)) {
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
				//1.接收請求參數 - 輸入格式的錯誤處理
				String p_no = req.getParameter("p_no");
				
				//2.開始刪除資料
				PlaceService placeSV = new PlaceService();
				placeSV.delete(p_no);
				System.out.println("delete is complete");
				String url = req.getContextPath() + "/front_end/rentManagement/placePublish.jsp";
				res.sendRedirect(url);
				return;
			//其他可能的錯誤處理
			} catch(Exception e) {
				out.write("false");
				e.printStackTrace();
//				errorMsgs.put("Exception",e.getMessage());
//				System.out.println(errorMsgs.get("Exception"));
				String url = "/front_end/rentManagement/pleacePublish.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
			}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
