package com.example.project.dao;

import java.util.List;

import com.example.project.model.Proyek;

public interface ProyekDao {
	public Proyek getById(long id);
	public List<Proyek> getAll();
	public void saveOrUpdate(Proyek pro);
	public List<Proyek> getByKategori(long id1);
	public List<Proyek> getTheUser(long id);
	public List<Proyek> getAllProyek();
	
}
