package com.forums.model;

import java.sql.Connection;
import java.sql.DriverManager;
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

public class ForumsJNDIDAO implements ForumsDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT =
			"INSERT INTO FORUMS VALUES(FORUMS_PK_SEQ.NEXTVAL,?,?,?,?,DEFAULT,DEFAULT,DEFAULT,SYSDATE)";
	private static final String GET_ALL =
			"SELECT TO_NUMBER(FORUM_no,'9999') FORUM_NO,MEM_NO,FORUM_NAME,FORUM_DESC,FORUM_NOTE,FORUM_STAT,FORUM_VIEWS,FORUM_MVIEWS,FORUM_DATE FROM FORUMS order by FORUM_NO"; 
	private static final String GET_ONE = 
			"SELECT  FORUM_NO,MEM_NO,FORUM_NAME,FORUM_DESC,FORUM_NOTE,FORUM_STAT,FORUM_VIEWS,FORUM_MVIEWS,FORUM_DATE FROM FORUMS WHERE FORUM_NO=?"; 
	private static final String GET_STAT = 
			"SELECT TO_NUMBER(FORUM_no,'9999') FORUM_NO,MEM_NO,FORUM_NAME,FORUM_DESC,FORUM_NOTE,FORUM_STAT,FORUM_VIEWS,FORUM_MVIEWS,FORUM_DATE FROM FORUMS WHERE FORUM_STAT=?"; 
	private static final String DELETE = 
			"DELETE FROM FORUMS where FORUM_NO = ?";
	private static final String UPDATE = 
			"UPDATE FORUMS set FORUM_STAT=? where FORUM_NO = ?";

	@Override
	public void insert(ForumsVO forumsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, forumsVO.getMem_no());
			pstmt.setString(2, forumsVO.getForum_name());
			pstmt.setString(3, forumsVO.getForum_desc());
			pstmt.setString(4, forumsVO.getForum_note());

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
	public void update(ForumsVO forumsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, forumsVO.getForum_stat());
			pstmt.setString(2, forumsVO.getForum_no());
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
	public void delete(String forum_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, forum_no);

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
	public ForumsVO findByPrimaryKey(String forum_no) {
		ForumsVO forumsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);

			pstmt.setString(1, forum_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// membersVo 也稱為 Domain objects
				forumsVO = new ForumsVO();
				forumsVO.setForum_no(rs.getString("forum_no"));
				forumsVO.setMem_no(rs.getString("mem_no"));
				forumsVO.setForum_name(rs.getString("forum_name"));
				forumsVO.setForum_desc(rs.getString("forum_desc"));
				forumsVO.setForum_note(rs.getString("forum_note"));
				forumsVO.setForum_stat(rs.getString("forum_stat"));
				forumsVO.setForum_views(rs.getInt("forum_views"));
				forumsVO.setForum_mviews(rs.getInt("forum_mviews"));
				forumsVO.setForum_date(rs.getDate("forum_date"));
				
			}

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
		return forumsVO;	
	}

	@Override
	public List<ForumsVO> getAll() {
		List<ForumsVO> list = new ArrayList<ForumsVO>();
		ForumsVO forumsVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				forumsVO = new ForumsVO();
				forumsVO.setForum_no(rs.getString("forum_no"));
				forumsVO.setMem_no(rs.getString("mem_no"));
				forumsVO.setForum_name(rs.getString("forum_name"));
				forumsVO.setForum_desc(rs.getString("forum_desc"));
				forumsVO.setForum_note(rs.getString("forum_note"));
				forumsVO.setForum_stat(rs.getString("forum_stat"));
				forumsVO.setForum_views(rs.getInt("forum_views"));
				forumsVO.setForum_mviews(rs.getInt("forum_mviews"));
				forumsVO.setForum_date(rs.getDate("forum_date"));
				list.add(forumsVO); // Store the row in the list
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
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
	public Set<ForumsVO> getStat(String forum_stat) {
		Set<ForumsVO> set = new LinkedHashSet<ForumsVO>();
		ForumsVO forumsVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_STAT);
			pstmt.setString(1, forum_stat);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				forumsVO = new ForumsVO();
				forumsVO.setForum_no(rs.getString("forum_no"));
				forumsVO.setMem_no(rs.getString("mem_no"));
				forumsVO.setForum_name(rs.getString("forum_name"));
				forumsVO.setForum_desc(rs.getString("forum_desc"));
				forumsVO.setForum_note(rs.getString("forum_note"));
				forumsVO.setForum_stat(rs.getString("forum_stat"));
				forumsVO.setForum_views(rs.getInt("forum_views"));
				forumsVO.setForum_mviews(rs.getInt("forum_mviews"));
				forumsVO.setForum_date(rs.getDate("forum_date"));
				
				set.add(forumsVO); // Store the row in the vector
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
		return set;
	}

}
