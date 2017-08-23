package com.platinf.model;

import java.sql.Date;

public class PlatinfVO implements java.io.Serializable{
	
	private String pin_no;
	private Date upd_date;
	private String com_address;
	private String cp_no;
	private String cs_email;
	private String pr_policy;
	private byte[] pin_photo;
	private String upd_date2;
	
	
	public String getUpd_date2() {
		return upd_date2;
	}
	public void setUpd_date2(String upd_date2) {
		this.upd_date2 = upd_date2;
	}
	public String getPin_no() {
		return pin_no;
	}
	public void setPin_no(String pin_no) {
		this.pin_no = pin_no;
	}
	public Date getUpd_date() {
		return upd_date;
	}
	public void setUpd_date(Date upd_date) {
		this.upd_date = upd_date;
	}
	public String getCom_address() {
		return com_address;
	}
	public void setCom_address(String com_address) {
		this.com_address = com_address;
	}
	public String getCp_no() {
		return cp_no;
	}
	public void setCp_no(String cp_no) {
		this.cp_no = cp_no;
	}
	public String getCs_email() {
		return cs_email;
	}
	public void setCs_email(String cs_email) {
		this.cs_email = cs_email;
	}
	public String getPr_policy() {
		return pr_policy;
	}
	public void setPr_policy(String pr_policy) {
		this.pr_policy = pr_policy;
	}
	public byte[] getPin_photo() {
		return pin_photo;
	}
	public void setPin_photo(byte[] pin_photo) {
		this.pin_photo = pin_photo;
	}
	
	
	
	
}