package com.course_time.model;

import java.util.List;
import java.util.Map;

public abstract interface Course_timeDAO_interface {
	public  void insert(Course_timeVO course_timeVO);

	public  void update(Course_timeVO course_timeVO);

	public  void delete(String ct_no);
	
	public  void deleteCalendar(String crs_date,Integer crs_time,String c_acc);

	public  Course_timeVO findByPK(String ct_no);

	public  List<Course_timeVO> getAll(String c_acc);
	
	public  List<Course_timeVO> getAllOpen(String c_acc);
	
	public  List<Course_timeVO> getAllRecord(String c_acc);
	
	public  List<Course_timeVO> getAllCrsList();
	
	public List<Course_timeVO> getAllCrsListSelect(String select);
	
	public List<Course_timeVO> getAllByCrs_no(String crs_no);
	
	public  List<Course_timeVO> getAllBeforeList();
	
	public  void open(String ct_no);

	List<Course_timeVO> getClass(Map<String, String[]> map);

	List<Course_timeVO> getItem(String category, String crs_date, String p_name);
}
