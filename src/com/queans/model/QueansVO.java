package com.queans.model;

import java.sql.Date;

public class QueansVO implements java.io.Serializable{
	
	private String que_no;
	private Date upd_date;
	private String que_type;
	private String que_title;
	private String ans_ctx;
	private byte[] que_photo;
	
	public String getQue_no() {
		return que_no;
	}
	public void setQue_no(String que_no) {
		this.que_no = que_no;
	}
	public Date getUpd_date() {
		return upd_date;
	}
	public void setUpd_date(Date upd_date) {
		this.upd_date = upd_date;
	}
	public String getQue_type() {
		return que_type;
	}
	public void setQue_type(String que_type) {
		this.que_type = que_type;
	}
	public String getQue_title() {
		return que_title;
	}
	public void setQue_title(String que_title) {
		this.que_title = que_title;
	}
	public String getAns_ctx() {
		return ans_ctx;
	}
	public void setAns_ctx(String ans_ctx) {
		this.ans_ctx = ans_ctx;
	}
	public byte[] getQue_photo() {
		return que_photo;
	}
	public void setQue_photo(byte[] que_photo) {
		this.que_photo = que_photo;
	}
	
	
	
	
	
	
	
	
	
}