package com.place.model;

import java.sql.Date;

import com.place_pic.model.Place_PicVO;
import com.place_publish.model.Place_PublishVO;

public class PlaceVO implements java.io.Serializable {

	private String p_no;
	private String g_acc;
	private String p_name;
	private String p_into;
	private Integer p_status;
	private String p_add;
	private String p_latlng;
	private Integer p_cap;
	private Date p_date;
	private Place_PublishVO place_publishVO;
	private Place_PicVO place_picVO;
	
	public Place_PublishVO getPlace_publishVO() {
		return place_publishVO;
	}
	public void setPlace_publishVO(Place_PublishVO place_publishVO) {
		this.place_publishVO = place_publishVO;
	}
	public Place_PicVO getPlace_picVO() {
		return place_picVO;
	}
	public void setPlace_picVO(Place_PicVO place_picVO) {
		this.place_picVO = place_picVO;
	}
	public String getP_no() {
		return p_no;
	}
	public void setP_no(String p_no) {
		this.p_no = p_no;
	}
	public String getG_acc() {
		return g_acc;
	}
	public void setG_acc(String g_acc) {
		this.g_acc = g_acc;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_into() {
		return p_into;
	}
	public void setP_into(String p_into) {
		this.p_into = p_into;
	}
	public Integer getP_status() {
		return p_status;
	}
	public void setP_status(Integer p_status) {
		this.p_status = p_status;
	}
	public String getP_add() {
		return p_add;
	}
	public void setP_add(String p_add) {
		this.p_add = p_add;
	}
	public String getP_latlng() {
		return p_latlng;
	}
	public void setP_latlng(String p_latlng) {
		this.p_latlng = p_latlng;
	}
	public Integer getP_cap() {
		return p_cap;
	}
	public void setP_cap(Integer p_cap) {
		this.p_cap = p_cap;
	}
	public Date getP_date() {
		return p_date;
	}
	public void setP_date(Date p_date) {
		this.p_date = p_date;
	}	
}
