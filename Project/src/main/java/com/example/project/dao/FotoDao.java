package com.example.project.dao;

import java.util.List;

import com.example.project.model.Foto;

public interface FotoDao {
	public Foto getById(long id);
	public List<Foto> getAll();
	public void saveOrUpdate(Foto ff);
	public Foto getPhoto (long id);
	public List<Foto> getAllPhoto (long id);
	public List<Foto> getTanpaUtamaProyek (long id);
	public List<Foto> getTanpaUtamaProgresProyek (long id);
	
}
