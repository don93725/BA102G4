package com.coaches.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.don.util.SQLHelper;
import com.members.model.MembersVO;

public class CoachesDAO implements CoachesDAO_interface{
	
	//連線池
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_MEM =
			"Insert into members(mem_no, mem_acc, mem_rank, mem_nickname, mr_num)"
			+ "Values(mem_no_seq.NEXTVAL, ?, '1', ?, default)";	
	private static final String INSERT_COA =
			"Insert into coaches(coa_acc, coa_no, coa_psw, coa_sta, coa_name, coa_sex, coa_id, coa_mail, coa_into, coa_pic, coa_pft)"
			+ "values(?, ?, ?, default, ?, ?, ?, ?, ?, ? , default)";
	private static final String LOGIN_COA =
			"Select * from coaches where coa_acc = ? and coa_psw = ?";
	private static final String UPDATE_COA =
			"Update coaches set coa_name = ?, coa_mail = ?, coa_into = ? where coa_acc = ?";
	private static final String PIC_COA =
			"Select coa_pic from coaches where coa_no = ?";
	private static final String GET_ALL_COA =
			"Select * from coaches where coa_sta = 1 order by coa_no";
	private static final String GET_STAT =
			"Select * from coaches where coa_sta=?";
	private static final String SEARCH_COA =
			"Select * from coaches ";
	private static final String LOOK_SEARCH_MEM =
			"Select * from coaches where coa_acc = ?　and coa_no = ?";
	private static final String UPDATE_STAT =
			"Update coaches set coa_sta=? where coa_acc = ?";
	
	
	@Override
	public void insert(MembersVO membersVO, CoachesVO coachesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String mem_no = null;
		
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			
			//新增會員
			String cols[] = { "mem_no" };
			pstmt = con.prepareStatement(INSERT_MEM, cols);
			pstmt.setString(1, membersVO.getMem_acc());
			pstmt.setString(2, membersVO.getMem_nickname());
					
			System.out.println("Insert into members(mem_no, mem_acc, mem_rank, mem_nickname)"
					+ "Values(mem_no_seq.NEXTVAL," + membersVO.getMem_acc() +", '1', " + membersVO.getMem_nickname() + ", default)");
			pstmt.executeUpdate();
			
			//取得自增主鍵
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				mem_no = rs.getString(1);
							
				coachesVO.setCoa_acc(membersVO.getMem_acc());
				coachesVO.setCoa_no(mem_no);			
				System.out.println("(CoachesDAO)coa_acc= " + coachesVO.getCoa_acc());
				} else {
					System.out.println("(CoachesDAO)沒有rs");
				}
				rs.close();
						
						
			//同時新增教練
			addWithMem_no(con, coachesVO);
			String sql = "insert into albums values(albums_pk_seq.nextval,"+mem_no+",default,'動態相簿',default,0,1)";			
			new SQLHelper().executeUpdate(sql, null,"mem_no",con);			
			con.commit();
			
