package com.example.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.dao.SponsorDao;
import com.example.project.model.Sponsor;
import com.example.project.repository.SponsorRepository;

@Service
public class SponsorServices implements SponsorDao {

	@Autowired
	SponsorRepository sr;
	
	@Autowired
	PenggunaServices pgs;
	
	@Autowired
	ProyekServices ps;
	
	@Override
	public Sponsor getById(long id) {
		return sr.findById(id).get();
	}

	@Override
	public List<Sponsor> getAll() {
		List<Sponsor> ls = new ArrayList<>();
		sr.findAll().forEach(ls::add);
		return ls;
	}
	
//	@Override
//	public List<Sponsor> getSponsorById(Long id){
//		return sr.findSponsorById(id);
//	}

	@Override
	public void saveOrUpdate(Sponsor s) {
		sr.save(s);
	}
	
	@Override
	public List<Sponsor> getByProyek (long id){
		return sr.findByProyek(ps.getById(id));
	}

	@Override
	public int getJumlahSponsor(long id) {
		return sr.findSponsorbyProyek(id);
	}
	
	@Override
	public float getTotalSponsor(long id) {
		return sr.findSumProyek(id);
	}
	
	@Override
	public int getTotalSponsor() {
		return sr.findTotalSponsor();
	}

	@Override
	public List<Sponsor> getByPengguna(long id) {
		return sr.findByPengguna(pgs.getId(id));
	}


}
