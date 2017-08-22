package com.place.model;

import java.util.List;

import com.place_time.model.Place_timeVO;

public interface PlaceDAO_interface {
	public void insert(PlaceVO placeVO);
	public List<PlaceVO> getPlaceList(String placeList_acc, String placeList_status);
	public void delete(String p_no);
    public PlaceVO findByPrimaryKey(String p_no);	
    public void update(PlaceVO placeVo);
//	    public List<PlaceVO> getAll();
	
}
