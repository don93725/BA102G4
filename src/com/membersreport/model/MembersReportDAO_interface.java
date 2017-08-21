package com.membersreport.model;

import java.util.List;
import java.util.Set;

public interface MembersReportDAO_interface {
	public void insert(MembersReportVO membersReportVO);
	public void update(MembersReportVO membersReportVO);
	public void updateMRNum(MembersReportVO membersReportVO);	
	public void delete(String mr_no);
	public MembersReportVO findByPrimaryKey(String mr_no);
	public List<MembersReportVO> getAll();
	public Set<MembersReportVO> getStat(Integer mr_stat);
	
}
