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
	public List<Proyek> getByKategori(long id1){
		return p.findByKategori(id1);
	}
	
	@Override
	public List<Proyek> getTheUser(long id) {
		return p.findByUser(id);
	}

	@Override
	public List<Proyek> getAllProyek() {
		return p.findAllProyek();
	}

}
