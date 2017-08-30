package com.gyms.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
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
import com.don.util.SQLHelper;
import com.members.model.MembersVO;
import com.students.model.StudentsVO;

public class GymsDAO implements GymsDAO_interface{
	
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
			+ "Values(mem_no_seq.NEXTVAL, ?, '2', ?, default)";	
	private static final String INSERT_GYM =
			"Insert into gyms(gym_acc, gym_no, gym_psw, gym_sta, gym_name, gym_mail, gym_add, gym_latlng, gym_into, gym_pic)"
			+ "values(?, ?, ?, default, ?, ?, ?, ?, ?, ?)";
	private static final String LOGIN_GYM =
			"Select * from gyms where gym_acc = ? and gym_psw = ?";
	private static final String PIC_GYM =
			"Select gym_pic from gyms where gym_no = ?";
	private static final String UPDATE_GYM =
			"Update gyms set gym_name = ?, gym_mail = ?, gym_into = ?, gym_add =?, gym_latlng = ? where gym_acc = ?";
	private static final String GET_ALL_GYM =
			"Select * from gyms where gym_sta = 1 order by gym_no";
	private static final String SEARCH_GYM =
			"Select * from gyms ";
	private static final String GET_ALL_BY_STAT =
			"Select * from gyms WHERE GYM_STA=?";
	private static final String UPDATE_STAT =
			"Update GYMS set GYM_sta=? where GYM_acc = ?";
	private static final String LOOK_SEARCH_MEM =
			"Select * from gyms where gym_acc = ?　and gym_no = ?";

	@Override
	public void insert(MembersVO membersVO, GymsVO gymsVO) {
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
					+ "Values(mem_no_seq.NEXTVAL," + membersVO.getMem_acc() +", '2', " + membersVO.getMem_nickname() + ", default)");
			pstmt.executeUpdate();
			
			//取得自增主鍵
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				mem_no = rs.getString(1);
							
