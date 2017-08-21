package com.Course_time.model;

import com.Course.model.CourseVO;
import com.place.model.PlaceVO;

import java.sql.Date;

public class Course_timeVO {
	String ct_no;
	String crs_no;
	String p_no;
	Date crs_date;
	Date deadline;
	Integer crs_time;
	String crs_timeShow;
	String price;
	String limit;
	String class_num;
	Integer status;
	CourseVO courseVO;
	Integer count;
	PlaceVO placeVO;

	public Course_timeVO() {
	}

	public Course_timeVO(String ct_no, String crs_no, String p_no, Date crs_date, Date deadline, Integer crs_time,
			String price, String limit, String class_num, Integer status) {
		this.ct_no = ct_no;
		this.crs_no = crs_no;
		this.p_no = p_no;
		this.crs_date = crs_date;
		this.deadline = deadline;
		this.crs_time = crs_time;
		this.price = price;
		this.limit = limit;
		this.status = status;
	}

	public String getCt_no() {
		return this.ct_no;
	}

	public void setCt_no(String ct_no) {
		this.ct_no = ct_no;
	}

	public String getCrs_no() {
		return this.crs_no;
	}

	public void setCrs_no(String crs_no) {
		this.crs_no = crs_no;
	}

	public String getP_no() {
		return this.p_no;
	}

	public void setP_no(String p_no) {
		this.p_no = p_no;
	}

	public Date getCrs_date() {
		return this.crs_date;
	}

	public void setCrs_date(Date crs_date) {
		this.crs_date = crs_date;
	}

	public Date getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Integer getCrs_time() {
		return this.crs_time;
	}

	public void setCrs_time(Integer crs_time) {
		this.crs_time = crs_time;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getLimit() {
		return this.limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getClass_num() {
		return this.class_num;
	}

	public void setClass_num(String class_num) {
		this.class_num = class_num;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCrs_timeShow() {
		return this.crs_timeShow;
	}

	public void setCrs_timeShow(String crs_timeShow) {
		this.crs_timeShow = crs_timeShow;
	}

	public CourseVO getCourseVO() {
		return this.courseVO;
	}

	public void setCourseVO(CourseVO courseVO) {
		this.courseVO = courseVO;
	}

	public PlaceVO getPlaceVO() {
		return placeVO;
	}

	public void setPlaceVO(PlaceVO placeVO) {
		this.placeVO = placeVO;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	

}
