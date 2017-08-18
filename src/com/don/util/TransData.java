package com.don.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;

import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;




public class TransData {
	public static byte[] transBlob(Part part) throws IOException{
		Blob blob=null;
		InputStream in = part.getInputStream();
		byte[] b = new byte[in.available()];
		in.read(b);				
		return b;
	}
	public static Clob transClob(String s) throws SerialException, SQLException{
		Clob clob=new SerialClob(s.toCharArray());
		
			
		return clob;
	}
}
