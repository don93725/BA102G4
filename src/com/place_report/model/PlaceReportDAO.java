package com.place_report.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.membersreport.model.MembersReportVO;

public class PlaceReportDAO implements PlaceReportDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_PR =
			"INSERT INTO PLACE_REPORT VALUES(PLACE_REPORT_PK_SEQ.NEXTVAL,?,?,?,SYSDATE,DEFAULT,?,?)";
	
	private static final String GET_ALL =
			"SELECT TO_NUMBER(PR_NO,'99999') PR_NO,P_NO,MEM_NO,PR_CTX,PR_TIME,PR_STAT,REF_CTX,PR_PT FROM PLACE_REPORT order by PR_NO"; 
	private static final String GET_STAT_PR = 
			"SELECT TO_NUMBER(PR_NO,'99999') PR_NO,P_NO,MEM_NO,PR_CTX,PR_TIME,PR_STAT,REF_CTX,PR_PT FROM PLACE_REPORT where PR_STAT = ?";
	
	
	private static final String UPDATE = 
			"UPDATE PLACE_REPORT set pr_stat=1 where PR_NO = ?";
//	private static final String UPDATE_MEM_PR_NUM=
//			"UPDATE MEMBERS SET MR_NUM=MR_NUM+1 WHERE MEM_NO=(select MEM_NO from PLACE_report where Pr_no=?)";
//	
	private static final String UPDATE_MEM_PR_NUM=
			"UPDATE MEMBERS SET MR_NUM=MR_NUM+1 WHERE MEM_acc=(select g_acc from PLACE where P_no=?)";

	@Override
	public void insert(PlaceReportVO placeReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_PR);

			pstmt.setString(1, placeReportVO.getP_no());
			pstmt.setString(2, placeReportVO.getMem_no());
			pstmt.setString(3, placeReportVO.getPr_ctx());
			pstmt.setString(4, placeReportVO.getRef_ctx());
			pstmt.setBytes(5, placeReportVO.getPr_pt());

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
	public void update(PlaceReportVO placeReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
System.out.println("DAO:~~update  "+placeReportVO.getPr_no());
			pstmt.setString(1, placeReportVO.getPr_no());
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
	public void updatePRNum(PlaceReportVO placeReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_MEM_PR_NUM);

			pstmt.setString(1, placeReportVO.getP_no());
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
	public List<PlaceReportVO> getAll() {
		List<PlaceReportVO> list = new ArrayList<PlaceReportVO>();
		PlaceReportVO placeReportVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				placeReportVO = new PlaceReportVO();
				placeReportVO.setPr_no(rs.getString("pr_no"));
				placeReportVO.setP_no(rs.getString("p_no"));
				placeReportVO.setMem_no(rs.getString("mem_no"));
				placeReportVO.setPr_ctx(rs.getString("pr_ctx"));
				placeReportVO.setPr_time(rs.getDate("pr_time"));
				placeReportVO.setPr_stat(rs.getInt("pr_stat"));
				placeReportVO.setRef_ctx(rs.getString("ref_ctx"));
				placeReportVO.setPr_pt(rs.getBytes("pr_pt"));
				list.add(placeReportVO); // Store the row in the list
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
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
	public List<PlaceReportVO> getStat(Integer pr_stat) {
		List<PlaceReportVO> list = new ArrayList<PlaceReportVO>();
		PlaceReportVO placeReportVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_STAT_PR);
			pstmt.setInt(1, pr_stat);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				placeReportVO = new PlaceReportVO();
				placeReportVO.setPr_no(rs.getString("pr_no"));
				placeReportVO.setP_no(rs.getString("p_no"));
				placeReportVO.setMem_no(rs.getString("mem_no"));
				placeReportVO.setPr_ctx(rs.getString("pr_ctx"));
				placeReportVO.setPr_time(rs.getDate("pr_time"));
				placeReportVO.setPr_stat(rs.getInt("pr_stat"));
				placeReportVO.setRef_ctx(rs.getString("ref_ctx"));
				placeReportVO.setPr_pt(rs.getBytes("pr_pt"));
				list.add(placeReportVO); // Store the row in the vector
			}
	
			// Handle any driver errors
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
		return list;
	}

}
