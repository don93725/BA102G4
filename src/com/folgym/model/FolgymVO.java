package com.folgym.model;

import java.sql.Date;

public class FolgymVO implements java.io.Serializable{
	private String fg_acc;
	private String fgp_acc;
	private Date fg_date;
	
	
	public String getFg_acc() {
		return fg_acc;
	}
	public void setFg_acc(String fg_acc) {
		this.fg_acc = fg_acc;
	}
	public String getFgp_acc() {
		return fgp_acc;
	}
	public void setFgp_acc(String fgp_acc) {
		this.fgp_acc = fgp_acc;
	}
	public Date getFg_date() {
		return fg_date;
	}
	public void setFg_date(Date fg_date) {
		this.fg_date = fg_date;
	}
	
	
	
	
	
	
	
	
}

