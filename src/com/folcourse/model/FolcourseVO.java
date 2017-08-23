package com.folcourse.model;

import java.sql.Date;

public class FolcourseVO implements java.io.Serializable{
	private String fcrs_no;
	private String fcrsp_acc;
	private Date fcrs_date;
	
	
	public String getFcrs_no() {
		return fcrs_no;
	}
	public void setFcrs_no(String fcrs_no) {
		this.fcrs_no = fcrs_no;
	}
	public String getFcrsp_acc() {
		return fcrsp_acc;
	}
	public void setFcrsp_acc(String fcrsp_acc) {
		this.fcrsp_acc = fcrsp_acc;
	}
	public Date getFcrs_date() {
		return fcrs_date;
	}
	public void setFcrs_date(Date fcrs_date) {
		this.fcrs_date = fcrs_date;
	}
	
	
	
	
	
	
	
}

