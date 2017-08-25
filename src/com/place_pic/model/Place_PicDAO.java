package com.place_pic.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.place.model.PlaceVO;

public class Place_PicDAO implements Place_PicDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String PLACE_PIC_LIST =
			"Select * from place_picture where p_pic_no = ?";
	private static final String FIND_PLACE_PIC =
			"Select * from place_picture where p_no = ?";
	@Override
	public byte[] getPlacePic(String p_pic_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] bytes = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(PLACE_PIC_LIST);
			pstmt.setString(1, p_pic_no);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Base64.Decoder decoder = Base64.getDecoder();
				bytes = decoder.decode(rs.getString("p_base"));
			}
		} catch(SQLException se) {
			try {
				se.printStackTrace();
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return bytes;
	}

	@Override
	public List getAllPPic(String p_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List pic_noList = new ArrayList();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_PLACE_PIC);
			pstmt.setString(1, p_no);
			rs = pstmt.executeQuery();
			while(rs.next()){
				pic_noList.add(rs.getString("p_base"));
			}
		} catch(SQLException se) {
			try {
				se.printStackTrace();
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return pic_noList;
	}
}
