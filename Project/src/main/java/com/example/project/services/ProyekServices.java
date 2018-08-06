package com.example.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.dao.ProyekDao;
import com.example.project.model.Proyek;
import com.example.project.repository.ProyekRepository;

@Service
public class ProyekServices implements ProyekDao{
	
	@Autowired
	ProyekRepository p;
	
	@Autowired
	PenggunaServices ps;

	@Autowired
	KategoriServices ks;
	
	@Override
	public Proyek getById(long id) {
		return p.findById(id).get() ;
	}

	@Override
	public List<Proyek> getAll() {
		List<Proyek> pro = new ArrayList<>();
		p.findAll().forEach(pro::add);
		return pro;
	}
	
	@Override
	public void saveOrUpdate(Proyek pro) {
		p.save(pro);
	}
	
	@Override
	public List<Proyek> getByKategoriAndStatus(long id,String status){
		return p.findByKategoriAndStatus(ks.getById(id), status);
	}
	
	@Override
	public List<Proyek> getTheUser(long id) {
		return p.findByPengguna(ps.getId(id));
	}


	@Override
	public List<Proyek> getByFitur() {
		return p.findByFitur(true);
	}

	@Override
	public float getSisaDana(long id) {
		return p.findSisaDana(id);
	}

	@Override
	public int getJumlahKategori(long id) {
		return p.findJumlahKategori(id);
	}
	
	@Override
	public List<Proyek> getByLast() {
		return p.findByLast();
	}

	@Override
	public List<Proyek> getByStatus(String status) {
		return p.findByStatus(status);
	}
	
	@Override
	public List<Proyek> getBySearch(String keyword) {
		return p.findBySearch(keyword);
	}

	@Override
	public Proyek getByProyek(String namaProyek) {
		return p.findByNamaProyek(namaProyek);
	}

	@Override
	public int findTotalStatusandPengguna(String status, long id) {
		return p.findTotalStatus(status, id);
	}

	@Override
	public List<Proyek> getByPenggunaAndStatus(long id, String status) {
		return p.findByPenggunaAndStatus(ps.getId(id), status);
	}

}
