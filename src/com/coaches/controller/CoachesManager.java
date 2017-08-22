package com.coaches.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coaches.model.CoachesDAO;
import com.coaches.model.CoachesService;
import com.coaches.model.CoachesVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


@SuppressWarnings("serial")
@WebServlet("/CoachesManager")

public class CoachesManager extends HttpServlet {
private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		
	public void doGet(HttpServletRequest rq, HttpServletResponse rp)
			throws ServletException, IOException {
//		Course_timeService course_timeSvc = new Course_timeService();
		
		
	
//		
//		java.util.Date du = new java.util.Date();
//		
//		java.sql.Date birthDate = new java.sql.Date(du.getTime());
		
//		Map<String, String[]> map = new TreeMap<String, String[]>();
//		map.put("crs_date", new String[] { "2017-08-09" });
//		map.put("crs_time", new String[] { "All" });
//		map.put("category", new String[] { "All" });				
//		
//		
//		List<Course_timeVO>list = course_timeSvc.getClass(map);
//		
//		
//		System.out.println(list);
//		
//		
//		writeText(rp, gson.toJson(list));
//		
		
//		Course_listVO course_listVO = new Course_listVO();
//		
//		java.util.Date du = new java.util.Date();
//		
//		java.sql.Date cl_date = new java.sql.Date(du.getTime());				
//		
//		
//		
//		course_listVO.setCt_no("C000006");
//	    course_listVO.setStu_acc("stu003");
//	    course_listVO.setCl_date(cl_date);
//	    course_listVO.setCrs_time(1);
//		
//	    Course_listService course_listService = new Course_listService();
//	    
//	    course_listVO=course_listService.addCourse_list("C000006", "stu003", cl_date, 1);
		
		
	}
	
							
		
	private void writeText(HttpServletResponse rp, String json) throws IOException {
		// TODO Auto-generated method stub
		
		rp.setContentType(CONTENT_TYPE);
		PrintWriter out = rp.getWriter();
		 System.out.println("outText: " + json);
		out.print(json);
	
	}



	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rp)
			throws ServletException, IOException {
		
		
		rq.setCharacterEncoding("UTF8");
		
		rp.setContentType("text/html;charset=utf8");
		
		//Gson gson = new Gson();
		BufferedReader br = rq.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(),JsonObject.class);
		CoachesService coachesSvc = new CoachesService();
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: " + action);

		
		if (action.equals("getAll")) {
			String coa_name = jsonObject.get("coa_name").getAsString();
			
			
			
			String coa_into = jsonObject.get("coa_into").getAsString();
			
			
			System.out.println(coa_name+coa_into);
			
			List<CoachesVO>courseList = coachesSvc.getAll();
		
			writeText(rp, gson.toJson(courseList));
		
		
		
		
		
		
		
		
		
		
		
//		if (action.equals("getClass")) {
//			String date = jsonObject.get("crs_date").getAsString();
//			
//			System.out.println(date );
//			
//			String category = jsonObject.get("category").getAsString();
//			String crs_time = jsonObject.get("crs_time").getAsString();
//			
//			System.out.println(date + category + crs_time);
//			
//			
//			
//			HashMap<String, String[]> map = new HashMap<String, String[]>();
//			
//			
//			if(category.equals("All")&&crs_time.equals("All")){
//				map.put("crs_date" ,new String[] {date});
//				map.put("action" , new String[] { "getClass"});
//									
//			
//			}else if(category.equals("All")&&!crs_time.equals("All")){
//				map.put("crs_date" ,new String[] {date});
//				map.put("action" , new String[] { "getClass"});
//				map.put("crs_time" ,new String[] { crs_time });
//			
//			}else if(!category.equals("All")&&crs_time.equals("All")){
//				map.put("crs_date" ,new String[] {date});
//				map.put("action" , new String[] { "getClass"});
//				map.put("category" ,new String[] { category });
//			}else{
//				map.put("crs_date" ,new String[] {date});
//				map.put("category" ,new String[] { category });
//				map.put("crs_time" ,new String[] { crs_time });
//				map.put("action" , new String[] { "getClass"});
//												
//				
//			}
//			List<Course_timeVO>courseList = course_timeSvc.getClass(map);
//			
//			writeText(rp, gson.toJson(courseList));
//			
//			System.out.println(map);
//			
//		}
//											
//					
//			if(action.equals("getItem")){
//				
//				String crs_date = jsonObject.get("crs_date").getAsString();
//				
//				System.out.println(crs_date );
//				
//				String category = jsonObject.get("category").getAsString();
//				String p_name = jsonObject.get("p_name").getAsString();
//				//String coa_name = jsonObject.get("coa_name").getAsString();
//				
//				
//				System.out.println(crs_date + category + p_name);
//				
//				List<Course_timeVO>courseList = course_timeSvc.getItem(category, crs_date, p_name);
//				
//				writeText(rp, gson.toJson(courseList));
//			}
//			
			
						
					
			
		
		
	
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
}