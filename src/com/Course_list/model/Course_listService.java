package com.Course_list.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class Course_listService {
	 private Course_listDAO_interface dao;
	  
	  public Course_listService() {
	    this.dao = new Course_listDAO();
	  }
	  
	  public Course_listVO addCourse_list(String ct_no,String stu_acc,Date cl_date,Integer crs_time){
		Course_listVO course_listVO = new Course_listVO();
		course_listVO.setCt_no(ct_no);
		course_listVO.setStu_acc(stu_acc);
		course_listVO.setCl_date(cl_date);
		course_listVO.setCrs_time(crs_time);
		
	    this.dao.insert(course_listVO);
	    return course_listVO;
	  }
	  
	  public Course_listVO updateCourse_list(){
		  Course_listVO course_listVO = new Course_listVO();
		    this.dao.update(course_listVO);
		    return course_listVO;
	  }
	  
	  public void deleteCourse_list(String ct_no,String stu_acc){
	    this.dao.delete(ct_no,stu_acc);
	  }
	
	  public void deleteCalendar(String cl_date,Integer crs_time,String stu_acc){
		  this.dao.deleteCalendar(cl_date,crs_time,stu_acc);
	  }
	  
	  public Course_listVO getOneCourse_list(String ct_no,String stu_acc){
	    return this.dao.findByPK(ct_no,stu_acc);
	  }
	  
	  public List<Course_listVO> getAll(String stu_acc){
	    return this.dao.getAll(stu_acc);
	  } 
	  
	  public void payCourse_list(String ct_no,String stu_acc){
		    this.dao.pay(ct_no,stu_acc);
	  }
	  
	  public boolean signable(String ct_no,String stu_acc){
		  return this.dao.findSignUp(ct_no,stu_acc);
	  }
	  
	  public List<Course_listVO> getAllByCt_no(String ct_no){
		    return this.dao.getAllByCt_no(ct_no);
	  }
	  
	  public void leave(String reason,String ct_no,String stu_acc){
		    this.dao.leave(reason,ct_no,stu_acc);
	  }
	  
	  public List<Course_listVO> getAllOpen(String stu_acc){
		    return this.dao.getAllOpen(stu_acc);
	  }

	  public List<Course_listVO> getAllRecord(String stu_acc){
		  return this.dao.getAllRecord(stu_acc);
	  }
	  
	  public void report(String report_ct,String ct_no,String stu_acc){
		    this.dao.report(report_ct,ct_no,stu_acc);
	  }
	  
	  public void evaluation(String evaluation_coa,String evaluation_crs,String feedback,String ct_no,String stu_acc){
		    this.dao.evaluation(evaluation_coa,evaluation_crs,feedback,ct_no,stu_acc);
	  }
		public Course_listVO updateReportSta(String ct_no) {
			Course_listVO course_listVO = new Course_listVO();
			course_listVO.setCt_no(ct_no);
			dao.updateRepSta(course_listVO);
			return course_listVO;
		}

		public Course_listVO updateCRNum(String ct_no) {
			Course_listVO course_listVO = new Course_listVO();
			course_listVO.setCt_no(ct_no);
			
			dao.updateCRNum(course_listVO);
			return course_listVO;
		}

}
