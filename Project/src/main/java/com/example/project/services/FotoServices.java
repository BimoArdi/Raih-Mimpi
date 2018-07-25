package com.example.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.dao.FotoDao;
import com.example.project.model.Foto;
import com.example.project.repository.FotoRepository;

@Service
public class FotoServices implements FotoDao {

	@Autowired
	FotoRepository f;
	
	@Override
	public Foto getById(long id) {
		return f.findById(id).get() ;
	}

	@Override
	public List<Foto> getAll() {
		List<Foto> ff = new ArrayList<>();
		f.findAll().forEach(ff::add);
		return ff;
	}
	
	@Override
	public void saveOrUpdate(Foto ff) {
		f.save(ff);
	}

	@Override
	public Foto getPhoto (long id) {
		return f.findPhoto(id);
	}
	
	@Override
	public List<Foto> getAllPhoto (long id) {
		return f.findAllPhoto(id);
	}
	
	@Override
	public List<Foto> getTanpaUtamaProyek (long id){
		return f.findTanpaUtama(id);
	}
	
	@Override
	public List<Foto> getTanpaUtamaProgresProyek (long id){
		return f.findTanpaUtamaProgresProyek(id);
	}
}
