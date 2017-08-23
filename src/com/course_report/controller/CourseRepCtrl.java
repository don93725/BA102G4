package com.course_report.controller;

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
import com.course.model.CourseService;
import com.course_list.model.Course_listService;
import com.course_list.model.Course_listVO;
import com.course_time.model.Course_timeService;
import com.manager.model.ManagerVO;
import com.members.model.MembersService;
import com.message.model.MessageService;

@WebServlet("/courserep/CourseRepCtrl")
public class CourseRepCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=Big5");
		
		String action = req.getParameter("action");

		
		
		if("Report".equals(action)){
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			MessageService messageSvc = new MessageService();
			try {
				String ct_no = req.getParameter("ct_no");
				System.out.println(ct_no);
				Course_listService CRSvc = new Course_listService();
				
				CRSvc.updateReportSta(ct_no);
				CRSvc.updateCRNum(ct_no);
				
				///檢舉訊息通知
				CourseService courseSvc = new CourseService();
				String c_acc = courseSvc.getCourse(ct_no).getC_acc();
				System.out.println("c_acc:   "+c_acc);
				MembersService memSvc = new MembersService();
				String c_no = memSvc.getMemAcc(c_acc).getMem_no();
				messageSvc.add(c_no, "0", "您開的課程被檢舉 ,請在近期內改善...");
				
				String url = "/back_end/courserep/listAllCourseRep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("審核失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/courserep/listAllCourseRep.jsp");
				failureView.forward(req, res);
			
			}
			
		}

		if("NO_Report".equals(action)){
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String ct_no = req.getParameter("ct_no");
				System.out.println(ct_no);
				
				Course_listService CRSvc = new Course_listService();
				CRSvc.updateReportSta(ct_no);
				
				String url = "/back_end/courserep/listAllCourseRep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
			} catch (Exception e) {
				errorMsgs.add("審核失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/courserep/listAllCourseRep.jsp");
				failureView.forward(req, res);
			
			}
		}

	}

}
