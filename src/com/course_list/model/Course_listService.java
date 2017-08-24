package com.course_list.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.course_list.model.Course_listVO;


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
	
	  public boolean deleteAdCourse_list(String ct_no,String stu_acc){
		    this.dao.delete(ct_no,stu_acc);
		 
		    return true;
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
	  
	  public boolean reportAd(String report_ct,String ct_no,String stu_acc){
		    this.dao.report(report_ct,ct_no,stu_acc);
		
		    return true;
	  }
	  
	  
	  public void evaluation(String evaluation_coa,String evaluation_crs,String feedback,String ct_no,String stu_acc){
		    this.dao.evaluation(evaluation_coa,evaluation_crs,feedback,ct_no,stu_acc);
	  }
	  public boolean evaluationAd(String evaluation_coa,String evaluation_crs,String feedback,String ct_no,String stu_acc){
		    this.dao.evaluation(evaluation_coa,evaluation_crs,feedback,ct_no,stu_acc);
	 
		    return true;
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
		  public List<Course_listVO> getReportSta(Integer report_sta) {
			
			return dao.getReportSta(report_sta);
		}
		  public List<Course_listVO> getReserve_List(String stu_acc){
			    return this.dao.getReserve(stu_acc);
			  }
		 
		  public List<Course_listVO> getCoachReserve_List(String coa_acc){
			    return this.dao.getReserve(coa_acc);
			  }


		  public boolean updateNSta(String ct_no,String stu_acc) {
				Course_listVO course_listVO = new Course_listVO();
				course_listVO.setCt_no(ct_no);
				course_listVO.setStu_acc(ct_no);
				
				dao.updateNSta(course_listVO);
				return true;}

		  public List<Course_listVO> getReady_List(String stu_acc){
			    return this.dao.getReady(stu_acc);
			  }
		  public List<Course_listVO> getCoachReady_List(String coa_acc){
			    return this.dao.getCoachReady(coa_acc);
			  }
		  
		  
		  public List<Course_listVO> getFinished(String stu_acc){
			    return this.dao.getFinished(stu_acc);
		  }
		  
		  public Course_listVO getEva(String ct_no) {
			return dao.getEva(ct_no);
		 }
		  

}
