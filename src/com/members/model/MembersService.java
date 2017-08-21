package com.members.model;

import java.util.List;

public class MembersService {
	
	private MembersDAO_interface dao;
	
	public MembersService(){
		dao = new MembersDAO();
	}
	
	public MembersVO select(String mem_acc) {
		return dao.select(mem_acc);
	}
	
	public Boolean insert_ck(String mem_acc) {
		return dao.insert_ck(mem_acc);
	}
	
	public MembersVO update(MembersVO membersVO, String mem_nickname) {
		membersVO.setMem_nickname(mem_nickname);	
		dao.update(membersVO);	
		return membersVO;
	}

	public MembersVO look_search_mem(String coa_no) {
		return dao.look_search_mem(coa_no);
	}
	public  MembersVO getPersonalComments(String coa_no){
		return ((MembersDAO)dao).getPersonalComments(coa_no);
	}
	public MembersVO getOneMem(String mem_no){
		return ((MembersDAO)dao).findByPrimaryKey(mem_no);
	}
	public MembersVO getMemAcc(String mem_acc){
		return ((MembersDAO)dao).GetOneMem(mem_acc);
	}
}
