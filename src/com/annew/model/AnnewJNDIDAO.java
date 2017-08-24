package com.annew.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class AnnewJNDIDAO implements AnnewDAO_interface {

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
		"INSERT INTO annew (ann_no,ann_date,upd_date,ann_title,ann_ctx,ann_photo,att_no) VALUES (annew_seq.NEXTVAL, default, null, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT ann_no,to_char(ann_date,'yyyy-mm-dd')ann_date,to_char(upd_date,'yyyy-mm-dd')upd_date,ann_title,ann_ctx,ann_photo,att_no FROM annew order by ann_no";
	private static final String GET_ONE_STMT = 
		"SELECT ann_no,to_char(ann_date,'yyyy-mm-dd')ann_date,to_char(upd_date,'yyyy-mm-dd')upd_date,ann_title,ann_ctx,ann_photo,att_no FROM annew where ann_no = ?";
	private static final String FIND_BY_TITLE_STMT = 
		"SELECT ann_no,to_char(ann_date,'yyyy-mm-dd')ann_date,to_char(upd_date,'yyyy-mm-dd')upd_date,ann_title,ann_ctx,ann_photo,att_no FROM annew where ";
	private static final String FIND_BY_DATE_STMT = 
		"SELECT ann_no,to_char(ann_date,'yyyy-mm-dd')ann_date,to_char(upd_date,'yyyy-mm-dd')upd_date,ann_title,ann_ctx,ann_photo,att_no FROM annew where to_char(ann_date,'yyyy-mm-dd')= ?";
	private static final String DELETE = 
		"DELETE FROM annew where ann_no = ?";
	private static final String UPDATE = 
		"UPDATE annew set upd_date=sysdate,ann_title=?,ann_ctx=?,ann_photo=?,att_no=? where ann_no = ?";

	@Override
	public void insert(AnnewVO annewVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, annewVO.getAnn_title());
			pstmt.setString(2, annewVO.getAnn_ctx());
			pstmt.setBytes(3, annewVO.getAnn_photo());
			pstmt.setBytes(4, annewVO.getAtt_no());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(AnnewVO annewVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, annewVO.getAnn_title());
			pstmt.setString(2, annewVO.getAnn_ctx());
			pstmt.setBytes(3, annewVO.getAnn_photo());
			pstmt.setBytes(4, annewVO.getAtt_no());
			pstmt.setString(5, annewVO.getAnn_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String ann_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, ann_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public AnnewVO findByPrimaryKey(String ann_no) {

		AnnewVO annewVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ann_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				annewVO = new AnnewVO();
				annewVO.setAnn_no(rs.getString("ann_no"));
				annewVO.setAnn_date(rs.getDate("ann_date"));
				annewVO.setUpd_date(rs.getDate("upd_date"));
				annewVO.setAnn_title(rs.getString("ann_title"));
				annewVO.setAnn_ctx(rs.getString("ann_ctx"));
				annewVO.setAnn_photo(rs.getBytes("ann_photo"));
				annewVO.setAtt_no(rs.getBytes("att_no"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return annewVO;
	}

	public List<AnnewVO> getPartByTitle(String title) {

		AnnewVO annewVO = null;
		Connection con = null;
		List<AnnewVO> list = new ArrayList<AnnewVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			String sql = FIND_BY_TITLE_STMT + " ann_title like '%"+ title +"%'";			
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				annewVO = new AnnewVO();
				annewVO.setAnn_no(rs.getString("ann_no"));
				annewVO.setAnn_date(rs.getDate("ann_date"));
				annewVO.setUpd_date(rs.getDate("upd_date"));
				annewVO.setAnn_title(rs.getString("ann_title"));
				annewVO.setAnn_ctx(rs.getString("ann_ctx"));
				annewVO.setAnn_photo(rs.getBytes("ann_photo"));
				annewVO.setAtt_no(rs.getBytes("att_no"));
				list.add(annewVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<AnnewVO> getAll() {
		List<AnnewVO> list = new ArrayList<AnnewVO>();
		AnnewVO annewVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// annewVO 也稱為 Domain objects
				annewVO = new AnnewVO();
				annewVO.setAnn_no(rs.getString("ann_no"));
				annewVO.setAnn_date(rs.getDate("ann_date"));
				annewVO.setUpd_date(rs.getDate("upd_date"));
				annewVO.setAnn_title(rs.getString("ann_title"));
				annewVO.setAnn_ctx(rs.getString("ann_ctx"));
				annewVO.setAnn_photo(rs.getBytes("ann_photo"));
				annewVO.setAtt_no(rs.getBytes("att_no"));
				list.add(annewVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<AnnewVO> getPartByDate(String ann_date) {
		List<AnnewVO> list = new ArrayList<AnnewVO>();
		AnnewVO annewVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			System.out.println(ann_date);
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_DATE_STMT);
			pstmt.setString(1, ann_date);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// fitkwVO 也稱為 Domain objects
				annewVO = new AnnewVO();
				annewVO.setAnn_no(rs.getString("ann_no"));
				annewVO.setAnn_date(rs.getDate("ann_date"));
				annewVO.setUpd_date(rs.getDate("upd_date"));
				annewVO.setAnn_title(rs.getString("ann_title"));
				annewVO.setAnn_ctx(rs.getString("ann_ctx"));
				annewVO.setAnn_photo(rs.getBytes("ann_photo"));
				annewVO.setAtt_no(rs.getBytes("att_no"));
				list.add(annewVO); // Store the row in the list
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
	public void updateTwo(AnnewVO annewVO) {
		// TODO Auto-generated method stub
		
	}

}