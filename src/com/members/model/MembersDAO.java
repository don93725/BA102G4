package com.members.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.coaches.model.CoachesVO;
import com.comments.model.Board_cmt;
import com.comments.model.Board_cmtDAO;

public class MembersDAO implements MembersDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
			
	private static final String LOGIN_MEM = "Select * from members where mem_acc = ?";
	private static final String INSERT_CK = "Select mem_acc from members where mem_acc = ?";
	private static final String UPDATE_MEM = 
			"Update members set mem_nickname = ? where mem_acc = ?";
	private static final String LOOK_SEARCH_MEM =
			"Select * from members where mem_no = ?";
	
	@Override
	public MembersVO select(String mem_acc) {
		MembersVO membersVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(LOGIN_MEM);
			
			pstmt.setString(1, mem_acc);
			rs = pstmt.executeQuery();
			System.out.println("(MembersDAO)mem_acc=" + mem_acc);
			
			while(rs.next()) {
				membersVO = new MembersVO();
				membersVO.setMem_no(rs.getString("mem_no"));
				membersVO.setMem_acc(rs.getString("mem_acc"));
				membersVO.setMem_rank(rs.getString("mem_rank"));
				membersVO.setMem_nickname(rs.getString("mem_nickname"));
				membersVO.setMr_num(rs.getInt("mr_num"));
			}
			
			System.out.println("VO=" + membersVO.getMem_no() + " VO=" + membersVO.getMem_acc());
			return membersVO;
		} catch (SQLException se) {
			System.out.println("AAA");
			se.printStackTrace();
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
	}
	
	public Boolean insert_ck(String mem_acc) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_CK);
			
			pstmt.setString(1, mem_acc);
			System.out.println("pstmt="+pstmt.toString());
			rs = pstmt.executeQuery();
			if(rs.next()){
				return true;
			}else{
				return false;
			}
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
	}

	public void update(MembersVO membersVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_MEM);
			con.setAutoCommit(false);
			
			pstmt.setString(1, membersVO.getMem_nickname());
			pstmt.setString(2, membersVO.getMem_acc());
			
			pstmt.executeUpdate();			
			con.commit();
			con.setAutoCommit(true);
		}catch(SQLException se) {
			try {
				se.printStackTrace();
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
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

	public MembersVO look_search_mem(String coa_no) {
		MembersVO membersVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(LOOK_SEARCH_MEM);
			
			pstmt.setString(1, coa_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				membersVO = new MembersVO();
				membersVO.setMem_no(rs.getString("mem_no"));
				membersVO.setMem_acc(rs.getString("mem_acc"));
				membersVO.setMem_rank(rs.getString("mem_rank"));
				membersVO.setMem_nickname(rs.getString("mem_nickname"));
				membersVO.setMr_num(rs.getInt("mr_num"));
			}
						
			return membersVO;
		} catch (SQLException se) {
			se.printStackTrace();
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
	}
	public MembersVO getPersonalComments(String coa_no) {
		MembersVO membersVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(LOOK_SEARCH_MEM);
			
			pstmt.setString(1, coa_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				membersVO = new MembersVO();
				membersVO.setMem_no(rs.getString("mem_no"));
				membersVO.setMem_acc(rs.getString("mem_acc"));
				membersVO.setMem_rank(rs.getString("mem_rank"));
				membersVO.setMem_nickname(rs.getString("mem_nickname"));
				membersVO.setMr_num(rs.getInt("mr_num"));
				List<Board_cmt> comments = new Board_cmtDAO().pageAndRank("2", rs.getString("mem_no"));
				membersVO.setComments(comments);
			}
						
			return membersVO;
		} catch (SQLException se) {
			se.printStackTrace();
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
	}
}
