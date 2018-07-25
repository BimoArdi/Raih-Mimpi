package com.example.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.dao.WilayahDao;
import com.example.project.model.Wilayah;
import com.example.project.repository.WilayahRepository;

@Service
public class WilayahServices implements WilayahDao {
	@Autowired
	WilayahRepository w;
	
	@Override
	public Wilayah getById(long id) {
		return w.findById(id).get() ;
	}

	@Override
	public List<Wilayah> getAll() {
		List<Wilayah> wi = new ArrayList<>();
		w.findAll().forEach(wi::add);
		return wi;
	}
	
	@Override
	public void saveOrUpdate(Wilayah wi) {
		w.save(wi);
	}

}
