package com.place_publish.model;

public interface Place_PublishDAO_interface {
	public void insert(Place_PublishVO place_publishVO);
	public void unPublish(String p_no);
	public void order(Place_PublishVO pp);
}
