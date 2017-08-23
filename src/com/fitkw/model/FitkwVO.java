package com.fitkw.model;

import java.sql.Date;

public class FitkwVO implements java.io.Serializable{
	private String fik_no;
	private Date upd_date;
	private String fik_type;
	private String fik_title;
	private String fik_ctx;
	private byte[] fik_photo;
	
	
	public String getFik_no() {
		return fik_no;
	}
	public void setFik_no(String fik_no) {
		this.fik_no = fik_no;
	}
	public Date getUpd_date() {
		return upd_date;
	}
	public void setUpd_date(Date upd_date) {
		this.upd_date = upd_date;
	}
	public String getFik_type() {
		return fik_type;
	}
	public void setFik_type(String fik_type) {
		this.fik_type = fik_type;
	}
	public String getFik_title() {
		return fik_title;
	}
	public void setFik_title(String fik_title) {
		this.fik_title = fik_title;
	}
	public String getFik_ctx() {
		return fik_ctx;
	}
	public void setFik_ctx(String fik_ctx) {
		this.fik_ctx = fik_ctx;
	}
	public byte[] getFik_photo() {
		return fik_photo;
	}
	public void setFik_photo(byte[] fik_photo) {
		this.fik_photo = fik_photo;
	}

	
	
	
	
	
	
}
