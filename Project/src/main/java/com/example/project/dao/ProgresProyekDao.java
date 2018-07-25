package com.example.project.dao;

import java.util.List;

import com.example.project.model.ProgresProyek;

public interface ProgresProyekDao {
	public ProgresProyek getById(long id);
	public List<ProgresProyek> getAll();
	public void saveOrUpdate(ProgresProyek pro);
	public List<ProgresProyek> getByProyek (long id);
}
