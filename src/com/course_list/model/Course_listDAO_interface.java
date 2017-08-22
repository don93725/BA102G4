package com.course_list.model;

import java.util.List;


public interface Course_listDAO_interface {
	
	public  void insert(Course_listVO course_listVO);

	public  void update(Course_listVO course_listVO);

	public  void delete(String ct_no,String stu_acc);
	
	public  void deleteCalendar(String cl_date,Integer crs_time,String stu_acc);
	
	public  int count(String ct_no);

	public  Course_listVO findByPK(String ct_no,String stu_acc);

	public  List<Course_listVO> getAll(String stu_acc);
	
	public  List<Course_listVO> getAllOpen(String stu_acc);

	public  List<Course_listVO> getAllRecord(String stu_acc);
	
	public void pay(String ct_no,String stu_acc);
	
	public  boolean findSignUp(String ct_no,String stu_acc);
	
	public  List<Course_listVO> getAllByCt_no(String ct_no);
	
	public void leave(String reason,String ct_no,String stu_acc);
	
	public void report(String report_ct,String ct_no,String stu_acc);
	
	public void evaluation(String evaluation_coa,String evaluation_crs,String feedback,String ct_no,String stu_acc);

	List<Course_listVO> getReportSta(Integer report_sta);

	void updateCRNum(Course_listVO course_listVO);

	void updateRepSta(Course_listVO course_listVO);

	List<Course_listVO> getReserve(String stu_acc);

	List<Course_listVO> getReady(String stu_acc);

	List<Course_listVO> getFinished(String stu_acc);

	List<Course_listVO> getCoachReserve(String coa_acc);

}
