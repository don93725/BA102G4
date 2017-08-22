package com.members.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
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
import com.members.model.MembersDAO;
import com.members.model.MembersVO;
import com.members.model.NewMembers;
import com.students.model.StudentsDAO;
import com.students.model.StudentsService;
import com.students.model.StudentsVO;

@SuppressWarnings("serial")
@WebServlet("/MemberSignupServlet")
public class MemberSignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	private StudentsDAO studentsDao = new StudentsDAO();
	private CoachesDAO coachesDao = new CoachesDAO();
	private MembersDAO membersDao = new MembersDAO();
	private StudentsVO studentsVO;
	private CoachesVO coachesVO;
	private MembersVO membersVO;
	
	
	Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = "";
		
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		
		//System.out.println(jsonIn);
		
		NewMembers newMembers = gson.fromJson(jsonIn.toString(),NewMembers.class);
		
		StudentsService stus = new StudentsService();
		CoachesService coas = new CoachesService();
		
		
		
		
		
		
		
		
		

		if(newMembers.getCoachesVO()==null){
		
		membersVO = newMembers.getMembersVO();
		studentsVO= newMembers.getStudentsVO();
		
		
		
		
		
		
		
		
		
		
		studentsDao.insert(membersVO,studentsVO);
		
		
		
		

		
		
		
		
		}else if(newMembers.getStudentsVO()==null){
			
			
			
			
			
			
			
			
			
			membersVO = newMembers.getMembersVO();
			coachesVO= newMembers.getCoachesVO();
			coachesDao.insert(membersVO,coachesVO);
		
		
		
		
		}
		 
			 
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		System.out.println("2");
	
	
	
	
	
	
	
	}


		
		
					
		
		
//		response.setContentType(CONTENT_TYPE);
//		PrintWriter out = response.getWriter();
//		out.println(string);
//		
//		
//		System.out.println("333333333333");
//
	


	
	private void writeText(HttpServletResponse response, String outText)
			throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		System.out.println("outText: " + outText);
		out.print(outText);
	}

}
