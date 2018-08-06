package com.example.project.dao;

import java.util.List;

import com.example.project.model.Komentar;

public interface KomentarDao {
	public Komentar getById(long id);
	public List<Komentar> getAll();
	public void saveOrUpdate(Komentar komentar);
	public List<Komentar> getByProyek(long id);
	public List<Komentar> getByProgresProyek(long id);

}
