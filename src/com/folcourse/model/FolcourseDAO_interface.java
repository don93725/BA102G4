package com.folcourse.model;

import java.util.*;



public interface FolcourseDAO_interface {
	public void insert(FolcourseVO folcourseVO);
    public void delete(String fcrs_no, String fcrsp_acc);
    public Set<FolcourseVO> getFolcourseByFcrsp_acc(String fcrsp_acc);
    

}
