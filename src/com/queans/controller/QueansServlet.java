package com.queans.controller;

import java.io.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.queans.model.*;
@WebServlet("/back_end/queans/queans.do")
@MultipartConfig(maxRequestSize=1024*1024*1024,maxFileSize=1024*1024*1024)
public class QueansServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自selectQueans_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("que_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/queans/selectQueans_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String que_no = null;
				try {
					que_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/queans/selectQueans_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				QueansService queansSvc = new QueansService();
				QueansVO queansVO = queansSvc.getOneQueans(que_no);
				if (queansVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/queans/selectQueans_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("queansVO", queansVO); // 資料庫取出的queansVO物件,存入req
				String url = "/back_end/queans/listOneQueans.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneQueans.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/queans/selectQueans_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getPart_By_Title".equals(action)) { // 來自selectQueans_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("que_title");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入標題");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/queans/selectQueans_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String que_title = null;
				try {
					que_title = new String(str);
				} catch (Exception e) {
					errorMsgs.add("標題格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/queans/selectQueans_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				QueansService queansSvc = new QueansService();
				List<QueansVO> queansVO = queansSvc.getPartByTitle(que_title);
				if (queansVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/queans/selectQueans_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("queansVO", queansVO); // 資料庫取出的queansVO物件,存入req
				String url = "/back_end/queans/listOneMoreQueans.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMoreQueans.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/queans/selectQueans_page.jsp");
				failureView.forward(req, res);
			}
		}
		
        if ("getPart_By_Type".equals(action)) { // 來自selectQueans_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("que_type");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入主題");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/queans/selectQueans_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String que_type = null;
				try {
					que_type = new String(str);
				} catch (Exception e) {
					errorMsgs.add("主題格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/queans/selectQueans_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				QueansService queansSvc = new QueansService();
				List<QueansVO> queansVO = queansSvc.getPartByType(que_type);
				if (queansVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/queans/selectQueans_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("queansVO", queansVO); // 資料庫取出的queansVO物件,存入req
				String url = "/back_end/queans/listOneMoreQueans.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMoreQueans.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/queans/selectQueans_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getPart_By_Date".equals(action)) { // 來自selectQueans_page.jsp的請求
			
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
							.getRequestDispatcher("/back_end/queans/selectQueans_page.jsp");
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
							.getRequestDispatcher("/back_end/queans/selectQueans_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				QueansService queansSvc = new QueansService();
				List<QueansVO> queansVO = queansSvc.getPartByDate(upd_date);
				if (queansVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/queans/selectQueans_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("queansVO", queansVO); // 資料庫取出的queansVO物件,存入req
				String url = "/back_end/queans/listOneMoreQueans.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMoreQueans.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/queans/selectQueans_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllQueans.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String que_no = new String(req.getParameter("que_no"));
				
				/***************************2.開始查詢資料****************************************/
				QueansService queansSvc = new QueansService();
				QueansVO queansVO = queansSvc.getOneQueans(que_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("queansVO", queansVO);         // 資料庫取出的queansVO物件,存入req
				String url = "/back_end/queans/update_queans_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_queans_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/queans/listAllQueans.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_queans_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String que_no = req.getParameter("que_no").trim();
				String que_type = req.getParameter("que_type").trim();
				String que_title = req.getParameter("que_title").trim();
				String ans_ctx = req.getParameter("ans_ctx").trim();				
				Part part = req.getPart("que_photo");
				byte[] que_photo = null;
				if(part.getSize()!=0){
					InputStream is =part.getInputStream();
					que_photo = new byte[is.available()];
					is.read(que_photo);
				}
				
				if(que_title.equals("")){
					errorMsgs.add("請輸入標題");
				}
				if(ans_ctx.equals("")){
					errorMsgs.add("請輸入內文");
				}
				
				QueansVO queansVO = new QueansVO();
				queansVO.setQue_no(que_no);
				queansVO.setQue_type(que_type);
				queansVO.setQue_title(que_title);
				queansVO.setAns_ctx(ans_ctx);
				queansVO.setQue_photo(que_photo);
				
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("queansVO", queansVO); // 含有輸入格式錯誤的queansVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/queans/update_queans_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				QueansService queansSvc = new QueansService();
				queansVO = queansSvc.updateQueans(que_no, que_type, que_title, ans_ctx, que_photo);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("queansVO", queansVO); // 資料庫update成功後,正確的的queansVO物件,存入req
				String url = "/back_end/queans/listAllQueans.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneQueans.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/queans/update_queans_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addQueans.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			
				String que_type = req.getParameter("que_type").trim();
				String que_title = req.getParameter("que_title").trim();
				String ans_ctx = req.getParameter("ans_ctx").trim();
				Part part = req.getPart("que_photo");
				byte[] que_photo = null;
				if(part.getSize()!=0){
					InputStream is =part.getInputStream();
					que_photo = new byte[is.available()];
					is.read(que_photo);
				}
				
				
				if(que_title.equals("")){
					errorMsgs.add("請輸入標題");
				}
				if(ans_ctx.equals("")){
					errorMsgs.add("請輸入內文");
				}
				
				
				QueansVO queansVO = new QueansVO();
				queansVO.setQue_type(que_type);
				queansVO.setQue_title(que_title);
				queansVO.setAns_ctx(ans_ctx);
				queansVO.setQue_photo(que_photo);
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("queansVO", queansVO); // 含有輸入格式錯誤的queansVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/queans/addQueans.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				QueansService queansSvc = new QueansService();
				queansVO = queansSvc.addQueans(que_type, que_title, ans_ctx, que_photo);
				
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back_end/queans/listAllQueans.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllQueans.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/queans/addQueans.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllQueans.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String que_no = new String(req.getParameter("que_no"));
				
				/***************************2.開始刪除資料***************************************/
				QueansService queansSvc = new QueansService();
				queansSvc.deleteQueans(que_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back_end/queans/listAllQueans.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/queans/listAllQueans.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
