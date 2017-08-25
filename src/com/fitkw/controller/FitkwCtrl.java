package com.fitkw.controller;

import java.io.*;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.fitkw.model.*;
@WebServlet("/FitkwCtrl")
public class FitkwCtrl extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自selectFitkw_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("fik_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/fitkw/selectFitkw_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String fik_no = null;
				try {
					fik_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/fitkw/selectFitkw_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				FitkwService fitkwSvc = new FitkwService();
				FitkwVO fitkwVO = fitkwSvc.getOneFitkw(fik_no);
				if (fitkwVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/fitkw/selectFitkw_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("fitkwVO", fitkwVO); // 資料庫取出的fitkwVO物件,存入req
				String url = "/front_end/fitkw/kwcontext.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneFitkw.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/fitkw/selectFitkw_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getPart_By_Title".equals(action)) { // 來自selectFitkw_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("fik_title");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入標題");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/fitkw/selectFitkw_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String fik_title = null;
				try {
					fik_title = new String(str);
				} catch (Exception e) {
					errorMsgs.add("標題格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/fitkw/selectFitkw_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				FitkwService fitkwSvc = new FitkwService();
				List<FitkwVO> fitkwVO = fitkwSvc.getPartByTitle(fik_title);
				if (fitkwVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/fitkw/selectFitkw_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("fitkwVO", fitkwVO); // 資料庫取出的fitkwVO物件,存入req
				String url = "/back_end/fitkw/listOneMoreFitkw.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMoreFitkw.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/fitkw/selectFitkw_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getPart_By_Type".equals(action)) { // 來自selectFitkw_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("fik_type");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入主題");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/fitkw/selectFitkw_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String fik_type = null;
				try {
					fik_type = new String(str);
				} catch (Exception e) {
					errorMsgs.add("主題格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/fitkw/selectFitkw_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				FitkwService fitkwSvc = new FitkwService();
				List<FitkwVO> fitkwVO = fitkwSvc.getPartByType(fik_type);
				if (fitkwVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/fitkw/selectFitkw_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("fitkwVO", fitkwVO); // 資料庫取出的fitkwVO物件,存入req
				String url = "/back_end/fitkw/listOneMoreFitkw.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMoreFitkw.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/fitkw/selectFitkw_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getPart_By_Date".equals(action)) { // 來自selectFitkw_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("upd_date");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入日期");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/fitkw/selectFitkw_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String upd_date = null;
				try {
					upd_date = new String(str);
				} catch (Exception e) {
					errorMsgs.add("日期格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/fitkw/selectFitkw_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				FitkwService fitkwSvc = new FitkwService();
				List<FitkwVO> fitkwVO = fitkwSvc.getPartByDate(upd_date);
				if (fitkwVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/fitkw/selectFitkw_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("fitkwVO", fitkwVO); // 資料庫取出的fitkwVO物件,存入req
				String url = "/back_end/fitkw/listOneMoreFitkw.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMoreFitkw.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/fitkw/selectFitkw_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllFitkw.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String fik_no = new String(req.getParameter("fik_no"));
				
				/***************************2.開始查詢資料****************************************/
				FitkwService fitkwSvc = new FitkwService();
				FitkwVO fitkwVO = fitkwSvc.getOneFitkw(fik_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("fitkwVO", fitkwVO);         // 資料庫取出的fitkwVO物件,存入req
				String url = "/back_end/fitkw/update_fitkw_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_fitkw_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/fitkw/listAllFitkw.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_fitkw_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String fik_no = req.getParameter("fik_no").trim();
				String fik_type = req.getParameter("fik_type").trim();
				String fik_title = req.getParameter("fik_title").trim();
				String fik_ctx = req.getParameter("fik_ctx").trim();
				Part part = req.getPart("fik_photo");
				byte[] fik_photo = null;
				if(part.getSize()!=0){
					InputStream is =part.getInputStream();
					fik_photo = new byte[is.available()];
					is.read(fik_photo);
				}
				
//				//以下是為了看file的資訊
//				for(String s:part.getHeaderNames()){
//					System.out.println(part.getHeader(s));
//				}
//				//以下是為了看有沒有讀進bytes
//				for(byte bb:fik_photo){
//					System.out.print(bb);
//				}
				
				if(fik_title.equals("")){
					errorMsgs.add("請輸入標題");
				}
				if(fik_ctx.equals("")){
					errorMsgs.add("請輸入內文");
				}
				

				
				FitkwVO fitkwVO = new FitkwVO();
				fitkwVO.setFik_no(fik_no);
				fitkwVO.setFik_type(fik_type);
				fitkwVO.setFik_title(fik_title);
				fitkwVO.setFik_ctx(fik_ctx);
				fitkwVO.setFik_photo(fik_photo);
				
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("fitkwVO", fitkwVO); // 含有輸入格式錯誤的fitkwVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/fitkw/update_fitkw_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				FitkwService fitkwSvc = new FitkwService();
				req.setAttribute("fitkwVO", fitkwVO);
				fitkwVO = fitkwSvc.updateFitkw(fik_no, fik_type, fik_title, fik_ctx, fik_photo);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("fitkwVO", fitkwVO); // 資料庫update成功後,正確的的fitkwVO物件,存入req
				String url = "/back_end/fitkw/listAllFitkw.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneFitkw.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/fitkw/update_fitkw_input.jsp");
				failureView.forward(req, res);
			}
		}
		
	

        if ("insert".equals(action)) { // 來自addFitkw.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			
				String fik_type = req.getParameter("fik_type").trim();
				String fik_title = req.getParameter("fik_title").trim();
				String fik_ctx = req.getParameter("fik_ctx").trim();
				Part part = req.getPart("fik_photo");
				byte[] fik_photo = null;
				if(part.getSize()!=0){
					InputStream is =part.getInputStream();
					fik_photo = new byte[is.available()];
					is.read(fik_photo);
				}
				
//				//以下是為了看file的資訊
//				for(String s:part.getHeaderNames()){
//					System.out.println(part.getHeader(s));
//				}
//				//以下是為了看有沒有讀進bytes
//				for(byte bb:fik_photo){
//					System.out.print(bb);
//				}
				
				if(fik_title.equals("")){
					errorMsgs.add("請輸入標題");
				}
				if(fik_ctx.equals("")){
					errorMsgs.add("請輸入內文");
				}
				
				FitkwVO fitkwVO = new FitkwVO();
				fitkwVO.setFik_type(fik_type);
				fitkwVO.setFik_title(fik_title);
				fitkwVO.setFik_ctx(fik_ctx);
				fitkwVO.setFik_photo(fik_photo);
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("fitkwVO", fitkwVO); // 含有輸入格式錯誤的fitkwVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/fitkw/addFitkw.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				
				FitkwService fitkwSvc = new FitkwService();
				fitkwVO = fitkwSvc.addFitkw(fik_type, fik_title, fik_ctx,fik_photo);
				
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back_end/fitkw/listAllFitkw.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllFitkw.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/fitkw/addFitkw.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllFitkw.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String fik_no = new String(req.getParameter("fik_no"));
				
				/***************************2.開始刪除資料***************************************/
				FitkwService fitkwSvc = new FitkwService();
				fitkwSvc.deleteFitkw(fik_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back_end/fitkw/listAllFitkw.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/fitkw/listAllFitkw.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
