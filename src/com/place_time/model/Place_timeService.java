package com.place_time.model;

import java.sql.Date;
import java.util.List;

import com.place.model.PlaceDAO;
import com.place.model.PlaceDAO_interface;
import com.place.model.PlaceVO;

public class Place_timeService {

	private Place_timeDAO_interface dao;

	public Place_timeService() {
		dao = new Place_timeDAO();
	}

	private String pt_no;
	private String p_no;
	private String opc_acc;
	private Date rp_date;
	private Integer rp_time;
	private Date op_date;
	private String pbu_price;
	private String pau_price;
	private Date pbu_date;
	private Date pau_date;
	private PlaceVO placeVO;

	public Place_timeVO addPlace_Time(String pt_no, String p_no, String opc_acc, java.sql.Date rp_date, Integer rp_time,
			java.sql.Date op_date, String pbu_price, String pau_price, java.sql.Date pbu_date, java.sql.Date pau_date) {

		Place_timeVO place_TimeVo = new Place_timeVO();

		place_TimeVo.setP_no(p_no);
		place_TimeVo.setOpc_acc(opc_acc);
		place_TimeVo.setRp_date(rp_date);
		place_TimeVo.setRp_time(rp_time);
		place_TimeVo.setOp_date(op_date);
		place_TimeVo.setPbu_price(pbu_price);
		place_TimeVo.setPau_price(pau_price);
		place_TimeVo.setPbu_date(pbu_date);
		place_TimeVo.setPau_date(pau_date);
		dao.insert(place_TimeVo);

		return place_TimeVo;
	}

	public Place_timeVO updatePlace(String pt_no, String p_no, String opc_acc, java.sql.Date rp_date, Integer rp_time,
			java.sql.Date op_date, String pbu_price, String pau_price, java.sql.Date pbu_date, java.sql.Date pau_date) {

		Place_timeVO place_TimeVo = new Place_timeVO();

		place_TimeVo.setPt_no(pt_no);
		place_TimeVo.setP_no(p_no);
		place_TimeVo.setOpc_acc(opc_acc);
		place_TimeVo.setRp_date(rp_date);
		place_TimeVo.setRp_time(rp_time);
		place_TimeVo.setOp_date(op_date);
		place_TimeVo.setPbu_price(pbu_price);
		place_TimeVo.setPau_price(pau_price);
		place_TimeVo.setPbu_date(pbu_date);
		place_TimeVo.setPau_date(pau_date);

		return place_TimeVo;
	}

	public void deletePlace_time(String pt_no) {

		dao.delete(pt_no);
	}

	public Place_timeVO getOnePlace_time(String pt_no) {
		return dao.findByPrimaryKey(pt_no);
	}

	public List<Place_timeVO> getAll(String opc_acc) {
		return dao.getAll(opc_acc);
	}

	public List<Place_timeVO> getAllCoa(String c_acc) {
		return dao.getAllCoa(c_acc);
	}
	
	public List<Place_timeVO> getAllUsed(String c_acc) {
		return dao.getAllUsed(c_acc);
	}

	public void payPlace_time(String pt_no) {

		dao.payPlace_time(pt_no);
	}
	
	public void payAfter(String pt_no) {
		
		dao.payAfter(pt_no);
	}
	
	public void report(String pt_no) {
		
		dao.report(pt_no);
	}
	
	public void eva(Integer eva,String eva_ct,String pt_no) {
		
		dao.eva(eva,eva_ct,pt_no);
	}
	public void deleteCalendar(String rp_date,Integer rp_time,String opc_acc) {
		
		dao.deleteCalendar(rp_date,rp_time,opc_acc);
	}

}
