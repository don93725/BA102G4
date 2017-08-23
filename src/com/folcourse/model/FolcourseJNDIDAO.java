package com.folcourse.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class FolcourseJNDIDAO implements FolcourseDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO folcourse (fcrs_no,fcrsp_acc,fcrs_date) VALUES (?, ?, ?)";
	private static final String GET_Folcourse_ByFcrsp_acc_STMT = "SELECT fcrs_no,fcrsp_acc,to_char(fcrs_date,'yyyy-mm-dd') fcrs_date FROM folcourse where fcrsp_acc = ? order by fcrs_no";
	private static final String DELETE = "DELETE FROM folcoach where fcrs_no = ? and where fcrsp_acc=?";

	@Override
	public void insert(FolcourseVO folcourseVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, folcourseVO.getFcrs_no());
			pstmt.setString(2, folcourseVO.getFcrsp_acc());
			pstmt.setDate(3, folcourseVO.getFcrs_date());

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
	public void delete(String fcrs_no, String fcrsp_acc) {		

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, fcrs_no);
			pstmt.setString(2, fcrsp_acc);
			
			
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
	public Set<FolcourseVO> getFolcourseByFcrsp_acc(String fcrsp_acc) {
		Set<FolcourseVO> set = new LinkedHashSet<FolcourseVO>();
		FolcourseVO folcourseVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Folcourse_ByFcrsp_acc_STMT);
			pstmt.setString(1, fcrsp_acc);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				folcourseVO = new FolcourseVO();
				folcourseVO.setFcrs_no(rs.getString("fcrs_no"));
				folcourseVO.setFcrsp_acc(rs.getString("fcrsp_acc"));
				folcourseVO.setFcrs_date(rs.getDate("fcrs_date"));
				set.add(folcourseVO); // Store the row in the vector
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