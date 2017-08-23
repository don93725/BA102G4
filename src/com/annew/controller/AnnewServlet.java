package com.annew.controller;

import java.io.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.annew.model.*;
@WebServlet("/back_end/annew/annew.do")
@MultipartConfig(maxRequestSize=1024*1024*1024,maxFileSize=1024*1024*1024)
public class AnnewServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自selectAnnew_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("ann_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/annew/selectAnnew_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String ann_no = null;
				try {
					ann_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/annew/selectAnnew_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				AnnewService annewsSvc = new AnnewService();
				AnnewVO annewVO = annewsSvc.getOneAnnew(ann_no);
				if (annewVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/annew/selectAnnew_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("annewVO", annewVO); // 資料庫取出的annewVO物件,存入req
				String url = "/back_end/annew/listOneAnnew.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAnnew.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/annew/selectAnnew_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		 if ("getPart_By_Title".equals(action)) { // 來自selectAnnew_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("ann_title");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入標題");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/annew/selectAnnew_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String ann_title = null;
				try {
					ann_title = new String(str);
				} catch (Exception e) {
					errorMsgs.add("標題格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/annew/selectAnnew_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				
				AnnewService annewSvc = new AnnewService();
				List<AnnewVO> annewVO = annewSvc.getPartByTitle(ann_title);
				if (annewVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/annew/selectAnnew_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("annewVO", annewVO); // 資料庫取出的annewVO物件,存入req
				String url = "/back_end/annew/listOneMoreAnnew.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMoreAnnew.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/annew/selectAnnew_page.jsp");
				failureView.forward(req, res);
			}
		}
		 
		 
			
		 if ("getPart_By_Date".equals(action)) { // 來自selectAnnew_page.jsp的請求
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String str = req.getParameter("ann_date");
					if (str == null || (str.trim()).length() == 0) {
						errorMsgs.add("請輸入日期");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/annew/selectAnnew_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					String ann_date = null;
					try {
						ann_date = new String(str);
					} catch (Exception e) {
						errorMsgs.add("日期格式不正確");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/annew/selectAnnew_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					/***************************2.開始查詢資料*****************************************/
					AnnewService annewSvc = new AnnewService();
					List<AnnewVO> annewVO = annewSvc.getPartByDate(ann_date);
					if (annewVO == null) {
						errorMsgs.add("查無資料");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back_end/annew/selectAnnew_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("annewVO", annewVO); // 資料庫取出的annewVO物件,存入req
					String url = "/back_end/annew/listOneMoreAnnew.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMoreFitkw.jsp
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/annew/selectAnnew_page.jsp");
					failureView.forward(req, res);
				}
			}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllAnnew.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String ann_no = new String(req.getParameter("ann_no"));
				
				/***************************2.開始查詢資料****************************************/
				AnnewService annewSvc = new AnnewService();
				AnnewVO annewVO = annewSvc.getOneAnnew(ann_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("annewVO", annewVO);         // 資料庫取出的annewVO物件,存入req
				String url = "/back_end/annew/update_annew_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_annew_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/annew/listAllAnnew.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_annew_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String ann_no = req.getParameter("ann_no").trim();
				String ann_title = req.getParameter("ann_title").trim();
				String ann_ctx = req.getParameter("ann_ctx").trim();				
				Part part = req.getPart("ann_photo");
				byte[] ann_photo = null;
				if(part.getSize()!=0){
					InputStream is =part.getInputStream();
					ann_photo = new byte[is.available()];
					is.read(ann_photo);
				}
				
				
				if(ann_title.equals("")){
					errorMsgs.add("請輸入標題");
				}
				if(ann_ctx.equals("")){
					errorMsgs.add("請輸入內文");
				}

				
				AnnewVO annewVO = new AnnewVO();
				annewVO.setAnn_no(ann_no);
				annewVO.setAnn_title(ann_title);
				annewVO.setAnn_ctx(ann_ctx);
				annewVO.setAnn_photo(ann_photo);
				
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("annewVO", annewVO); // 含有輸入格式錯誤的annewVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/annew/update_annew_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				AnnewService annewSvc = new AnnewService();
				annewVO = annewSvc.updateAnnew(ann_no, ann_title, ann_ctx, ann_photo);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("annewVO", annewVO); // 資料庫update成功後,正確的的annewVO物件,存入req
				String url = "/back_end/annew/listAllAnnew.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneAnnew.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/annew/update_annew_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addAnnew.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			
				String ann_title = req.getParameter("ann_title").trim();
				String ann_ctx = req.getParameter("ann_ctx").trim();
				Part part = req.getPart("ann_photo");
				byte[] ann_photo = null;
				if(part.getSize()!=0){
					InputStream is =part.getInputStream();
					ann_photo = new byte[is.available()];
					is.read(ann_photo);
				}
				
				if(ann_title.equals("")){
					errorMsgs.add("請輸入標題");
				}
				if(ann_ctx.equals("")){
					errorMsgs.add("請輸入內文");
				}
				
				AnnewVO annewVO = new AnnewVO();
				annewVO.setAnn_title(ann_title);
				annewVO.setAnn_ctx(ann_ctx);
				annewVO.setAnn_photo(ann_photo);
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("annewVO", annewVO); // 含有輸入格式錯誤的annewVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/annew/addAnnew.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				AnnewService annewSvc = new AnnewService();
				annewVO = annewSvc.addAnnew(ann_title, ann_ctx, ann_photo);
				
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back_end/annew/listAllAnnew.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllAnnew.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/annew/addAnnew.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllAnnew.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String ann_no = new String(req.getParameter("ann_no"));
				
				/***************************2.開始刪除資料***************************************/
				AnnewService annewSvc = new AnnewService();
				annewSvc.deleteAnnew(ann_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back_end/annew/listAllAnnew.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/annew/listAllAnnew.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
