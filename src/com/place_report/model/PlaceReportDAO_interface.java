package com.place_report.model;

import java.util.List;

public interface PlaceReportDAO_interface {

	public void insert(PlaceReportVO placeReportVO);
	public void update(PlaceReportVO placeReportVO);
	public void updatePRNum(PlaceReportVO placeReportVO);
	public List<PlaceReportVO> getAll();
	public List<PlaceReportVO> getStat(Integer pr_stat);
	public PlaceReportVO getByPt(String pt_no);
}
