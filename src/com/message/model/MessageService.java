package com.message.model;

import java.util.Date;
import java.util.List;

import com.members.model.MembersVO;

public class MessageService {
	// 封裝新增物件

	public Message add(String rcv_no, String post_no, String msg_ctx) {
		Message message = new Message();
		MembersVO rcv = new MembersVO();
		rcv.setMem_no(rcv_no);
		MembersVO post = new MembersVO();
		post.setMem_no(post_no);
		message.setRcv_no(rcv);
		message.setPost_no(post);
		message.setMsg_ctx(msg_ctx);
		MessageDAO dao = new MessageDAO();
		message = dao.add(message);
		return message;
	}
	
	public List<Message> getLastestMsg(String user_no) {
		MessageDAO dao = new MessageDAO();
		List<Message> list = dao.getLastest(user_no);
		return list;
	}
	public List<Message> getOneMsg(int thisPage,int pageSize ,String user_no, String other_no) {
		MessageDAO dao = new MessageDAO();
		List<Message> list = dao.chatPageAndRank(thisPage, pageSize, user_no, other_no);
		return list;
	}
	public int getOneMsgNum(String user_no, String other_no){
		String sql = "select count(*) from (select msg_no,rcv_no,post_no,send_time,msg_ctx,if_read,b.mem_nickname bnick,b.mem_rank brk,c.mem_nickname cnick,c.mem_rank crk from message a join members b on a.rcv_no=b.mem_no join members c on a.post_no = c.mem_no)";
		sql = sql + " where (rcv_no="+user_no+" and post_no="+other_no+" ) or (rcv_no="+other_no+" and post_no="+user_no+" )" ;
		sql = sql + " order by send_time desc";
		MessageDAO dao = new MessageDAO();
		int num = dao.countBySQL(sql);
		return num;
	}
	public int getNRNum(String user_no){
		String sql = "select count(*) from message where if_read=0 and rcv_no="+user_no;
		MessageDAO dao = new MessageDAO();
		int num = dao.countBySQL(sql);
		return num;
	}
	public boolean clear(String user_no,String other_no){
		MessageDAO dao = new MessageDAO();
		return dao.clear(user_no, other_no);
	}
	

}
