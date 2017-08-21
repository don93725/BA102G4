package com.Course_picture.model;

public class Course_pictureVO {
	String crs_pic_no,crs_no,crs_base;
	
	public Course_pictureVO(){}
	
	public Course_pictureVO(String crs_pic_no,String crs_no,String crs_base){
		this.crs_pic_no = crs_pic_no;
		this.crs_no = crs_no;
		this.crs_base = crs_base;
	}

	public String getCrs_pic_no() {
		return crs_pic_no;
	}

	public void setCrs_pic_no(String crs_pic_no) {
		this.crs_pic_no = crs_pic_no;
	}

	public String getCrs_no() {
		return crs_no;
	}

	public void setCrs_no(String crs_no) {
		this.crs_no = crs_no;
	}

	public String getCrs_base() {
		return crs_base;
	}

	public void setCrs_base(String crs_base) {
		this.crs_base = crs_base;
	}

}
