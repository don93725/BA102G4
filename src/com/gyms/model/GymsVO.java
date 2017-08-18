package com.gyms.model;

import java.io.Serializable;

public class GymsVO implements Serializable{
	private String gym_acc;
	private String gym_no;
	private String gym_psw;
	private Integer gym_sta;
	private String gym_name;
	private String gym_mail;
	private String gym_add;
	private String gym_latlng;
	private String gym_into;
	private byte[] gym_pic;
	
	public GymsVO(){
		
	}

	public String getGym_acc() {
		return gym_acc;
	}

	public void setGym_acc(String gym_acc) {
		this.gym_acc = gym_acc;
	}

	public String getGym_no() {
		return gym_no;
	}

	public void setGym_no(String gym_no) {
		this.gym_no = gym_no;
	}

	public String getGym_psw() {
		return gym_psw;
	}

	public void setGym_psw(String gym_psw) {
		this.gym_psw = gym_psw;
	}

	public Integer getGym_sta() {
		return gym_sta;
	}

	public void setGym_sta(Integer gym_sta) {
		this.gym_sta = gym_sta;
	}

	public String getGym_name() {
		return gym_name;
	}

	public void setGym_name(String gym_name) {
		this.gym_name = gym_name;
	}

	public String getGym_mail() {
		return gym_mail;
	}

	public void setGym_mail(String gym_mail) {
		this.gym_mail = gym_mail;
	}

	public String getGym_add() {
		return gym_add;
	}

	public void setGym_add(String gym_add) {
		this.gym_add = gym_add;
	}

	public String getGym_latlng() {
		return gym_latlng;
	}

	public void setGym_latlng(String gym_latlng) {
		this.gym_latlng = gym_latlng;
	}

	public String getGym_into() {
		return gym_into;
	}

	public void setGym_into(String gym_into) {
		this.gym_into = gym_into;
	}

	public byte[] getGym_pic() {
		return gym_pic;
	}

	public void setGym_pic(byte[] gym_pic) {
		this.gym_pic = gym_pic;
	}
	
	
}
