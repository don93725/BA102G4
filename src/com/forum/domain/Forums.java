package com.forum.domain;

import java.util.Date;

public class Forums  {

Integer forum_stat,forum_views,forum_mviews;
String forum_no,mem_no,forum_name,forum_desc,forum_note;
Date forum_date;
public Integer getForum_stat() {
	return forum_stat;
}
public void setForum_stat(Integer forum_stat) {
	this.forum_stat = forum_stat;
}
public Integer getForum_views() {
	return forum_views;
}
public void setForum_views(Integer forum_views) {
	this.forum_views = forum_views;
}
public Integer getForum_mviews() {
	return forum_mviews;
}
public void setForum_mviews(Integer forum_mviews) {
	this.forum_mviews = forum_mviews;
}
public String getForum_no() {
	return forum_no;
}
public void setForum_no(String forum_no) {
	this.forum_no = forum_no;
}
public String getMem_no() {
	return mem_no;
}
public void setMem_no(String mem_no) {
	this.mem_no = mem_no;
}
public String getForum_name() {
	return forum_name;
}
public void setForum_name(String forum_name) {
	this.forum_name = forum_name;
}
public String getForum_desc() {
	return forum_desc;
}
public void setForum_desc(String forum_desc) {
	this.forum_desc = forum_desc;
}
public String getForum_note() {
	return forum_note;
}
public void setForum_note(String forum_note) {
	this.forum_note = forum_note;
}
public Date getForum_date() {
	return forum_date;
}
public void setForum_date(Date forum_date) {
	this.forum_date = forum_date;
}

}
