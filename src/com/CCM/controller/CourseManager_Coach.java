package com.CCM.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course_list.model.Course_listService;
import com.course_list.model.Course_listVO;
import com.course_picture.model.Course_pictureDAO;
import com.course_picture.model.Course_pictureService;
import com.course_picture.model.Course_pictureVO;
import com.course_time.model.Course_timeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;




@SuppressWarnings("serial")
@WebServlet("/CourseManager_Coach")


public class CourseManager_Coach extends HttpServlet {

private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		
	public void doGet(HttpServletRequest rq, HttpServletResponse rp)
			throws ServletException, IOException {
		
		
		
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
		Course_timeService course_timeSvc = new Course_timeService();
		Course_listService course_listSvc = new Course_listService();
		
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: " + action);

		
		
		
		if (action.equals("getCoachReserve")) {
			
			
			String coa_acc = jsonObject.get("coa_acc").getAsString();
			
			System.out.println(coa_acc );
			
			
												
						
			
			List<Course_listVO>courseList = course_listSvc.getReserve_List(coa_acc);
			
			writeText(rp, gson.toJson(courseList));
			
			System.out.println(courseList);
			
		}
											
				
		
		
		
		
		
		if (action.equals("getReady")) {
			
			
			String coa_acc = jsonObject.get("coa_acc").getAsString();
			
			System.out.println(coa_acc );
			
																
						
			
			List<Course_listVO>courseList = course_listSvc.getCoachReady_List(coa_acc);
			
			writeText(rp, gson.toJson(courseList));
			
			System.out.println(courseList);
			
		}	
			
		
		
		if (action.equals("getFinished")) {
			
			
			String coa_acc = jsonObject.get("coa_acc").getAsString();
			
			System.out.println(coa_acc );
			
																
						
			
			List<Course_listVO>courseList = course_listSvc.getReady_List(coa_acc);
			
			writeText(rp, gson.toJson(courseList));
			
			System.out.println(courseList);
			
		}	
		

		if (action.equals("QRcode")) {
			
			String ct_no = jsonObject.get("ct_no").getAsString();
			String stu_acc = jsonObject.get("stu_acc").getAsString();
			
			System.out.println(ct_no+""+stu_acc );
			
				
			Map<String,String>msgs = new LinkedHashMap<String,String>()	;				
			
			boolean msg;
						
			
			msg = course_listSvc.updateNSta(ct_no, stu_acc);
			
			if(msg ){
				
				msgs.put("true","true");
				
				
				writeText(rp, gson.toJson(msgs));
				
				System.out.println(msgs);
			}else{
				
				
				System.out.println("false");
			}
			
	
		
		
		
		
		}	
		
		
		
		
		if (action.equals("dontGoClass")) {
			
			String ct_no = jsonObject.get("ct_no").getAsString();
			String stu_acc = jsonObject.get("stu_acc").getAsString();
			String reason = jsonObject.get("reason").getAsString();
			System.out.println(ct_no+""+stu_acc+""+reason );
			
				
			Map<String,String>msgs = new LinkedHashMap<String,String>()	;				
			
			boolean msg;
						
			
			msg = course_listSvc.updateNStaAndroid(ct_no, stu_acc, reason);
			
			if(msg == true){
				
				msgs.put("true","true");
				
				
				writeText(rp, gson.toJson(msgs));
				
				System.out.println(msgs);
			}else{
				
				
				System.out.println("false");
			}
			
	
		
		
		
		
		}	
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	














}





