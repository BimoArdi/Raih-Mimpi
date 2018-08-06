package com.example.project.dao;

import java.util.List;

import com.example.project.model.Sponsor;

public interface SponsorDao {
	public Sponsor getById (long id);
	public List<Sponsor> getAll();
//	public List<Sponsor> getSponsorById(Long id);
	public void saveOrUpdate(Sponsor s);
	public List<Sponsor> getByProyek (long id);
	public int getJumlahSponsor (long id);
	public float getTotalSponsor (long id);
	public int getTotalSponsor();
	public List<Sponsor> getByPengguna(long id);
	public int getByStatusandPengguna (String Status, long id);
	public float getTotalSponsorbyPengguna(long id);
	public float getTotalByPenggunaAndProyek(long id);
}