package com.CPM.conroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.course_list.model.Course_listService;
import com.course_list.model.Course_listVO;
import com.course_time.model.Course_timeService;
import com.course_time.model.Course_timeVO;
import com.members.model.MembersVO;

/**
 * Servlet implementation class CoachesPlaceManager
 */
@WebServlet("/CPM/CoachesPlaceManager.do")
public class CoachesPlaceManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=utf-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		if ("pay".equals(action)) {
			String ct_no = req.getParameter("ct_no");
			String stu_acc = req.getParameter("stu_acc");
			
			Course_listService course_listSVC = new Course_listService();
			course_listSVC.payCourse_list(ct_no, stu_acc);
			Course_listVO course_listVO = course_listSVC.getOneCourse_list(ct_no, stu_acc);
			
			req.setAttribute("course_listVO", course_listVO);
			RequestDispatcher pay = req.getRequestDispatcher("/front_end/SCM/PaySuccess.jsp");
			pay.forward(req, res);
			return;
		}else if("deleteCourse".equals(action)){
			String ct_no = req.getParameter("ct_no");
			String stu_acc = req.getParameter("stu_acc");
			
			Course_listService course_listSVC = new Course_listService();
			course_listSVC.deleteCourse_list(ct_no, stu_acc);
			
			req.setAttribute("active", "1");
			req.setAttribute("page", "/front_end/SCM/CourseList.jsp");
			RequestDispatcher courseList = req.getRequestDispatcher("/front_end/SCM/index.jsp");
			courseList.forward(req, res);
			return;
		}else if("signUp".equals(action)){
			
			Course_listService course_listSVC = new Course_listService();
			course_listSVC.addCourse_list(req.getParameter("ct_no"), ((MembersVO) session.getAttribute("user")).getMem_acc(), java.sql.Date.valueOf(req.getParameter("cl_date")), Integer.valueOf(req.getParameter("crs_time")));
			
			return;
		}else if("showBlock".equals(action)){
			
			Course_timeService course_timeSVC = new Course_timeService();
			Course_timeVO course_timeVO = course_timeSVC.getOneCourse_time(req.getParameter("ct_no"));
			
			req.setAttribute("course_timeVO", course_timeVO);
			
			RequestDispatcher courseList = req.getRequestDispatcher("/front_end/SCM/ShowBlock.jsp");
			courseList.forward(req, res);
			return;
		}else if("showLeaveBlock".equals(action)){
			
			Course_listService course_listSVC = new Course_listService();
			Course_listVO course_listVO = course_listSVC.getOneCourse_list(req.getParameter("ct_no"),req.getParameter("stu_acc"));
			
			req.setAttribute("course_listVO", course_listVO);
			
			RequestDispatcher courseList = req.getRequestDispatcher("/front_end/SCM/ShowLeaveBlock.jsp");
			courseList.forward(req, res);
			return;
		}else if("showReportBlock".equals(action)){
			
			Course_listService course_listSVC = new Course_listService();
			Course_listVO course_listVO = course_listSVC.getOneCourse_list(req.getParameter("ct_no"),req.getParameter("stu_acc"));
			
			req.setAttribute("course_listVO", course_listVO);
			
			RequestDispatcher courseList = req.getRequestDispatcher("/front_end/SCM/ShowReportBlock.jsp");
			courseList.forward(req, res);
			return;
		}else if("courseList".equals(action)){
			
			req.setAttribute("active", "1");
			req.setAttribute("which", "選課列表");
			req.setAttribute("page", "/front_end/SCM/CourseList.jsp");
			RequestDispatcher courseList = req.getRequestDispatcher("/front_end/SCM/index.jsp");
			courseList.forward(req, res);
			return;
		}else if("courseOpen".equals(action)){
			
			req.setAttribute("active", "2");
			req.setAttribute("which", "開課列表");
			req.setAttribute("page", "/front_end/SCM/CourseOpen.jsp");
			RequestDispatcher courseList = req.getRequestDispatcher("/front_end/SCM/index.jsp");
			courseList.forward(req, res);
			return;
		}else if("courseRecord".equals(action)){
			
			req.setAttribute("active", "3");
			req.setAttribute("which", "課程紀錄");
			req.setAttribute("page", "/front_end/SCM/CourseRecord.jsp");
			RequestDispatcher courseList = req.getRequestDispatcher("/front_end/SCM/index.jsp");
			courseList.forward(req, res);
			return;
		}else if("calendar".equals(action)){
			
			req.setAttribute("active", "4");
			req.setAttribute("which", "行事曆");
			req.setAttribute("page", "/front_end/SCM/Calendar.jsp");
			RequestDispatcher courseList = req.getRequestDispatcher("/front_end/SCM/index.jsp");
			courseList.forward(req, res);
			return;
		}else if("leaveCourse".equals(action)){
			String reason = req.getParameter("reason");
			String ct_no = req.getParameter("ct_no");
			String stu_acc = req.getParameter("stu_acc");
			
			Course_listService course_listSVC = new Course_listService();
			course_listSVC.leave(reason,ct_no, stu_acc);

			return;
		}else if("reportCourse".equals(action)){
			String report_ct = req.getParameter("report_ct");
			String ct_no = req.getParameter("ct_no");
			String stu_acc = req.getParameter("stu_acc");
			
			Course_listService course_listSVC = new Course_listService();
			course_listSVC.report(report_ct,ct_no, stu_acc);

			return;
		}else if("evaluation".equals(action)){
			String ct_no = req.getParameter("ct_no");
			String stu_acc = req.getParameter("stu_acc");
			String evaluation_coa = req.getParameter("evaluation_coa");
			String evaluation_crs = req.getParameter("evaluation_crs");
			String feedback = req.getParameter("feedback");
			
			Course_listService course_listSVC = new Course_listService();
			course_listSVC.evaluation(evaluation_coa,evaluation_crs,feedback,ct_no, stu_acc);
			Course_listVO course_listVO = course_listSVC.getOneCourse_list(ct_no, stu_acc);
			
			req.setAttribute("course_listVO", course_listVO);
			RequestDispatcher pay = req.getRequestDispatcher("/front_end/SCM/EvaluationSuccess.jsp");
			pay.forward(req, res);
			return;
		}else if("deleteCalendarCourse".equals(action)){
			String stu_acc = req.getParameter("stu_acc");
			String cl_date = req.getParameter("cl_date");
			Integer crs_time = Integer.valueOf(req.getParameter("crs_time"));
			
			Course_listService course_listSVC = new Course_listService();
			course_listSVC.deleteCalendar(cl_date,crs_time,stu_acc);

			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
