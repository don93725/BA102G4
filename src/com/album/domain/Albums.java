package com.album.domain;

import java.util.Date;

import com.members.model.MembersVO;

public class Albums {
	Integer al_views;
	String al_no,al_name,al_prvt,al_board;
	MembersVO mem_no;
	Date al_date;
	public Integer getAl_views() {
		return al_views;
	}
	public void setAl_views(Integer al_views) {
		this.al_views = al_views;
	}
	public String getAl_no() {
		return al_no;
	}
	public void setAl_no(String al_no) {
		this.al_no = al_no;
	}
	
	public MembersVO getMem_no() {
		return mem_no;
	}
	public void setMem_no(MembersVO mem_no) {
		this.mem_no = mem_no;
	}
	public String getAl_name() {
		return al_name;
	}
	public void setAl_name(String al_name) {
		this.al_name = al_name;
	}
	public String getAl_prvt() {
		return al_prvt;
	}
	public void setAl_prvt(String al_prvt) {
		this.al_prvt = al_prvt;
	}
	public Date getAl_date() {
		return al_date;
	}
	public void setAl_date(Date al_date) {
		this.al_date = al_date;
	}
	public String getAl_board() {
		return al_board;
	}
	public void setAl_board(String al_board) {
		this.al_board = al_board;
	}
	
	
}
