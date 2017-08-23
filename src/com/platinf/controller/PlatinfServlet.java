package com.platinf.controller;

import java.io.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.platinf.model.*;
@WebServlet("/back_end/editAbout/platinf.do")
@MultipartConfig(maxRequestSize=1024*1024*1024,maxFileSize=1024*1024*1024)
public class PlatinfServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自selectPlatinf_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("pin_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/platinf/selectPlatinf_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String pin_no = null;
				try {
					pin_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/platinf/selectPlatinf_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				PlatinfService platinfSvc = new PlatinfService();
				PlatinfVO platinfVO = platinfSvc.getOnePlatinf(pin_no);
				if (platinfVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/platinf/selectPlatinf_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("platinfVO", platinfVO); // 資料庫取出的fitkwVO物件,存入req
				String url = "/back_end/platinf/listOnePlatinf.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOnePlatinf.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/platinf/selectPlatinf_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllPlatinf.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String pin_no = new String(req.getParameter("pin_no"));
				
				/***************************2.開始查詢資料****************************************/
				PlatinfService platinfSvc = new PlatinfService();
				PlatinfVO platinfVO = platinfSvc.getOnePlatinf(pin_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("platinfVO", platinfVO);         // 資料庫取出的fitkwVO物件,存入req
				String url = "/back_end/platinf/update_platinf_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_platinf_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/platinf/listAllPlatinf.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_platinf_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String pin_no = req.getParameter("pin_no").trim();
				String com_address = req.getParameter("com_address").trim();
				String cp_no = req.getParameter("cp_no").trim();
				String cs_email = req.getParameter("cs_email").trim();				
				String pr_policy = req.getParameter("pr_policy").trim();	
				Part part = req.getPart("pin_photo");
				byte[] pin_photo = null;
				if(part.getSize()!=0){
					InputStream is =part.getInputStream();
					pin_photo = new byte[is.available()];
					is.read(pin_photo);
				}

				
				
				PlatinfVO platinfVO = new PlatinfVO();
				platinfVO.setPin_no(pin_no);
				platinfVO.setCom_address(com_address);
				platinfVO.setCp_no(cp_no);
				platinfVO.setCs_email(cs_email);
				platinfVO.setPr_policy(pr_policy);
				platinfVO.setPin_photo(pin_photo);
				
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("platinfVO", platinfVO); // 含有輸入格式錯誤的platinfVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/editAbout/about.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				PlatinfService platinfSvc = new PlatinfService();
				platinfVO = platinfSvc.updatePlatinf(pin_no, com_address, cp_no, cs_email, pr_policy, pin_photo);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("platinfVO", platinfVO); // 資料庫update成功後,正確的的platinfVO物件,存入req
				String url = "/back_end/editAbout/about.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOnePlatinf.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/editAbout/about.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addPlatinf.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			
				
				String com_address = req.getParameter("com_address").trim();
				String cp_no = req.getParameter("cp_no").trim();
				String cs_email = req.getParameter("cs_email").trim();				
				String pr_policy = req.getParameter("pr_policy").trim();				
				Part part = req.getPart("pin_photo");
				byte[] pin_photo = null;
				if(part.getSize()!=0){
					InputStream is =part.getInputStream();
					pin_photo = new byte[is.available()];
					is.read(pin_photo);
				}

				
				PlatinfVO platinfVO = new PlatinfVO();
				platinfVO.setCom_address(com_address);
				platinfVO.setCp_no(cp_no);
				platinfVO.setCs_email(cs_email);
				platinfVO.setPr_policy(pr_policy);
				platinfVO.setPin_photo(pin_photo);
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("platinfVO", platinfVO); // 含有輸入格式錯誤的platinfVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/platinf/addPlatinf.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				PlatinfService platinfSvc = new PlatinfService();
				platinfVO = platinfSvc.addPlatinf(com_address, cp_no, cs_email, pr_policy, pin_photo);
				
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back_end/platinf/listAllPlatinf.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllPlatinf.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/platinf/addPlatinf.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllPlatinf.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String pin_no = new String(req.getParameter("pin_no"));
				
				/***************************2.開始刪除資料***************************************/
				PlatinfService platinfSvc = new PlatinfService();
				platinfSvc.deletePlatinf(pin_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back_end/platinf/listAllPlatinf.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/platinf/listAllPlatinf.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
