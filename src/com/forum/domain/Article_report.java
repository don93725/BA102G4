package com.forum.domain;

import java.util.Date;

import com.members.model.MembersVO;

public class Article_report {
	String art_rpt_no,rpt_type,rpt_ctx,rpt_stat;
	Articles art_no;
	MembersVO rpt_mem_no;
	Date rpt_time;
	public String getArt_rpt_no() {
		return art_rpt_no;
	}
	public void setArt_rpt_no(String art_rpt_no) {
		this.art_rpt_no = art_rpt_no;
	}
	
	public Articles getArt_no() {
		return art_no;
	}
	public void setArt_no(Articles art_no) {
		this.art_no = art_no;
	}
	
	public MembersVO getRpt_mem_no() {
		return rpt_mem_no;
	}
	public void setRpt_mem_no(MembersVO rpt_mem_no) {
		this.rpt_mem_no = rpt_mem_no;
	}
	public String getRpt_type() {
		return rpt_type;
	}
	public void setRpt_type(String rpt_type) {
		this.rpt_type = rpt_type;
	}
	public String getRpt_ctx() {
		return rpt_ctx;
	}
	public void setRpt_ctx(String rpt_ctx) {
		this.rpt_ctx = rpt_ctx;
	}
	public String getRpt_stat() {
		return rpt_stat;
	}
	public void setRpt_stat(String rpt_stat) {
		this.rpt_stat = rpt_stat;
	}
	public Date getRpt_time() {
		return rpt_time;
	}
	public void setRpt_time(Date rpt_time) {
		this.rpt_time = rpt_time;
	}
	
}
