package com.coaches.controller;

import java.io.IOException;
import java.util.Base64;
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
import com.members.model.MembersService;
import com.members.model.MembersVO;
import com.tools.Tools;

@WebServlet("/CoachesServlet")
@MultipartConfig(maxFileSize=5*1024*1024)
public class CoachesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		System.out.println("(C)action= " + action);
			
			if(action == null) {
				System.out.println("action == null");
				String url = "/front_end/index.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
		
			//來自coaches_register.jsp的請求
			if ("insert".equals(action)) { 
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				//1.接收請求參數 - 輸入格式的錯誤處理
				//驗證姓名
				String coa_name = req.getParameter("coa_name");
				String coa_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,6}$";
				if (coa_name == null || coa_name.trim().length() == 0) {
					errorMsgs.put("coa_name","會員姓名: 請勿空白");
				} else if(!(coa_name.matches(coa_nameReg))) {
					errorMsgs.put("coa_name","會員姓名: 只能是中、英文字母 ,且長度必需在2到6之間");
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
				String coa_psw = req.getParameter("coa_psw");
				String coa_pswReg = "^[(a-zA-Z0-9_)]{6,20}$";
				if(coa_psw == null || coa_psw.trim().length() == 0) {
					errorMsgs.put("coa_psw", "會員密碼: 請勿空白");
				}else if(!(coa_psw.matches(coa_pswReg))) {
					errorMsgs.put("coa_psw","會員密碼: 只能是大小寫英數字(含_)，且長度必需在6到20之間");
				}

				//驗證確認密碼
				String coa_psw_ck = req.getParameter("coa_psw_ck");
				String coa_psw_ckReg = "^[(a-zA-Z0-9_)]{6,20}$";
				if(coa_psw_ck == null || coa_psw_ck.trim().length() == 0) {
					errorMsgs.put("coa_psw", "會員確認密碼: 請勿空白");
				} else if(!(coa_psw_ck.matches(coa_psw_ckReg)) || !coa_psw_ck.trim().equals(coa_psw)) {
					errorMsgs.put("coa_psw_ck", "確認密碼錯誤，請重新輸入");
				}
				
				//驗證性別
				Integer coa_sex = null;
				try {
					coa_sex = Integer.valueOf(req.getParameter("coa_sex"));
					if(coa_sex != 1 && coa_sex != 2) {
						errorMsgs.put("coa_psw", "會員性別: 請勿空白");
					}
				} catch (NumberFormatException e) {
					errorMsgs.put("coa_psw", "會員性別: 請勿空白");
				}	
				
				//驗證身分證號
				Tools tools = new Tools();
				String coa_id = String.valueOf(req.getParameter("coa_id"));
				String coa_idReg = "^[A-Z]{1}[1-2]{1}[0-9]{8}$";
				if(coa_id == null || coa_id.trim().length() == 0){
					errorMsgs.put("coa_id", "會員身分證號: 請勿空白");
				}else if(!(coa_id.matches(coa_idReg)) || !(tools.checkId(coa_id, coa_sex))) {
					errorMsgs.put("coa_id", "會員身分證號: 格式錯誤");
				}
				
				//驗證信箱
				String coa_mail = req.getParameter("coa_mail");
				String coa_mailReg = "^[_A-Za-z0-9-]+([.][_A-Za-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
				if(coa_mail == null || coa_mail.trim().length() == 0) {
					errorMsgs.put("coa_mail", "會員信箱: 請勿空白");
				}else if(!(coa_mail.matches(coa_mailReg)) || coa_mail.length() > 50) {
					errorMsgs.put("coa_mail", "會員信箱: 格式錯誤");
				}

				//驗證自我介紹
				String coa_into = req.getParameter("coa_into");
				if(coa_into == null || coa_into.trim().length() == 0) {
					errorMsgs.put("coa_into", "會員自我介紹: 請勿空白");
				}else if(coa_into.length() > 500) {
					errorMsgs.put("coa_into", "會員自我介紹: 格式錯誤");
				}
				
				//驗證大頭貼
				String cropped_pic = req.getParameter("cropped_pic");
				//base64轉byte[]
				Base64.Decoder decoder = Base64.getDecoder();
				byte[] coa_pic_byte = null;
				
				if(cropped_pic == null || "".equals(cropped_pic)) {
					errorMsgs.put("coa_pic", "會員大頭貼: 請鎖定圖片");
				}else {
					coa_pic_byte = decoder.decode(cropped_pic.split(",")[1]);
					int pic_length = coa_pic_byte.length;
					String pic_type = cropped_pic.substring(5, 10);
					if(pic_length > (5*1024*1024)) {
						errorMsgs.put("coa_pic", "會員大頭貼: 檔案過大");
					} else if(!("image".equals(pic_type))) {
						errorMsgs.put("coa_pic", "會員大頭貼: 僅允許圖片格式");
					}
				}
				
				// 資料有誤就返回form表單
				if(!errorMsgs.isEmpty()) {
					System.out.println("I got the errorMsgs");
					String url = "/front_end/register/coaches_register.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
				
				//2.開始新增資料
				CoachesService coachesSV = new CoachesService();
				coachesSV.addCoaches(mem_acc, mem_nickname, coa_psw, coa_name, coa_sex, coa_id, coa_mail, coa_into, coa_pic_byte);
				System.out.println("insert is complete");
				String url = req.getContextPath() + "/front_end/register/register_success.jsp";
				res.sendRedirect(url);
				return;
				
				
			//其他可能的錯誤處理
			} catch(Exception e) {	
				e.printStackTrace();
				errorMsgs.put("Exception",e.getMessage());
				System.out.println(errorMsgs.get("Exception"));
				String url = "/front_end/register/coaches_register.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
			}
			
			// 來自MembersServlet轉交login.jsp的請求
			if ("login".equals(action)) {		
				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					//1.【取得使用者輸入的帳號(account) 密碼(password)】			
					MembersVO membersVO = (MembersVO)req.getAttribute("user");
					String account = membersVO.getMem_acc();
					String password = req.getParameter("password");

					// 2.【檢查該帳號 , 密碼是否有效】
					CoachesService coachesSV = new CoachesService();
					CoachesVO coachesVO = coachesSV.loginCoaches(account, password);
					String coa_acc = coachesVO.getCoa_acc();
					String coa_psw = coachesVO.getCoa_psw();
					Integer coa_sta = coachesVO.getCoa_sta();
					
					//3.【審核狀態2，帳密錯誤】
					if(coachesVO == null || !(account.equals(coa_acc)) || !(password.equals(coa_psw))) {
						System.out.println("(CoachesSerlvet)帳密錯誤");
						String url = req.getContextPath() + "/front_end/login/login_fail.jsp";
						res.sendRedirect(url);
						return;
					}
					//3.【審核狀態0，未審核】
					else if(coa_sta == 0) {
						System.out.println("(CoachesSerlvet)審核未通過");
						HttpSession session = req.getSession();
						String url = req.getContextPath() + "/front_end/login/login_fail_sta.jsp";
						res.sendRedirect(url);
						return;
					}
					
					//3.【審核狀態1，成功登入】
					else if(coa_sta == 1) {
						HttpSession session = req.getSession();
						session.setAttribute("user", membersVO);
						session.setAttribute("coach", coachesVO);
						System.out.println("(CoachesServlet)登入成功");
						
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
				}
			}
			// 來自index.jsp的logout請求
			if ("logout".equals(action)) {
				HttpSession session = req.getSession();
				session.invalidate();
				res.sendRedirect(req.getContextPath()+"/front_end/index.jsp");
			    return;
			}
			
			//來自personal.jsp的請求
			if ("update".equals(action)) { 
				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				HttpSession session = req.getSession();
				MembersVO membersVO = (MembersVO) session.getAttribute("user");
				CoachesVO coachesVO = (CoachesVO) session.getAttribute("coach");
				try {
					//1.接收請求參數 - 輸入格式的錯誤處理
					//驗證姓名
					String coa_name = req.getParameter("coa_name");
					String coa_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,6}$";
					if (coa_name == null || coa_name.trim().length() == 0) {
						errorMsgs.put("coa_name","會員姓名: 請勿空白");
					} else if(!(coa_name.matches(coa_nameReg))) {
						errorMsgs.put("coa_name","會員姓名: 只能是中、英文字母 ,且長度必需在2到6之間");
		            }
					
					//驗證信箱
					String coa_mail = req.getParameter("coa_mail");
					String coa_mailReg = "^[_A-Za-z0-9-]+([.][_A-Za-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
					if(coa_mail == null || coa_mail.trim().length() == 0) {
						errorMsgs.put("coa_mail", "會員信箱: 請勿空白");
					}else if(!(coa_mail.matches(coa_mailReg)) || coa_mail.length() > 50) {
						errorMsgs.put("coa_mail", "會員信箱: 格式錯誤");
					}

					//驗證自我介紹
					String coa_into = req.getParameter("coa_into");
					if(coa_into == null || coa_into.trim().length() == 0) {
						errorMsgs.put("coa_into", "會員自我介紹: 請勿空白");
					}else if(coa_into.length() > 500) {
						errorMsgs.put("coa_into", "會員自我介紹: 格式錯誤");
					}
					
//					//驗證大頭貼
//					String cropped_pic = req.getParameter("cropped_pic");
//					//base64轉byte[]
//					Base64.Decoder decoder = Base64.getDecoder();
//					byte[] coa_pic_byte = null;
//					
//					if(cropped_pic == null || "".equals(cropped_pic)) {
//						errorMsgs.put("coa_pic", "會員大頭貼: 請鎖定圖片");
//					}else {
//						coa_pic_byte = decoder.decode(cropped_pic.split(",")[1]);
//						//圖片大小(kb...)
//						int pic_length = coa_pic_byte.length;
//						String pic_type = cropped_pic.substring(5, 10);
////						Part coa_pic = req.getPart("upload-file");
////						String fileType = coa_pic.getContentType();
//						if(pic_length > (5*1024*1024)) {
//							errorMsgs.put("coa_pic", "會員大頭貼: 檔案過大");
//						} else if(!("image".equals(pic_type))) {
//							errorMsgs.put("coa_pic", "會員大頭貼: 僅允許圖片格式");
//						}
//					}
					
//					System.out.println("PartSize=" + coa_pic.getSize());
//					System.out.println("I got the parameter from form");
					
					// 資料有誤就返回form表單
					if(!errorMsgs.isEmpty()) {
						System.out.println("I got the errorMsgs");
						String url = "/front_end/editPage/personal.jsp";
						RequestDispatcher failureView = req.getRequestDispatcher(url);
						failureView.forward(req, res);
						return;
					}				
					//2.開始修改資料
					CoachesService coachesSV = new CoachesService();
					coachesVO = coachesSV.updateCoaches(coachesVO, coa_name, coa_mail, coa_into);
					coachesVO = coachesSV.loginCoaches(coachesVO.getCoa_acc(), coachesVO.getCoa_psw());
					//3.修改完成,準備轉交(Send the Success view)
					System.out.println("update is complete");
					session.setAttribute("user", membersVO);
					session.setAttribute("coach", coachesVO);
					String url = req.getContextPath() + "/front_end/editPage/personal.jsp";
					System.out.println("ok!");
					res.sendRedirect(url);
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
			
			if ("search_coa".equals(action)) {
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String search_Name = req.getParameter("search_Name");
					System.out.println("search_Name= " + search_Name);
					if (search_Name == null || (search_Name.trim()).length() == 0) {
						search_Name = " ";
					}
					String search_Type = req.getParameter("search_Type");
					System.out.println("search_Type= " + search_Type);
					if("0".equals(search_Type)) {
						search_Type = " ";
					}else if("A".equals(search_Type)) {
						search_Type = "瑜珈";
					}else if("B".equals(search_Type)) {
						search_Type = "飛輪有氧";
					}else if("C".equals(search_Type)) {
						search_Type = "舞動有氧";
					}else if("D".equals(search_Type)) {
						search_Type = "拳擊有氧";
					}else if("E".equals(search_Type)) {
						search_Type = "基礎重訓";		
					}else if("F".equals(search_Type)) {
						search_Type = "進階重訓";					
					}else if("G".equals(search_Type)) {
						search_Type = "皮拉提斯";				
					}else if("H".equals(search_Type)) {
						search_Type = "TRX肌力雕塑";	
					}else if("O".equals(search_Type)) {
						search_Type = "其他";				
					}
					
					System.out.println("Final search_Type= " + search_Type);
					CoachesService coacesSV = new CoachesService();
					
					List<CoachesVO> searchResult = coacesSV.searchCoa(search_Name, search_Type);
					if (searchResult.size() == 0) {
						errorMsgs.add("查無資料");
					}

					req.setAttribute("searchResult", searchResult);
					String url = "/front_end/browse/find_coaches.jsp";
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
					
					CoachesService coachesSV = new CoachesService();
					CoachesVO coachesVO = coachesSV.look_search_mem(membersVO);
					
					req.setAttribute("coach", coachesVO);
					String url = "/front_end/search/lookPersonal.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					return;
				} catch(Exception e) {
					System.out.println("(C)例外發生");
					e.printStackTrace();
					errorMsgs.put("Exception",e.getMessage());
					System.out.println(errorMsgs.get("Exception"));
					String url = req.getContextPath() + "/front_end/browse/find_coaches.jsp";
					res.sendRedirect(url);
					return;
				}
			}
			
			
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}