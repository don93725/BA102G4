package com.tools;

import java.util.Base64;

public class toolBase64 {

	//編碼
	public String encoderPwd(String pwd){
		
		byte[] text = pwd.getBytes();
		Base64.Encoder encoder = Base64.getEncoder();
		String encoderPwd = encoder.encodeToString(text);
		
		return encoderPwd;
	}

	//解碼
	public String decoderPwd(String encoderPwd){
		
		Base64.Decoder decoder = Base64.getDecoder();
		String pwd = new String(decoder.decode(encoderPwd));
		
		return pwd;
	}
	
	//圖轉64
	public String encoderPic(byte[] pic){
		Base64.Encoder encoder = Base64.getEncoder();
		String encoderPic = encoder.encodeToString(pic);
		return encoderPic;
	}
	
	//解64圖
	public byte[] decoderPic(String encoderPic){
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] pic = decoder.decode(encoderPic);
		return pic;
	}
}
