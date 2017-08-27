package com.index.model;

public class CountService {

	public int getCount(String from){
		CountDAO dao = new CountDAO();
		int len = dao.getCount(from);
		return len;
	}
	
	public int getCount(String from,String where,String value){
		CountDAO dao = new CountDAO();
		int len = dao.getCount(from,where,value);
		return len;
	}
	
	public int getCountIntValue(String from,String where,int value){
		CountDAO dao = new CountDAO();
		int len = dao.getCountIntValue(from,where,value);
		return len;
	}
	
	
}
