package com.course.model;

import java.util.List;

public  interface CourseDAO_interface
{
  public  void insert(CourseVO courseVO, List<String> crs_base);
  
  public  void update(CourseVO courseVO);
  
  public  void delete(String crs_no);
  
  public  CourseVO findByPK(String crs_no);
  
  public  List<CourseVO> getAll(String c_acc);
  
  public CourseVO fingByCourse(String ct_no);
}
