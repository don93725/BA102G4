package com.place.model;

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
	
	public List<PlaceVO> placeList(String placeList_acc) {
		return dao.getPlaceList(placeList_acc);
	}

	public void delete(String p_no) {
		dao.delete(p_no);
	}
//	public PlaceVO updatePlace(String p_no, String g_acc, String p_name,
//			Integer status) {
//
//		PlaceVO placeVO = new PlaceVO();
//
//		placeVO.setP_no(p_no);
//		
//		placeVO.setG_acc(g_acc);
//		placeVO.setP_name(p_name);
//		placeVO.setStatus(status);
//		dao.update(placeVO);
//
//		return placeVO;
//	}
//
//	public void deletePlace(String p_no) {
//		
//		dao.delete(p_no);
//	}
//
//	public PlaceVO getOnePlace(String p_no) {
//		return dao.findByPrimaryKey(p_no);
//	}
//
//	public List<PlaceVO> getAll() {
//		return dao.getAll();
//	}
	
	
	
}