			//清空指令，重複利用
			pstmt.clearParameters();
			con.setAutoCommit(true);
			
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
	}

	//新增members的同時，新增coaches
	private static void addWithMem_no(Connection con, CoachesVO coachesVO){
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(INSERT_COA);
				
			pstmt.setString(1, coachesVO.getCoa_acc());
			pstmt.setString(2, coachesVO.getCoa_no());
			pstmt.setString(3, coachesVO.getCoa_psw());
			pstmt.setString(4, coachesVO.getCoa_name());
			pstmt.setInt(5, coachesVO.getCoa_sex());
			pstmt.setString(6, coachesVO.getCoa_id());
			pstmt.setString(7, coachesVO.getCoa_mail());
			pstmt.setString(8, coachesVO.getCoa_into());
			pstmt.setBytes(9, coachesVO.getCoa_pic());
					
			pstmt.executeUpdate();
				
		} catch (SQLException se) {
			se.getStackTrace();
			try{
				con.rollback();
			} catch (Exception e) {
				e.getStackTrace();
			}

		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void update(CoachesVO coachesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(UPDATE_COA);
			pstmt.setString(1, coachesVO.getCoa_name());
			pstmt.setString(2, coachesVO.getCoa_mail());
			pstmt.setString(3, coachesVO.getCoa_into());
			pstmt.setString(4, coachesVO.getCoa_acc());
			
			pstmt.executeUpdate();
			System.out.println("CCC");
			con.commit();
		}catch(SQLException se) {
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
	}

	@Override
	public CoachesVO login(String coa_account, String coa_password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer coa_sta = null;
		CoachesVO coachesVO = new CoachesVO();
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(LOGIN_COA);
			pstmt.setString(1, coa_account);
			pstmt.setString(2, coa_password);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				coachesVO.setCoa_acc(rs.getString("coa_acc"));
				coachesVO.setCoa_no(rs.getString("coa_no"));
				coachesVO.setCoa_psw(rs.getString("coa_psw"));
				coachesVO.setCoa_sta(rs.getInt("coa_sta"));
				coachesVO.setCoa_name(rs.getString("coa_name"));
				coachesVO.setCoa_sex(rs.getInt("coa_sex"));
				coachesVO.setCoa_id(rs.getString("coa_id"));
				coachesVO.setCoa_mail(rs.getString("coa_mail"));
				coachesVO.setCoa_into(rs.getString("coa_into"));
				coachesVO.setCoa_pic(rs.getBytes("coa_pic"));
				coachesVO.setCoa_pft(rs.getInt("coa_pft"));

				return coachesVO;
			}else {
				return null;
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
	
	public byte[] getPicByte(String coa_no){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] picByte = null;
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(PIC_COA);
			
			pstmt.setString(1, coa_no);
			rs = pstmt.executeQuery();
			while(rs.next()){
				picByte = rs.getBytes("coa_pic");
			}
			
			con.commit();
			con.setAutoCommit(true);
			
			
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
		return picByte;
	}

	public List<CoachesVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CoachesVO coachesVO= null;
		List<CoachesVO> coachesList = new ArrayList<CoachesVO>();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_COA);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				coachesVO = new CoachesVO();
				coachesVO.setCoa_no(rs.getString("coa_no"));
				coachesVO.setCoa_name(rs.getString("coa_name"));
				coachesVO.setCoa_into(rs.getString("coa_into"));
				coachesList.add(coachesVO);
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
		return coachesList;
	}
	
	public List<CoachesVO> searchCoa(String search_Name, String search_Type) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CoachesVO coachesVO= null;
		List<CoachesVO> coachesList = new ArrayList<CoachesVO>();
		String SQL = null;
		try {
			if(search_Name.trim().length() == 0 && search_Type.trim().length() == 0 ) {
				SQL = SEARCH_COA + " where coa_sta = 1 order by coa_no";
			}else if(search_Name.trim().length() != 0 && search_Type.trim().length() != 0) {
				SQL = SEARCH_COA + "where coa_name like '%" + search_Name +"%' and coa_into like '%" + search_Type + "%' and coa_sta = 1 order by coa_no";
			}else if(search_Name.trim().length() == 0 && search_Type.trim().length() != 0) {
				SQL = SEARCH_COA + "where coa_into like '%" + search_Type + "%' and coa_sta = 1 order by coa_no";
			}else if(search_Name.trim().length() != 0 && search_Type.trim().length() == 0) {
				SQL = SEARCH_COA + "where coa_name like '%" + search_Name + "%' and coa_sta = 1 order by coa_no";
			} 
			System.out.println("(CDAO)1= " + search_Name);
			System.out.println("(CDAO)2= " + search_Type);
			con = ds.getConnection();
			pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				coachesVO = new CoachesVO();
				coachesVO.setCoa_no(rs.getString("coa_no"));
				coachesVO.setCoa_name(rs.getString("coa_name"));
				coachesVO.setCoa_into(rs.getString("coa_into"));
				coachesList.add(coachesVO);
				System.out.println("(CDAO)= " + rs.getString("coa_name"));
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
		return coachesList;
	}

	public CoachesVO look_search_mem(MembersVO membersVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CoachesVO coachesVO = null;
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(LOOK_SEARCH_MEM);
			pstmt.setString(1, membersVO.getMem_acc());
			pstmt.setString(2, membersVO.getMem_no());
			System.out.println( membersVO.getMem_acc()+" "+membersVO.getMem_no());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				coachesVO = new CoachesVO();
				coachesVO.setCoa_acc(rs.getString("coa_acc"));
				coachesVO.setCoa_no(rs.getString("coa_no"));
				coachesVO.setCoa_psw(rs.getString("coa_psw"));
				coachesVO.setCoa_sta(rs.getInt("coa_sta"));
				coachesVO.setCoa_name(rs.getString("coa_name"));
				coachesVO.setCoa_sex(rs.getInt("coa_sex"));
				coachesVO.setCoa_id(rs.getString("coa_id"));
				coachesVO.setCoa_mail(rs.getString("coa_mail"));
				coachesVO.setCoa_into(rs.getString("coa_into"));
				coachesVO.setCoa_pic(rs.getBytes("coa_pic"));
				coachesVO.setCoa_pft(rs.getInt("coa_pft"));

				return coachesVO;
			}else {
				return null;
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

	@Override
	public List<CoachesVO> getAllBySta(Integer coa_sta) {
		List<CoachesVO> list = new ArrayList<CoachesVO>();
		CoachesVO coachesVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_STAT);
			pstmt.setInt(1, coa_sta);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				coachesVO = new CoachesVO();
				coachesVO.setCoa_acc(rs.getString("coa_acc"));
				coachesVO.setCoa_no(rs.getString("coa_no"));
				coachesVO.setCoa_psw(rs.getString("coa_psw"));
				coachesVO.setCoa_sta(rs.getInt("coa_sta"));
				coachesVO.setCoa_name(rs.getString("coa_name"));
				coachesVO.setCoa_sex(rs.getInt("coa_sex"));
				coachesVO.setCoa_id(rs.getString("coa_id"));
				coachesVO.setCoa_mail(rs.getString("coa_mail"));
				coachesVO.setCoa_into(rs.getString("coa_into"));
				coachesVO.setCoa_pic(rs.getBytes("coa_pic"));
				coachesVO.setCoa_pft(rs.getInt("coa_pft"));
				list.add(coachesVO);
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

	@Override
	public void setSta(Integer coa_sta ,String coa_acc) {
		 Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STAT);
			pstmt.setInt(1, coa_sta);
			pstmt.setString(2, coa_acc);
			
			pstmt.executeUpdate();
		
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
}

