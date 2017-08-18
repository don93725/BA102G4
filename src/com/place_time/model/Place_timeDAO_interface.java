package com.place_time.model;

import java.util.List;

import com.place.model.PlaceVO;

public interface Place_timeDAO_interface {

	public void insert(Place_timeVO place_TimeVo);
    public void update(Place_timeVO place_TimeVo);
    public void delete(String pt_no);
    public Place_timeVO findByPrimaryKey(String pt_no);
    public List<Place_timeVO> getAll(String opc_acc);


}
