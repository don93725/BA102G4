package com.place_time.model;

import java.util.List;

import com.place.model.PlaceVO;

public interface Place_timeDAO_interface {

	public void insert(Place_timeVO place_TimeVo);
    public void update(Place_timeVO place_TimeVo);
    public void delete(String pt_no);
    public void deleteCalendar(String rp_date,Integer rp_time,String opc_acc);
    public Place_timeVO findByPrimaryKey(String pt_no);
    public List<Place_timeVO> getAll(String opc_acc);
    public List<Place_timeVO> getAllCoa(String c_acc);
    public List<Place_timeVO> getAllUsed(String c_acc);
    public void payPlace_time(String pt_no);
    public void payAfter(String pt_no);
    public void report(String pt_no);
    public void eva(Integer eva,String eva_ct,String pt_no);


}
