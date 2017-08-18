package com.board.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.album.domain.Photos;
import com.comments.model.Board_cmt;
import com.members.model.MembersVO;

public class Message_board {
	Integer bd_likes;
	String bd_msg_no,bd_type,bd_msg_ctx,bd_ref_ctx,bd_prvt,bd_ref_url;
	Date bd_msg_time,bd_upd_time;
	byte[] bd_film;
	List<Photos> photos;	
	MembersVO mem_no;
	boolean ifClick;
	List<Board_cmt> comments;
	
	public List<Board_cmt> getComments() {
		return comments;
	}
	public void setComments(List<Board_cmt> comments) {
		this.comments = comments;
	}
	public boolean isIfClick() {
		return ifClick;
	}
	public void setIfClick(boolean ifClick) {
		this.ifClick = ifClick;
	}
	public MembersVO getMem_no() {
		return mem_no;
	}
	public void setMem_no(MembersVO mem_no) {
		this.mem_no = mem_no;
	}
	
	public List<Photos> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photos> photos) {
		this.photos = photos;
	}
	public String getBd_ref_url() {
		return bd_ref_url;
	}
	public void setBd_ref_url(String bd_ref_url) {
		this.bd_ref_url = bd_ref_url;
	}
	public byte[] getBd_film() {
		return bd_film;
	}
	public void setBd_film(byte[] bd_film) {
		this.bd_film = bd_film;
	}
	public Integer getBd_likes() {
		return bd_likes;
	}
	public void setBd_likes(Integer bd_likes) {
		this.bd_likes = bd_likes;
	}
	public String getBd_msg_no() {
		return bd_msg_no;
	}
	public void setBd_msg_no(String bd_msg_no) {
		this.bd_msg_no = bd_msg_no;
	}
	public String getBd_type() {
		return bd_type;
	}
	public void setBd_type(String bd_type) {
		this.bd_type = bd_type;
	}
	public String getBd_msg_ctx() {
		return bd_msg_ctx;
	}
	public void setBd_msg_ctx(String bd_msg_ctx) {
		this.bd_msg_ctx = bd_msg_ctx;
	}
	public String getBd_ref_ctx() {
		return bd_ref_ctx;
	}
	public void setBd_ref_ctx(String bd_ref_ctx) {
		this.bd_ref_ctx = bd_ref_ctx;
	}
	public String getBd_prvt() {
		return bd_prvt;
	}
	public void setBd_prvt(String bd_prvt) {
		this.bd_prvt = bd_prvt;
	}
	public Date getBd_msg_time() {
		return bd_msg_time;
	}
	public void setBd_msg_time(Date bd_msg_time) {
		this.bd_msg_time = bd_msg_time;
	}
	public Date getBd_upd_time() {
		return bd_upd_time;
	}
	public void setBd_upd_time(Date bd_upd_time) {
		this.bd_upd_time = bd_upd_time;
	}
	
}
