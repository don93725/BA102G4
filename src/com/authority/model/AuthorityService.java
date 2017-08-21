package com.authority.model;

import java.util.List;
import java.util.Set;

import com.manager.model.ManagerVO;

public class AuthorityService {

	private AuthorityDAO_interface dao;
	
	public AuthorityService(){
		dao = new AuthorityDAO();
	}
	public AuthorityVO addAut(String mgr_no , String f_no){
		
		AuthorityVO authorityVO = new AuthorityVO();
		
		authorityVO.setMgr_no(mgr_no);
		authorityVO.setF_no(f_no);
		dao.insert(authorityVO);
		return authorityVO;
	}
	
	public void deleteAut(String mgr_no){
		dao.delete(mgr_no);
	}
	
	public AuthorityVO getOneFun(String mgr_no,String f_no){
		return dao.findByPrimaryKey(mgr_no,f_no);
	}
	
	public List<AuthorityVO> getAll(){
		return dao.getAll();
	}
	public Set<AuthorityVO> getFno(String mgr_no){
		return dao.getFun(mgr_no);
	}
	public ManagerVO getMgrNO(String mgr_id){
		return dao.findByMgrNo(mgr_id);
	}
}
