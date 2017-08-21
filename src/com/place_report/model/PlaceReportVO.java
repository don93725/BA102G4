package com.place_report.model;

import java.sql.Date;

public class PlaceReportVO implements java.io.Serializable {

	private String pr_no;
	private String p_no;
	private String mem_no;
	private String pr_ctx;
	private Date pr_time;
	private Integer pr_stat;
	private String ref_ctx;
	private byte[] pr_pt;
	
	public String getRef_ctx() {
		return ref_ctx;
	}
	public void setRef_ctx(String ref_ctx) {
		this.ref_ctx = ref_ctx;
	}
	public String getPr_no() {
		return pr_no;
	}
	public void setPr_no(String pr_no) {
		this.pr_no = pr_no;
	}
	public String getP_no() {
		return p_no;
	}
	public void setP_no(String p_no) {
		this.p_no = p_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getPr_ctx() {
		return pr_ctx;
	}
	public void setPr_ctx(String pr_ctx) {
		this.pr_ctx = pr_ctx;
	}
	public Date getPr_time() {
		return pr_time;
	}
	public void setPr_time(Date pr_time) {
		this.pr_time = pr_time;
	}
	public Integer getPr_stat() {
		return pr_stat;
	}
	public void setPr_stat(Integer pr_stat) {
		this.pr_stat = pr_stat;
	}
	public byte[] getPr_pt() {
		return pr_pt;
	}
	public void setPr_pt(byte[] pr_pt) {
		this.pr_pt = pr_pt;
	}
	
}
