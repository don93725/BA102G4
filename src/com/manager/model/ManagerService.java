package com.manager.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.members.model.MembersVO;

public class ManagerService {
	
	private ManagerDAO_interface dao;

	public ManagerService() {
		dao = new ManagerDAO();
	}

	public ManagerVO addMgr(String mem_nickname,String mgr_id,String mgr_pwd,Integer mgr_job,String mgr_name,String mgr_email,Integer mgr_status,byte[] mgr_pic,String mgr_int){
		
		MembersVO membersVO = new MembersVO();
		ManagerVO managerVO = new ManagerVO();
		
//		membersVO.setMem_acc(mem_acc);		
		membersVO.setMem_nickname(mem_nickname);
		
		managerVO.setMgr_id(mgr_id);
		managerVO.setMgr_pwd(mgr_pwd);
		managerVO.setMgr_job(mgr_job);
		managerVO.setMgr_name(mgr_name);
		managerVO.setMgr_email(mgr_email);
		managerVO.setMgr_status(mgr_status);
		managerVO.setMgr_pic(mgr_pic);
		managerVO.setMgr_int(mgr_int);
		dao.insert(managerVO, membersVO);
		
		
		//???????????? no return membersVO
		return managerVO;
	}
	public ManagerVO updateMgr(String mem_nickname,String mgr_no,String mgr_id,String mgr_pwd,Integer mgr_job,String mgr_name,String mgr_email,Integer mgr_status,byte[] mgr_pic,String mgr_int){
		ManagerVO managerVO = new ManagerVO();
		MembersVO membersVO = new MembersVO();
		
		membersVO.setMem_nickname(mem_nickname);
		managerVO.setMgr_no(mgr_no);
		managerVO.setMgr_id(mgr_id);
		managerVO.setMgr_pwd(mgr_pwd);
		managerVO.setMgr_job(mgr_job);
		managerVO.setMgr_name(mgr_name);
		managerVO.setMgr_email(mgr_email);
		managerVO.setMgr_status(mgr_status);
		managerVO.setMgr_pic(mgr_pic);
		managerVO.setMgr_int(mgr_int);

		dao.update(managerVO,membersVO);
		return managerVO; 
	}
	public void deleteMgr(String mgr_no){
		dao.delete(mgr_no);
	}
	public ManagerVO getOneMgr(String mgr_no){
		return dao.findByPrimaryKey(mgr_no);
	}
	
	public List<ManagerVO> getAll(){
		return dao.getAll();
	}
	public List<ManagerVO> getAll(Map<String,String[]> map){
		return dao.getAll(map);
	}
	public Set<ManagerVO> getMgrByGob(Integer mgr_job){
		return dao.getMgrByJob(mgr_job);
	}
	public Set<ManagerVO> getMgrByStatus(Integer mgr_status){
		return dao.getMgrByStatus(mgr_status);
	}
	public boolean loginMgr(String mgr_account,String mgr_password){
		return dao.login(mgr_account, mgr_password);
	}
	public ManagerVO loginMgrTest(String mgr_account,String mgr_password){
		return dao.loginTest(mgr_account, mgr_password);
	}
}
