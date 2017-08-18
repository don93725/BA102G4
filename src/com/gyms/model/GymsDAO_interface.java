package com.gyms.model;

import com.members.model.MembersVO;

public interface GymsDAO_interface {
	public void insert(MembersVO membersVO, GymsVO gymsVO);
	public GymsVO login(String coa_account, String coa_password);
	public void update(GymsVO gymsVO);
}
