package com.folcoach.model;

import java.util.Set;



public class FolcoachService {

	private FolcoachDAO_interface dao;

	public FolcoachService() {
		dao = new FolcoachDAO();
	}

	public FolcoachVO addFolcoach(String fc_acc, String fcp_acc, java.sql.Date fc_date) {

		FolcoachVO folcoachVO = new FolcoachVO();

		folcoachVO.setFc_acc(fc_acc);
		folcoachVO.setFcp_acc(fcp_acc);
		folcoachVO.setFc_date(fc_date);
		dao.insert(folcoachVO);
		
		return folcoachVO;
	}

	public Set<FolcoachVO> getFolcoachByFcp_acc(String fcp_acc) {
		return dao.getFolcoachByFcp_acc(fcp_acc);
	} 

	public void deleteFolcoach(String fc_acc, String fcp_acc) {
		dao.delete(fc_acc, fcp_acc);
	}
}
