package com.course_list.model;

import java.sql.Date;

import com.coaches.model.CoachesVO;
import com.course.model.CourseVO;
import com.course_time.model.Course_timeVO;
import com.place.model.PlaceVO;
import com.students.model.StudentsVO;

public class Course_listVO {
	Integer crs_time, stu_pay_sta,report_sta,n_sta,count;
	String ct_no,stu_acc,report_ct,feedback,evaluation_cao,evaluation_crs,reason,crs_timeShow;
	Date cl_date,stu_pay_date;
	Course_timeVO course_timeVO;
	CourseVO courseVO;
	CoachesVO coachesVO;
	PlaceVO placeVO;
	StudentsVO studentsVO;
	
	public  Course_listVO(){};
	
	public  Course_listVO(String ct_no,String stu_acc,Date cl_date,Integer crs_time,Integer stu_pay_sta,Date stu_pay_date,Integer report_sta,String report_ct,String feedback,String evaluation_cao,String evaluation_crs,Integer n_sta,String reason){
		this.crs_time = crs_time;
		this.stu_pay_sta = stu_pay_sta;
		this.report_sta = report_sta;
		this.n_sta = n_sta;
		this.ct_no = ct_no;
		this.stu_acc = stu_acc;
		this.report_ct = report_ct;
		this.feedback = feedback;
		this.evaluation_cao = evaluation_cao;
		this.evaluation_crs = evaluation_crs;
		this.reason = reason;
		this.cl_date = cl_date;
		this.stu_pay_date = stu_pay_date;
	}

	public Integer getCrs_time() {
		return crs_time;
	}

	public void setCrs_time(Integer crs_time) {
		this.crs_time = crs_time;
	}

	public Integer getStu_pay_sta() {
		return stu_pay_sta;
	}

	public void setStu_pay_sta(Integer stu_pay_sta) {
		this.stu_pay_sta = stu_pay_sta;
	}

	public Integer getReport_sta() {
		return report_sta;
	}

	public void setReport_sta(Integer report_sta) {
		this.report_sta = report_sta;
	}

	public Integer getN_sta() {
		return n_sta;
	}

	public void setN_sta(Integer n_sta) {
		this.n_sta = n_sta;
	}

	public String getCt_no() {
		return ct_no;
	}

	public void setCt_no(String ct_no) {
		this.ct_no = ct_no;
	}

	public String getStu_acc() {
		return stu_acc;
	}

	public void setStu_acc(String stu_acc) {
		this.stu_acc = stu_acc;
	}

	public String getReport_ct() {
		return report_ct;
	}

	public void setReport_ct(String report_ct) {
		this.report_ct = report_ct;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getEvaluation_cao() {
		return evaluation_cao;
	}

	public void setEvaluation_cao(String evaluation_cao) {
		this.evaluation_cao = evaluation_cao;
	}

	public String getEvaluation_crs() {
		return evaluation_crs;
	}

	public void setEvaluation_crs(String evaluation_crs) {
		this.evaluation_crs = evaluation_crs;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getCl_date() {
		return cl_date;
	}

	public void setCl_date(Date cl_date) {
		this.cl_date = cl_date;
	}

	public Date getStu_pay_date() {
		return stu_pay_date;
	}

	public void setStu_pay_date(Date stu_pay_date) {
		this.stu_pay_date = stu_pay_date;
	}

	public Course_timeVO getCourse_timeVO() {
		return course_timeVO;
	}

	public void setCourse_timeVO(Course_timeVO course_timeVO) {
		this.course_timeVO = course_timeVO;
	}

	public CourseVO getCourseVO() {
		return courseVO;
	}

	public void setCourseVO(CourseVO courseVO) {
		this.courseVO = courseVO;
	}

	public CoachesVO getCoachesVO() {
		return coachesVO;
	}

	public void setCoachesVO(CoachesVO coachesVO) {
		this.coachesVO = coachesVO;
	}

	public PlaceVO getPlaceVO() {
		return placeVO;
	}

	public void setPlaceVO(PlaceVO placeVO) {
		this.placeVO = placeVO;
	}

	public StudentsVO getStudentsVO() {
		return studentsVO;
	}

	public void setStudentsVO(StudentsVO studentsVO) {
		this.studentsVO = studentsVO;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getCrs_timeShow() {
		return crs_timeShow;
	}

	public void setCrs_timeShow(String crs_timeShow) {
		this.crs_timeShow = crs_timeShow;
	}
	

}
