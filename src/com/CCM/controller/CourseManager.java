
package com.CCM.controller;

import com.members.model.*;
import com.Course.model.CourseService;
import com.Course.model.CourseVO;
import com.Course_list.model.Course_listService;
import com.Course_list.model.Course_listVO;
import com.Course_picture.model.Course_pictureService;
import com.Course_time.model.Course_timeService;
import com.Course_time.model.Course_timeVO;
import com.place_time.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet({ "/CCM/CourseManager.do" })
public class CourseManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=utf-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

		
		if ("courseList".equals(action)) {
			req.setAttribute("active", "1");
			req.setAttribute("page", "/front_end/CCM/CourseList.jsp");
			RequestDispatcher courseList = req.getRequestDispatcher("/front_end/CCM/index.jsp");
			courseList.forward(req, res);
			return;
		} else if ("coursePublish".equals(action)) {
			String crs_name = req.getParameter("crs_name");
			String crs_no = req.getParameter("crs_no");
			String details = req.getParameter("details");
			String category = req.getParameter("category");
			Place_timeService place_timeSVC = new Place_timeService();
			Course_pictureService course_pictureSVC = new Course_pictureService();
			ArrayList<Place_timeVO> cpicList = (ArrayList) course_pictureSVC.getAll(crs_no);
			ArrayList<Place_timeVO> ptList = (ArrayList) place_timeSVC.getAll(((MembersVO) session.getAttribute("user")).getMem_acc());
			req.setAttribute("ptList", ptList);
			req.setAttribute("cpicList", cpicList);
			req.setAttribute("crs_no", crs_no);
			req.setAttribute("crs_name", crs_name);
			req.setAttribute("details", details);
			req.setAttribute("category", category);
			req.setAttribute("active", "1");
			req.setAttribute("page", "/front_end/CCM/CoursePublish.jsp");
			RequestDispatcher coursePublishList = req.getRequestDispatcher("/front_end/CCM/index.jsp");
			coursePublishList.forward(req, res);
			return;
		} else if ("courseInsert".equals(action)) {
			String errorMsgs = "";
			PrintWriter out = res.getWriter();
	
			try{
				List<String> imgbase = new ArrayList<String>();
				String crs_name = req.getParameter("crs_name");
				String details = req.getParameter("details");
				int crs_pic_count = Integer.valueOf(req.getParameter("crs_pic_count"));
				for(int i=0;i<crs_pic_count;i++){
					String no = "crs_pic_base" + i;
					imgbase.add(req.getParameter(no));
				}
				
				
				if(crs_name.length()>10 || crs_name.length()<2){
					out.print("課程名稱需介於2~10字");
					return;
				}
				
				if(details == ""){
					out.print("請填寫課程內容");
					return;
				}
				
				if(crs_pic_count == 0){
					out.print("至少新增一張圖片");
					return;
				}
				
				
				
				CourseService courseSVC = new CourseService();
				courseSVC.addCourse(((MembersVO) session.getAttribute("user")).getMem_acc(), req.getParameter("crs_name"),
						req.getParameter("details"), req.getParameter("category"),imgbase);	
				out.println("success");
				return;
			}catch(Exception e) {
				out.print("課程內容過長");
			}
		} else if ("courseUpdate".equals(action)) {
			CourseService courseSvc = new CourseService();
			String crs_name = req.getParameter("crs_name");
			String crs_no = req.getParameter("crs_no");
			String details = req.getParameter("details");
			String category = req.getParameter("category");
			courseSvc.updateCourse(crs_no, crs_name, details, category);

			return;
		}else if ("courseDelete".equals(action)) {
			CourseService courseSVC = new CourseService();
			courseSVC.deleteCourse(req.getParameter("crs_no"));
			req.setAttribute("active", "1");
			req.setAttribute("page", "/front_end/CCM/CourseList.jsp");
			RequestDispatcher courseList = req.getRequestDispatcher("/front_end/CCM/index.jsp");
			courseList.forward(req, res);
			return;
		} else if ("coursePublishList".equals(action)) {
			req.setAttribute("active", "1");
			req.setAttribute("page", "/front_end/CCM/CoursePublishList.jsp");
			RequestDispatcher coursePublishList = req.getRequestDispatcher("/front_end/CCM/index.jsp");
			coursePublishList.forward(req, res);
			return;
		}else if ("course_timeInsert".equals(action)) {
			Course_timeService course_timeSvc = new Course_timeService();
			CourseVO courseVO = new CourseVO();
			Map<String, String[]> map = (Map<String, String[]>) req.getParameterMap();
			String crs_no = req.getParameter("crs_no");
			String category = req.getParameter("category");
			int count = Integer.parseInt(req.getParameter("count"));
			courseVO.setCrs_no(crs_no);
			courseVO.setCategory(category);
			for (int i = 0; i <= count; i++) {
				course_timeSvc.addCourse_time(null, map.get("p_no")[i].equals("null")?null:map.get("p_no")[i], java.sql.Date.valueOf(map.get("date")[i]),
						java.sql.Date.valueOf(map.get("deadline")[i]),
						Integer.parseInt((String) req.getParameter("time" + i)), map.get("price")[i],
						map.get("limit")[i], map.get("class_num")[i], 1, courseVO);
			}

			req.setAttribute("active", "1");
			req.setAttribute("page", "/front_end/CCM/CoursePublishList.jsp");
			RequestDispatcher coursePublishList = req.getRequestDispatcher("/front_end/CCM/index.jsp");
			coursePublishList.forward(req, res);
			return;
		}  else if ("course_timeUpdate".equals(action)) {
			Course_timeService course_timeSvc = new Course_timeService();
			String ct_no = req.getParameter("ct_no");
			String deadline = req.getParameter("deadline");
			String crs_date = req.getParameter("crs_date");
			String crs_time = req.getParameter("crs_time");
			String price = req.getParameter("price");
			String p_no = req.getParameter("p_no");
			course_timeSvc.updateCourse_time(ct_no, p_no.equals("null")?null:p_no, java.sql.Date.valueOf(crs_date), java.sql.Date.valueOf(deadline), Integer.valueOf(crs_time), price);

			return;
		}else if ("course_timeDelete".equals(action)) {
			Course_timeService course_timeSvc = new Course_timeService();
			Course_listService course_listSVC = new Course_listService();
			ArrayList<Course_listVO> clist = (ArrayList) course_listSVC.getAllByCt_no(req.getParameter("ct_no"));
			for(int i=0;i<clist.size();i++){
				course_listSVC.deleteCourse_list(req.getParameter("ct_no"), clist.get(i).getStu_acc());
			}
			course_timeSvc.deleteCourse_time(req.getParameter("ct_no"));
			req.setAttribute("active", "1");
			req.setAttribute("page", "/front_end/CCM/CoursePublishList.jsp");
			RequestDispatcher coursePublishList = req.getRequestDispatcher("/front_end/CCM/index.jsp");
			coursePublishList.forward
			(req, res);
			return;
		}else if ("courseOpenList".equals(action)) {
			req.setAttribute("active", "1");
			req.setAttribute("page", "/front_end/CCM/CourseOpenList.jsp");
			RequestDispatcher coursePublishList = req.getRequestDispatcher("/front_end/CCM/index.jsp");
			coursePublishList.forward(req, res);
			return;
		}else if ("courseRecord".equals(action)) {
			req.setAttribute("page", "/front_end/CCM/CourseRecord.jsp");
			req.setAttribute("active", "2");
			RequestDispatcher coursePublishList = req.getRequestDispatcher("/front_end/CCM/index.jsp");
			coursePublishList.forward(req, res);
			return;
		}else if ("courseReport".equals(action)) {
			req.setAttribute("page", "/front_end/CCM/CourseReport.jsp");
			req.setAttribute("active", "3");
			RequestDispatcher coursePublishList = req.getRequestDispatcher("/front_end/CCM/index.jsp");
			coursePublishList.forward(req, res);
			return;
		}else if ("course_picDelete".equals(action)) {
			Course_pictureService course_pictureSVC = new Course_pictureService();
			for(int i=0;i<Integer.valueOf(req.getParameter("deletePicCount"));i++){
				course_pictureSVC.deleteCourse_picture(req.getParameter("crs_pic_no"+i));
			}
			
			return;
		}else if ("course_picInsert".equals(action)) {
			Course_pictureService course_pictureSVC = new Course_pictureService();
			List<String> imgbase = new ArrayList<String>();
			String crs_no = req.getParameter("crs_no");
			int crs_pic_count = Integer.valueOf(req.getParameter("crs_pic_count"));
			for(int i=0;i<crs_pic_count;i++){
				String no = "crs_pic_base" + i;
				imgbase.add(req.getParameter(no));
			}
			course_pictureSVC.addCourse_picture(crs_no, imgbase);
			
			return;
		}else if ("crsDetailList".equals(action)) {
			Course_timeService course_timeSVC = new Course_timeService();
			ArrayList<Course_timeVO> crsList = (ArrayList)course_timeSVC.getAllCrsList();
			for (int i = 0; i < crsList.size(); i++) {
				((Course_timeVO) crsList.get(i)).setCrs_timeShow((String) getServletContext()
						.getAttribute(String.valueOf(((Course_timeVO) crsList.get(i)).getCrs_time())));
				((Course_timeVO) crsList.get(i)).getCourseVO().setCategoryChange((String) getServletContext()
						.getAttribute(((Course_timeVO) crsList.get(i)).getCourseVO().getCategory()));
			}
			req.setAttribute("crsList",crsList);
			RequestDispatcher coursePublishList = req.getRequestDispatcher("/front_end/CourseDetails/CrsList.jsp");
			coursePublishList.forward(req, res);
			return;
		}else if ("crsSelectList".equals(action)) {
			Course_timeService course_timeSVC = new Course_timeService();
			String coa_name = req.getParameter("coa_name");
			String crs_name = req.getParameter("crs_name");
			String category = req.getParameter("category");
			String crs_date = req.getParameter("crs_date");
			String crs_time = req.getParameter("crs_time");
			
			String select = (coa_name == ""?"":" And coa.coa_name LIKE '%"+ coa_name +"%'") 
						  + (crs_name == ""?"":" And c.crs_name LIKE '%"+ crs_name +"%'") 
						  + (category.equals("null")?"":" And c.category = '"+ category +"'")
						  + (crs_date == ""?"":" And ct.crs_date = TO_DATE('" + crs_date + "','YYYY-MM-DD')")
						  + (crs_time.equals("null")?"":" And ct.crs_time = '"+ crs_time + "'");

			ArrayList<Course_timeVO> crsList = (ArrayList)course_timeSVC.getAllCrsListSelect(select);
			for (int i = 0; i < crsList.size(); i++) {
				((Course_timeVO) crsList.get(i)).setCrs_timeShow((String) getServletContext()
						.getAttribute(String.valueOf(((Course_timeVO) crsList.get(i)).getCrs_time())));
				((Course_timeVO) crsList.get(i)).getCourseVO().setCategoryChange((String) getServletContext()
						.getAttribute(((Course_timeVO) crsList.get(i)).getCourseVO().getCategory()));
			}
			req.setAttribute("crsList",crsList);
			RequestDispatcher coursePublishList = req.getRequestDispatcher("/front_end/CourseDetails/CrsList.jsp");
			coursePublishList.forward(req, res);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
