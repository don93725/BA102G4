package com.adapply.model;

import java.sql.Date;

public class AD_ApplyVO implements java.io.Serializable  {
	private String ad_no;
	private String mem_no;
	private Integer arv_stat;
	private Date pay_date;
	private String ad_name;
	private String ad_url;
	private Date ad_ondate;
	private Date ad_offdate;
	private String ad_ctx;
	private byte[] ad_pt;
	public String getAd_no() {
		return ad_no;
	}
	public void setAd_no(String ad_no) {
		this.ad_no = ad_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public Integer getArv_stat() {
		return arv_stat;
	}
	public void setArv_stat(Integer arv_stat) {
		this.arv_stat = arv_stat;
	}
	public Date getPay_date() {
		return pay_date;
	}
	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}
	public String getAd_name() {
		return ad_name;
	}
	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}
	public String getAd_url() {
		return ad_url;
	}
	public void setAd_url(String ad_url) {
		this.ad_url = ad_url;
	}
	public Date getAd_ondate() {
		return ad_ondate;
	}
	public void setAd_ondate(Date ad_ondate) {
		this.ad_ondate = ad_ondate;
	}
	public Date getAd_offdate() {
		return ad_offdate;
	}
	public void setAd_offdate(Date ad_offdate) {
		this.ad_offdate = ad_offdate;
	}
	public String getAd_ctx() {
		return ad_ctx;
	}
	public void setAd_ctx(String ad_ctx) {
		this.ad_ctx = ad_ctx;
	}
	public byte[] getAd_pt() {
		return ad_pt;
	}
	public void setAd_pt(byte[] ad_pt) {
		this.ad_pt = ad_pt;
	}
	
}
