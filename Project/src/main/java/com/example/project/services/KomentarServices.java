package com.example.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.dao.KomentarDao;
import com.example.project.model.Komentar;
import com.example.project.repository.KomentarRepository;

@Service
public class KomentarServices implements KomentarDao{
	@Autowired
	KomentarRepository kr;
	
	@Autowired
	ProyekServices ps;
	
	@Autowired
	ProgresProyekServices pps;

	@Override
	public Komentar getById(long id) {
		return kr.findById(id).get() ;
	}

	@Override
	public List<Komentar> getAll() {
		List<Komentar> komentar = new ArrayList<>();
		kr.findAll().forEach(komentar::add);
		return komentar;
	}
	
	@Override
	public void saveOrUpdate(Komentar komentar) {
		kr.save(komentar);
	}
	
	@Override
	public List<Komentar> getByProyek(long id){
		return kr.findByProyek(ps.getById(id));
	}
	
	@Override
	public List<Komentar> getByProgresProyek(long id){
		return kr.findByProgresProyek(pps.getById(id));
	}

}
