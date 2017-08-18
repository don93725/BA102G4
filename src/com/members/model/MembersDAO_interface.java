package com.members.model;

import java.util.List;

public interface MembersDAO_interface {
	public MembersVO select(String mem_acc);
	public Boolean insert_ck(String mem_acc);
	public void update(MembersVO membersVO);
	public MembersVO look_search_mem(String coa_no);
}
