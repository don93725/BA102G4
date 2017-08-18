package com.comments.model;

import java.util.Date;

import com.members.model.MembersVO;

public class Board_cmt {
	Integer cmt_likes;
	String bd_cmt_no,cmt_type,org_no,bd_cmt_ctx;
	Date bd_cmt_time;
	MembersVO mem_no;
	boolean ifClick;
	
	public boolean isIfClick() {
		return ifClick;
	}
	public void setIfClick(boolean ifClick) {
		this.ifClick = ifClick;
	}
	public Integer getCmt_likes() {
		return cmt_likes;
	}
	public void setCmt_likes(Integer cmt_likes) {
		this.cmt_likes = cmt_likes;
	}
	public String getBd_cmt_no() {
		return bd_cmt_no;
	}
	public void setBd_cmt_no(String bd_cmt_no) {
		this.bd_cmt_no = bd_cmt_no;
	}
	
	public MembersVO getMem_no() {
		return mem_no;
	}
	public void setMem_no(MembersVO mem_no) {
		this.mem_no = mem_no;
	}
	public String getCmt_type() {
		return cmt_type;
	}
	public void setCmt_type(String cmt_type) {
		this.cmt_type = cmt_type;
	}
	public String getOrg_no() {
		return org_no;
	}
	public void setOrg_no(String org_no) {
		this.org_no = org_no;
	}
	public String getBd_cmt_ctx() {
		return bd_cmt_ctx;
	}
	public void setBd_cmt_ctx(String bd_cmt_ctx) {
		this.bd_cmt_ctx = bd_cmt_ctx;
	}
	public Date getBd_cmt_time() {
		return bd_cmt_time;
	}
	public void setBd_cmt_time(Date bd_cmt_time) {
		this.bd_cmt_time = bd_cmt_time;
	}
	
}
