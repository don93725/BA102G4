package com.students.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course_time.model.Course_timeService;
import com.coaches.model.CoachesDAO;
import com.coaches.model.CoachesService;
import com.coaches.model.CoachesVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.members.model.MembersDAO;
import com.members.model.MembersService;
import com.members.model.MembersVO;
import com.members.model.NewMembers;
import com.students.model.StudentsDAO;
import com.students.model.StudentsService;
import com.students.model.StudentsVO;
import com.tools.Tools;

@SuppressWarnings("serial")
@WebServlet("/StudentsSignupServlet")

public class StudentsSignupServlet extends HttpServlet{

		
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	
	
	
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
		
		
		
					
		
		StudentsService stus = new StudentsService();
		CoachesService coas = new CoachesService();
		
		
		
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(),JsonObject.class);
		
		String mem_rank = jsonObject.get("mem_rank").getAsString();
		System.out.println("mem_rank: " + mem_rank);

		
		
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		
				
		
		
								
						
		try {
			//1.接收請求參數 - 輸入格式的錯誤處理
			//驗證姓名
			String stu_name = jsonObject.get("stu_name").getAsString();
			
			System.out.println("mem_rank: " + stu_name);
			
			String stu_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,6}$";
			if (stu_name == null || stu_name.trim().length() == 0) {
				errorMsgs.put("stu_name","會員姓名: 請勿空白");
			} else if(!(stu_name.matches(stu_nameReg))) {
				errorMsgs.put("stu_name","會員姓名: 只能是中、英文字母 ,且長度必需在2到6之間");
			}
	
			//驗證暱稱
			String mem_nickname = jsonObject.get("mem_nickname").getAsString();
			
			System.out.println("mem_nickname: " + mem_nickname);
			
			String mem_nicknameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{1,15}$";
			if(mem_nickname == null || mem_nickname.trim().length() == 0){
				errorMsgs.put("mem_nickname","會員暱稱: 請勿空白");
			} else if(!(mem_nickname.matches(mem_nicknameReg))){
				errorMsgs.put("mem_nickname","會員暱稱: 只能是中、英文、數字，且長度必需在1到15之間");
			}
	
			//驗證帳號
			String mem_acc = jsonObject.get("mem_acc").getAsString();
			
			System.out.println("mem_acc: " + mem_acc);
			
			String mem_accReg = "^[(a-zA-Z0-9_)]{6,20}$";
			MembersService membersSV = new MembersService();
	
			if(membersSV.insert_ck(mem_acc)) {
				errorMsgs.put("mem_acc","會員帳號: 已被使用，請更換");
			}else if(mem_acc == null || mem_acc.trim().length() == 0) {
				errorMsgs.put("mem_acc","會員帳號: 請勿空白");
			}else if(!(mem_acc.matches(mem_accReg))) {
				errorMsgs.put("mem_acc","會員帳號: 只能是大小寫英數字(含_)，且長度必需在6到20之間");
			}
	
			//驗證密碼
			String stu_psw = jsonObject.get("stu_psw").getAsString();
			
			System.out.println("stu_psw: " + stu_psw);

									
			String stu_pswReg = "^[(a-zA-Z0-9_)]{6,20}$";
			if(stu_psw == null || stu_psw.trim().length() == 0) {
				errorMsgs.put("stu_psw", "會員密碼: 請勿空白");
			}else if(!(stu_psw.matches(stu_pswReg))) {
				errorMsgs.put("stu_psw","會員密碼: 只能是大小寫英數字(含_)，且長度必需在6到20之間");
			}

			//驗證確認密碼
			String stu_psw_ck = jsonObject.get("stu_psw_ck").getAsString();
			
			System.out.println("stu_psw_ck: " + stu_psw_ck);

			
			String stu_psw_ckReg = "^[(a-zA-Z0-9_)]{6,20}$";
			if(stu_psw_ck == null || stu_psw_ck.trim().length() == 0) {
				errorMsgs.put("stu_psw", "會員確認密碼: 請勿空白");
			} else if(!(stu_psw_ck.matches(stu_psw_ckReg)) || !stu_psw_ck.trim().equals(stu_psw)) {
				errorMsgs.put("stu_psw_ck", "確認密碼錯誤，請重新輸入");
			}
	
			//驗證性別
			Integer stu_sex = null;
			
			
			System.out.println("stu_sex: " + stu_sex);

			
			try {
				stu_sex =  Integer.valueOf(jsonObject.get("stu_sex").getAsString());
				if(stu_sex != 1 && stu_sex != 2) {
					errorMsgs.put("stu_sex", "會員性別: 請勿空白");
				}
			} catch (NumberFormatException e) {
				errorMsgs.put("stu_sex", "會員性別: 請勿空白");
			}	
	
			//驗證身分證號
			Tools tools = new Tools();
			String stu_id = jsonObject.get("stu_id").getAsString();
			
			System.out.println("stu_id: " + stu_id);
			
			String stu_idReg = "^[A-Z]{1}[1-2]{1}[0-9]{8}$";
			if(stu_id == null || stu_id.trim().length() == 0){
				errorMsgs.put("stu_id", "會員身分證號: 請勿空白");
			}else if(!(stu_id.matches(stu_idReg)) || !(tools.checkId(stu_id, stu_sex))) {
				errorMsgs.put("stu_id", "會員身分證號: 格式錯誤");
			}
	
			//驗證信箱
			String stu_mail = jsonObject.get("stu_mail").getAsString();
			
			System.out.println("stu_mail: " + stu_mail);
						
			String stu_mailReg = "^[_A-Za-z0-9-]+([.][_A-Za-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
			if(stu_mail == null || stu_mail.trim().length() == 0) {
				errorMsgs.put("stu_mail", "會員信箱: 請勿空白");
			}else if(!(stu_mail.matches(stu_mailReg)) || stu_mail.length() > 50) {
				errorMsgs.put("stu_mail", "會員信箱: 格式錯誤");
			}

			//驗證自我介紹
			String stu_into = jsonObject.get("stu_into").getAsString();
			
			System.out.println("stu_into: " + stu_into);

			
			if(stu_into == null || stu_into.trim().length() == 0) {
				errorMsgs.put("stu_into", "會員自我介紹: 請勿空白");
			}else if(stu_into.length() > 500) {
				errorMsgs.put("stu_into", "會員自我介紹: 格式錯誤");
			}
	
			
		
