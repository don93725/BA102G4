package com.annew.model;

import java.sql.Date;

public class AnnewVO implements java.io.Serializable{
	private String ann_no;
	private Date ann_date;
	private String ann_date2;
	private Date upd_date;
	private String upd_date2;
	private String ann_title;
	private String ann_ctx;
	private byte[] ann_photo;
	private byte[] att_no;
	
	
	public String getAnn_date2() {
		return ann_date2;
	}
	public void setAnn_date2(String ann_date2) {
		this.ann_date2 = ann_date2;
	}
	public String getUpd_date2() {
		return upd_date2;
	}
	public void setUpd_date2(String upd_date2) {
		this.upd_date2 = upd_date2;
	}
	public String getAnn_no() {
		return ann_no;
	}
	public void setAnn_no(String ann_no) {
		this.ann_no = ann_no;
	}
	public Date getAnn_date() {
		return ann_date;
	}
	public void setAnn_date(Date ann_date) {
		this.ann_date = ann_date;
	}
	public Date getUpd_date() {
		return upd_date;
	}
	public void setUpd_date(Date upd_date) {
		this.upd_date = upd_date;
	}
	
	public String getAnn_title() {
		return ann_title;
	}
	public void setAnn_title(String ann_title) {
		this.ann_title = ann_title;
	}
	public String getAnn_ctx() {
		return ann_ctx;
	}
	public void setAnn_ctx(String ann_ctx) {
		this.ann_ctx = ann_ctx;
	}
	public byte[] getAnn_photo() {
		return ann_photo;
	}
	public void setAnn_photo(byte[] ann_photo) {
		this.ann_photo = ann_photo;
	}
	public byte[] getAtt_no() {
		return att_no;
	}
	public void setAtt_no(byte[] att_no) {
		this.att_no = att_no;
	}

	
	
	
}
