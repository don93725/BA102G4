package com.CCM.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

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
import com.course_time.model.Course_timeDAO;
import com.course_time.model.Course_timeService;
import com.course_time.model.Course_timeVO;
import com.coaches.model.CoachesService;
import com.coaches.model.CoachesVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


@SuppressWarnings("serial")
@WebServlet("/CourseManager_ClassManager")

public class CourseManager_ClassManager extends HttpServlet{


private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		
	public void doGet(HttpServletRequest rq, HttpServletResponse rp)
			throws ServletException, IOException {
		
		
		
		
		
		Course_pictureService course_pictureService= new Course_pictureService();
		
		Course_pictureDAO  course_pictureDAO = new Course_pictureDAO();
		
		Course_pictureVO course_pictureVO = new Course_pictureVO();
		final Base64.Decoder decoder = Base64.getDecoder();
		final Base64.Encoder encoder = Base64.getEncoder();
		
		
		FileInputStream fis1 = new java.io.FileInputStream(new File("C:/Users/cuser/Dropbox/BA1020817/BA102G40817/WebContent/front_end/CCM/picture/images4.jpg"));
		ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis1.read(buffer)) != -1) {
			baos1.write(buffer, 0, i);
		}
		baos1.close();
		fis1.close();

		byte[]pic1=baos1.toByteArray();
		
		String encodedText1 = encoder.encodeToString(pic1);
		
		
		
		
		FileInputStream fis = new java.io.FileInputStream(new File("C:/Users/cuser/Dropbox/BA1020817/BA102G40817/WebContent/front_end/CCM/picture/images1.jpg"));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer1 = new byte[8192];
		int y;
		while ((y = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, y);
		}
		baos.close();
		fis.close();

		byte[]pic=baos.toByteArray();
		
		
		
		
		
		 String encodedText = encoder.encodeToString(pic);
		 System.out.println(encodedText);
		
		 
		 
		 //BufferedReader br = new BufferedReader(new java.io.FileReader(path));
		
		
		
		//String pic2 = new String(encodedText1);
		
		
		
		
				
		List<String> crs_base = new ArrayList<String>();
		
		crs_base.add(encodedText1);
		
		
		course_pictureService.addCourse_picture("C0012", crs_base);
		
		
		
		

		
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

		
		
		
		if (action.equals("getReserve")) {
			
			
			String stu_acc = jsonObject.get("stu_acc").getAsString();
			
			System.out.println(stu_acc );
			
			
			
										
						
			
			List<Course_listVO>courseList = course_listSvc.getReserve_List(stu_acc);
			
			writeText(rp, gson.toJson(courseList));
			
			System.out.println(courseList);
			
		}
											
					
		if (action.equals("getReady")) {
			
			
			String stu_acc = jsonObject.get("stu_acc").getAsString();
			
			System.out.println(stu_acc );
			
																
						
			
			List<Course_listVO>courseList = course_listSvc.getReady_List(stu_acc);
			
			writeText(rp, gson.toJson(courseList));
			
			System.out.println(courseList);
			
		}	
			
		
		
		if (action.equals("getFinished")) {
			
			
			String stu_acc = jsonObject.get("stu_acc").getAsString();
			
			System.out.println(stu_acc );
			
																
						
			
			List<Course_listVO>courseList = course_listSvc.getReady_List(stu_acc);
			
			writeText(rp, gson.toJson(courseList));
			
			System.out.println(courseList);
			
		}	
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	














}