				gymsVO.setGym_acc(membersVO.getMem_acc());
				gymsVO.setGym_no(mem_no);			
				System.out.println("(GymsDAO)Gym_acc= " + gymsVO.getGym_acc());
				} else {
					System.out.println("(GymsDAO)沒有rs");
				}
				rs.close();		
						
			//同時新增教練
			addWithMem_no(con, gymsVO);
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

	//新增members的同時，新增gyms
	private static void addWithMem_no(Connection con, GymsVO gymsVO){
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(INSERT_GYM);
				
			pstmt.setString(1, gymsVO.getGym_acc());
			pstmt.setString(2, gymsVO.getGym_no());
			pstmt.setString(3, gymsVO.getGym_psw());
			pstmt.setString(4, gymsVO.getGym_name());
			pstmt.setString(5, gymsVO.getGym_mail());
			pstmt.setString(6, gymsVO.getGym_add());
			pstmt.setString(7, gymsVO.getGym_latlng());
			pstmt.setString(8, gymsVO.getGym_into());
			pstmt.setBytes(9, gymsVO.getGym_pic());
					
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
	public GymsVO login(String gym_account, String gym_password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GymsVO gymsVO = new GymsVO();
		
		try {
			System.out.println("GG"+gym_account);
			System.out.println("GG"+gym_password);
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(LOGIN_GYM);
			pstmt.setString(1, gym_account);
			pstmt.setString(2, gym_password);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				gymsVO.setGym_acc(rs.getString("gym_acc"));
				gymsVO.setGym_no(rs.getString("gym_no"));
				gymsVO.setGym_psw(rs.getString("gym_psw"));
				gymsVO.setGym_sta(rs.getInt("gym_sta"));
				gymsVO.setGym_name(rs.getString("gym_name"));
				gymsVO.setGym_mail(rs.getString("gym_mail"));
				gymsVO.setGym_add(rs.getString("gym_add"));
				gymsVO.setGym_latlng(rs.getString("gym_latlng"));
				gymsVO.setGym_into(rs.getString("gym_into"));
				gymsVO.setGym_pic(rs.getBytes("gym_pic"));
				
				return gymsVO;
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

	public byte[] getPicByte(String gym_no){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] picByte = null;
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(PIC_GYM);
			
			pstmt.setString(1, gym_no);
			rs = pstmt.executeQuery();
			while(rs.next()){
				picByte = rs.getBytes("gym_pic");
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

	public void update(GymsVO gymsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(UPDATE_GYM);
			pstmt.setString(1, gymsVO.getGym_name());
			pstmt.setString(2, gymsVO.getGym_mail());
			pstmt.setString(3, gymsVO.getGym_into());
			pstmt.setString(4, gymsVO.getGym_add());
			pstmt.setString(5, gymsVO.getGym_latlng());
			pstmt.setString(6, gymsVO.getGym_acc());
			
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
	public List<GymsVO> getGymsbySQL(String sql,Object[] param){
		SQLHelper helper = new SQLHelper();
		List<Object[]> list = helper.executeQuery(sql, param);
		List<GymsVO> temp = new ArrayList<GymsVO>();
		for(int i = 0 ; i < list.size() ; i++){
			Object[] obj = list.get(i);
			GymsVO gym = new GymsVO();
			if(obj[0]!=null){
				gym.setGym_acc(String.valueOf(obj[0]));
			}
			if(obj[1]!=null){
				gym.setGym_no(String.valueOf(obj[1]));
			}
			if(obj[2]!=null){
				gym.setGym_psw(String.valueOf(obj[2]));
			}
			if(obj[3]!=null){
				gym.setGym_sta(Integer.parseInt(String.valueOf(obj[3])));
			}
			if(obj[4]!=null){
				gym.setGym_name(String.valueOf(obj[4]));
			}
			if(obj[5]!=null){
				gym.setGym_mail(String.valueOf(obj[5]));
			}
			if(obj[6]!=null){
				gym.setGym_add(String.valueOf(obj[6]));
			}
			if(obj[7]!=null){
				gym.setGym_latlng(String.valueOf(obj[7]));
			}
			if(obj[8]!=null){
				gym.setGym_into(String.valueOf(obj[8]));
			}
			
			temp.add(gym);
		}
		return temp;
	}
	public List<GymsVO> getAll(){
		return getGymsbySQL(GET_ALL_GYM,null);
	}
	public List<GymsVO> searchGyms(String search_Name, String search_Type){
		String SQL = "";
		if(search_Name.trim().length() == 0 && search_Type.trim().length() == 0 ) {
			SQL = SEARCH_GYM + " where gym_sta=1 order by gym_no";
		}else if(search_Name.trim().length() != 0 && search_Type.trim().length() != 0) {
			SQL = SEARCH_GYM + "where gym_name like '%" + search_Name +"%' and gym_into like '%" + search_Type + "%' and gym_sta=1 order by gym_no";
		}else if(search_Name.trim().length() == 0 && search_Type.trim().length() != 0) {
			SQL = SEARCH_GYM + "where gym_into like '%" + search_Type + "%' gym_sta=1 order by gym_no";
		}else if(search_Name.trim().length() != 0 && search_Type.trim().length() == 0) {
			SQL = SEARCH_GYM + "where gym_name like '%" + search_Name + "%' gym_sta=1 order by gym_no";
		}
		return getGymsbySQL(SQL,null);
	}

	@Override
	public List<GymsVO> getAllBySta(Integer gym_sta) {
		List<GymsVO> list = new ArrayList<GymsVO>();
		GymsVO gymsVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_STAT);
			pstmt.setInt(1, gym_sta);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				gymsVO = new GymsVO();
				gymsVO.setGym_acc(rs.getString("gym_acc"));
				gymsVO.setGym_no(rs.getString("gym_no"));
				gymsVO.setGym_psw(rs.getString("gym_psw"));
				gymsVO.setGym_sta(rs.getInt("gym_sta"));
				gymsVO.setGym_name(rs.getString("gym_name"));
				gymsVO.setGym_mail(rs.getString("gym_mail"));
				gymsVO.setGym_add(rs.getString("gym_add"));
				gymsVO.setGym_latlng(rs.getString("gym_latlng"));
				gymsVO.setGym_into(rs.getString("gym_into"));
				gymsVO.setGym_pic(rs.getBytes("gym_pic"));
				list.add(gymsVO);
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
	public void setSta(Integer gym_sta, String gym_acc) {
		 Connection con = null;
			PreparedStatement pstmt = null;
			try{
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_STAT);
				pstmt.setInt(1, gym_sta);
				pstmt.setString(2, gym_acc);
				
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
	public GymsVO look_search_mem(MembersVO membersVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GymsVO gymsVO = null;
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(LOOK_SEARCH_MEM);
			pstmt.setString(1, membersVO.getMem_acc());
			pstmt.setString(2, membersVO.getMem_no());

			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				gymsVO = new GymsVO();
				gymsVO.setGym_acc(rs.getString("gym_acc"));
				gymsVO.setGym_no(rs.getString("gym_no"));
				gymsVO.setGym_psw(rs.getString("gym_psw"));
				gymsVO.setGym_sta(rs.getInt("gym_sta"));
				gymsVO.setGym_name(rs.getString("gym_name"));
				gymsVO.setGym_mail(rs.getString("gym_mail"));
				gymsVO.setGym_add(rs.getString("gym_add"));
				gymsVO.setGym_latlng(rs.getString("gym_latlng"));
				gymsVO.setGym_into(rs.getString("gym_into"));
				gymsVO.setGym_pic(rs.getBytes("gym_pic"));

				return gymsVO;
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

}
