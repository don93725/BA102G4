package com.Course_list.model;

import java.sql.Date;
import java.util.List;

public class course_listService {
	private Course_listDAO_interface dao;

	public course_listService() {
		dao = new Course_listDAO();
	}

	public Course_listVO addCourse_list() {
		Course_listVO course_listVO = new Course_listVO();
		dao.insert(course_listVO);
		return course_listVO;
	}

	public Course_listVO updateCourse_list() {
		Course_listVO course_listVO = new Course_listVO();
		dao.update(course_listVO);
		return course_listVO;
	}

	public void deleteCourse_list(String ct_no, String stu_acc) {
		dao.delete(ct_no, stu_acc);
	}

	public Course_listVO getOneCourse_list(String ct_no, String stu_acc) {
		return dao.findByPK(ct_no, stu_acc);
	}

	public List<Course_listVO> getAll() {
		return dao.getAll();
	}

	// 77777 77777
	public List<Course_listVO> getReportSta(Integer report_sta) {
		return dao.getReportSta(report_sta);
	}

	public Course_listVO updateReportSta(String ct_no) {
		Course_listVO course_listVO = new Course_listVO();
		course_listVO.setCt_no(ct_no);
		dao.updateRepSta(course_listVO);
		return course_listVO;
	}

	public Course_listVO updateCRNum(String ct_no) {
		Course_listVO course_listVO = new Course_listVO();
		course_listVO.setCt_no(ct_no);
		
		dao.updateCRNum(course_listVO);
		return course_listVO;
	}

}
