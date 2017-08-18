package com.don.util;

import java.util.HashMap;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

public class Validation {
	public static boolean checkLengthTen2ThrH(String str, String key,HashMap<String,String> map){
		String regex = "[\\S\\s]{10,}";
		if(str.matches(regex)){
			return true;
		}else{			
			map.put(key, "請輸入10字以上的內容。");
			return false;
		}
	}
	public static boolean checkLengthTen2FakeThrH(String str, String key,HashMap<String,String> map){
		String regex = "[\\S\\s]{10,}";
		if(str.matches(regex)){
			return true;
		}else{			
			map.put(key, "請輸入10字以上的內容。");
			return false;
		}
	}
	public static boolean checkLengthOne2Ten(String str, String key,HashMap<String,String> map){
		String regex = "[\\S\\s]{1,10}";
		if(str.matches(regex)){
			return true;
		}else{			
			map.put(key, "請輸入1-10字的內容。");
			return false;
		}
	}
	public static boolean checkLengthOne2Thirty(String str, String key,HashMap<String,String> map){
		String regex = "[\\S\\s]{1,30}";
		if(str.matches(regex)){
			return true;
		}else{			
			map.put(key, "請輸入1-30字的內容。");
			return false;
		}
	}

	public static boolean checkLengthLessFifteen(String str, String key,HashMap<String,String> map){
		String regex = "[\\S\\s]{,15}";
		if(str.matches(regex)){
			return true;
		}else{			
			map.put(key, "請輸入15字以下的內容。");
			return false;
		}
	}
//	public static void main(String[] args){
//		String str =" aaaaaaa       ";
//		String regex = "[\\S\\s]{10,}";
//		
//		Pattern pattern = Pattern.compile(regex);
//		System.out.println(str.matches(regex));
//	}
}
