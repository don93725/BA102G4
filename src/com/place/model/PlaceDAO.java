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

import com.place_pic.model.Place_PicService;
import com.place_pic.model.Place_PicVO;
import com.place_publish.model.Place_PublishVO;
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
			"Insert into place(p_no, g_acc , p_name, p_into, p_status, p_add, p_latlng, p_cap, p_date)"
			+ "Values(p_no_seq.NEXTVAL, ?, ?, ?, default, ?, ?, ?, default)";
	private static final String PLACE_LIST =
			"select * from place where g_acc = ?";
	private static final String DELETE_PLACE =
			"Delete from place where p_no = ?";
	private static final String DELETE_PLACE_PIC =
			"Delete from place_picture where p_no = ?";
	private static final String GET_ONE_PLACE =
			"SELECT * FROM place where p_no = ?";
	private static final String UPDATE =
			"UPDATE place set p_name = ?, p_into = ?, p_add = ?, p_latlng = ?, p_cap = ? where p_no = ?";
	private static final String INSERT_PIC =
			"INSERT INTO PLACE_PICTURE VALUES(place_pic_sq.NEXTVAL, ?, ?)";
	private static final String PLACEINFO =
			"select * from place_time pt join place p on pt.p_no = "
			+ "p.p_no join place_picture pp on pp.p_no = p.p_no where pt_no = ?";
	private static final String PLACEINFOBYNO =
			"select * from place_time pt join place p on p.p_no = "
			+ "pt.p_no join place_picture pp on p.p_no = pp.p_no where pt.pt_no = ?";

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
	
	public List<PlaceVO> getPlaceList(String placeList_acc, String placeList_status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		List<PlaceVO> placeList = new ArrayList<PlaceVO>();
		PlaceVO placeVO = null;
		Place_PublishVO place_publishVO = null;
		
		String SQL = null;
		try {
			con = ds.getConnection();
			if(placeList_status == null) {
				SQL = PLACE_LIST + " order by p_date desc";
			}else if("0".equals(placeList_status)) {
				SQL = PLACE_LIST + " and p_status = 0 order by p_date desc";
			}else if("1".equals(placeList_status)) {
				SQL = PLACE_LIST + " and p_status = 1 order by p_date desc";
			}else if("2".equals(placeList_status)) {
				SQL = PLACE_LIST + " and p_status = 2 order by p_date desc";
			}
			System.out.println("SQL= " + SQL);
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, placeList_acc);
			rs = pstmt.executeQuery();
			while(rs.next()){
				placeVO = new PlaceVO();
				place_publishVO = new Place_PublishVO();
				placeVO.setP_no(rs.getString("p_no"));
				placeVO.setP_name(rs.getString("p_name"));
				placeVO.setP_cap(rs.getInt("p_cap"));
				placeVO.setP_add(rs.getString("p_add"));
				placeVO.setP_latlng(rs.getString("p_latlng"));
				placeVO.setP_into(rs.getString("p_into"));
				placeVO.setP_status(rs.getInt("p_status"));
				placeVO.setP_date(rs.getDate("p_date"));

				
				placeList.add(placeVO);
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
		PreparedStatement pstmt2 = null;

		try {
			con = ds.getConnection();
			pstmt2 = con.prepareStatement(DELETE_PLACE_PIC);
			pstmt2.setString(1, p_no);
			System.out.println("SQL= " + DELETE_PLACE_PIC + p_no);
			pstmt2.executeUpdate();
			pstmt = con.prepareStatement(DELETE_PLACE);
			pstmt.setString(1, p_no);
			pstmt.executeUpdate();
			
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if(pstmt2 != null) {
				try {
					pstmt2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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

	@Override
	public PlaceVO findByPrimaryKey(String p_no) {

		PlaceVO placeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_PLACE);

			pstmt.setString(1, p_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				placeVO = new PlaceVO();
				placeVO.setP_no(rs.getString("p_no"));
				placeVO.setG_acc(rs.getString("g_acc"));
				placeVO.setP_name(rs.getString("p_name"));
				placeVO.setP_into(rs.getString("p_into"));
				placeVO.setP_status(rs.getInt("p_status"));
				placeVO.setP_add(rs.getString("p_add"));
				placeVO.setP_latlng(rs.getString("p_latlng"));
				placeVO.setP_cap(rs.getInt("p_cap"));
				placeVO.setP_date(rs.getDate("p_date"));	
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		System.out.println("into= " + placeVO.getP_into());
		return placeVO;
	}
	
	@Override
	public void update(PlaceVO placeVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, placeVO.getP_name());
			pstmt.setString(2, placeVO.getP_into());
			pstmt.setString(3, placeVO.getP_add());
			pstmt.setString(4, placeVO.getP_latlng());
			pstmt.setInt(5, placeVO.getP_cap());
			pstmt.setString(6, placeVO.getP_no());
			pstmt.executeUpdate();
			con.commit();
			System.out.println(UPDATE + "1." + placeVO.getP_name() + " 2." + placeVO.getP_into() + " 3."
								+ placeVO.getP_add() + " 4." + placeVO.getP_latlng() + " 5." + placeVO.getP_cap()
								+ " 6." + placeVO.getP_no());
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
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	public void insertPic(List list, String ppp_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT_PIC);

			for(int i = 0 ; i < list.size() ; i++){
				pstmt.setString(1, ppp_no);
				pstmt.setString(2, list.get(i).toString());
				pstmt.executeUpdate();
				System.out.println(INSERT_PIC);
				System.out.println(ppp_no);
				System.out.println(list.get(i).toString());
				con.commit();
			}
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

	@Override
	public PlaceVO getOnePlacePt_no(String pt_no) {
		PlaceVO placeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM place where p_no = (select p_no from place_time where pt_no = ?)");

			pstmt.setString(1, pt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				placeVO = new PlaceVO();
				placeVO.setP_no(rs.getString("p_no"));
				placeVO.setG_acc(rs.getString("g_acc"));
				placeVO.setP_name(rs.getString("p_name"));
				placeVO.setP_into(rs.getString("p_into"));
				placeVO.setP_status(rs.getInt("p_status"));
				placeVO.setP_add(rs.getString("p_add"));
				placeVO.setP_latlng(rs.getString("p_latlng"));
				placeVO.setP_cap(rs.getInt("p_cap"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		System.out.println("into= " + placeVO.getP_into());
		return placeVO;
	}

	@Override
	public PlaceVO getPlaceInfoByP_no(String pt_no) {
		PlaceVO placeVO = null;
		Place_PicVO place_picVO = null;
		Place_PublishVO place_publishVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List list = null;
		Place_PicService sv = new Place_PicService();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(PLACEINFOBYNO);

			pstmt.setString(1, pt_no);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				placeVO = new PlaceVO();		
				placeVO.setP_no(rs.getString("p_no"));
				placeVO.setG_acc(rs.getString("g_acc"));
				placeVO.setP_name(rs.getString("p_name"));
				placeVO.setP_into(rs.getString("p_into"));
				placeVO.setP_status(rs.getInt("p_status"));
				placeVO.setP_add(rs.getString("p_add"));
				placeVO.setP_latlng(rs.getString("p_latlng"));
				placeVO.setP_cap(rs.getInt("p_cap"));
				place_publishVO = new Place_PublishVO();
				place_publishVO.setPp_no(rs.getString("pt_no"));
				place_publishVO.setPbu_price(rs.getString("pbu_price"));
				place_publishVO.setPau_price(rs.getString("pau_price"));
				place_picVO = new Place_PicVO();
				place_picVO.setP_pic_no(rs.getString("p_pic_no"));
				place_picVO.setP_base(rs.getString("p_base"));
				placeVO.setPlace_publishVO(place_publishVO);
				placeVO.setPlace_picVO(place_picVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return placeVO;
	}

}
