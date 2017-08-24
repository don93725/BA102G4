package com.place_pic.model;

import java.util.List;

import com.place.model.PlaceDAO;

public class Place_PicService {
	private Place_PicDAO_interface dao;
	public Place_PicService() {
		dao = new Place_PicDAO();
	}
	public List getAllPPic(String p_no) {
		return dao.getAllPPic(p_no);
	}
	
}
