package com.coaches.model;

import java.util.List;

import com.members.model.MembersVO;

public interface CoachesDAO_interface {
	public void insert(MembersVO membersVO, CoachesVO coachesVO);
	public CoachesVO login(String coa_account, String coa_password);
	public void update(CoachesVO coachesVO);
	public List<CoachesVO> getAll();
	public List<CoachesVO> searchCoa(String search_Name, String search_Type);
	public CoachesVO look_search_mem(MembersVO membersVO);
	public List<CoachesVO> getAllBySta(Integer coa_sta);
	public void setSta(Integer coa_sta,String coa_acc);
	public void update_forPic(String coa_acc, byte[] coa_pic_byte);
}