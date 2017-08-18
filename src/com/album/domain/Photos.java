	package com.album.domain;

import java.util.Date;
import java.util.List;

import com.comments.model.Board_cmt;

public class Photos {

	String photo_no, al_no, photo_desc;
	byte[] photo, sphoto;
	Date ul_Date;
	String src;
	List<Board_cmt> comments;
	
	public List<Board_cmt> getComments() {
		return comments;
	}

	public void setComments(List<Board_cmt> comments) {
		this.comments = comments;
	}

	public Date getUl_Date() {
		return ul_Date;
	}

	public void setUl_Date(Date ul_Date) {
		this.ul_Date = ul_Date;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getPhoto_desc() {
		return photo_desc;
	}

	public void setPhoto_desc(String photo_desc) {
		this.photo_desc = photo_desc;
	}

	public String getPhoto_no() {
		return photo_no;
	}

	public void setPhoto_no(String photo_no) {
		this.photo_no = photo_no;
	}


	public String getAl_no() {
		return al_no;
	}

	public void setAl_no(String al_no) {
		this.al_no = al_no;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public byte[] getSphoto() {
		return sphoto;
	}

	public void setSphoto(byte[] sphoto) {
		this.sphoto = sphoto;
	}

}
