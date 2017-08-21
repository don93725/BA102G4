package com.manager.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.members.model.MembersVO;

public interface ManagerDAO_interface {
	public void insert(ManagerVO managerVO,MembersVO membersVO);
	public void update(ManagerVO managerVO,MembersVO membersVO);
	public void delete(String mgr_no);
	public ManagerVO findByPrimaryKey(String mgr_no);
	public List<ManagerVO> getAll();
	public Set<ManagerVO> getMgrByJob(Integer mgr_job);
	public Set<ManagerVO> getMgrByStatus(Integer mgr_status);
	public ManagerVO loginTest(String mgr_account,String mgr_password);
	public boolean login(String mgr_account, String mgr_password);
//	public boolean loginMgrSta(String mgr_account);
	 //萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<ManagerVO> getAll(Map<String, String[]> map); 
	
}
