package com.CPM.conroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.place_pic.model.Place_PicService;
import com.place_pic.model.Place_PicVO;
import com.place_report.model.PlaceReportService;
import com.place_report.model.PlaceReportVO;
import com.place_time.model.Place_timeService;
import com.place_time.model.Place_timeVO;

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
			String pt_no = req.getParameter("pt_no");
			
			Place_timeService place_timeSVC = new Place_timeService();
			place_timeSVC.payPlace_time(pt_no);
			Place_timeVO place_timeVO = place_timeSVC.getOnePlace_time(pt_no);
			
			req.setAttribute("place_timeVO", place_timeVO);
			RequestDispatcher pay = req.getRequestDispatcher("/front_end/CPM/PaySuccess.jsp");
			pay.forward(req, res);
			return;
		}else if("payAfter".equals(action)){
			String pt_no = req.getParameter("pt_no");
			
			Place_timeService place_timeSVC = new Place_timeService();
			place_timeSVC.payAfter(pt_no);
			Place_timeVO place_timeVO = place_timeSVC.getOnePlace_time(pt_no);
			
			req.setAttribute("place_timeVO", place_timeVO);
			RequestDispatcher pay = req.getRequestDispatcher("/front_end/CPM/PayAfterSuccess.jsp");
			pay.forward(req, res);
			return;
		}else if("deletePlace".equals(action)){
			String pt_no = req.getParameter("pt_no");
			
			Place_timeService place_timeSVC = new Place_timeService();
			place_timeSVC.deletePlace_time(pt_no);
			
			return;
		}else if("signUp".equals(action)){
			
			Course_listService course_listSVC = new Course_listService();
			course_listSVC.addCourse_list(req.getParameter("ct_no"), ((MembersVO) session.getAttribute("user")).getMem_acc(), java.sql.Date.valueOf(req.getParameter("cl_date")), Integer.valueOf(req.getParameter("crs_time")));
			
			return;
		}else if("showBlock".equals(action)){
			
			Place_timeService place_timeSVC = new Place_timeService();
			Place_timeVO place_timeVO = place_timeSVC.getOnePlace_time(req.getParameter("pt_no"));
			
			Place_PicService place_picSVC = new Place_PicService();
			List<Place_PicVO> place_PicList = place_picSVC.getAllPPic(place_timeVO.getP_no());
			req.setAttribute("place_timeVO", place_timeVO);
			req.setAttribute("place_PicList", place_PicList);
			RequestDispatcher courseList = req.getRequestDispatcher("/front_end/CPM/ShowBlock.jsp");
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
			
			PlaceReportService placeReportSVC = new PlaceReportService();
			PlaceReportVO placeReportVO = placeReportSVC.getByPt(req.getParameter("pt_no"));
			req.setAttribute("placeReportVO", placeReportVO);
			
			RequestDispatcher courseList = req.getRequestDispatcher("/front_end/CPM/ShowReportBlock.jsp");
			courseList.forward(req, res);
			return;
		}else if("placeList".equals(action)){
			
			req.setAttribute("active", "1");
			req.setAttribute("which", "場地列表");
			req.setAttribute("page", "/front_end/CPM/PlaceList.jsp");
			RequestDispatcher placeList = req.getRequestDispatcher("/front_end/CPM/index.jsp");
			placeList.forward(req, res);
			return;
		}else if("placeRecord".equals(action)){
			
			req.setAttribute("active", "3");
			req.setAttribute("which", "場地使用紀錄");
			req.setAttribute("page", "/front_end/CPM/PlaceRecord.jsp");
			RequestDispatcher courseList = req.getRequestDispatcher("/front_end/CPM/index.jsp");
			courseList.forward(req, res);
			return;
		}else if("calendar".equals(action)){
			
			req.setAttribute("active", "4");
			req.setAttribute("which", "行事曆");
			req.setAttribute("page", "/front_end/CPM/Calendar.jsp");
			RequestDispatcher courseList = req.getRequestDispatcher("/front_end/CPM/index.jsp");
			courseList.forward(req, res);
			return;
		}else if("leaveCourse".equals(action)){
			String reason = req.getParameter("reason");
			String ct_no = req.getParameter("ct_no");
			String stu_acc = req.getParameter("stu_acc");
			
			Course_listService course_listSVC = new Course_listService();
			course_listSVC.leave(reason,ct_no, stu_acc);

			return;
		}else if("reportPlace".equals(action)){
			
			String pr_ctx = req.getParameter("pr_ctx");
			String pt_no = req.getParameter("pt_no");
			
			Place_timeService place_timeSVC = new Place_timeService();
			place_timeSVC.report(pt_no);
			
			PlaceReportService placeReportSVC = new PlaceReportService();
			placeReportSVC.addPR(pt_no, ((MembersVO)session.getAttribute("user")).getMem_no(), pr_ctx, null, null);

			return;
		}else if("evaluation".equals(action)){
			String pt_no = req.getParameter("pt_no");
			Integer eva =Integer.valueOf(req.getParameter("eva"));
			String eva_ct = req.getParameter("eva_ct");
			
			Place_timeService place_timeSVC = new Place_timeService();
			place_timeSVC.eva(eva, eva_ct, pt_no);
			Place_timeVO place_timeVO = place_timeSVC.getOnePlace_time(pt_no);
			
			req.setAttribute("place_timeVO", place_timeVO);
			RequestDispatcher pay = req.getRequestDispatcher("/front_end/CPM/EvaluationSuccess.jsp");
			pay.forward(req, res);
			return;
		}else if("deleteCalendarPlace".equals(action)){
			String rp_date = req.getParameter("rp_date");
			Integer rp_time = Integer.valueOf(req.getParameter("rp_time"));
			
			Place_timeService place_timeSVC = new Place_timeService();
			place_timeSVC.deleteCalendar(rp_date,rp_time,((MembersVO)session.getAttribute("user")).getMem_acc());

			return;
		}else if("placeDetailList".equals(action)){
			Place_timeService place_timeSVC = new Place_timeService();
			ArrayList<Place_timeVO> plist = (ArrayList) place_timeSVC.getAllList(); 
			for (int i = 0; i < plist.size(); i++) {
				plist.get(i).setRp_timeShow(
						(String) getServletContext().getAttribute(String.valueOf(((Place_timeVO) plist.get(i)).getRp_time())));
			}
			req.setAttribute("plist", plist);
			RequestDispatcher pay = req.getRequestDispatcher("/front_end/PlaceDetails/PlaceList.jsp");
			pay.forward(req, res);
			return;
		}else if ("placeSelectList".equals(action)) {
			Course_timeService course_timeSVC = new Course_timeService();
			String p_name = req.getParameter("p_name");
			String p_add = req.getParameter("p_add");
			String p_cap = req.getParameter("p_cap");
			
			String select = (p_name == ""?"":" And p.p_name LIKE '%"+ p_name +"%'") 
						  + (p_add == ""?"":" And p.p_add LIKE '%"+ p_add +"%'") 
						  + (p_cap.equals("null")?"":" And p.p_cap between "+ p_cap.substring(0, p_cap.indexOf("-")) + " and " + p_cap.substring(p_cap.indexOf("-")+1, p_cap.lastIndexOf("0")+1));

			Place_timeService place_timeSVC = new Place_timeService();
			ArrayList<Place_timeVO> plist = (ArrayList) place_timeSVC.getAllListSelect(select); 
			for (int i = 0; i < plist.size(); i++) {
				plist.get(i).setRp_timeShow(
						(String) getServletContext().getAttribute(String.valueOf(((Place_timeVO) plist.get(i)).getRp_time())));
			}
			req.setAttribute("plist", plist);
			RequestDispatcher coursePublishList = req.getRequestDispatcher("/front_end/PlaceDetails/PlaceList.jsp");
			coursePublishList.forward(req, res);
			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
