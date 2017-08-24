package com.manager.model;

import com.members.model.MembersVO;

public class ManagerVO implements java.io.Serializable{
	private String mgr_no;
	private String mem_no;
	private String mgr_id;
	private String mgr_pwd;
	private Integer mgr_job;
	private String mgr_name;
	private String mgr_email;
	private Integer mgr_status;
	private byte[] mgr_pic;
	private String mgr_int;
//	private MembersVO mem_no;
	
	
	
	
	public String getMgr_no() {
		return mgr_no;
	}
	public String getMgr_int() {
		return mgr_int;
	}
	public void setMgr_int(String mgr_int) {
		this.mgr_int = mgr_int;
	}
	public void setMgr_no(String mgr_no) {
		this.mgr_no = mgr_no;
	}
	public String getMem_no() {		
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getMgr_id() {
		return mgr_id;
	}
	public void setMgr_id(String mgr_id) {
		this.mgr_id = mgr_id;
	}
	public String getMgr_pwd() {
		return mgr_pwd;
	}
	public void setMgr_pwd(String mgr_pwd) {
		this.mgr_pwd = mgr_pwd;
	}
	public Integer getMgr_job() {
		return mgr_job;
	}
	public void setMgr_job(Integer mgr_job) {
		this.mgr_job = mgr_job;
	}
	public String getMgr_name() {
		return mgr_name;
	}
	public void setMgr_name(String mgr_name) {
		this.mgr_name = mgr_name;
	}
	public String getMgr_email() {
		return mgr_email;
	}
	public void setMgr_email(String mgr_email) {
		this.mgr_email = mgr_email;
	}
	public Integer getMgr_status() {
		return mgr_status;
	}
	public void setMgr_status(Integer mgr_status) {
		this.mgr_status = mgr_status;
	}
	public byte[] getMgr_pic() {
		return mgr_pic;
	}
	public void setMgr_pic(byte[] mgr_pic) {
		this.mgr_pic = mgr_pic;
	}
	
}
