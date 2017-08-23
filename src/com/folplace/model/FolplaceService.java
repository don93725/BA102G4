package com.folplace.model;

import java.util.Set;



public class FolplaceService {

	private FolplaceDAO_interface dao;

	public FolplaceService() {
		dao = new FolplaceDAO();
	}

	public FolplaceVO addFolplace(String fp_no, String fpp_acc) {

		FolplaceVO folplaceVO = new FolplaceVO();

		folplaceVO.setFp_no(fp_no);
		folplaceVO.setFpp_acc(fpp_acc);
		dao.insert(folplaceVO);
		
		return folplaceVO;
	}

	public Set<FolplaceVO> getFolplaceByFpp_acc(String fpp_acc) {
		return dao.getFolplaceByFpp_acc(fpp_acc);
	} 

	public void deleteFolplace(String fp_no, String fpp_acc) {
		dao.delete(fp_no, fpp_acc);
	}
}

