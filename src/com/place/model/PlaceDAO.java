package com.place.model;

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

import com.place_time.model.Place_timeVO;
import com.students.model.StudentsVO;

public class PlaceDAO implements PlaceDAO_interface {

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
			"Insert into place(p_no, g_acc , p_name, p_into, p_status, p_add, p_latlng, p_cap)"
			+ "Values(p_no_seq.NEXTVAL, ?, ?, ?, default, ?, ?, ?)";
	private static final String PLACE_LIST =
			"Select * from place where g_acc = ?";
	private static final String DELETE_PLACE =
			"Delete * from place where p_no = ?";
	
//	private static final String GET_ALL_STMT = "SELECT p_no,g_acc FROM place order by p_no";
//	private static final String GET_ONE_STMT = "SELECT p_no,g_acc FROM place where p_no = ?";
//	private static final String DELETE_PLACE_TIME = "DELETE FROM place_time where pt_no = ?";
//	private static final String DELETE_PLACE = "DELETE FROM place where p_no = ?";
//	private static final String UPDATE = "UPDATE place set g_acc = ?, p_name = ?, status = ? where p_no = ?";

	@Override
	public void insert(PlaceVO placeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, placeVO.getG_acc());
			pstmt.setString(2, placeVO.getP_name());
			pstmt.setString(3, placeVO.getP_into());
			pstmt.setString(4, placeVO.getP_add());
			pstmt.setString(5, placeVO.getP_latlng());
			pstmt.setInt(6, placeVO.getP_cap());

			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException se) {
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
				if(pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}	
		}
	}
	
	public List<PlaceVO> getPlaceList(String placeList_acc) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PlaceVO> placeList = new ArrayList<PlaceVO>();
		PlaceVO placeVO = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(PLACE_LIST);
			pstmt.setString(1, placeList_acc);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				placeVO = new PlaceVO();	
				placeVO.setP_name(rs.getString("p_name"));
				placeVO.setP_cap(rs.getInt("p_cap"));
				placeVO.setP_add(rs.getString("p_add"));
				placeVO.setP_latlng(rs.getString("p_latlng"));
				placeVO.setP_into(rs.getString("p_into"));
				placeVO.setP_status(rs.getInt("p_status"));
				placeList.add(placeVO);
				System.out.println("(PDAO)= " + rs.getString("p_name"));
			}
		} catch(SQLException se) {
			try {
				se.printStackTrace();
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return placeList;
	}
	
	public void delete(String p_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_PLACE);
			pstmt.setString(1, p_no);
			rs = pstmt.executeQuery();
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
//	@Override
//	public void update(PlaceVO placeVO) {
//		// TODO Auto-generated method stub
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, placeVO.getG_acc());
//			pstmt.setString(2, placeVO.getP_name());
//			pstmt.setInt(3, placeVO.getStatus());
//			pstmt.setString(4, placeVO.getP_no());
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
//
//	@Override
//	public void delete(String p_no) {
//		// TODO Auto-generated method stub
//		int updateCount_Place_Time = 0;
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			con.setAutoCommit(false);
//			
//			pstmt = con.prepareStatement(DELETE_PLACE_TIME);
//			pstmt.setString(1, p_no);
//			updateCount_Place_Time = pstmt.executeUpdate();
//			
//			pstmt = con.prepareStatement(DELETE_PLACE);
//			pstmt.setString(1, p_no);
//			pstmt.executeUpdate();
//			
//			con.commit();
//			con.setAutoCommit(true);
//			
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
//
//	@Override
//	public PlaceVO findByPrimaryKey(String p_no) {
//		// TODO Auto-generated method stub
//
//		PlaceVO placeVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setString(1, p_no);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// empVo �]�٬� Domain objects
//				placeVO = new PlaceVO();
//				placeVO.setP_no(rs.getString("p_no"));
//				placeVO.setG_acc(rs.getString("g_acc"));
//				placeVO.setP_name(rs.getString("p_name"));
//				placeVO.setStatus(rs.getInt("status"));
//				
//
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return placeVO;
//
//	}
//
//	@Override
//	public List<PlaceVO> getAll() {
//		// TODO Auto-generated method stub
//
//		List<PlaceVO> list = new ArrayList<PlaceVO>();
//		PlaceVO placeVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// empVO �]�٬� Domain objects
//				placeVO = new PlaceVO();
//				placeVO.setP_no(rs.getString("p_no"));
//				placeVO.setG_acc(rs.getString("g_acc"));
//				placeVO.setP_name(rs.getString("p_name"));
//				placeVO.setStatus(rs.getInt("status"));
//				list.add(placeVO); // Store the row in the list
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//
//	}

}
