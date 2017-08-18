package com.message.model;

import java.util.Date;

import com.members.model.MembersVO;

public class Message {

String msg_no;
String msg_ctx,if_read;
Date send_time;
MembersVO rcv_no,post_no;
String day,date;
Integer nr;


public String getDay() {
	return day;
}
public void setDay(String day) {
	this.day = day;
}
public Integer getNr() {
	return nr;
}
public void setNr(Integer nr) {
	this.nr = nr;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getIf_read() {
	return if_read;
}
public void setIf_read(String if_read) {
	this.if_read = if_read;
}
public String getMsg_no() {
	return msg_no;
}
public void setMsg_no(String msg_no) {
	this.msg_no = msg_no;
}

public MembersVO getRcv_no() {
	return rcv_no;
}
public void setRcv_no(MembersVO rcv_no) {
	this.rcv_no = rcv_no;
}
public MembersVO getPost_no() {
	return post_no;
}
public void setPost_no(MembersVO post_no) {
	this.post_no = post_no;
}
public String getMsg_ctx() {
	return msg_ctx;
}
public void setMsg_ctx(String msg_ctx) {
	this.msg_ctx = msg_ctx;
}
public Date getSend_time() {
	return send_time;
}
public void setSend_time(Date send_time) {
	this.send_time = send_time;
}

}
