package com.members.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;



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
import com.members.model.MembersDAO;
import com.members.model.MembersVO;

import com.students.model.StudentsDAO;
import com.students.model.StudentsService;
import com.students.model.StudentsVO;

@SuppressWarnings("serial")
@WebServlet("/MemberLoginServlet")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	private CoachesDAO coachesDao = new CoachesDAO();
	
	Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<CoachesVO> coachesList = coachesDao.getAll();
		writeText(response, new Gson().toJson(coachesList));
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html;charset=utf8");
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(),
				JsonObject.class);
		
		StudentsService studentsSvc = new StudentsService();
		CoachesService coashesSvc = new CoachesService();
		
					
		
		String role = jsonObject.get("role").getAsString();
		
		String username = jsonObject.get("username").getAsString();
		
		String password = jsonObject.get("password").getAsString();
		
		
		System.out.print(role+username+password);
		
		if (role.equals("1")) {
			CoachesVO coachesVO = coashesSvc.loginCoaches(username, password);
		
			writeText(response, gson.toJson(coachesVO));
			
			System.out.println(coachesVO);
		
			
		
		}else {
			
			
			StudentsVO studentsVO = studentsSvc.loginStudents(username, password);
		
			writeText(response, gson.toJson(studentsVO));
			
			System.out.println(studentsVO);
		
		
		
		}
		
		
		
		
		
		
		
	}

	private void writeText(HttpServletResponse response, String outText)
			throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		System.out.println("outText: " + outText);
		out.print(outText);
	}
}
