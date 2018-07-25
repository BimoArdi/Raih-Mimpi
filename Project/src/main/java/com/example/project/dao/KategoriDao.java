package com.example.project.dao;

import java.util.List;

import com.example.project.model.Kategori;

public interface KategoriDao {
	public Kategori getById(long id);
	public List<Kategori> getAll();
	public void saveOrUpdate(Kategori kt);
	public Kategori getByNama(String namaKategori);
	
}
