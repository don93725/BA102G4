package com.folgym.model;

import java.util.*;



public interface FolgymDAO_interface {
	public void insert(FolgymVO folgymVO);
    public void delete(String fg_acc, String fgp_acc);
    public Set<FolgymVO> getFolgymByFgp_acc(String fgp_acc);
    

}