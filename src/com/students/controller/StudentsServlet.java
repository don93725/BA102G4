package com.students.controller;

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
import com.students.model.StudentsService;
import com.students.model.StudentsVO;
import com.tools.Tools;

/**
 * Servlet implementation class StudentsServlet
 */
@WebServlet("/StudentsServlet")
@MultipartConfig(maxFileSize=5*1024*1024)
public class StudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		
		if(action == null) {
			System.out.println("action == null");
			String url = "/front_end/index.jsp";
			RequestDispatcher failureView = req.getRequestDispatcher(url);
			failureView.forward(req, res);
			return;
		}
		
		//來自stuches_register.jsp的請求
		if ("insert".equals(action)) { 
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				//1.接收請求參數 - 輸入格式的錯誤處理
				//驗證姓名
				String stu_name = req.getParameter("stu_name");
				String stu_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,6}$";
				if (stu_name == null || stu_name.trim().length() == 0) {
					errorMsgs.put("stu_name","會員姓名: 請勿空白");
				} else if(!(stu_name.matches(stu_nameReg))) {
					errorMsgs.put("stu_name","會員姓名: 只能是中、英文字母 ,且長度必需在2到6之間");
				}
				System.out.println("stu name= " + stu_name);
				//驗證暱稱
				String mem_nickname = req.getParameter("mem_nickname");
				String mem_nicknameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{1,15}$";
				if(mem_nickname == null || mem_nickname.trim().length() == 0){
					errorMsgs.put("mem_nickname","會員暱稱: 請勿空白");
				} else if(!(mem_nickname.matches(mem_nicknameReg))){
					errorMsgs.put("mem_nickname","會員暱稱: 只能是中、英文、數字，且長度必需在1到15之間");
				}
				System.out.println("nick_name= " + mem_nickname);
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
				System.out.println("memacc= " + mem_acc);
				//驗證密碼
				String stu_psw = req.getParameter("stu_psw");
				String stu_pswReg = "^[(a-zA-Z0-9_)]{6,20}$";
				if(stu_psw == null || stu_psw.trim().length() == 0) {
					errorMsgs.put("stu_psw", "會員密碼: 請勿空白");
				}else if(!(stu_psw.matches(stu_pswReg))) {
					errorMsgs.put("stu_psw","會員密碼: 只能是大小寫英數字(含_)，且長度必需在6到20之間");
				}
				System.out.println("mem_pse= " + stu_psw);
				//驗證確認密碼
				String stu_psw_ck = req.getParameter("stu_psw_ck");
				String stu_psw_ckReg = "^[(a-zA-Z0-9_)]{6,20}$";
				if(stu_psw_ck == null || stu_psw_ck.trim().length() == 0) {
					errorMsgs.put("stu_psw", "會員確認密碼: 請勿空白");
				} else if(!(stu_psw_ck.matches(stu_psw_ckReg)) || !stu_psw_ck.trim().equals(stu_psw)) {
					errorMsgs.put("stu_psw_ck", "確認密碼錯誤，請重新輸入");
				}
			
				//驗證性別
				Integer stu_sex = null;
				try {
					stu_sex = Integer.valueOf(req.getParameter("stu_sex"));
					if(stu_sex != 1 && stu_sex != 2) {
						errorMsgs.put("stu_psw", "會員性別: 請勿空白");
					}
				} catch (NumberFormatException e) {
					errorMsgs.put("stu_psw", "會員性別: 請勿空白");
				}	
				System.out.println("stu_sex= " + stu_sex);
				//驗證身分證號
				Tools tools = new Tools();
				String stu_id = String.valueOf(req.getParameter("stu_id"));
				String stu_idReg = "^[A-Z]{1}[1-2]{1}[0-9]{8}$";
				if(stu_id == null || stu_id.trim().length() == 0){
					errorMsgs.put("stu_id", "會員身分證號: 請勿空白");
				}else if(!(stu_id.matches(stu_idReg)) || !(tools.checkId(stu_id, stu_sex))) {
					errorMsgs.put("stu_id", "會員身分證號: 格式錯誤");
				}
				System.out.println("stu_id= " + stu_id);
				//驗證信箱
				String stu_mail = req.getParameter("stu_mail");
				String stu_mailReg = "^[_A-Za-z0-9-]+([.][_A-Za-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
				if(stu_mail == null || stu_mail.trim().length() == 0) {
					errorMsgs.put("stu_mail", "會員信箱: 請勿空白");
				}else if(!(stu_mail.matches(stu_mailReg)) || stu_mail.length() > 50) {
					errorMsgs.put("stu_mail", "會員信箱: 格式錯誤");
				}
				System.out.println("stu_mail= " + stu_mail);
				//驗證自我介紹
				String stu_into = req.getParameter("stu_into");
				if(stu_into == null || stu_into.trim().length() == 0) {
					errorMsgs.put("stu_into", "會員自我介紹: 請勿空白");
				}else if(stu_into.length() > 500) {
					errorMsgs.put("stu_into", "會員自我介紹: 格式錯誤");
				}
				System.out.println("stu_into= " + stu_into);
				//驗證大頭貼
				String cropped_pic = req.getParameter("cropped_pic");
				//base64轉byte[]
				Base64.Decoder decoder = Base64.getDecoder();
				byte[] stu_pic_byte = null;
	
				if(cropped_pic == null || "".equals(cropped_pic)) {
					errorMsgs.put("stu_pic", "會員大頭貼: 請鎖定圖片");
				}else {
					stu_pic_byte = decoder.decode(cropped_pic.split(",")[1]);
					//圖片大小(kb...)
					int pic_length = stu_pic_byte.length;
					String pic_type = cropped_pic.substring(5, 10);
//					Part coa_pic = req.getPart("upload-file");
//					String fileType = coa_pic.getContentType();
					if(pic_length > (5*1024*1024)) {
						errorMsgs.put("stu_pic", "會員大頭貼: 檔案過大");
					} else if(!("image".equals(pic_type))) {
						errorMsgs.put("stu_pic", "會員大頭貼: 僅允許圖片格式");
					}
				}

				// 資料有誤就返回form表單
				if(!errorMsgs.isEmpty()) {
					System.out.println("I got the errorMsgs");
					String url = "/front_end/register/students_register.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
			
					//2.開始新增資料
					StudentsService studentsSV = new StudentsService();
					studentsSV.addStudents(mem_acc, mem_nickname, stu_psw, stu_name, stu_sex, stu_id, stu_mail, stu_into, stu_pic_byte);

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
				String url = "/front_end/register/students_register.jsp";
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
				StudentsService studentsSV = new StudentsService();
				StudentsVO studentsVO = studentsSV.loginStudents(account, password);
				String stu_acc = studentsVO.getStu_acc();
				String stu_psw = studentsVO.getStu_psw();
				
				//失敗就轉交至以下網頁
				if(studentsVO == null || !(account.equals(stu_acc)) || !(password.equals(stu_psw))) {
					System.out.println("(StudentsSerlvet)帳密錯誤");
					String url = req.getContextPath() + "/front_end/login/login_fail.jsp";
					res.sendRedirect(url);
					return;
				} else {
					//3.【登入成功,準備轉交來源網頁，若沒有，則轉回首頁】
					HttpSession session = req.getSession();
					session.setAttribute("user", membersVO);
					session.setAttribute("student", studentsVO);
					System.out.println("(StudentsServlet)登入成功");
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
								
			//4.【其他可能的錯誤處理】
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
			StudentsVO studentsVO = (StudentsVO) session.getAttribute("student");
			try {
				//1.接收請求參數 - 輸入格式的錯誤處理
				//驗證姓名
				String stu_name = req.getParameter("stu_name");
				String stu_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,6}$";
				if (stu_name == null || stu_name.trim().length() == 0) {
					errorMsgs.put("stu_name","會員姓名: 請勿空白");
				} else if(!(stu_name.matches(stu_nameReg))) {
					errorMsgs.put("stu_name","會員姓名: 只能是中、英文字母 ,且長度必需在2到6之間");
	            }
				
				//驗證信箱
				String stu_mail = req.getParameter("stu_mail");
				System.out.println("test= " + stu_mail);
				String stu_mailReg = "^[_A-Za-z0-9-]+([.][_A-Za-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
				if(stu_mail == null || stu_mail.trim().length() == 0) {
					errorMsgs.put("stu_mail", "會員信箱: 請勿空白");
				}else if(!(stu_mail.matches(stu_mailReg)) || stu_mail.length() > 50) {
					errorMsgs.put("stu_mail", "會員信箱: 格式錯誤");
				}

				//驗證自我介紹
				String stu_into = req.getParameter("stu_into");
				if(stu_into == null || stu_into.trim().length() == 0) {
					errorMsgs.put("stu_into", "會員自我介紹: 請勿空白");
				}else if(stu_into.length() > 500) {
					errorMsgs.put("stu_into", "會員自我介紹: 格式錯誤");
				}
				
				// 資料有誤就返回form表單
				if(!errorMsgs.isEmpty()) {
					System.out.println("I got the errorMsgs");
					String url = "/front_end/editPage/personal.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}				
				//2.開始修改資料
				StudentsService studentsSV = new StudentsService();
				studentsVO = studentsSV.updateStudents(studentsVO, stu_name, stu_mail, stu_into);
				studentsVO = studentsSV.loginStudents(studentsVO.getStu_acc(), studentsVO.getStu_psw());
				//3.修改完成,準備轉交(Send the Success vsiew)
				System.out.println("update is complete");
				session.setAttribute("user", membersVO);
				session.setAttribute("student", studentsVO);
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
		
		if ("search_stu".equals(action)) {
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
				StudentsService studentsSV = new StudentsService();
				
				List<StudentsVO> searchResult = studentsSV.searchStu(search_Name, search_Type);
				if (searchResult.size() == 0) {
					errorMsgs.add("查無資料");
				}

				req.setAttribute("searchResult", searchResult);
				String url = "/front_end/browse/find_students.jsp";
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
				
				StudentsService studentsSV = new StudentsService();
				StudentsVO studentsVO = studentsSV.look_search_mem(membersVO);
				System.out.println(studentsVO.getStu_acc());
				req.setAttribute("student", studentsVO);
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

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
