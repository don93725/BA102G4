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
	
	private static final String INSERT_PT =
			"Insert into place_time(pt_no, p_no, opc_acc, rp_date, rp_time, op_date,"
			+ " pbu_price, pau_price, pbu_date, pau_date, report, eva, eva_ct)"
			+ "values(PLACE_time_sq.NEXTVAL, ?, null, null, null, null,  ?, ? ,null, null, default,"
			+ " default, null)";
	private static final String UPDATE_PUBLIS_STAT =
			"Update place set p_status = 1 where p_no = ?";
	private static final String DELETE_PT = 
			"Delete place_time where p_no = ?";
	private static final String UNPUBLISH_PUBLIS_STAT =
			"Update place set p_status = 0 where p_no = ?";
	
	@Override
	public void insert(Place_PublishVO place_publishVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(INSERT_PT);
			pstmt.setString(1, place_publishVO.getP_no());
			pstmt.setString(2, place_publishVO.getPbu_price());
			pstmt.setString(3, place_publishVO.getPau_price());
			pstmt.executeUpdate();
			System.out.println("insert publish complete");
			pstmt2 = con.prepareStatement(UPDATE_PUBLIS_STAT);
			pstmt2.setString(1, place_publishVO.getP_no());
			pstmt2.executeUpdate();
			System.out.println("update place_status complete");
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
	@Override
	public void unPublish(String p_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(DELETE_PT);
			pstmt.setString(1, p_no);
			pstmt.executeUpdate();
			pstmt2 = con.prepareStatement(UNPUBLISH_PUBLIS_STAT);
			pstmt2.setString(1, p_no);
			pstmt2.executeUpdate();
			System.out.println("delete pt and update place_status complete");
			
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
