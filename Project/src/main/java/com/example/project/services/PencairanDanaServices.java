package com.example.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.dao.PencairanDanaDao;
import com.example.project.model.PencairanDana;
import com.example.project.repository.PencairanDanaRepository;

@Service
public class PencairanDanaServices implements PencairanDanaDao {

	@Autowired
	PencairanDanaRepository p;
	
	@Autowired
	ProyekServices ps;

	@Override
	public PencairanDana getById(long id) {
		return p.findById(id).get() ;
	}

	@Override
	public List<PencairanDana> getAll() {
		List<PencairanDana> pd = new ArrayList<>();
		p.findAll().forEach(pd::add);
		return pd;
	}
	
	@Override
	public void saveOrUpdate(PencairanDana pd) {
		p.save(pd);
	}
	
	@Override
	public float getByProyek(long id) {
		return p.sumByProyek(id);
	}

}
