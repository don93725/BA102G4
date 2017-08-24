package com.folcoach.model;

import java.util.*;



public interface FolcoachDAO_interface {
	public void insert(FolcoachVO folcoachVO);
    public void delete(String fc_acc, String fcp_acc);
    public Set<FolcoachVO> getFolcoachByFcp_acc(String fcp_acc);
    

}
