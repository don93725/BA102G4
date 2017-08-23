package com.place_publish.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.don.util.SQLHelper;

public class Place_PublishDAO implements Place_PublishDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_PP =
			"Insert into place_publish(pp_no, p_no, opc_acc, rp_date, rp_time, op_date, pbu_price, pau_price, pbu_date, pau_date)"
			+ "values(place_publish_sq.NEXTVAL, ?, null, null, null, null,  ?, ? ,null, null)";
	@Override
	public void insert(Place_PublishVO place_publishVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(INSERT_PP);
			pstmt.setString(1, place_publishVO.getP_no());
			pstmt.setString(2, place_publishVO.getPbu_price());
			pstmt.setString(3, place_publishVO.getPau_price());

			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			
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
	}

}
