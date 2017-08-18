package com.friends.model;

import java.util.Date;

public class Friends {

	String mem_no,mem_nickname,fd_no,fd_nickname;
	Date fd_date;
	
	public String getMem_nickname() {
		return mem_nickname;
	}
	public void setMem_nickname(String mem_nickname) {
		this.mem_nickname = mem_nickname;
	}
	public String getFd_nickname() {
		return fd_nickname;
	}
	public void setFd_nickname(String fd_nickname) {
		this.fd_nickname = fd_nickname;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getFd_no() {
		return fd_no;
	}
	public void setFd_no(String fd_no) {
		this.fd_no = fd_no;
	}
	public Date getFd_date() {
		return fd_date;
	}
	public void setFd_date(Date fd_date) {
		this.fd_date = fd_date;
	}
	
}
