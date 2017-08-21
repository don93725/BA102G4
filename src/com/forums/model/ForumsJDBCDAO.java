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

import com.members.model.MembersVO;


public class ForumsJDBCDAO implements ForumsDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "jdbc";
//	String userid = "JDBC";
	String passwd = "123";
	
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, forumsVO.getMem_no());
			pstmt.setString(2, forumsVO.getForum_name());
			pstmt.setString(3, forumsVO.getForum_desc());
			pstmt.setString(4, forumsVO.getForum_note());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void delete(String forum_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, forum_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void update(ForumsVO forumsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, forumsVO.getForum_stat());
			pstmt.setString(2, forumsVO.getForum_no());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public ForumsVO findByPrimaryKey(String forum_no) {
		ForumsVO forumsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	
	public static void main(String[] args) {
		ForumsJDBCDAO dao = new ForumsJDBCDAO();
		// 新增
//		ForumsVO forumsVO1 = new ForumsVO();
//		forumsVO1.setMem_no("5");
//		forumsVO1.setForum_name("微笑版");
//		forumsVO1.setForum_desc("你今天微笑了嗎?");
//		forumsVO1.setForum_note("微笑面具");
//		dao.insert(forumsVO1);
		
		// 修改
		ForumsVO forumsVO2 = new ForumsVO();
		forumsVO2.setForum_stat("1");
		forumsVO2.setForum_no("1");
		dao.update(forumsVO2);

		// 刪除
//		dao.delete("4");
		
		// 查詢 one
		ForumsVO forumsVO3 = dao.findByPrimaryKey("3");
		System.out.print(forumsVO3.getForum_no() + ",");
		System.out.print(forumsVO3.getMem_no() + ",");
		System.out.print(forumsVO3.getForum_name() + ",");
		System.out.print(forumsVO3.getForum_desc() + ",");
		System.out.print(forumsVO3.getForum_note() + ",");
		System.out.print(forumsVO3.getForum_stat() + ",");
		System.out.print(forumsVO3.getForum_views() + ",");
		System.out.print(forumsVO3.getForum_mviews() + ",");
		System.out.println(forumsVO3.getForum_date());
		System.out.println("---------------------");

		// 查詢stat
		Set<ForumsVO> set = dao.getStat("0");
		for (ForumsVO aStat : set) {
			System.out.print(aStat.getForum_no() + ",");
			System.out.print(aStat.getMem_no() + ",");
			System.out.print(aStat.getForum_name() + ",");
			System.out.print(aStat.getForum_desc() + ",");
			System.out.print(aStat.getForum_note() + ",");
			System.out.print(aStat.getForum_stat() + ",");
			System.out.print(aStat.getForum_views() + ",");
			System.out.print(aStat.getForum_mviews() + ",");
			System.out.print(aStat.getForum_date());
			System.out.println();
		}
		System.out.println("---------------------");
		// 查詢
		List<ForumsVO> list = dao.getAll();
		for (ForumsVO aForums : list) {
			System.out.print(aForums.getForum_no() + ",");
			System.out.print(aForums.getMem_no() + ",");
			System.out.print(aForums.getForum_name() + ",");
			System.out.print(aForums.getForum_desc() + ",");
			System.out.print(aForums.getForum_note() + ",");
			System.out.print(aForums.getForum_stat() + ",");
			System.out.print(aForums.getForum_views() + ",");
			System.out.print(aForums.getForum_mviews() + ",");
			System.out.print(aForums.getForum_date());
			System.out.println();
		}
	}

	

	

}
