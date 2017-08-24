package com.place_publish.model;

import com.place_pic.model.Place_PicDAO;
import com.place_pic.model.Place_PicDAO_interface;

public class Place_PublishService {
	private Place_PublishDAO_interface dao;
	public Place_PublishService() {
		dao = new Place_PublishDAO();
	}
	
	public void insert(String p_no, String pbu_price, String pau_price) {
		Place_PublishVO ppVO = new Place_PublishVO();
		ppVO.setP_no(p_no);
		ppVO.setPbu_price(pbu_price);
		ppVO.setPau_price(pau_price);
		dao.insert(ppVO);
	}

	public void unPublish(String p_no) {
		dao.unPublish(p_no);
	}
}
