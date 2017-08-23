package com.place_pic.model;

import java.util.List;

public interface Place_PicDAO_interface {
	public byte[] getPlacePic(String p_pic_no);
	public List getAllPPic(String p_no);
}
