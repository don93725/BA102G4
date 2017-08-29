package com.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Part;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Tools {
	
	//處理圖片
	public byte[] getPictureByteArray(Part part) {
		byte[] buf = null;
		
		try {
			InputStream fis = part.getInputStream();
			buf = new byte[fis.available()];
			fis.read(buf);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buf;
	}
	
	//使用者輸入的ID跟選擇的性別同時做比對
	public boolean checkId(String coa_id, Integer coa_sex) {
		try{
			String loc = coa_id.substring(0, 1);//ID地區碼
			Integer sex = Integer.valueOf(coa_id.substring(1,2));//ID性別碼
			Map map = new HashMap();//地區碼轉數字
			map.put("A", 10);map.put("B", 11);map.put("C",12);map.put("D", 13);map.put("E", 14);
			map.put("F", 15);map.put("G", 16);map.put("H", 17);map.put("I", 34);map.put("J", 18);
			map.put("K", 19);map.put("L", 20);map.put("M", 21);map.put("N", 22);map.put("O", 35);
			map.put("P", 23);map.put("Q", 24);map.put("R", 25);map.put("S", 26);map.put("T", 27);
			map.put("U", 28);map.put("V", 29);map.put("W", 32);map.put("X", 30);map.put("Y", 31);
			map.put("Z", 33);
			Integer loc_int1 = Integer.valueOf(((map.get(loc)).toString()).substring(0,1));//ID地區碼轉數字第一碼
			Integer loc_int2 = Integer.valueOf(((map.get(loc)).toString()).substring(1));//ID地區碼轉數字第二碼
			Integer ck_int = 10- ((loc_int1*1 + loc_int2*9 + sex*8 + (Integer.valueOf(coa_id.substring(2,3)))*7
					+ (Integer.valueOf(coa_id.substring(3,4)))*6 + (Integer.valueOf(coa_id.substring(4,5)))*5
					+ (Integer.valueOf(coa_id.substring(5,6)))*4 + (Integer.valueOf(coa_id.substring(6,7)))*3
					+ (Integer.valueOf(coa_id.substring(7,8)))*2 + (Integer.valueOf(coa_id.substring(8,9)))*1
					)%10);//檢查碼
			if(ck_int == 10) {
				ck_int = 0;
			}
			System.out.println("ID=" + loc_int1 + loc_int2 + sex + Integer.valueOf(coa_id.substring(2,3))
					+ Integer.valueOf(coa_id.substring(3,4)) + Integer.valueOf(coa_id.substring(4,5))
					+ Integer.valueOf(coa_id.substring(5,6)) + Integer.valueOf(coa_id.substring(6,7))
					+ Integer.valueOf(coa_id.substring(7,8)) + Integer.valueOf(coa_id.substring(8,9))
					+ ck_int);
			
			if(sex != coa_sex || Integer.valueOf(coa_id.substring(9)) != ck_int) {
				System.out.println("身分證格式不符!");
				return false;
			}
		} catch(Exception e) {
			System.out.println("身分證核對有例外發生!");
			e.getMessage();
			return false;
		}
		return true;
	}
	
	//使用者輸入的地址轉換成坐標
	public String getLATLNG(String gym_add) {
		String latlng = null;
		try {
			String sKeyWord = gym_add;
            URL url  = new URL(String.format("http://maps.googleapis.com/maps/api/geocode/json?address=%s&language=zh-TW",
            		URLEncoder.encode(sKeyWord, "UTF-8")));//p=%s is KeyWord in
            URLConnection connection = url.openConnection();
            String line;
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            while ((line = reader.readLine()) != null) {builder.append(line);}
            JSONObject json = new JSONObject(builder.toString()); //轉換json格式
            JSONArray ja = json.getJSONArray("results");//取得json的Array物件
            	System.out.println(json);
            	Double lat = (Double)ja.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lat");
            	Double lng = (Double)ja.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lng");

            	String final_lat = lat.toString();
            	String final_lng = lng.toString();
            	latlng = final_lat + "," + final_lng;
            	
        } catch (JSONException ex) {
//            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        	ex.printStackTrace();
        	System.out.println("Opps!地址轉換有誤" + ex.toString());
        } catch (IOException ex) {
//            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        	ex.printStackTrace();
        	System.out.println("Opps!地址轉換有誤" + ex.toString());
        }
		System.out.println("latlng:" + latlng);
		return latlng;
}

	
	//教練、健身房申請會員成功後，寄發審核郵件通知
	public void sendMail(String to, String nickname) {
			String subject = "健貨-會員審核通知";
			String messageText = nickname + "感謝您申請健貨會員，審核結果稍後將會通知您";
		   try {
			   //Gmail smtp Server
			   Properties props = new Properties();
			   props.put("mail.smtp.host", "smtp.gmail.com");
			   props.put("mail.smtp.socketFactory.port", "465");
			   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			   props.put("mail.smtp.auth", "true");
			   props.put("mail.smtp.port", "465");

	       //輸入發送者Gmail資料
		     final String myGmail = "ixlogic.wu@gmail.com";
		     final String myGmail_password = "AAA45678";
			   Session session = Session.getInstance(props, new Authenticator() {
				   protected PasswordAuthentication getPasswordAuthentication() {
					   return new PasswordAuthentication(myGmail, myGmail_password);
				   }
			   });

			   Message message = new MimeMessage(session);
			   message.setFrom(new InternetAddress(myGmail));
			   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
			  
			   //信件標題
			   message.setSubject(subject);
			   //信件內容
			   message.setText(messageText);

			   Transport.send(message);
			   System.out.println("寄信成功!");
	     }catch (MessagingException e){
		     System.out.println("寄信失敗!");
		     e.printStackTrace();
	     }
	   }
}
