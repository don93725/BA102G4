package com.folcoach.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class FolcoachDAO implements FolcoachDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO folcoach (fc_acc,fcp_acc,fc_date) VALUES (?, ?, ?)";
	private static final String GET_Folcoach_ByFcp_acc_STMT = "SELECT fc_acc,fcp_acc,to_char(fc_date,'yyyy-mm-dd') fc_date FROM folcoach where fcp_acc = ? order by fc_acc";
	private static final String DELETE = "DELETE FROM folcoach where fc_acc = ? and where fcp_acc= ?";
	
	@Override
	public void insert(FolcoachVO folcoachVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, folcoachVO.getFc_acc());
			pstmt.setString(2, folcoachVO.getFcp_acc());
			pstmt.setDate(2, folcoachVO.getFc_date());

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
	public void delete(String fc_acc, String fcp_acc) {		

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, fc_acc);
			pstmt.setString(2, fcp_acc);

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
	public Set<FolcoachVO> getFolcoachByFcp_acc(String fcp_acc) {
		Set<FolcoachVO> set = new LinkedHashSet<FolcoachVO>();
		FolcoachVO folcoachVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Folcoach_ByFcp_acc_STMT);
			pstmt.setString(1, fcp_acc);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				folcoachVO = new FolcoachVO();
				folcoachVO.setFc_acc(rs.getString("fc_acc"));
				folcoachVO.setFcp_acc(rs.getString("fcp_acc"));
				folcoachVO.setFc_date(rs.getDate("fc_date"));
				set.add(folcoachVO); // Store the row in the vector
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