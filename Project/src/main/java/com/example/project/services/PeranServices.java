package com.example.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.dao.PeranDao;
import com.example.project.model.Peran;
import com.example.project.repository.PeranRepository;

@Service
public class PeranServices implements PeranDao {
	
	@Autowired
	PeranRepository pr;
	
	@Override
	public Peran getById(long id) {
		return pr.findById(id).get() ;
	}

	@Override
	public List<Peran> getAll() {
		List<Peran> lp = new ArrayList<>();
		pr.findAll().forEach(lp::add);
		return lp;
	}
	
	@Override
	public void saveOrUpdate(Peran peran) {
		pr.save(peran);
	}

	@Override
	public Peran getPeran(String peran) {
		return pr.findByPeran(peran);
	}


}
