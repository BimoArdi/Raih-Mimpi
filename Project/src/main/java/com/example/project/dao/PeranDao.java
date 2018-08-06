package com.example.project.dao;

import java.util.List;

import com.example.project.model.Peran;

public interface PeranDao {
	public Peran getById(long id);
	public List<Peran> getAll();
	public void saveOrUpdate(Peran peran);
	public Peran getPeran (String peran);
}
