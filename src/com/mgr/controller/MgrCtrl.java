package com.mgr.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.authority.model.AuthorityService;
import com.authority.model.AuthorityVO;
import com.manager.model.ManagerService;
import com.manager.model.ManagerVO;
import com.members.model.MembersService;
import com.members.model.MembersVO;
import com.mailservice.MailService;

@WebServlet("/mgr/MgrCtrl")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MgrCtrl extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=Big5");

		String action = req.getParameter("action");

		// System.out.println(getServletContext().getServerInfo());
		// System.out.println("ContentType="+req.getContentType()); // 測試用

		// 來自MembersServlet轉交login.jsp的請求
		if ("login".equals(action)) {

			// res.setContentType("text/html; charset=Big5");
			// // Store this set in the request scope, in case we need to
			// // send the ErrorPage view.

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.【取得使用者輸入的帳號(account) 密碼(password)】
				MembersVO membersVO = (MembersVO) req.getAttribute("user");
				String mgr_account = membersVO.getMem_acc();
				String mgr_password = req.getParameter("password").trim();
//System.out.println(mgr_account);	 		
//System.out.println(mgr_password);
				// 2.【檢查該帳號 , 密碼是否有效】
				ManagerService mgrSvc = new ManagerService();
				boolean mgrPwd = mgrSvc.loginMgr(mgr_account,mgr_password);
				System.out.println("mgrPwd: " + mgrPwd);
				System.out.println("密碼是否錯誤: "+ (!mgrPwd));
				
				// 失敗就轉交至以下網頁
				if (!mgrPwd) {
					System.out.println("(MgrCtl)密碼錯誤");
					String url = req.getContextPath() + "/front_end/login_pw_fail.jsp";
					res.sendRedirect(url);
					return;
				}
				
				int mgrSta = mgrSvc.loginMgrTest(mgr_account, mgr_password).getMgr_status();
				System.out.println("mgrSta: " + mgrSta);
				System.out.println("停權: " + (mgrSta == 1));
				if(mgrSta == 1){
					System.out.println("(MgrCtl)帳號以停權");
					String url = req.getContextPath() + "/front_end/login_fail_mgr_sta.jsp";
					res.sendRedirect(url);
					return;
				}

				
					// 3.【登入成功,準備轉交來源網頁，若沒有，則轉回首頁】
					HttpSession session = req.getSession();
					ManagerVO managerVO = mgrSvc.loginMgrTest(mgr_account, mgr_password);
					session.setAttribute("user", membersVO);
					session.setAttribute("userMgr", managerVO);

					AuthorityService autSvc = new AuthorityService();
					Set<AuthorityVO> authorityVO = autSvc.getFno(managerVO.getMgr_no());
					session.setAttribute("userMgrFun", authorityVO);
					System.out.println("(MgrCtl)登入成功");
					// 4.【登入成功,準備轉交來源網頁，若沒有，則轉回首頁】
					try {
						String location = (String) session.getAttribute("location");
						if (location != null) {
							session.removeAttribute("location");
							res.sendRedirect(location);
							return;
						}
					} catch (Exception ignored) {
					}
					System.out.println("登入成功");
					res.sendRedirect(req.getContextPath() + "/back_end/login_success.jsp");
					return;
				

				// 4.【其他可能的錯誤處理】
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("Exception", e.getMessage());
				System.out.println(errorMsgs.get("Exception"));
				String url = "/front_end/login_fail.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				// res.sendRedirect(url);
				failureView.forward(req, res);
			}
		}
		if("logout".equals(action)){
			HttpSession session = req.getSession();
			session.invalidate();
			res.sendRedirect(req.getContextPath()+"/front_end/login.jsp");
			return;
		}


		if ("insert".equals(action)) { // 來自addMgr.jsp的請求
			Collection<Part> parts = req.getParts(); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
//				MailService mailService = new MailService(); // 宣告 MailService
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mgr_name = req.getParameter("mgr_name");
				String mgr_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mgr_name == null || mgr_name.trim().length() == 0) {
					errorMsgs.put("mgr_name","姓名: 請勿空白");
				} else if(!mgr_name.trim().matches(mgr_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("mgr_name","姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String mem_nickname = req.getParameter("mem_nickname").trim();
				String mem_nicknameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,25}$";
				if (mem_nickname == null || mem_nickname.trim().length() == 0) {
					errorMsgs.put("mem_nickname","暱稱: 請勿空白");
				} else if(!mem_nickname.trim().matches(mem_nicknameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("mem_nickname","暱稱: 只能是中、英文字母、數字和_ , 且長度必需在2到25之間");
	            }
				
				String mgr_id = req.getParameter("mgr_id").trim();
				String mgr_idReg = "^[(a-zA-Z0-9_)]{2,20}$";
				if (mgr_id == null || mgr_id.trim().length() == 0) {
					errorMsgs.put("mgr_id","帳號: 請勿空白");
				} else if(!mgr_id.trim().matches(mgr_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("mgr_id","帳號: 只能是英文字母、數字和_ , 且長度必需在2到20之間");
	            }
				
				// 密碼亂數
				String mgr_pwd = MailService.getRandomPw();
				
				String mgr_email = req.getParameter("mgr_email").trim();
				String mgr_emailReg = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
				if (mgr_email == null || mgr_email.trim().length() == 0) {
					errorMsgs.put("mgr_email","信箱: 請勿空白");
				} else if(!mgr_email.trim().matches(mgr_emailReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("mgr_email","信箱: 只能是英文字母、數字和_ , 且長度必需在2到20之間");
	            }
				String mgr_int = req.getParameter("mgr_int");
				String mgr_intReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_,)]{1,50}$";
				if (mgr_int == null || mgr_int.trim().length() == 0) {
					errorMsgs.put("mgr_int","自我介紹: 請勿空白");
				} else if(!mgr_name.trim().matches(mgr_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("mgr_int","自我介紹: 只能是中、英文字母、數字和_ , 且長度必需在1到50之間");
	            }
				int mgr_job = 0;
				int mgr_status = 0;
				byte[] mgr_pic = null;

				String[] f_no = null;
				try {
					f_no = req.getParameterValues("f_no");
					System.out.println(f_no.length);
					for (int i = 0; i < f_no.length; i++) {
						
						System.out.println("f_no:" + f_no[i]);
					}
				} catch (Exception e) {
					errorMsgs.put("f_no","權限: 最少給一個");
				}


				try {
					mgr_job = new Integer(req.getParameter("mgr_job").trim());
				} catch (Exception e) {
					errorMsgs.put("mgr_job","職位: error!!");
				}

				for (Part part : parts) {
					if (getFileNameFromPart(part) != null && part.getContentType() != null) {
						System.out.println("3");
						String name = part.getName();
						String filename = getFileNameFromPart(part);
						// 這裡只有IE能檢察出檔案實際內容，chrome檢查不出 只會秀出檔案的副檔名
						String ContentType = part.getContentType();
						long size = part.getSize();
						InputStream in = part.getInputStream();
						byte[] buf = new byte[in.available()];
						in.read(buf);
						in.close();
						System.out.println(buf);
						mgr_pic = buf;
					}
				}

				ManagerVO managerVO = new ManagerVO();
				MembersVO membersVO = new MembersVO();
				AuthorityVO authorityVO = new AuthorityVO();

				managerVO.setMgr_name(mgr_name);
				membersVO.setMem_nickname(mem_nickname);
				managerVO.setMgr_id(mgr_id);

				managerVO.setMgr_pwd(mgr_pwd);
				managerVO.setMgr_email(mgr_email);
				managerVO.setMgr_job(mgr_job);
				managerVO.setMgr_pic(mgr_pic);
				managerVO.setMgr_int(mgr_int);	
				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("managerVO", managerVO);
//					req.setAttribute("membersVO", membersVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/mgr/addMgr.jsp");
					failureView.forward(req, res);
					return;
				}
				
				ManagerService mgrSvc = new ManagerService();
				managerVO = mgrSvc.addMgr(mem_nickname, mgr_id, mgr_pwd, mgr_job, mgr_name, mgr_email, mgr_status,
						mgr_pic,mgr_int);

				AuthorityService autSvc = new AuthorityService();
				managerVO = autSvc.getMgrNO(mgr_id);
				String mgr_no = managerVO.getMgr_no();

				for (int i = 0; i < f_no.length; i++) {
					authorityVO = autSvc.addAut(mgr_no, f_no[i]);
				}

				// send Mail
				String messageText = "Hello! " + mgr_name + " 請謹記此密碼: " + mgr_pwd + "\n" + " (已經啟用)";
				MailService.sendMail(mgr_email, "恭喜你成為健貨", messageText);

				String url = "/back_end/mgr/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.put("Exception",e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/mgr/addMgr.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllMgr.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				
				String mem_no = req.getParameter("mem_no");
				String mgr_no = req.getParameter("mgr_no");
				ManagerService mgrSvc = new ManagerService();
				MembersService memSvc = new MembersService();
				// AuthorityService autSvc = new AuthorityService();
				ManagerVO managerVO = mgrSvc.getOneMgr(mgr_no);
				MembersVO membersVO = memSvc.getOneMem(mem_no);
				// AuthorityVO authorityVO = autSvc.getFno(mgr_no);
				req.setAttribute("managerVO", managerVO);
				req.setAttribute("membersVO", membersVO);
				String url = "/back_end/mgr/update_mgr_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/mgr/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		

		if ("update".equals(action) || "updatePersonal".equals(action)) { // 來自update_mgr_input.jsp的請求
			Collection<Part> parts = req.getParts(); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
System.out.println("~~~~~~~~~~~~~");
			try {
				String mem_no = req.getParameter("mem_no").trim();
				System.out.println("mem_no"+mem_no);
				String mgr_no = req.getParameter("mgr_no").trim();
				System.out.println("mgr_no"+mgr_no);
				String mgr_name = req.getParameter("mgr_name").trim();
				System.out.println("mgr_name "+mgr_name );
				String mem_nickname = req.getParameter("mem_nickname").trim();
				System.out.println("mem_nickname "+mem_nickname );
				String mgr_id = req.getParameter("mgr_id").trim();
				System.out.println("mgr_id "+mgr_id );
				String mgr_pwd = req.getParameter("mgr_pwd").trim();
				System.out.println("mgr_pwd  "+mgr_pwd  );
				String mgr_email = req.getParameter("mgr_email").trim();
				String mgr_int = req.getParameter("mgr_int").trim();
				System.out.println("mgr_email  "+mgr_email  );
				String[] f_no = req.getParameterValues("f_no");
				System.out.println("f_no  "+f_no   );
				byte[] mgr_pic = null;

				for (Part part : parts) {
					if (getFileNameFromPart(part) != null && part.getContentType() != null) {
						System.out.println("3");
						String name = part.getName();
						String filename = getFileNameFromPart(part);
						// 這裡只有IE能檢察出檔案實際內容，chrome檢查不出 只會秀出檔案的副檔名
						String ContentType = part.getContentType();
						long size = part.getSize();
						InputStream in = part.getInputStream();
						byte[] buf = new byte[in.available()];
						in.read(buf);
						in.close();
						System.out.println(buf);
						mgr_pic = buf;
					}
				}

				Integer mgr_job = null;
				try {
					mgr_job = new Integer(req.getParameter("mgr_job").trim());
				} catch (Exception e) {
					errorMsgs.add("mgr_job error!!");
				}
				Integer mgr_status = null;
				try {
					mgr_status = new Integer(req.getParameter("mgr_status").trim());
				} catch (Exception e) {
					errorMsgs.add("mgr_status error!!");
				}

				ManagerVO managerVO = new ManagerVO();
				MembersVO membersVO = new MembersVO();
				AuthorityVO authorityVO = new AuthorityVO();

				managerVO.setMgr_no(mgr_no);
				managerVO.setMgr_name(mgr_name);
				membersVO.setMem_no(mem_no);
				membersVO.setMem_nickname(mem_nickname);
				managerVO.setMgr_id(mgr_id);
				managerVO.setMgr_pwd(mgr_pwd);
				managerVO.setMgr_email(mgr_email);
				managerVO.setMgr_pic(mgr_pic);
				managerVO.setMgr_job(mgr_job);
				managerVO.setMgr_status(mgr_status);
				managerVO.setMgr_int(mgr_int);

				// authorityVO.setMgr_no(mgr_no);
				// for(int i=0 ;i<f_no.length;i++){
				// authorityVO.setF_no(f_no[i]);
				// }
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("managerVO", managerVO);
					req.setAttribute("membersVO", membersVO);
					String url = null;
					if("updatePersonal".equals(action)){
						url = "/back_end/update_personal/updatePersonal.jsp";
					}
					if("update".equals(action)){
						url = "/back_end/mgr/update_mgr_input.jsp";
					}
					
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return; // 程式中斷
				}

				ManagerService mgrSvc = new ManagerService();
				managerVO = mgrSvc.updateMgr(mem_nickname, mgr_no, mgr_id, mgr_pwd, mgr_job, mgr_name, mgr_email,
						mgr_status, mgr_pic,mgr_int);
				
				if("update".equals(action)){
					
					AuthorityService autSvc = new AuthorityService();
					autSvc.deleteAut(mgr_no);
					for (int i = 0; i < f_no.length; i++) {
						authorityVO = autSvc.addAut(mgr_no, f_no[i]);
					}
				}
				// MembersService memSvc = new MembersService();
				// membersVO = memSvc.updateMem(mem_no, mem_nickname);
				req.setAttribute("managerVO", managerVO); // 資料庫update成功後,正確的的empVO物件,存入req
				req.setAttribute("membersVO", membersVO);
//				String url = "/back_end/mgr/listOneMgr.jsp";
				String url = null;
				if("update".equals(action)){
					url = "/back_end/mgr/listOneMgr.jsp";
				}
				if("updatePersonal".equals(action)){
					url = "/back_end/update_personal/updatePersonal.jsp";
					HttpSession session =req.getSession();
					session.removeAttribute("user");
					session.removeAttribute("userMgr");
					session.setAttribute("user", membersVO);
					session.setAttribute("userMgr", managerVO);

				}
			
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				String url = null;
				if("updatePersonal".equals(action)){
					url = "/back_end/update_personal/updatePersonal.jsp";
				}
				if("update".equals(action)){
					url = "/back_end/mgr/update_mgr_input.jsp";
				}
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
		}
			
		if ("listMgr_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
//			System.out.println("~~~~~~~~~~~~~~~~~~~~`");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>)map1.clone();
					session.setAttribute("map",map2);
					map = (HashMap<String, String[]>)req.getParameterMap();
				} 
				
				/***************************2.開始複合查詢***************************************/
				ManagerService mgrSvc = new ManagerService();
				List<ManagerVO> list  = mgrSvc.getAll(map);
//				System.out.println("222222222222222`");
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listMgr_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/mgr/select_page.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/mgr/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
	
	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		// System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		// System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

}
