package com.folgym.model;

import java.util.Set;



public class FolgymService {

	private FolgymDAO_interface dao;

	public FolgymService() {
		dao = new FolgymDAO();
	}

	public FolgymVO addFolgym(String fg_acc, String fgp_acc, java.sql.Date fg_date) {

		FolgymVO folgymVO = new FolgymVO();

		folgymVO.setFg_acc(fg_acc);
		folgymVO.setFgp_acc(fgp_acc);
		folgymVO.setFg_date(fg_date);
		dao.insert(folgymVO);
		
		return folgymVO;
	}

	public Set<FolgymVO> getFolgymByFgp_acc(String fgp_acc) {
		return dao.getFolgymByFgp_acc(fgp_acc);
	}

	public void deleteFolgym(String fg_acc, String fgp_acc) {
		dao.delete(fg_acc, fgp_acc);
	}
}

