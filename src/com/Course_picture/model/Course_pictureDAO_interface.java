package com.Course_picture.model;

import java.util.List;


public interface Course_pictureDAO_interface {
	public  void insert(Course_pictureVO course_pictureVO);

	public  void update(Course_pictureVO course_pictureVO);

	public  void delete(String crs_pic_no);

	public  Course_pictureVO findByPK(String crs_pic_no);

	public  List<Course_pictureVO> getAll(String crs_no);
}
