package com.place.model;

import java.util.List;

import com.place_time.model.Place_timeVO;

public interface PlaceDAO_interface {
	public void insert(PlaceVO placeVO);
	public List<PlaceVO> getPlaceList(String placeList_acc, String placeList_status);
	public void delete(String p_no);
    public PlaceVO findByPrimaryKey(String p_no);	
    public PlaceVO getOnePlacePt_no(String pt_no);	
    public void update(PlaceVO placeVo);
    public void insertPic(List list, String ppp_no);
//	    public List<PlaceVO> getAll();
	
}
