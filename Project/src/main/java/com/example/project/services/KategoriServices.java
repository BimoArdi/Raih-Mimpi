package com.example.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.dao.KategoriDao;
import com.example.project.model.Kategori;
import com.example.project.repository.KategoriRepository;

@Service
public class KategoriServices implements KategoriDao {
	
	@Autowired
	KategoriRepository k;
	
	@Override
	public Kategori getById(long id) {
		return k.findById(id).get() ;
	}

	@Override
	public List<Kategori> getAll() {
		List<Kategori> kt = new ArrayList<>();
		k.findAll().forEach(kt::add);
		return kt;
	}
	
	@Override
	public void saveOrUpdate(Kategori kt) {
		k.save(kt);
	}
	
	@Override
	public Kategori getByNama(String namaKategori) {
		return k.findByNamaKategori(namaKategori);
	}
}
