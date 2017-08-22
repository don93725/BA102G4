package com.place.controller;
import java.util.Base64;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
import com.google.gson.Gson;
import com.members.model.MembersService;
import com.members.model.MembersVO;
import com.place.model.PlaceService;
import com.place.model.PlaceVO;
import com.students.model.StudentsService;
import com.students.model.StudentsVO;
import com.sun.media.jfxmedia.events.NewFrameEvent;
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
					errorMsgs.put("p_name","請勿空白");
				}
				if(!(p_name.matches(p_nameReg))) {
					errorMsgs.put("p_name","只能是中、英文字母 ,且長度必需在1到18之間");
				}
				System.out.println("p_name= " + p_name);			
				String cap = req.getParameter("p_cap");
				String p_capReg = "^([1-9][0-9]*){1,2}$";
				if (cap == null || cap.trim().length() == 0 || "".equals(cap)) {
					errorMsgs.put("p_cap","請勿空白");
				}
				if(!(cap.matches(p_capReg))) {
					errorMsgs.put("p_cap","只能是1-99");
	            }
				System.out.println("cap= " + cap);		
				String p_add = req.getParameter("p_add");
				String p_addReg = "^[(\u4e00-\u9fa5)(a-zA-Z)(0-9)(-)]{1,50}$";
				if(p_add == null || p_add.trim().length() == 0) {
					errorMsgs.put("p_add", "請勿空白");
				}
				if(!(p_add.matches(p_addReg)) || p_add.length() > 50) {
					errorMsgs.put("p_add", "只能是中文大小寫英數字，且長度必需在1到50之間");
				}
				System.out.println("p_add= " + p_add);
				Tools tools = new Tools();
				String p_latlng = tools.getLATLNG(p_add);
				if(p_latlng == null) {
					errorMsgs.put("p_add", "地址轉換失敗");
				}
				String p_into = req.getParameter("p_into");
				if(p_into == null || p_into.trim().length() == 0) {
					errorMsgs.put("p_into", "請勿空白");
				}else if(p_into.length() > 500) {
					errorMsgs.put("p_into", "只能是1-500個字元");
				}
				System.out.println("p_into= " + p_into);
				// 資料有誤就返回form表單
				if(!errorMsgs.isEmpty()) {
					System.out.println("errorMsgs != 空的");
					Gson gSon = new Gson();
					out.print(gSon.toJson(errorMsgs));
					return;
				}
				Integer p_cap = Integer.valueOf(cap);
				System.out.println("p_cap= " + p_cap);
				
				//2.開始新增資料
				PlaceService placeSV = new PlaceService();
				placeSV.addPlace(membersVO, p_name, p_into, p_add, p_latlng, p_cap);
				System.out.println("insert is complete");
//				return;

			//其他可能的錯誤處理
			} catch(Exception e) {
				e.printStackTrace();
				errorMsgs.put("Exception",e.getMessage());
				System.out.println(errorMsgs.get("Exception"));
				return;
			}
			}
			
			if("placeList".equals(action)) {
				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsgs", errorMsgs);
				HttpSession session = req.getSession();
				MembersVO membersVO = (MembersVO) session.getAttribute("user");
				
				try {
					String placeList_acc = membersVO.getMem_acc();
					String placeList_status = req.getParameter("placeList_status");
					PlaceService placeSV = new PlaceService();
					List<PlaceVO> placeList = placeSV.placeList(placeList_acc, placeList_status);

					req.setAttribute("placeList", placeList);
					req.setAttribute("page","/front_end/include/placeList.jsp");
					String url = "/front_end/rentManagement/placePublish.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					return;
				} catch (Exception e) {
					e.printStackTrace();
					errorMsgs.put("無法取得資料:" , e.getMessage());
					String url = "/front_end/index.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
			}
			
			if("delete".equals(action)) {
				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
				//1.接收請求參數 - 輸入格式的錯誤處理
				String p_no = req.getParameter("p_no");
				String p_status = req.getParameter("p_status");
				if("1".equals(p_status) || "2".equals(p_status)) {
					errorMsgs.put("p_status","場地狀態為上架 或 使用中");
				}
				if(!errorMsgs.isEmpty()) {
					Gson gSon = new Gson();
					out.print(gSon.toJson(errorMsgs));
					return;
				}
				//2.開始刪除資料
				PlaceService placeSV = new PlaceService();
				placeSV.delete(p_no);
				System.out.println("delete is complete");
				return;
			//其他可能的錯誤處理
			} catch(Exception e) {
				out.print("\"例外\" : \"有例外\"");
				e.printStackTrace();
				errorMsgs.put("Exception",e.getMessage());
				System.out.println(errorMsgs.get("Exception"));
				String url = "/front_end/rentManagement/placePublish.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
			}
			
			if ("update".equals(action)) { 
				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsgs", errorMsgs);
				try {
					//1.接收請求參數 - 輸入格式的錯誤處理
					String p_name = req.getParameter("p_name");
					String p_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{1,18}$";
					if (p_name == null || p_name.trim().length() == 0) {
						errorMsgs.put("place_name","請勿空白");
					}
					if(!(p_name.matches(p_nameReg))) {
						errorMsgs.put("place_name","只能是中、英文字母 ,且長度必需在1到18之間");
					}
					
					String cap = req.getParameter("p_cap");
					String p_capReg = "^([1-9][0-9]*){1,2}$";
					if (cap == null || cap.trim().length() == 0 || "".equals(cap)) {
						errorMsgs.put("place_cap","請勿空白");
					}
					if(!(cap.matches(p_capReg))) {
						errorMsgs.put("place_cap","只能是1-99");
		            }
					String p_add = req.getParameter("p_add");
					String p_addReg = "^[(\u4e00-\u9fa5)(a-zA-Z)(0-9)(-)]{1,50}$";
					if(p_add == null || p_add.trim().length() == 0) {
						errorMsgs.put("place_add", "請勿空白");
					}
					if(!(p_add.matches(p_addReg)) || p_add.length() > 50) {
						errorMsgs.put("place_add", "只能是中文大小寫英數字，且長度必需在1到50之間");
					}
					Tools tools = new Tools();
					String p_latlng = tools.getLATLNG(p_add);
					if(p_latlng == null) {
						errorMsgs.put("place_add", "地址轉換失敗");
					}

					String p_into = req.getParameter("p_into");
					if(p_into == null || p_into.trim().length() == 0) {
						errorMsgs.put("place_into", "請勿空白");
					}else if(p_into.length() > 500) {
						errorMsgs.put("place_into", "只能是1-500個字元");
					}
					String p_no = req.getParameter("p_no");
					System.out.println("p_no= " + p_no);
					// 資料有誤就返回form表單
					if(!errorMsgs.isEmpty()) {
						System.out.println("errorMsgs != 空的");
						Gson gSon = new Gson();
						out.print(gSon.toJson(errorMsgs));
						return;
					}
					Integer p_cap = Integer.valueOf(cap);
					
					//2.開始新增資料
					PlaceService placeSV = new PlaceService();
					placeSV.updatePlace(p_name, p_into, p_add, p_latlng, p_cap, p_no);
					System.out.println("update is complete");
//					return;

				//其他可能的錯誤處理
				} catch(Exception e) {
					e.printStackTrace();
					errorMsgs.put("Exception",e.getMessage());
					System.out.println(errorMsgs.get("Exception"));
					return;
				}
				}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
