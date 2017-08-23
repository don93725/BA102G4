package com.folplace.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class FolplaceDAO implements FolplaceDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO folplace (fp_no,fpp_acc,fp_date) VALUES (?, ?, ?)";
	private static final String GET_Folplace_ByFpp_acc_STMT = "SELECT fp_no,fpp_acc,to_char(fp_date,'yyyy-mm-dd') fp_date FROM folplace where fpp_acc = ? order by fp_no";
	private static final String DELETE = "DELETE FROM folplace where fp_no = ? and fpp_acc=?";
	
	@Override
	public void insert(FolplaceVO folplaceVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, folplaceVO.getFp_no());
			pstmt.setString(2, folplaceVO.getFpp_acc());
			pstmt.setDate(3, folplaceVO.getFp_date());

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
	public void delete(String fp_no, String fpp_acc) {		

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, fp_no);
			pstmt.setString(2, fpp_acc);
			

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
	public Set<FolplaceVO> getFolplaceByFpp_acc(String fpp_acc) {
		Set<FolplaceVO> set = new LinkedHashSet<FolplaceVO>();
		FolplaceVO folplaceVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Folplace_ByFpp_acc_STMT);
			pstmt.setString(1, fpp_acc);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				folplaceVO = new FolplaceVO();
				folplaceVO.setFp_no(rs.getString("fp_no"));
				folplaceVO.setFpp_acc(rs.getString("fpp_acc"));
				folplaceVO.setFp_date(rs.getDate("fp_date"));
				set.add(folplaceVO); // Store the row in the vector
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
