package com.forum.domain;

import java.sql.Blob;
import java.util.Date;

import com.members.model.MembersVO;

public class Article_comments {
	String art_cmt_no,art_no,art_cmt_ctx;
	MembersVO mem_no;
	byte[] art_cmt_img;
	Date art_cmt_time;
	
	public String getArt_cmt_no() {
		return art_cmt_no;
	}
	public void setArt_cmt_no(String art_cmt_no) {
		this.art_cmt_no = art_cmt_no;
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
	public String getArt_cmt_ctx() {
		return art_cmt_ctx;
	}
	public void setArt_cmt_ctx(String art_cmt_ctx) {
		this.art_cmt_ctx = art_cmt_ctx;
	}
	public byte[] getArt_cmt_img() {
		return art_cmt_img;
	}
	public void setArt_cmt_img(byte[] art_cmt_img) {
		this.art_cmt_img = art_cmt_img;
	}
	public Date getArt_cmt_time() {
		return art_cmt_time;
	}
	public void setArt_cmt_time(Date art_cmt_time) {
		this.art_cmt_time = art_cmt_time;
	}
	
}
