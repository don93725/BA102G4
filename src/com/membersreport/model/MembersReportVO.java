package com.membersreport.model;

import java.sql.Date;

public class MembersReportVO implements java.io.Serializable {
	private String mr_no;
	private String mr_def;
	private String mr_pro;
	private String mr_ctx;
	private Date mr_time;
	private Integer mr_stat;
	private String ref_ctx;
	private byte[] mr_pt;
	
	public String getRef_ctx() {
		return ref_ctx;
	}
	public void setRef_ctx(String ref_ctx) {
		this.ref_ctx = ref_ctx;
	}
	public String getMr_no() {
		return mr_no;
	}
	public void setMr_no(String mr_no) {
		this.mr_no = mr_no;
	}
	public String getMr_def() {
		return mr_def;
	}
	public void setMr_def(String mr_def) {
		this.mr_def = mr_def;
	}
	public String getMr_pro() {
		return mr_pro;
	}
	public void setMr_pro(String mr_pro) {
		this.mr_pro = mr_pro;
	}
	public String getMr_ctx() {
		return mr_ctx;
	}
	public void setMr_ctx(String mr_ctx) {
		this.mr_ctx = mr_ctx;
	}
	public Date getMr_time() {
		return mr_time;
	}
	public void setMr_time(Date mr_time) {
		this.mr_time = mr_time;
	}
	public Integer getMr_stat() {
		return mr_stat;
	}
	public void setMr_stat(Integer mr_stat) {
		this.mr_stat = mr_stat;
	}
	public byte[] getMr_pt() {
		return mr_pt;
	}
	public void setMr_pt(byte[] mr_pt) {
		this.mr_pt = mr_pt;
	}
	
}
