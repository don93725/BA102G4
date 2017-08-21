package com.Course.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CourseJDBCDAO implements CourseDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "jdbc";
//	 String userid = "JDBC";
	String passwd = "123";
	private static final String GET_COURSE = "SELECT * FROM COURSE WHERE CRS_NO=(SELECT CRS_NO FROM COURSE_TIME WHERE CT_NO=(SELECT CT_NO FROM COURSE_LIST WHERE CT_NO=?))";

	@Override
	public void insert(CourseVO courseVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(CourseVO courseVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String crs_no) {
		// TODO Auto-generated method stub

	}

	@Override
	public CourseVO findByPK(String crs_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CourseVO> getAll(String c_acc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CourseVO fingByCourse(String ct_no) {
		CourseVO courseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_COURSE);
			pstmt.setString(1,ct_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				courseVO = new CourseVO();
				courseVO.setCrs_no(rs.getString("crs_no"));
				courseVO.setC_acc(rs.getString("c_acc"));
				courseVO.setCrs_name(rs.getString("crs_name"));
				courseVO.setDetails(rs.getString("details"));
				courseVO.setCategory(rs.getString("category"));
				courseVO.setStatus(rs.getInt("status"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return courseVO;
	}

	public static void main(String[] args) {

		CourseJDBCDAO dao = new CourseJDBCDAO();
		CourseVO courseVO = dao.fingByCourse("A000001");
		System.out.print(courseVO.getCrs_no());

	}

}
