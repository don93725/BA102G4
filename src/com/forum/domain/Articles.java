package com.forum.domain;

import java.util.Date;

import com.members.model.MembersVO;

public class Articles {	
	Integer art_views,art_mviews;
	
	String art_no,forum_no,art_type,art_name,art_ctx;
	Date art_add_date,art_upd_date;
	MembersVO mem_no;
	Article_comments newestCmmt;
	
	public Article_comments getNewestCmmt() {
		return newestCmmt;
	}
	public void setNewestCmmt(Article_comments newestCmmt) {
		this.newestCmmt = newestCmmt;
	}
	public Integer getArt_views() {
		return art_views;
	}
	public void setArt_views(Integer art_views) {
		this.art_views = art_views;
	}
	public Integer getArt_mviews() {
		return art_mviews;
	}
	public void setArt_mviews(Integer art_mviews) {
		this.art_mviews = art_mviews;
	}
	public String getArt_no() {
		return art_no;
	}
	public void setArt_no(String art_no) {
		this.art_no = art_no;
	}
	
	public MembersVO getMem_no() {
		return mem_no;
	}
	public void setMem_no(MembersVO mem_no) {
		this.mem_no = mem_no;
	}
	public String getForum_no() {
		return forum_no;
	}
	public void setForum_no(String forum_no) {
		this.forum_no = forum_no;
	}
	public String getArt_type() {
		return art_type;
	}
	public void setArt_type(String art_type) {
		this.art_type = art_type;
	}
	public String getArt_name() {
		return art_name;
	}
	public void setArt_name(String art_name) {
		this.art_name = art_name;
	}
	public String getArt_ctx() {
		return art_ctx;
	}
	public void setArt_ctx(String art_ctx) {
		this.art_ctx = art_ctx;
	}
	public Date getArt_add_date() {
		return art_add_date;
	}
	public void setArt_add_date(Date art_add_date) {
		this.art_add_date = art_add_date;
	}
	public Date getArt_upd_date() {
		return art_upd_date;
	}
	public void setArt_upd_date(Date art_upd_date) {
		this.art_upd_date = art_upd_date;
	}
	

}
