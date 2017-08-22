package com.CCM.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.model.CourseService;
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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.students.model.StudentsService;

@SuppressWarnings("serial")
@WebServlet("/CourseManager_SearchClass")


public class CourseManager_SearchClass extends HttpServlet{

	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		
	public void doGet(HttpServletRequest rq, HttpServletResponse rp)
			throws ServletException, IOException {
		
		
		
		
		
		Course_pictureService course_pictureService= new Course_pictureService();
		
		Course_pictureDAO  course_pictureDAO = new Course_pictureDAO();
		
		Course_pictureVO course_pictureVO = new Course_pictureVO();
		final Base64.Decoder decoder = Base64.getDecoder();
		final Base64.Encoder encoder = Base64.getEncoder();
		
		
		FileInputStream fis1 = new java.io.FileInputStream(new File("C:/Users/cuser/Dropbox/BA1020822/BA102G4/WebContent/front_end/forum/images/tomcat.gif"));
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
		
		
		
		
		FileInputStream fis = new java.io.FileInputStream(new File("C:/Users/cuser/Dropbox/BA1020822/BA102G4/WebContent/front_end/forum/images/tomcat.gif"));
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
		
		
		for(int w = 1 ; w<=32;w++){
			
			String num = String.valueOf(w);
			
			if(w>0 && w<5){
			
			course_pictureService.addCourse_picture("A000"+num, crs_base);
			
			}
			else if(w>4 && w<9){
				
				course_pictureService.addCourse_picture("B000"+num, crs_base);
			}else if(w>8 && w<10){
				course_pictureService.addCourse_picture("C000"+num, crs_base);
			}else if(w>9 && w<13){
				
				course_pictureService.addCourse_picture("C00"+num, crs_base);
			}else if(w>12 && w <17){
				course_pictureService.addCourse_picture("D00"+num, crs_base);
			}else if(w>16 && w <21){
				course_pictureService.addCourse_picture("E00"+num, crs_base);
			}else if(w>20 && w <25){
			
				course_pictureService.addCourse_picture("F00"+num, crs_base);
			}else if(w>24 && w <29){
			
				course_pictureService.addCourse_picture("G00"+num, crs_base);
			}else if(w>28 && w <33){
				course_pictureService.addCourse_picture("H00"+num, crs_base);
				
			}
			
			
			
		}
		
		
		
		
		
		

		
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
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: " + action);

		
		
		
		if (action.equals("getClass")) {
			String date = jsonObject.get("crs_date").getAsString();
			
			System.out.println(date );
			
			String category = jsonObject.get("category").getAsString();
			String crs_time = jsonObject.get("crs_time").getAsString();
			
			System.out.println(date + category + crs_time);
			
			
			
			HashMap<String, String[]> map = new HashMap<String, String[]>();
			
			
			if(category.equals("All")&&crs_time.equals("All")){
				map.put("crs_date" ,new String[] {date});
				map.put("action" , new String[] { "getClass"});
									
			
			}else if(category.equals("All")&&!crs_time.equals("All")){
				map.put("crs_date" ,new String[] {date});
				map.put("action" , new String[] { "getClass"});
				map.put("crs_time" ,new String[] { crs_time });
			
			}else if(!category.equals("All")&&crs_time.equals("All")){
				map.put("crs_date" ,new String[] {date});
				map.put("action" , new String[] { "getClass"});
				map.put("category" ,new String[] { category });
			}else{
				map.put("crs_date" ,new String[] {date});
				map.put("category" ,new String[] { category });
				map.put("crs_time" ,new String[] { crs_time });
				map.put("action" , new String[] { "getClass"});
												
				
			}
			List<Course_timeVO>courseList = course_timeSvc.getClass(map);
			
			writeText(rp, gson.toJson(courseList));
			
			System.out.println(map);
			
		}
											
					
			if(action.equals("getItem")){
				
				String crs_date = jsonObject.get("crs_date").getAsString();
				
				System.out.println(crs_date );
				
				String category = jsonObject.get("category").getAsString();
				String p_name = jsonObject.get("p_name").getAsString();
				String stu_acc =jsonObject.get("stu_acc").getAsString();
				String ct_no = jsonObject.get("ct_no").getAsString();
				
				
				//String coa_name = jsonObject.get("coa_name").getAsString();
				
				
				System.out.println(crs_date + category + p_name);
				
				List<Course_timeVO>courseList = course_timeSvc.getItem(category, crs_date, p_name);
				
				
				Course_timeDAO course_timeDAO = new Course_timeDAO();
				boolean courseList1=course_timeDAO.findSignUp(ct_no, stu_acc);
				
				String courseItem = gson.toJson(courseList);
				String reserveCourse = gson.toJson(courseList1 );
			
				HashMap<String, String> map = new HashMap<String, String>();
				
				map.put("course",courseItem );
				map.put("reserve", reserveCourse);
				
				writeText(rp, gson.toJson(map));
			
			}
			
			
			if(action.equals("insertList")){
				
				Course_listService course_listSvc = new Course_listService();
				
				
				String ct_no = jsonObject.get("ct_no").getAsString();
				String stu_acc=jsonObject.get("stu_acc").getAsString();
				String cl_date1=jsonObject.get("cl_date").getAsString();
				String crs_time1=jsonObject.get("crs_time").getAsString();
				
				
				
				java.sql.Date cl_date = java.sql.Date.valueOf(cl_date1);
				
				Integer crs_time = Integer.valueOf(crs_time1);
												
				System.out.println(ct_no + stu_acc + cl_date +crs_time);
				
				Course_listVO course_listVO = course_listSvc.addCourse_list(ct_no, stu_acc, cl_date, crs_time);
							
				if(course_listVO != null){
					
					writeText(rp, gson.toJson(true));
				}
				
				
			}
					
			
			if (action.equals("getAll")) {
				
				CoachesService coachesSvc=new CoachesService();
				String coa_name = jsonObject.get("coa_name").getAsString();
				
					
				String coa_into = jsonObject.get("coa_into").getAsString();
				
				
				System.out.println(coa_name+coa_into);
				
				List<CoachesVO>courseList = coachesSvc.getAll();
			
				writeText(rp, gson.toJson(courseList));
		
			}
	
		
			
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
