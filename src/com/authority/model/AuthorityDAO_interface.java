package com.authority.model;

import java.util.List;
import java.util.Set;

import com.manager.model.ManagerVO;

public interface AuthorityDAO_interface {

	public void insert(AuthorityVO authorityVO);
//	public void update(AuthorityVO authorityVO);
	public void delete(String mgr_no);
	public AuthorityVO findByPrimaryKey(String mgr_no,String f_no);
	public List<AuthorityVO> getAll();
	public Set<String> getFun(String mgr_no);
	public ManagerVO findByMgrNo(String mgr_id);
}
