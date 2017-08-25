package com.members.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import com.members.model.MembersService;
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
		
		
		
				
//		String mem_acc = jsonObject.get("mem_acc").getAsString();
		
		
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		
		
		
		
		String mem_accReg = "^[(a-zA-Z0-9_)]{6,20}$";
		
		
		String username = jsonObject.get("username").getAsString();
		
		
		
//		if(username == null || username.trim().length() == 0) {
//			errorMsgs.put("mem_acc","會員帳號: 請勿空白");
//		}else if(!(username.matches(mem_accReg))) {
//			errorMsgs.put("mem_acc","會員帳號: 只能是大小寫英數字(含_)，且長度必需在6到20之間");
//		}
		

		
		String password = jsonObject.get("password").getAsString();
		
		String stu_pswReg = "^[(a-zA-Z0-9_)]{6,20}$";
		if(password == null || password.trim().length() == 0) {
			errorMsgs.put("stu_psw", "會員密碼: 請勿空白");
		}else if(!(password.matches(stu_pswReg))) {
			errorMsgs.put("stu_psw","會員密碼: 只能是大小寫英數字(含_)，且長度必需在6到20之間");
		}
		
		
		
		
		
		if(!errorMsgs.isEmpty()) {
			System.out.println("I got the errorMsgs");

				
								
				
			writeText(response, gson.toJson(errorMsgs));
				
			System.out.println(errorMsgs);
		}else{
		
		
		System.out.print(role+username+password);
		
		Map<String,String>msgs = new LinkedHashMap<String,String>();
		
		if (role.equals("1")) {
			CoachesVO coachesVO = coashesSvc.loginCoaches(username, password);
		
			if(coachesVO != null){
				msgs.put("msgs", "true");
			
				System.out.print(role+""+username+""+password);
			
				writeText(response, gson.toJson(msgs));
			
			
				System.out.println(msgs);
			}else{
				
				msgs.put("false", "false");
			
				writeText(response, gson.toJson(msgs));
				
				
				System.out.println(msgs);
				
			}
			
			
		
		}else {
			
			
			StudentsVO studentsVO = studentsSvc.loginStudents(username, password);
		
			if(studentsVO != null){
				
				msgs.put("msgs", "true");
			
			
			
				writeText(response, gson.toJson(msgs));
			
			
				System.out.println(msgs);
			}else{
				
				msgs.put("false", "false");
			
				writeText(response, gson.toJson(msgs));
				
				
				System.out.println(msgs);
				
			}
		
		
		
		}
		
		
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
