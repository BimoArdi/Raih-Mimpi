package com.example.project.dao;

import java.util.List;

import com.example.project.model.Pengguna;

public interface PenggunaDao {
	public Pengguna getId(long id);
	public Pengguna getNama (String nama);
	public List<Pengguna> getAll();
	public Pengguna getByTelp(String telepon);
	public void saveOrUpdate(Pengguna pg);
	public Pengguna getByLogin (Long id);
}
