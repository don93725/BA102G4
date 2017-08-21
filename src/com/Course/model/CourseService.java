package com.Course.model;

import java.util.List;

public class CourseService{
  private CourseDAO_interface dao;
  
  public CourseService()
  {
    this.dao = new CourseDAO();
  }
  
  public CourseVO addCourse(String c_acc, String crs_name, String details, String category, List<String> crs_base)
  {
    CourseVO couresVO = new CourseVO();
    couresVO.setC_acc(c_acc);
    couresVO.setCrs_name(crs_name);
    couresVO.setDetails(details);
    couresVO.setCategory(category);
    this.dao.insert(couresVO,crs_base);
    
    return couresVO;
  }
  
  public CourseVO updateCourse(String crs_no, String crs_name, String details, String category)
  {
    CourseVO couresVO = new CourseVO();
    couresVO.setCrs_no(crs_no);
    couresVO.setCrs_name(crs_name);
    couresVO.setDetails(details);
    couresVO.setCategory(category);
    this.dao.update(couresVO);
    
    return couresVO;
  }
  
  public void deleteCourse(String crs_no)
  {
    this.dao.delete(crs_no);
  }
  
  public CourseVO getOneCourse(String crs_no)
  {
    return this.dao.findByPK(crs_no);
  }
  
  public List<CourseVO> getAll(String c_acc)
  {
    return this.dao.getAll(c_acc);
  }
}
