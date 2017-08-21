package com.Course_picture.model;

import java.util.List;


public interface Course_pictureDAO_interface {
	public abstract void insert(Course_pictureVO course_pictureVO);

	public abstract void update(Course_pictureVO course_pictureVO);

	public abstract void delete(String crs_pic_no);

	public abstract Course_pictureVO findByPK(String crs_pic_no);

	public abstract List<Course_pictureVO> getAll(String crs_no);
}
