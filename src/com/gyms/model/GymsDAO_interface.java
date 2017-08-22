package com.gyms.model;

import java.util.List;

import com.members.model.MembersVO;

public interface GymsDAO_interface {
	public void insert(MembersVO membersVO, GymsVO gymsVO);
	public GymsVO login(String coa_account, String coa_password);
	public void update(GymsVO gymsVO);
	public List<GymsVO> getAllBySta(Integer gym_sta);
	public void setSta(Integer gym_sta ,String gym_acc);
} 
