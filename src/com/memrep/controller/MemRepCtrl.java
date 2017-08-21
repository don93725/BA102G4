package com.memrep.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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
import com.manager.model.ManagerVO;
import com.membersreport.model.MembersReportService;
import com.membersreport.model.MembersReportVO;

@WebServlet("/memrep/MemRepCtrl")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemRepCtrl extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=Big5");
		
		String action = req.getParameter("action");
		
		
//		Collection<Part> parts = req.getParts(); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理
		
		
		if("Report".equals(action)){
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String mr_no = req.getParameter("mr_no");
//				String mr_def = req.getParameter("mr_def");
				MembersReportService MRSvc = new MembersReportService();
				MRSvc.updateMR(mr_no);
				MRSvc.updateMRNum(mr_no);
				String url = "/back_end/memrep/setCheckMemRep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("審核失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/memrep/setCheckMemRep.jsp");
				failureView.forward(req, res);
			
			}
			
			
		}
		if("NO_Report".equals(action)){
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String mr_no = req.getParameter("mr_no");
//				String mr_def = req.getParameter("mr_def");
				MembersReportService MRSvc = new MembersReportService();
				MRSvc.updateMR(mr_no);
				String url = "/back_end/memrep/setCheckMemRep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("審核失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/memrep/setCheckMemRep.jsp");
				failureView.forward(req, res);
			
			}
		}
	}

}
