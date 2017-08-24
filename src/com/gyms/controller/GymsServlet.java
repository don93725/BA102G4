package com.gyms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.coaches.model.CoachesService;
import com.coaches.model.CoachesVO;
import com.google.gson.Gson;
import com.gyms.model.GymsService;
import com.gyms.model.GymsVO;
import com.members.model.MembersService;
import com.members.model.MembersVO;
import com.students.model.StudentsService;
import com.students.model.StudentsVO;
import com.tools.Tools;

@WebServlet("/GymsServlet")
@MultipartConfig(maxFileSize=5*1024*1024)
public class GymsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		PrintWriter out = res.getWriter();
		
		if(action == null) {
			System.out.println("action == null");
			String url = "/front_end/index.jsp";
			RequestDispatcher failureView = req.getRequestDispatcher(url);
			failureView.forward(req, res);
			return;
		}
		
		//來自gyms_register.jsp的請求
		if ("insert".equals(action)) { 			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				//1.接收請求參數 - 輸入格式的錯誤處理
				//驗證場館名稱
				String gym_name = req.getParameter("gym_name");
				String gym_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{1,16}$";
				if (gym_name == null || gym_name.trim().length() == 0) {
					errorMsgs.put("gym_name","會員名稱: 請勿空白");
				} else if(!(gym_name.matches(gym_nameReg))) {
					errorMsgs.put("gym_name","會員名稱: 只能是中、英文字母 ,且長度必需在1到16之間");
	            }
				
				//驗證暱稱
				String mem_nickname = req.getParameter("mem_nickname");
				String mem_nicknameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{1,15}$";
				if(mem_nickname == null || mem_nickname.trim().length() == 0){
					errorMsgs.put("mem_nickname","會員暱稱: 請勿空白");
				} else if(!(mem_nickname.matches(mem_nicknameReg))){
					errorMsgs.put("mem_nickname","會員暱稱: 只能是中、英文、數字，且長度必需在1到15之間");
				}
				
				//驗證帳號
				String mem_acc = req.getParameter("mem_acc");
				String mem_accReg = "^[(a-zA-Z0-9_)]{6,20}$";
				MembersService membersSV = new MembersService();
				
				if(membersSV.insert_ck(mem_acc)) {
					errorMsgs.put("mem_acc","會員帳號: 已被使用，請更換");
				}else if(mem_acc == null || mem_acc.trim().length() == 0) {
					errorMsgs.put("mem_acc","會員帳號: 請勿空白");
				}else if(!(mem_acc.matches(mem_accReg))) {
					errorMsgs.put("mem_acc","會員帳號: 只能是大小寫英數字(含_)，且長度必需在6到20之間");
				}
				
				//驗證密碼
				String gym_psw = req.getParameter("gym_psw");
				String gym_pswReg = "^[(a-zA-Z0-9_)]{6,20}$";
				if(gym_psw == null || gym_psw.trim().length() == 0) {
					errorMsgs.put("gym_psw", "會員密碼: 請勿空白");
				}else if(!(gym_psw.matches(gym_pswReg))) {
					errorMsgs.put("gym_psw","會員密碼: 只能是大小寫英數字(含_)，且長度必需在6到20之間");
				}

				//驗證確認密碼
				String gym_psw_ck = req.getParameter("gym_psw_ck");
				String gym_psw_ckReg = "^[(a-zA-Z0-9_)]{6,20}$";
				if(gym_psw_ck == null || gym_psw_ck.trim().length() == 0) {
					errorMsgs.put("gym_psw", "會員確認密碼: 請勿空白");
				} else if(!(gym_psw_ck.matches(gym_psw_ckReg)) || !gym_psw_ck.trim().equals(gym_psw)) {
					errorMsgs.put("gym_psw_ck", "確認密碼錯誤，請重新輸入");
				}
				
				//驗證信箱
				String gym_mail = req.getParameter("gym_mail");
				String gym_mailReg = "^[_A-Za-z0-9-]+([.][_A-Za-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
				if(gym_mail == null || gym_mail.trim().length() == 0) {
					errorMsgs.put("gym_mail", "會員信箱: 請勿空白");
				}else if(!(gym_mail.matches(gym_mailReg)) || gym_mail.length() > 50) {
					errorMsgs.put("gym_mail", "會員信箱: 格式錯誤或長度有誤");
				}

				//驗證場館簡介
				String gym_into = req.getParameter("gym_into");
				if(gym_into == null || gym_into.trim().length() == 0) {
					errorMsgs.put("gym_into", "會員自我介紹: 請勿空白");
				}else if(gym_into.length() > 500) {
					errorMsgs.put("gym_into", "會員自我介紹: 只能輸入1-500個字元");
				}
				
				//驗證大頭貼
				String cropped_pic = req.getParameter("cropped_pic");
				//base64轉byte[]
				Base64.Decoder decoder = Base64.getDecoder();
				byte[] gym_pic_byte = null;
				
				if(cropped_pic == null || "".equals(cropped_pic)) {
					errorMsgs.put("gym_pic", "會員大頭貼: 請鎖定圖片");
				}else {
					gym_pic_byte = decoder.decode(cropped_pic.split(",")[1]);
					//圖片大小(kb...)
					int pic_length = gym_pic_byte.length;
					String pic_type = cropped_pic.substring(5, 10);
					if(pic_length > (5*1024*1024)) {
						errorMsgs.put("gym_pic", "會員大頭貼: 檔案過大");
					} else if(!("image".equals(pic_type))) {
						errorMsgs.put("gym_pic", "會員大頭貼: 僅允許圖片格式");
					}
				}

				//驗證地址
				String gym_add = req.getParameter("gym_add");
				String gym_addReg = "^[(\u4e00-\u9fa5)(a-zA-Z)(0-9)(-)]{1,50}$";
				if(gym_add == null || gym_add.trim().length() == 0) {
					errorMsgs.put("gym_add", "會員地址: 請勿空白");
				}else if(!(gym_add.matches(gym_addReg)) || gym_add.length() > 50) {
					errorMsgs.put("gym_add", "會員地址: 只能是大小寫英數字，且長度必需在1到50之間");
				}
				//地址轉經緯度
				Tools tools = new Tools();
				String gym_latlng = tools.getLATLNG(gym_add);
				if(gym_latlng == null) {
					errorMsgs.put("gym_add", "會員地址: 請不要玩弄Google地圖");
				}
				System.out.println("I got the parameter from form");
				
				// 資料有誤就返回form表單
				if(!errorMsgs.isEmpty()) {
					System.out.println("I got the errorMsgs");
					String url = "/front_end/register/gyms_register.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
				
				//2.開始新增資料
				GymsService gymsSV = new GymsService();
				gymsSV.addGyms(gym_name, mem_nickname, mem_acc, gym_psw, gym_mail, gym_into, gym_pic_byte, gym_add, gym_latlng);
				
				//3.新增完成,準備轉交(Send the Success view)
				System.out.println("insert is complete");
				String url = req.getContextPath() + "/front_end/register/register_success.jsp";
				res.sendRedirect(url);
				return;
				
			//其他可能的錯誤處理
			} catch(Exception e) {	
				e.printStackTrace();
				errorMsgs.put("Exception",e.getMessage());
				System.out.println(errorMsgs.get("Exception"));
				String url = "/front_end/register/gyms_register.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
			}
			
			// 來自MembersServlet轉交login.jsp的請求
			if ("login".equals(action)) {
				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					//1.【取得使用者輸入的帳號(account) 密碼(password)】			
					MembersVO membersVO = (MembersVO)req.getAttribute("user");
					String account = membersVO.getMem_acc();
					String password = req.getParameter("password");

					// 2.【檢查該帳號 , 密碼是否有效】
					GymsService gymsSV = new GymsService();
					GymsVO gymsVO = gymsSV.loginGyms(account, password);
					String gym_acc = gymsVO.getGym_acc();
					String gym_psw = gymsVO.getGym_psw();
					Integer gym_sta = gymsVO.getGym_sta();
					
					//3.【審核狀態2，帳密錯誤】
					if(gymsVO == null || !(account.equals(gym_acc)) || !(password.equals(gym_psw))) {
						System.out.println("(GymsSerlvet)帳密錯誤");
						String url = req.getContextPath() + "/front_end/login/login_fail.jsp";
						res.sendRedirect(url);
						return;
					}
					//3.【審核狀態0，未審核】
					else if(gym_sta == 0) {
						System.out.println("(GymsSerlvet)審核未通過");
						HttpSession session = req.getSession();
						String url = req.getContextPath() + "/front_end/login/login_fail_sta.jsp";
						res.sendRedirect(url);
						return;
					}
					//3.【審核狀態1，成功登入】
					else if(gym_sta == 1) {
						HttpSession session = req.getSession();
						session.setAttribute("user", membersVO);
						session.setAttribute("gym", gymsVO);
						System.out.println("(GymsSerlvet)登入成功");
						
						//4.【登入成功,準備轉交來源網頁，若沒有，則轉回首頁】
						try {
						    String location = (String) session.getAttribute("location");
						    if (location != null) {
						        session.removeAttribute("location");
						        res.sendRedirect(location);
						        return;
						    }
						} catch(Exception ignored) {}
						    System.out.println("登入成功");
						    res.sendRedirect(req.getContextPath()+"/front_end/login/login_success.jsp");
						    return;
					}
					//5.【其他可能的錯誤處理】
				} catch(Exception e) {	
					e.printStackTrace();
					errorMsgs.put("Exception",e.getMessage());
					System.out.println(errorMsgs.get("Exception"));
					String url = "/front_end/login/login_fail.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
			}
			
			//來自personal.jsp的請求
			if ("update".equals(action)) { 
				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				HttpSession session = req.getSession();
				MembersVO membersVO = (MembersVO) session.getAttribute("user");
				GymsVO gymsVO = (GymsVO) session.getAttribute("gym");
				try {
					//1.接收請求參數 - 輸入格式的錯誤處理
					//驗證場館名稱
					String gym_name = req.getParameter("gym_name");
					String gym_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{1,16}$";
					if (gym_name == null || gym_name.trim().length() == 0) {
						errorMsgs.put("gym_name","場館名稱: 請勿空白");
					} else if(!(gym_name.matches(gym_nameReg))) {
						errorMsgs.put("gym_name","場館名稱: 只能是中、英文字母 ,且長度必需在1到16之間");
		            }
					
					//驗證信箱
					String gym_mail = req.getParameter("gym_mail");
					String gym_mailReg = "^[_A-Za-z0-9-]+([.][_A-Za-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
					if(gym_mail == null || gym_mail.trim().length() == 0) {
						errorMsgs.put("gym_mail", "會員信箱: 請勿空白");
					}else if(!(gym_mail.matches(gym_mailReg)) || gym_mail.length() > 50) {
						errorMsgs.put("gym_mail", "會員信箱: 格式錯誤");
					}
					
					//驗證地址
					String gym_add = req.getParameter("gym_add");
					String gym_addReg = "^[(\u4e00-\u9fa5)(a-zA-Z)(0-9)(-)]{1,50}$";
					if(gym_add == null || gym_add.trim().length() == 0) {
						errorMsgs.put("gym_add", "會員地址: 請勿空白");
					}else if(!(gym_add.matches(gym_addReg)) || gym_add.length() > 50) {
						errorMsgs.put("gym_add", "會員地址: 只能是大小寫英數字，且長度必需在1到50之間");
					}
					//地址轉經緯度
					Tools tools = new Tools();
					String gym_latlng = tools.getLATLNG(gym_add);
					if(gym_latlng == null) {
						errorMsgs.put("gym_add", "會員地址: 請不要玩弄Google地圖");
					}
					
					//驗證自我介紹
					String gym_into = req.getParameter("gym_into");
					if(gym_into == null || gym_into.trim().length() == 0) {
						errorMsgs.put("gym_into", "會員自我介紹: 請勿空白");
					}else if(gym_into.length() > 500) {
						errorMsgs.put("gym_into", "會員自我介紹: 格式錯誤");
					}
			
					// 資料有誤就返回form表單
					if(!errorMsgs.isEmpty()) {
						Gson gSon = new Gson();
						out.print(gSon.toJson(errorMsgs));
						return;
					}		
					//2.開始修改資料
					GymsService gymsSV = new GymsService();
					gymsVO = gymsSV.updateGyms(gymsVO, gym_name, gym_mail, gym_into, gym_add, gym_latlng);
					gymsVO = gymsSV.loginGyms(gymsVO.getGym_acc(), gymsVO.getGym_psw());
					//3.修改完成,準備轉交(Send the Success view)
					session.setAttribute("user", membersVO);
					session.setAttribute("gym", gymsVO);
					System.out.println("update is complete");
					return;
					
				//其他可能的錯誤處理
				} catch(Exception e) {	
					e.printStackTrace();
					errorMsgs.put("Exception",e.getMessage());
					System.out.println(errorMsgs.get("Exception"));
					String url = "/front_end/editPage/personal.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
			}
			if ("search_gym".equals(action)) {
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String search_Name = req.getParameter("search_Name");
					System.out.println("search_Name= " + search_Name);
					if (search_Name == null || (search_Name.trim()).length() == 0) {
						search_Name = "";
					}
					String search_Type = req.getParameter("search_Type");
//					System.out.println("search_Type= " + search_Type);
					if(search_Type==null){
						search_Type = "";
					}
//					if("0".equals(search_Type)) {
//						search_Type = " ";
//					}else if("A".equals(search_Type)) {
//						search_Type = "瑜珈";
//					}else if("B".equals(search_Type)) {
//						search_Type = "飛輪有氧";
//					}else if("C".equals(search_Type)) {
//						search_Type = "舞動有氧";
//					}else if("D".equals(search_Type)) {
//						search_Type = "拳擊有氧";
//					}else if("E".equals(search_Type)) {
//						search_Type = "基礎重訓";		
//					}else if("F".equals(search_Type)) {
//						search_Type = "進階重訓";					
//					}else if("G".equals(search_Type)) {
//						search_Type = "皮拉提斯";				
//					}else if("H".equals(search_Type)) {
//						search_Type = "TRX肌力雕塑";	
//					}else if("O".equals(search_Type)) {
//						search_Type = "其他";				
//					}
//					System.out.println("Final search_Type= " + search_Type);
					GymsService gymsSV = new GymsService();
					
					List<GymsVO> searchResult = gymsSV.searchGyms(search_Name, search_Type);
					if (searchResult.size() == 0) {
						errorMsgs.add("查無資料");
					}

					req.setAttribute("searchResult", searchResult);
					String url = "/front_end/browse/find_gyms.jsp";
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
			if("lookPersonal".equals(action)) {
				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsgs", errorMsgs);
				try {
					MembersVO membersVO = (MembersVO) req.getAttribute("user");
					
					GymsService gymsService = new GymsService();
					GymsVO gymVO = gymsService.look_search_mem(membersVO);
					req.setAttribute("gym", gymVO);
					String url = "/front_end/search/lookPersonal.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					return;
				} catch(Exception e) {
					System.out.println("(S)例外發生");
					e.printStackTrace();
					errorMsgs.put("Exception",e.getMessage());
					System.out.println(errorMsgs.get("Exception"));
					String url = req.getContextPath() + "/front_end/browse/find_students.jsp";
					res.sendRedirect(url);
					return;
				}
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
