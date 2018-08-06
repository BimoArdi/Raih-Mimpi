package com.example.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.dao.ProgresProyekDao;
import com.example.project.model.ProgresProyek;
import com.example.project.repository.ProgresProyekRepository;

@Service
public class ProgresProyekServices implements ProgresProyekDao {

	@Autowired
	ProgresProyekRepository p;
	
	@Autowired
	ProyekServices ps;

	@Override
	public ProgresProyek getById(long id) {
		return p.findById(id).get() ;
	}

	@Override
	public List<ProgresProyek> getAll() {
		List<ProgresProyek> pro = new ArrayList<>();
		p.findAll().forEach(pro::add);
		return pro;
	}
	
	@Override
	public void saveOrUpdate(ProgresProyek pro) {
		p.save(pro);
	}
	
	@Override
	public List<ProgresProyek> getByProyek (long id){
		return p.findByProyek(ps.getById(id));
	}
}
