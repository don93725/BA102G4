package com.platinf.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PlatinfJNDIDAO implements PlatinfDAO_interface {

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

	    private static final String INSERT_STMT = 
			"INSERT INTO platinf (pin_no,com_address,cp_no,cs_email,pr_policy,upd_date,pin_photo) VALUES (platinf_seq.NEXTVAL, ?, ?, ?, ?, default, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT pin_no,com_address,cp_no,cs_email,pr_policy,to_char(upd_date,'yyyy-mm-dd') upd_date,pin_photo FROM platinf order by pin_no";
		private static final String GET_ONE_STMT = 
			"SELECT pin_no,com_address,cp_no,cs_email,pr_policy,to_char(upd_date,'yyyy-mm-dd') upd_date,pin_photo FROM platinf where pin_no = ?";
		private static final String DELETE = 
			"DELETE FROM platinf where pin_no = ?";
		private static final String UPDATE = 
			"UPDATE platinf set com_address=?,cp_no=?,cs_email=?,pr_policy=?,upd_date=sysdate,pin_photo where pin_no = ?";
		
	@Override
	public void insert(PlatinfVO platinfVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, platinfVO.getCom_address());
			pstmt.setString(2, platinfVO.getCp_no());
			pstmt.setString(3, platinfVO.getCs_email());
			pstmt.setString(4, platinfVO.getPr_policy());
			pstmt.setBytes(5, platinfVO.getPin_photo());

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
	public void update(PlatinfVO platinfVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, platinfVO.getCom_address());
			pstmt.setString(2, platinfVO.getCp_no());
			pstmt.setString(3, platinfVO.getCs_email());
			pstmt.setString(4, platinfVO.getPr_policy());
			pstmt.setBytes(5, platinfVO.getPin_photo());
			pstmt.setString(6, platinfVO.getPin_no());

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
	public void delete(String pin_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, pin_no);

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
	public PlatinfVO findByPrimaryKey(String pin_no) {

		PlatinfVO platinfVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, pin_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				platinfVO = new PlatinfVO();
				platinfVO.setPin_no(rs.getString("pin_no"));
				platinfVO.setCom_address(rs.getString("com_address"));
				platinfVO.setCp_no(rs.getString("cp_no"));
				platinfVO.setCs_email(rs.getString("cs_email"));
				platinfVO.setPr_policy(rs.getString("pr_policy"));
				platinfVO.setUpd_date(rs.getDate("upd_date"));
				platinfVO.setPin_photo(rs.getBytes("pin_photo"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return platinfVO;
	}

	@Override
	public List<PlatinfVO> getAll() {
		List<PlatinfVO> list = new ArrayList<PlatinfVO>();
		PlatinfVO platinfVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// platinfVO 也稱為 Domain objects
				platinfVO = new PlatinfVO();
				platinfVO.setPin_no(rs.getString("pin_no"));
				platinfVO.setCom_address(rs.getString("com_address"));
				platinfVO.setCp_no(rs.getString("cp_no"));
				platinfVO.setCs_email(rs.getString("cs_email"));
				platinfVO.setPr_policy(rs.getString("pr_policy"));
				platinfVO.setUpd_date(rs.getDate("upd_date"));
				platinfVO.setPin_photo(rs.getBytes("pin_photo"));
				list.add(platinfVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return list;
	}

	@Override
	public void updateTwo(PlatinfVO platinfVO) {
		// TODO Auto-generated method stub
		
	}
}
