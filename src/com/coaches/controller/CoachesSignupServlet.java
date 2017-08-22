package com.coaches.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coaches.model.CoachesService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.members.model.MembersService;
import com.students.model.StudentsService;
import com.tools.Tools;


@SuppressWarnings("serial")
@WebServlet("/CoachesSignupServlet")


public class CoachesSignupServlet extends HttpServlet{

	
	
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
			String coa_name = jsonObject.get("coa_name").getAsString();
			String coa_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,6}$";
			if (coa_name == null || coa_name.trim().length() == 0) {
				errorMsgs.put("coa_name","會員姓名: 請勿空白");
			} else if(!(coa_name.matches(coa_nameReg))) {
				errorMsgs.put("coa_name","會員姓名: 只能是中、英文字母 ,且長度必需在2到6之間");
            }
			
			//驗證暱稱
			String mem_nickname = jsonObject.get("mem_nickname").getAsString();
			String mem_nicknameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{1,15}$";
			if(mem_nickname == null || mem_nickname.trim().length() == 0){
				errorMsgs.put("mem_nickname","會員暱稱: 請勿空白");
			} else if(!(mem_nickname.matches(mem_nicknameReg))){
				errorMsgs.put("mem_nickname","會員暱稱: 只能是中、英文、數字，且長度必需在1到15之間");
			}
			
			//驗證帳號
			String mem_acc = jsonObject.get("mem_acc").getAsString();
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
			String coa_psw = jsonObject.get("coa_psw").getAsString();
			String coa_pswReg = "^[(a-zA-Z0-9_)]{6,20}$";
			if(coa_psw == null || coa_psw.trim().length() == 0) {
				errorMsgs.put("coa_psw", "會員密碼: 請勿空白");
			}else if(!(coa_psw.matches(coa_pswReg))) {
				errorMsgs.put("coa_psw","會員密碼: 只能是大小寫英數字(含_)，且長度必需在6到20之間");
			}

			//驗證確認密碼
			String coa_psw_ck = jsonObject.get("coa_psw_ck").getAsString();
			String coa_psw_ckReg = "^[(a-zA-Z0-9_)]{6,20}$";
			if(coa_psw_ck == null || coa_psw_ck.trim().length() == 0) {
				errorMsgs.put("coa_psw", "會員確認密碼: 請勿空白");
			} else if(!(coa_psw_ck.matches(coa_psw_ckReg)) || !coa_psw_ck.trim().equals(coa_psw)) {
				errorMsgs.put("coa_psw_ck", "確認密碼錯誤，請重新輸入");
			}
			
			//驗證性別
			Integer coa_sex = null;
			try {
				coa_sex = Integer.valueOf(jsonObject.get("coa_sex").getAsString());
				if(coa_sex != 1 && coa_sex != 2) {
					errorMsgs.put("coa_psw", "會員性別: 請勿空白");
				}
			} catch (NumberFormatException e) {
				errorMsgs.put("coa_psw", "會員性別: 請勿空白");
			}	
			
			//驗證身分證號
			Tools tools = new Tools();
			String coa_id = jsonObject.get("coa_id").getAsString();
			String coa_idReg = "^[A-Z]{1}[1-2]{1}[0-9]{8}$";
			if(coa_id == null || coa_id.trim().length() == 0){
				errorMsgs.put("coa_id", "會員身分證號: 請勿空白");
			}else if(!(coa_id.matches(coa_idReg)) || !(tools.checkId(coa_id, coa_sex))) {
				errorMsgs.put("coa_id", "會員身分證號: 格式錯誤");
			}
			
			//驗證信箱
			String coa_mail = jsonObject.get("coa_mail").getAsString();
			String coa_mailReg = "^[_A-Za-z0-9-]+([.][_A-Za-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
			if(coa_mail == null || coa_mail.trim().length() == 0) {
				errorMsgs.put("coa_mail", "會員信箱: 請勿空白");
			}else if(!(coa_mail.matches(coa_mailReg)) || coa_mail.length() > 50) {
				errorMsgs.put("coa_mail", "會員信箱: 格式錯誤");
			}

			//驗證自我介紹
			String coa_into = jsonObject.get("coa_into").getAsString();
			if(coa_into == null || coa_into.trim().length() == 0) {
				errorMsgs.put("coa_into", "會員自我介紹: 請勿空白");
			}else if(coa_into.length() > 500) {
				errorMsgs.put("coa_into", "會員自我介紹: 格式錯誤");
			}
			
//			//驗證大頭貼
//			String cropped_pic = req.getParameter("cropped_pic");
//			//base64轉byte[]
//			Base64.Decoder decoder = Base64.getDecoder();
//			byte[] coa_pic_byte = null;
//			
//			if(cropped_pic == null || "".equals(cropped_pic)) {
//				errorMsgs.put("coa_pic", "會員大頭貼: 請鎖定圖片");
//			}else {
//				coa_pic_byte = decoder.decode(cropped_pic.split(",")[1]);
//				int pic_length = coa_pic_byte.length;
//				String pic_type = cropped_pic.substring(5, 10);
//				if(pic_length > (5*1024*1024)) {
//					errorMsgs.put("coa_pic", "會員大頭貼: 檔案過大");
//				} else if(!("image".equals(pic_type))) {
//					errorMsgs.put("coa_pic", "會員大頭貼: 僅允許圖片格式");
//				}
//			}
			

			// 資料有誤就返回form表單
			if(!errorMsgs.isEmpty()) {
				System.out.println("I got the errorMsgs");

				
								
				
				writeText(response, gson.toJson(errorMsgs));
				
				System.out.println(errorMsgs);
				
			
			}else{
	
		
			//2.開始新增資料
		
		
		
		Map<String,String>msgs = new LinkedHashMap<String,String>();
		
		boolean insertMembers ;
		
		CoachesService coachesSV = new CoachesService();
		
		
		insertMembers=coachesSV.addAndroidCoaches(mem_acc, mem_nickname, coa_psw, coa_name, coa_sex, coa_id, coa_mail, coa_into);
		
		
		
		
		System.out.println(msgs);
		
		
		
		
		if(insertMembers = true){
			
			msgs.put("msgs", "SignUpSuccess");
		
				
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

	
	

	 

