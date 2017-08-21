package com.Course_time.model;

import java.util.List;

public abstract interface Course_timeDAO_interface {
	public abstract void insert(Course_timeVO course_timeVO);

	public abstract void update(Course_timeVO course_timeVO);

	public abstract void delete(String ct_no);

	public abstract Course_timeVO findByPK(String ct_no);

	public abstract List<Course_timeVO> getAll(String c_acc);
	
	public abstract List<Course_timeVO> getAllOpen(String c_acc);
	
	public abstract List<Course_timeVO> getAllRecord(String c_acc);
}
