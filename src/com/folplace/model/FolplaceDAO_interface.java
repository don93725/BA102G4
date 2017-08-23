package com.folplace.model;

import java.util.*;



public interface FolplaceDAO_interface {
	public void insert(FolplaceVO folplaceVO);
    public void delete(String fp_no, String fpp_acc);
    public Set<FolplaceVO> getFolplaceByFpp_acc(String fpp_acc);
    

}

