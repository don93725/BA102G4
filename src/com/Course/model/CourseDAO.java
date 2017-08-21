package com.Course.model;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.Course_picture.model.Course_pictureDAO;
import com.Course_picture.model.Course_pictureService;
import com.Course_picture.model.Course_pictureVO;
import com.place_time.model.Place_timeVO;

public class CourseDAO implements CourseDAO_interface {
	private static DataSource ds = null;
	private static final String INSERT_STMT = "INSERT INTO course (crs_no,c_acc,crs_name,details,category,status) VALUES (? || lpad(course_sq.Nextval,4,'0'), ?, ?, ?, ?,default)";
	private static final String GET_ALL_STMT = "SELECT * FROM course where c_acc = ? and status = 1 order by category";
	private static final String GET_ONE_STMT = "SELECT * FROM course where crs_no = ?";
	private static final String DELETE = "UPDATE course set status=2 where crs_no = ?";
	private static final String UPDATE = "UPDATE course set crs_name=?, details=?, category=? where crs_no = ?";

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(CourseVO courseVO, List<String> crs_base) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;

		try {

			con = ds.getConnection();
			String[] cols = { "crs_no" };
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT_STMT, cols);

			pstmt.setString(1, courseVO.getCategory());
			pstmt.setString(2, courseVO.getC_acc());
			pstmt.setString(3, courseVO.getCrs_name());
			pstmt.setString(4, courseVO.getDetails());
			pstmt.setString(5, courseVO.getCategory());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			String crs_no = rs.getString(1);

			rs.close();
			pstmt2 = con.prepareStatement(
					"INSERT INTO course_picture (crs_pic_no,crs_no,crs_base) VALUES (course_pic_sq.Nextval, ?, ?)");
			for (int i = 0; i < crs_base.size(); i++) {
				pstmt2 = con.prepareStatement(
						"INSERT INTO course_picture (crs_pic_no,crs_no,crs_base) VALUES (course_pic_sq.Nextval, ?, ?)");
				Clob clob = con.createClob();
				clob.setString(1, crs_base.get(i));
				pstmt2.setString(1, crs_no);
				pstmt2.setClob(2, clob);
				pstmt2.executeUpdate();
			}

			con.commit();
			// Handle any driver errors
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}if (pstmt2 != null) {
				try {
					pstmt2.close();
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
	public void update(CourseVO courseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, courseVO.getCrs_name());
			pstmt.setString(2, courseVO.getDetails());
			pstmt.setString(3, courseVO.getCategory());
			pstmt.setString(4, courseVO.getCrs_no());

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
	public void delete(String c_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, c_no);

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
	public CourseVO findByPK(String crs_no) {
		CourseVO courseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, crs_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				courseVO = new CourseVO();
				courseVO.setCrs_no(rs.getString("crs_no"));
				courseVO.setC_acc(rs.getString("c_acc"));
				courseVO.setCrs_name(rs.getString("crs_name"));
				courseVO.setDetails(rs.getString("details"));
				courseVO.setCategory(rs.getString("catrgory"));
				courseVO.setStatus(rs.getInt("status"));

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
		return courseVO;
	}

	@Override
	public List<CourseVO> getAll(String c_acc) {
		List<CourseVO> list = new ArrayList<CourseVO>();
		CourseVO courseVO = null;
		Course_pictureService course_pictureSVC = new Course_pictureService();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setString(1, c_acc);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				courseVO = new CourseVO();
				courseVO.setCrs_no(rs.getString("crs_no"));
				courseVO.setC_acc(rs.getString("c_acc"));
				courseVO.setCrs_name(rs.getString("crs_name"));
				courseVO.setDetails(rs.getString("details"));
				courseVO.setCategory(rs.getString("category"));
				courseVO.setStatus(rs.getInt("status"));
				courseVO.setPicList(course_pictureSVC.getAll(rs.getString("crs_no")));
				list.add(courseVO); // Store the row in the list
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

}