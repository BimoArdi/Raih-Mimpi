package com.example.project.dao;

import java.util.List;

import com.example.project.model.Sponsor;

public interface SponsorDao {
	public Sponsor getById (long id);
	public List<Sponsor> getAll ();
	public List<Sponsor> getSponsorById(Long id);
	public void saveOrUpdate(Sponsor s);
	public List<Sponsor> getByProyek (long id);
	public double jumlahdana(long id);
	
}
