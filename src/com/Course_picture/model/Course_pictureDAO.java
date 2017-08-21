package com.Course_picture.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Clob;
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

import com.Course.model.CourseVO;

public class Course_pictureDAO implements Course_pictureDAO_interface{
	
	private static DataSource ds = null;
	private static final String INSERT_STMT = "INSERT INTO course_picture (crs_pic_no,crs_no,crs_base) VALUES (course_pic_sq.Nextval, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM course_picture where crs_no = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM course_picture where crs_pic_no = ?";
	private static final String DELETE = "Delete course_picture where crs_pic_no = ?";
	private static final String UPDATE = "UPDATE course_picture set crs_name=?, details=?, category=? where crs_no = ?";

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
			Clob clob = con.createClob();
			clob.setString(1, course_pictureVO.getCrs_base());
			pstmt.setString(1, course_pictureVO.getCrs_no());
			pstmt.setClob(2, clob);

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
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, crs_pic_no);

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
	public Course_pictureVO findByPK(String crs_pic_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course_pictureVO> getAll(String crs_no) {
		List<Course_pictureVO> list = new ArrayList<Course_pictureVO>();
		Course_pictureVO course_pictureVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setString(1, crs_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				course_pictureVO = new Course_pictureVO();
				course_pictureVO.setCrs_pic_no(rs.getString("crs_pic_no"));
				course_pictureVO.setCrs_base(readString(rs.getClob("crs_base")));
				

				list.add(course_pictureVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (Exception e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
	
	public static String readString(Clob clob) throws IOException, SQLException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(clob.getCharacterStream());
		String str;
		while((str = br.readLine()) != null) {
			sb.append(str);
		}
		br.close();

		return sb.toString();
	}

}
