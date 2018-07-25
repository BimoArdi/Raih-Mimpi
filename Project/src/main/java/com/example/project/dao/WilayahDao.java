package com.example.project.dao;

import java.util.List;

import com.example.project.model.Wilayah;

public interface WilayahDao {
	public Wilayah getById(long id);
	public List<Wilayah> getAll();
	public void saveOrUpdate(Wilayah wi);
	
}
