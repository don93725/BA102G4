package com.Course_time.model;

import java.sql.Date;
import java.util.List;

import com.Course.model.CourseVO;

public class Course_timeService{
  private Course_timeDAO_interface dao;
  
  public Course_timeService() {
    this.dao = new Course_timeDAO();
  }
  
  public Course_timeVO addCourse_time(String crs_no, String p_no, Date crs_date, Date deadline, Integer crs_time, String price, String limit, String class_num, int status, CourseVO courseVO){
    Course_timeVO course_timeVO = new Course_timeVO();
    course_timeVO.setCrs_no(crs_no);
    course_timeVO.setP_no(p_no);
    course_timeVO.setCrs_date(crs_date);
    course_timeVO.setDeadline(deadline);
    course_timeVO.setCrs_time(crs_time);
    course_timeVO.setPrice(price);
    course_timeVO.setLimit(limit);
    course_timeVO.setClass_num(class_num);
    course_timeVO.setStatus(status);
    course_timeVO.setCourseVO(courseVO);
    this.dao.insert(course_timeVO);
    
    return course_timeVO;
  }
  
  public Course_timeVO updateCourse_time(String ct_no, String p_no, Date crs_date, Date deadline, Integer crs_time, String price){
    Course_timeVO course_timeVO = new Course_timeVO();
    course_timeVO.setCt_no(ct_no);
    course_timeVO.setP_no(p_no);
    course_timeVO.setCrs_date(crs_date);
    course_timeVO.setDeadline(deadline);
    course_timeVO.setCrs_time(crs_time);
    course_timeVO.setPrice(price);
    this.dao.update(course_timeVO);
    
    return course_timeVO;
  }
  
  public void deleteCourse_time(String ct_no){
    this.dao.delete(ct_no);
  }
  
  public Course_timeVO getOneCourse_time(String crs_no){
    return this.dao.findByPK(crs_no);
  }
  
  public List<Course_timeVO> getAll(String c_acc){
    return this.dao.getAll(c_acc);
  }
  
  public List<Course_timeVO> getAllOpen(String c_acc){
	    return this.dao.getAllOpen(c_acc);
  }
  
  public List<Course_timeVO> getAllRecord(String c_acc){
	    return this.dao.getAllRecord(c_acc);
  }
}
