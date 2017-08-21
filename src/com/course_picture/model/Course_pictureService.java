package com.course_picture.model;

import java.util.List;


public class Course_pictureService {
	private Course_pictureDAO_interface dao;
	  
	  public Course_pictureService()
	  {
	    this.dao = new Course_pictureDAO();
	  }
	  
	  public void addCourse_picture(String crs_no, List<String> crs_base)
	  {
		  Course_pictureVO course_pictureVO = new Course_pictureVO();
		  course_pictureVO.setCrs_no(crs_no);
		  for(int i=0;i<crs_base.size();i++){
			  course_pictureVO.setCrs_base(crs_base.get(i));
			  this.dao.insert(course_pictureVO);
		  }
		  
	  }
	  
	  public Course_pictureVO updateCourse_picture(String crs_no, String crs_name, String details, String category)
	  {
		  Course_pictureVO course_pictureVO = new Course_pictureVO();
	    return course_pictureVO;
	  }
	  
	  public void deleteCourse_picture(String crs_pic_no)
	  {
	    this.dao.delete(crs_pic_no);
	  }
	  
	  public Course_pictureVO getOneCourse_picture(String crs_no)
	  {
	    return this.dao.findByPK(crs_no);
	  }
	  
	  public List<Course_pictureVO> getAll(String crs_no)
	  {
	    return this.dao.getAll(crs_no);
	  }
}
