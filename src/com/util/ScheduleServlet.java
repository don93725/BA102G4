package com.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course_list.model.*;
import com.course_time.model.*;

@WebServlet("/ScheduleServlet")
public class ScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Timer timer;
    Course_timeService course_timeSVC = new Course_timeService(); 
    Course_listService course_listSVC = new Course_listService();
    public void init() throws ServletException {
      TimerTask task = new TimerTask(){ 
	        public void run() {
	        	ArrayList<Course_timeVO> list = (ArrayList)course_timeSVC.getAllBeforeList();
	        	for(int i=0;i<list.size();i++){
	        		ArrayList<Course_listVO> clist = (ArrayList) course_listSVC.getAllByCt_no(list.get(i).getCt_no());
	        		if(Integer.valueOf(list.get(i).getCount()) < Integer.valueOf(list.get(i).getClass_num())){
	        			for(int j=0;j<clist.size();j++){
	        				course_listSVC.deleteCourse_list(list.get(i).getCt_no(), clist.get(j).getStu_acc());
	        			}
	        			course_timeSVC.deleteCourse_time(list.get(i).getCt_no());
	        			System.out.println(list.get(i).getCourseVO().getCrs_name() + " 時間: " +list.get(i).getDeadline() + "開課失敗");
	        		}else{
	        			course_timeSVC.open(list.get(i).getCt_no());
	        		}
	        	}
	        } 
      };
      timer = new Timer(); 
      Calendar cal = new GregorianCalendar(2017, Calendar.AUGUST, 19, 0, 0, 0);
      timer.scheduleAtFixedRate(task, cal.getTime(), 24*60*60*1000);
    }
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	 public void destroy() {
	        timer.cancel();
	    }

}
