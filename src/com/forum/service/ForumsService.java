package com.forum.service;

import java.util.List;

import com.don.inteface.DAOInterface;
import com.forum.dao.ForumsDAO;
import com.forum.domain.Art_types;
import com.forum.domain.Forums;

public class ForumsService {
	// 封裝新增物件
	DAOInterface<Forums> dao;

	public ForumsService() {
		dao = new ForumsDAO();
	}
	public boolean add(String mem_no, String forum_name, String forum_desc, String forum_note) {
		Forums forums = new Forums();
		forums.setMem_no(mem_no);
		forums.setForum_name(forum_name);
		forums.setForum_desc(forum_desc);
		forums.setForum_note(forum_note);
		boolean result = dao.executeInsert(forums);
		return result;
	}
	public boolean add(String mem_no, String forum_name, String forum_desc, String forum_note, String[] art_type_name) {
		Forums forums = new Forums();
		forums.setMem_no(mem_no);
		forums.setForum_name(forum_name);
		forums.setForum_desc(forum_desc);
		forums.setForum_note(forum_note);
		boolean result = new ForumsDAO().executeInsert(forums,art_type_name);
		return result;
	}
	public boolean update(String forum_no, String forum_desc, String forum_note) {
		Forums forums = new Forums();
		forums.setForum_desc(forum_desc);
		forums.setForum_note(forum_note);
		forums.setForum_no(forum_no);

		boolean result = dao.updateByVO(forums);
		return result;
	}
	public boolean update(String forum_no, String forum_desc,String[] art_type_name) {
		Forums forums = new Forums();
		forums.setForum_desc(forum_desc);
		forums.setForum_no(forum_no);
		ForumsDAO forumDAO = new ForumsDAO();
		boolean result = forumDAO.updateByVO(forums,art_type_name);
		return result;
	}

	public boolean increaseViews(String forum_no) {
		ForumsDAO forumDAO = new ForumsDAO();
		String col = "forum_views,forum_mviews";
		Object[] param = { forum_no };
		Object[] viewVal = forumDAO.getCol(col, param);
		for (int i = 0; i < viewVal.length; i++) {
			viewVal[i] = Integer.parseInt(viewVal[i].toString()) + 1;
		}
		String SQL = "update forums set forum_views=?,forum_mviews=? where forum_no=" + forum_no;

		boolean result = forumDAO.executeUpdate(SQL, viewVal);
		return result;
	}
	public List<Forums> getApplyForums(int page, int pageSize){
		
		String where = "forum_stat=0";
		String order = "forum_date";
		List<Forums> list = dao.pageAndRank(page, pageSize, order, where);
		return list;
		
	}
	public boolean confirm(String forum_no,String forum_stat){
		boolean result = false;
		ForumsDAO forumDAO = new ForumsDAO();
		String SQL = "update forums set forum_stat=? where forum_no=?";
		Object[] param = {forum_stat,forum_no};
		result = forumDAO.executeUpdate(SQL, param);
		return result;
	}
	public Forums findByPK(String forum_no){
		return (Forums) dao.getVOByPK(forum_no);
	}
	public String getMem(String forum_no){
		ForumsDAO forumDAO = new ForumsDAO();
		Object[] param = {forum_no};
		String mem_no = String.valueOf(forumDAO.getCol("mem_no", param)[0]);
		return mem_no;
	}
	public int getApplyNum(int pageSize){
		ForumsDAO forumDAO = new ForumsDAO();
		String sql = "select count(*) from forums where forum_stat=0";
		return (forumDAO.countBySQL(sql)-1)/pageSize+1;
	}
}
