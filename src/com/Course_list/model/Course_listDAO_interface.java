package com.Course_list.model;

import java.util.List;


public interface Course_listDAO_interface {
	
	public abstract void insert(Course_listVO course_listVO);

	public void update(Course_listVO course_listVO);

	public abstract void delete(String ct_no,String stu_acc);
	
	public abstract int count(String ct_no);

	public abstract Course_listVO findByPK(String ct_no,String stu_acc);

	public abstract List<Course_listVO> getAll();

	//  7777777
	public void updateRepSta(Course_listVO course_listVO);
	public void updateCRNum(Course_listVO course_listVO);
	public List<Course_listVO> getReportSta(Integer report_sta);
}
