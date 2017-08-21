package com.forums.model;

import java.util.List;
import java.util.Set;


public interface ForumsDAO_interface {
	public void insert(ForumsVO forumsVO);
	public void update(ForumsVO forumsVO);
	public void delete(String forum_no);
	public ForumsVO findByPrimaryKey(String forum_no);
	public List<ForumsVO> getAll();
	public Set<ForumsVO> getStat(String forum_stat);
}
