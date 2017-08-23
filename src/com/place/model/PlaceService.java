package com.place.model;
import java.util.List;
import java.sql.Date;
import java.util.List;

import com.members.model.MembersVO;
import com.students.model.StudentsVO;



public class PlaceService {

	private PlaceDAO_interface dao;
	
	public PlaceService() {
		dao = new PlaceDAO();
	}
	
	public void addPlace(MembersVO membersVO, String p_name, String p_into, String p_add, String p_latlng,Integer p_cap) {
		PlaceVO placeVO = new PlaceVO();
		placeVO.setG_acc(membersVO.getMem_acc());
		placeVO.setP_name(p_name);
		placeVO.setP_into(p_into);
		placeVO.setP_add(p_add);
		placeVO.setP_latlng(p_latlng);
		placeVO.setP_cap(p_cap);
		dao.insert(placeVO);
	}
	
	public List<PlaceVO> placeList(String placeList_acc, String placeList_status) {
		return dao.getPlaceList(placeList_acc , placeList_status);
	}

	public void delete(String p_no) {
		dao.delete(p_no);
	}

	public PlaceVO getOnePlace(String p_no) {
	return dao.findByPrimaryKey(p_no);
	}

	public void updatePlace(String p_name, String p_into, String p_add, String p_latlng, Integer p_cap, String p_no) {
		PlaceVO placeVO = new PlaceVO();

		placeVO.setP_name(p_name);
		placeVO.setP_into(p_into);
		placeVO.setP_add(p_add);
		placeVO.setP_latlng(p_latlng);
		placeVO.setP_cap(p_cap);
		placeVO.setP_no(p_no);
		dao.update(placeVO);
	}

	public void insertPPic(List list, String ppp_no){
		dao.insertPic(list, ppp_no);
	}


//	public List<PlaceVO> getAll() {
//		return dao.getAll();
//	}
//	
//	
	
}
