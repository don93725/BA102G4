package com.coaches.model;

import java.io.Serializable;

public class CoachesVO implements Serializable{
	private String coa_acc;
	private String coa_no;
	private String coa_psw;
	private Integer coa_sta;
	private String coa_name;
	private Integer coa_sex;
	private String coa_id;
	private String coa_mail;
	private String coa_into;
	private byte[] coa_pic;
	private Integer coa_pft;
	
	public CoachesVO(){
		
	}

	public String getCoa_acc() {
		return coa_acc;
	}

	public void setCoa_acc(String coa_acc) {
		this.coa_acc = coa_acc;
	}

	public String getCoa_no() {
		return coa_no;
	}

	public void setCoa_no(String coa_no) {
		this.coa_no = coa_no;
	}

	public String getCoa_psw() {
		return coa_psw;
	}

	public void setCoa_psw(String coa_psw) {
		this.coa_psw = coa_psw;
	}

	public Integer getCoa_sta() {
		return coa_sta;
	}

	public void setCoa_sta(Integer coa_sta) {
		this.coa_sta = coa_sta;
	}

	public String getCoa_name() {
		return coa_name;
	}

	public void setCoa_name(String coa_name) {
		this.coa_name = coa_name;
	}

	public Integer getCoa_sex() {
		return coa_sex;
	}

	public void setCoa_sex(Integer coa_sex) {
		this.coa_sex = coa_sex;
	}

	public String getCoa_id() {
		return coa_id;
	}

	public void setCoa_id(String coa_id) {
		this.coa_id = coa_id;
	}

	public String getCoa_mail() {
		return coa_mail;
	}

	public void setCoa_mail(String coa_mail) {
		this.coa_mail = coa_mail;
	}

	public String getCoa_into() {
		return coa_into;
	}

	public void setCoa_into(String coa_into) {
		this.coa_into = coa_into;
	}

	public byte[] getCoa_pic() {
		return coa_pic;
	}

	public void setCoa_pic(byte[] coa_pic) {
		this.coa_pic = coa_pic;
	}

	public Integer getCoa_pft() {
		return coa_pft;
	}

	public void setCoa_pft(Integer coa_pft) {
		this.coa_pft = coa_pft;
	}
	
}
