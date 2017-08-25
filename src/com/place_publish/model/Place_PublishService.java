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

	public void order(String p_no, String rp_date, Integer rp_time, String  pbu_price, String pau_price,String acc){
		Place_PublishVO pp = new Place_PublishVO();
		pp.setP_no(p_no);
		pp.setRp_date(java.sql.Date.valueOf(rp_date));
		pp.setRp_time(rp_time);
		pp.setPbu_price(pbu_price);
		pp.setPau_price(pau_price);
		pp.setOpc_acc(acc);
		dao.order(pp);
	}
}