//			//驗證大頭貼
//			String cropped_pic = req.getParameter("cropped_pic");
//			//base64轉byte[]
//			Base64.Decoder decoder = Base64.getDecoder();
//			byte[] stu_pic_byte = null;
//			
//			if(cropped_pic == null || "".equals(cropped_pic)) {
//				errorMsgs.put("stu_pic", "會員大頭貼: 請鎖定圖片");
//			}else {
//				stu_pic_byte = decoder.decode(cropped_pic.split(",")[1]);
//				//圖片大小(kb...)
//				int pic_length = stu_pic_byte.length;
//				String pic_type = cropped_pic.substring(5, 10);
////				Part coa_pic = req.getPart("upload-file");
////				String fileType = coa_pic.getContentType();
//				if(pic_length > (5*1024*1024)) {
//					errorMsgs.put("stu_pic", "會員大頭貼: 檔案過大");
//				} else if(!("image".equals(pic_type))) {
//					errorMsgs.put("stu_pic", "會員大頭貼: 僅允許圖片格式");
//				}
			
//		}
			

			// 資料有誤就返回form表單
		if(!errorMsgs.isEmpty()) {
			System.out.println("I got the errorMsgs");

				
								
				
			writeText(response, gson.toJson(errorMsgs));
				
			System.out.println(errorMsgs);
				
			
		}else{
	
		
			//2.開始新增資料
			StudentsService studentsSV = new StudentsService();
		
		
			Map<String,String>msgs = new LinkedHashMap<String,String>();
		
			boolean insertMembers ;
		
			System.out.println(mem_acc+mem_nickname+stu_psw+stu_name+stu_sex+stu_id+stu_mail+stu_into);
			
			insertMembers = studentsSV.addAndroidStudents(mem_acc, mem_nickname, stu_psw, stu_name, stu_sex, stu_id, stu_mail, stu_into);
	
		
			System.out.println(insertMembers);
		
		if(insertMembers = true){
		
			msgs.put("msgs", "true");
		
				
			writeText(response, gson.toJson(msgs));
		
		
			System.out.println(msgs);
		}else{
			
			msgs.put("msgs", "false");
			
			
			writeText(response, gson.toJson(msgs));
			
			
			System.out.println(msgs);
			}
		
			}
		
		
		
		
		
		} catch(Exception e) {	
			

			System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
		
		
		}

		
		
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
