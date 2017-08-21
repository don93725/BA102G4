package com.Course_picture.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Course_pictureDAO implements Course_pictureDAO_interface{
	
	private static DataSource ds = null;
	private static final String INSERT_STMT = "INSERT INTO course_pic (crs_pic_no,crs_no,crs_base) VALUES (course_pic_sq.Nextval, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM course_pic where crs_no = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM course_pic where crs_pic_no = ?";
	private static final String DELETE = "UPDATE course_pic where crs_pic_no = ?";
	private static final String UPDATE = "UPDATE course_pic set crs_name=?, details=?, category=? where crs_no = ?";

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(Course_pictureVO course_pictureVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, course_pictureVO.getCrs_no());
			pstmt.setString(2, course_pictureVO.getCrs_base());

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
	public void update(Course_pictureVO course_pictureVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String crs_pic_no) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Course_pictureVO findByPK(String crs_pic_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course_pictureVO> getAll(String crs_no) {
		// TODO Auto-generated method stub
		return null;
	}

}
