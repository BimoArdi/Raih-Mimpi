package com.example.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.dao.PenggunaDao;
import com.example.project.model.Pengguna;
import com.example.project.repository.PenggunaRepository;

@Service
public class PenggunaServices implements PenggunaDao {

	@Autowired
	PenggunaRepository p;

	@Autowired
	LoginServices ls;

	@Override
	public Pengguna getId(long id) {
		return p.findById(id).get(); 
	}

	@Override
	public Pengguna getNama(String nama) {
		return p.findByNama(nama);
	}

	@Override
	public List<Pengguna> getAll() {
		List<Pengguna> lp = new ArrayList<>();
		p.findAll().forEach(lp::add);
		return lp;
	}

	@Override
	public Pengguna getByTelp(String telepon) {
		return p.findByTelephone(telepon);
	}

	@Override
	public void saveOrUpdate(Pengguna pg) {
		p.save(pg);
	}
	
	@Override
	public Pengguna getByLogin (Long id) {
		return p.findByLogin(ls.getById(id));
	}

}
