package com.Course.model;

public class CourseVO {
	private String crs_no;
	private String c_acc;
	private String crs_name;
	private String details;
	private String category;
	private String categoryChange;
	private Integer status;
	public CourseVO() {
	}

	public CourseVO(String crs_no, String c_acc, String crs_name, String details, String category, Integer status) {
		this.crs_no = crs_no;
		this.c_acc = c_acc;
		this.crs_name = crs_name;
		this.details = details;
		this.category = category;
		this.status = status;
	}

	public String getCrs_no() {
		return this.crs_no;
	}

	public void setCrs_no(String crs_no) {
		this.crs_no = crs_no;
	}

	public String getC_acc() {
		return this.c_acc;
	}

	public void setC_acc(String c_acc) {
		this.c_acc = c_acc;
	}

	public String getCrs_name() {
		return this.crs_name;
	}

	public void setCrs_name(String crs_name) {
		this.crs_name = crs_name;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCategoryChange() {
		return categoryChange;
	}

	public void setCategoryChange(String categoryChange) {
		this.categoryChange = categoryChange;
	}

}
