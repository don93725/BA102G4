package com.forums.model;

import java.util.List;
import java.util.Set;

public class ForumsService {

	private ForumsDAO_interface dao;
	
	public ForumsService(){
		dao = new ForumsDAO();
	}
	public ForumsVO addForum(String mem_no,String forum_name,String forum_desc,String forum_note){
		ForumsVO forumsVO = new ForumsVO();
		
		forumsVO.setMem_no(mem_no);
		forumsVO.setForum_name(forum_name);
		forumsVO.setForum_desc(forum_desc);
		forumsVO.setForum_note(forum_note);
		dao.insert(forumsVO);
		return forumsVO;
	}
	public ForumsVO updateForum(String forum_stat,String forum_no){
		ForumsVO forumsVO = new ForumsVO();
		
		forumsVO.setForum_stat(forum_stat);
		forumsVO.setForum_no(forum_no);
		dao.update(forumsVO);
		return forumsVO;
	}
	public void deleteForum(String forum_no){
		dao.delete(forum_no);
	}
	public ForumsVO getOneForum(String forum_no){
		return dao.findByPrimaryKey(forum_no);
	}
	public List<ForumsVO> getAll(){
		return dao.getAll();
	}
	public Set<ForumsVO> getStat(String forum_stat){
		return dao.getStat(forum_stat);
	}
		
}
