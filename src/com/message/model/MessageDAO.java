package com.message.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.don.inteface.DAOInterface;
import com.don.util.BasicDAO;
import com.don.util.SQLHelper;
import com.members.model.MembersVO;

public class MessageDAO extends BasicDAO implements DAOInterface<Message> {
	// 建置查詢

	public List<Message> getVOBySQL(String sql, Object[] param) {
		List list = new SQLHelper().executeQuery(sql, param);
		List<Message> tempList = new ArrayList<Message>();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			Message message = new Message();
			if (obj[0] != null) {
				message.setMsg_no((String) obj[0]);
			}
			if (obj[1] != null) {
				MembersVO rcv = new MembersVO();
				rcv.setMem_no(String.valueOf(obj[1]));
				rcv.setMem_nickname(String.valueOf(obj[6]));
				rcv.setMem_rank(String.valueOf(obj[7]));
				message.setRcv_no(rcv);
			}
			if (obj[2] != null) {
				MembersVO post = new MembersVO();
				post.setMem_no(String.valueOf(obj[2]));
				post.setMem_nickname(String.valueOf(obj[8]));
				post.setMem_rank(String.valueOf(obj[9]));
				message.setPost_no(post);				
			}
			if (obj[3] != null) {
				message.setSend_time((Date) obj[3]);
			}
			if (obj[4] != null) {
				BufferedReader br =null;
				try {
					Clob clob = (Clob) obj[4];				
					br = new BufferedReader(clob.getCharacterStream());
					StringBuilder msg_ctx = new StringBuilder();
					String temp = null;
					while( (temp = br.readLine()) !=null){
						msg_ctx.append(temp);
					}
					br.close();
					message.setMsg_ctx(msg_ctx.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (obj[5] != null) {
				message.setIf_read(String.valueOf(obj[5]));;
			}
			tempList.add(message);
		}
		return tempList;
	}
	public List<Message> getVOBySQLForNew(String sql, Object[] param,String user_no) {
		List list = new SQLHelper().executeQuery(sql, param);
		List<Message> tempList = new ArrayList<Message>();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			Message message = new Message();
			if (obj[0] != null) {
				message.setMsg_no((String) obj[0]);
			}
			if (obj[1] != null) {
				MembersVO rcv = new MembersVO();
				rcv.setMem_no(String.valueOf(obj[1]));
				rcv.setMem_nickname(String.valueOf(obj[6]));
				rcv.setMem_rank(String.valueOf(obj[7]));
				message.setRcv_no(rcv);
			}
			if (obj[2] != null) {
				MembersVO post = new MembersVO();
				post.setMem_no(String.valueOf(obj[2]));
				post.setMem_nickname(String.valueOf(obj[8]));
				post.setMem_rank(String.valueOf(obj[9]));
				message.setPost_no(post);
				sql = "select count(*) from message where if_read=0 and rcv_no="+user_no+"and post_no="+String.valueOf(obj[2]);
				int num = countBySQL(sql);
				message.setNr(num);
			}
			if (obj[3] != null) {
				message.setSend_time((Date) obj[3]);
			}
			if (obj[4] != null) {
				BufferedReader br =null;
				try {
					Clob clob = (Clob) obj[4];				
					br = new BufferedReader(clob.getCharacterStream());
					StringBuilder msg_ctx = new StringBuilder();
					String temp = null;
					while( (temp = br.readLine()) !=null){
						msg_ctx.append(temp);
					}
					br.close();
					temp = msg_ctx.toString();
					int index = temp.indexOf("<img");
					if(index!=-1){
						
						String temp2 = msg_ctx.substring(index,temp.length());
						int index2 = temp2.indexOf(">");
						temp2 = "(訊息圖片)"+temp2.substring(index2+1, temp2.length());
						temp = temp.substring(0,index)+temp2;
					}
					if(temp.length()>8){
						temp = temp.substring(0,8)+"..";						
					}
					message.setMsg_ctx(temp);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (obj[5] != null) {
				message.setIf_read(String.valueOf(obj[5]));;
			}
			tempList.add(message);
		}
		return tempList;
	}

	// 建置查詢單筆

	public Message getVOByPK(String msg_no) {
		String sql = "Select * from message where msg_no=?";
		Object[] param = { msg_no };
		List<Message> list = getVOBySQL(sql, param);
		Message message = list.get(0);
		return message;
	}
	// 建置查詢全部

	public List<Message> getAll() {
		String sql = "select msg_no,rcv_no,post_no,send_time,msg_ctx,if_read,b.mem_nickname bnick,b.mem_rank brk,c.mem_nickname cnick,c.mem_rank crk from message a join members b on a.rcv_no=b.mem_no join members c on a.post_no = c.mem_no where rcv_no=1 and (send_time,post_no) in (select max(send_time),post_no from message where rcv_no=1 group by post_no)";
		List<Message> list = getVOBySQL(sql, null);
		return list;
	}
	public List<Message> getLastest(String user_no) {
		String sql = "select msg_no,rcv_no,post_no,send_time,msg_ctx,if_read,b.mem_nickname bnick,b.mem_rank brk,c.mem_nickname cnick,c.mem_rank crk from "+ 
				"message a join members b on a.rcv_no=b.mem_no join members c on a.post_no=c.mem_no  "+
				"where (rcv_no,post_no,send_time) in (select distinct * from (select rcv_no,max(post_no),sdate from (select greatest(max1,(case when max2 is null then max1 else"+
				"(case when max2>max1 then max2 else max1 end) end)) sdate,(case when max1>=(case when max2 is null then max1 else max2 end )then rcv1 else rcv2 end)"+
				" rcv_no,(case when max1>=(case when max2 is null then max1 else max2 end )then post1 else post2 end) post_no from "+
				"(select max(send_time) max1,rcv_no rcv1,post_no post1 from message where  rcv_no="+user_no+" or post_no="+user_no+" group by rcv_no,post_no) a  "+
				"left outer join(select max(send_time) max2,rcv_no rcv2,post_no post2 from message where  rcv_no="+user_no+" or post_no="+user_no+
				" group by rcv_no,post_no) b on rcv1=post2 and post1=rcv2) group by post_no,rcv_no,sdate)) order by send_time desc";
		List<Message> list = getVOBySQLForNew(sql, null,user_no);
		return list;
	}
//	public List<Message> getLastest(String user_no) {
//		String sql = "select msg_no,rcv_no,post_no,send_time,msg_ctx,if_read,b.mem_nickname bnick,b.mem_rank brk,c.mem_nickname cnick,c.mem_rank crk from message a join members b on a.rcv_no=b.mem_no join members c on a.post_no = c.mem_no where rcv_no="+user_no+" and (send_time,post_no) in (select max(send_time),post_no from message where rcv_no="+user_no+" group by post_no) order by send_time";
//		List<Message> list = getVOBySQL(sql, null);
//		return list;
//	}
	// 算數量
	

	public int countAll() {
		String sql = "select count(*) from message";
		return countBySQL(sql);
	}
	// 建置修改

	public boolean updateByVO(Message message) {
		String sql = "update message set rcv_no=?,post_no=?,msg_ctx=? where msg_no=?";
		Object[] param = { message.getMsg_no(), message.getRcv_no().getMem_no(), message.getPost_no().getMem_no(), message.getSend_time(),
				message.getMsg_ctx() };
		boolean updateResult = new SQLHelper().executeUpdate(sql, param);
		return updateResult;
	}
	// 建置已讀

	public boolean updateIfRead(String msg_no) {
		String sql = "update message set if_read=1 where msg_no="+msg_no;		
		boolean updateResult = new SQLHelper().executeUpdate(sql, null);
		return updateResult;
	}
	public boolean executeInsert(Message message)  {
		return false;
		
	}
	public Message add(Message message)  {
		SQLHelper helper = new SQLHelper();
		Connection con = null;
		PreparedStatement pstmt = null;
		String insertResult = null;
		con =helper.getConnection();
		Clob clob =null;		
		try {
			clob = con.createClob();
			clob.setString(1, message.getMsg_ctx());
			String sql = "insert into message values(message_pk_seq.nextval,?,?,default,?,default)";
			Object[] param = { message.getRcv_no().getMem_no(), message.getPost_no().getMem_no(),
					clob};
			insertResult = helper.executeUpdate(sql, param,"msg_no",con);
			if(insertResult!=null){
				con.commit();
				sql = "select msg_no,rcv_no,post_no,send_time,msg_ctx,if_read,b.mem_nickname bnick,b.mem_rank brk,c.mem_nickname cnick,c.mem_rank crk from message a join members b on a.rcv_no=b.mem_no join members c on a.post_no = c.mem_no where msg_no="+ insertResult;
				List<Message> list =  getVOBySQL(sql, null);
				if(list.size()!=0){
					message = list.get(0);
					return message;
				}
				return null;
			}else{
				con.rollback();
				return null ;	
			}
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally{
			helper.close(con, pstmt);
		}
		return null;
	}
	// 建置刪除

	public boolean executeDelete(String msg_no) {
		String sql = "delete from message where msg_no=?";
		Object[] param = { msg_no };
		boolean deleteResult = new SQLHelper().executeUpdate(sql, param);
		return deleteResult;
	}
	// 建置分頁(彈性排序可設條件)

	public List<Message> pageAndRank(int page, int pageSize, String order, String where) {
		int firstPage = (page - 1) * pageSize + 1;
		int lastPage = page * pageSize;
		String sql = "select * from (select msg_no,rcv_no,post_no,send_time,msg_ctx,if_read,bnick,brk,cnick,crk, rownum rn from (select * from (select msg_no,rcv_no,post_no,send_time,msg_ctx,if_read,b.mem_nickname bnick,b.mem_rank brk,c.mem_nickname cnick,c.mem_rank crk from message a join members b on a.rcv_no=b.mem_no join members c on a.post_no = c.mem_no)";
		if (where != null) {
			sql = sql + " where " + where;
		}
		sql = sql + " order by " + order + ")) where rn between " + firstPage + " and " + lastPage;
		List<Message> list = getVOBySQL(sql, null);
		return list;
	}
	public List<Message> chatPageAndRank(int page, int pageSize, String user_no,String other_no) {
		int firstPage = (page - 1) * pageSize + 1;
		int lastPage = page * pageSize;
		String sql = "select * from (select msg_no,rcv_no,post_no,send_time,msg_ctx,if_read,bnick,brk,cnick,crk, rownum rn from (select * from (select msg_no,rcv_no,post_no,send_time,msg_ctx,if_read,b.mem_nickname bnick,b.mem_rank brk,c.mem_nickname cnick,c.mem_rank crk from message a join members b on a.rcv_no=b.mem_no join members c on a.post_no = c.mem_no)";
		
		sql = sql + " where (rcv_no="+user_no+" and post_no="+other_no+" ) or (rcv_no="+other_no+" and post_no="+user_no+" )" ;
		
		sql = sql + " order by send_time desc)) where rn between " + firstPage + " and " + lastPage;
		List<Message> list = getVOBySQL(sql, null);
		clear(user_no,other_no);
		return list;
	}
	public boolean clear(String user_no,String other_no) {
		String sql = "update message set if_read=1 where rcv_no="+user_no+" and post_no="+other_no;
		boolean updateResult = new SQLHelper().executeUpdate(sql, null);
		return updateResult;
	}
	// 建置分頁(彈性排序不設條件)

	public List<Message> pageAndRank(int page, int pageSize, String order) {
		List<Message> list = pageAndRank(page, pageSize, order);
		return list;
	}
	// 建置分頁(PK排序)

	public List<Message> pageAndRankByPk(int page, int pageSize) {
		List<Message> list = pageAndRank(page, pageSize, "msg_no");
		return list;
	}
	// 建置取得欄位資料

	public Object[] getCol(String col, Object[] param) {
		String sql = "select " + col + " from message where msg_no=?";
		List<Object[]> list = new SQLHelper().executeQuery(sql, param);
		Object[] colData = list.get(0);
		return colData;
	}
}
