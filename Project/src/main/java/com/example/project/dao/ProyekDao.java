package com.example.project.dao;

import java.util.List;

import com.example.project.model.Proyek;

public interface ProyekDao {
	public Proyek getById(long id);
	public List<Proyek> getAll();
	public void saveOrUpdate(Proyek pro);
	public List<Proyek> getByKategoriAndStatus(long id,String status);
	public List<Proyek> getTheUser(long id);
	public List<Proyek> getByFitur();
	public float getSisaDana(long id);
	public int getJumlahKategori(long id);
	public List<Proyek> getByLast();
	public List<Proyek> getByStatus(String status);
	public List<Proyek> getBySearch (String keyword);
	public  Proyek getByProyek(String namaProyek);
	
}
