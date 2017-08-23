package com.folgym.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class FolgymDAO implements FolgymDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO folgym (fg_acc,fgp_acc,fg_date) VALUES (?, ?, ?)";
	private static final String GET_Folgym_ByFgp_acc_STMT = "SELECT fg_acc,fgp_acc,to_char(fg_date,'yyyy-mm-dd') fg_date FROM folgym where fgp_acc = ? order by fg_acc";
	private static final String DELETE = "DELETE FROM folgym where fg_acc = ? and where fgp_acc= ?";

	@Override
	public void insert(FolgymVO folgymVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, folgymVO.getFg_acc());
			pstmt.setString(2, folgymVO.getFgp_acc());
			pstmt.setDate(3, folgymVO.getFg_date());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	

	@Override
	public void delete(String fg_acc, String fgp_acc) {		

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, fg_acc);
			pstmt.setString(2, fgp_acc);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	

	

	@Override
	public Set<FolgymVO> getFolgymByFgp_acc(String fgp_acc) {
		Set<FolgymVO> set = new LinkedHashSet<FolgymVO>();
		FolgymVO folgymVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Folgym_ByFgp_acc_STMT);
			pstmt.setString(1, fgp_acc);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				folgymVO = new FolgymVO();
				folgymVO.setFg_acc(rs.getString("fg_acc"));
				folgymVO.setFgp_acc(rs.getString("fgp_acc"));
				folgymVO.setFg_date(rs.getDate("fg_date"));
				set.add(folgymVO); // Store the row in the vector
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return set;
	}
}