package com.Course.model;

import java.util.List;

public abstract interface CourseDAO_interface
{
  public abstract void insert(CourseVO courseVO);
  
  public abstract void update(CourseVO courseVO);
  
  public abstract void delete(String crs_no);
  
  public abstract CourseVO findByPK(String crs_no);
  
  public abstract List<CourseVO> getAll(String c_acc);
  
  //  7777777
  public CourseVO fingByCourse(String ct_no);
}
