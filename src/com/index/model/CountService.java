package com.index.model;

public class CountService {

	public int getCount(String from){
		CountDAO dao = new CountDAO();
		int len = dao.getCount(from);
		return len;
	}
}
