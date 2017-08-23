package com.folcoach.model;

import java.sql.Date;

public class FolcoachVO implements java.io.Serializable{
	private String fc_acc;
	private String fcp_acc;
	private Date fc_date;
	
	
	public String getFc_acc() {
		return fc_acc;
	}
	public void setFc_acc(String fc_acc) {
		this.fc_acc = fc_acc;
	}
	public String getFcp_acc() {
		return fcp_acc;
	}
	public void setFcp_acc(String fcp_acc) {
		this.fcp_acc = fcp_acc;
	}
	public Date getFc_date() {
		return fc_date;
	}
	public void setFc_date(Date fc_date) {
		this.fc_date = fc_date;
	}
	
	
	
	
	
	
	
}

