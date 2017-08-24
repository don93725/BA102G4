package com.folcourse.model;

import java.util.Set;



public class FolcourseService {

	private FolcourseDAO_interface dao;

	public FolcourseService() {
		dao = new FolcourseDAO();
	}

	public FolcourseVO addFolcourse(String fcrs_no, String fcrsp_acc, java.sql.Date fcrs_date) {

		FolcourseVO folcourseVO = new FolcourseVO();

		folcourseVO.setFcrs_no(fcrs_no);
		folcourseVO.setFcrsp_acc(fcrsp_acc);
		folcourseVO.setFcrs_date(fcrs_date);
		dao.insert(folcourseVO);
		
		return folcourseVO;
	}

	public Set<FolcourseVO> getFolcourseByFcrsp_acc(String fcrsp_acc) {
		return dao.getFolcourseByFcrsp_acc(fcrsp_acc);
	}

	public void deleteFolcourse(String fcrs_no, String fcrsp_acc) {
		dao.delete(fcrs_no, fcrsp_acc);
	}
}

