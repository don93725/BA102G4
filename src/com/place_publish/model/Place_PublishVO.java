package com.place_publish.model;

import java.sql.Date;

public class Place_PublishVO implements java.io.Serializable{
	private String pp_no;
	private String p_no;
	private String opc_acc;
	private Date rp_date;
	private Integer rp_time;
	private Date op_date;
	private String pbu_price;
	private String pau_price;
	private Date pbu_date;
	private Date pau_date;
	public String getPp_no() {
		return pp_no;
	}
	public void setPp_no(String pp_no) {
		this.pp_no = pp_no;
	}
	public String getP_no() {
		return p_no;
	}
	public void setP_no(String p_no) {
		this.p_no = p_no;
	}
	public String getOpc_acc() {
		return opc_acc;
	}
	public void setOpc_acc(String opc_acc) {
		this.opc_acc = opc_acc;
	}
	public Date getRp_date() {
		return rp_date;
	}
	public void setRp_date(Date rp_date) {
		this.rp_date = rp_date;
	}
	public Integer getRp_time() {
		return rp_time;
	}
	public void setRp_time(Integer rp_time) {
		this.rp_time = rp_time;
	}
	public Date getOp_date() {
		return op_date;
	}
	public void setOp_date(Date op_date) {
		this.op_date = op_date;
	}
	public String getPbu_price() {
		return pbu_price;
	}
	public void setPbu_price(String pbu_price) {
		this.pbu_price = pbu_price;
	}
	public String getPau_price() {
		return pau_price;
	}
	public void setPau_price(String pau_price) {
		this.pau_price = pau_price;
	}
	public Date getPbu_date() {
		return pbu_date;
	}
	public void setPbu_date(Date pbu_date) {
		this.pbu_date = pbu_date;
	}
	public Date getPau_date() {
		return pau_date;
	}
	public void setPau_date(Date pau_date) {
		this.pau_date = pau_date;
	}
}
