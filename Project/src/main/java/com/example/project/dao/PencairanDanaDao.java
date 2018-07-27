package com.example.project.dao;

import java.util.List;

import com.example.project.model.PencairanDana;

public interface PencairanDanaDao {
	public PencairanDana getById(long id);
	public List<PencairanDana> getAll();
	public void saveOrUpdate(PencairanDana pd);
	public float getByProyek(long id);
	
}
