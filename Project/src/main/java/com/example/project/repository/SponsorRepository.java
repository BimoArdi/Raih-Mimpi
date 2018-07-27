package com.example.project.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project.model.Pengguna;
import com.example.project.model.Proyek;
import com.example.project.model.Sponsor;

@Repository
public interface SponsorRepository extends CrudRepository<Sponsor,Long> {
	
	
	List<Sponsor> findByProyek(Proyek byId);
	
	@Query("select count(a.jumlahSponsor) from Sponsor a where a.proyek.id =:id")
	int findSponsorbyProyek(@Param("id")long id);
	
	@Query("select sum(a.jumlahSponsor) from Sponsor a where a.proyek.id=:id")
	float findSumProyek(@Param("id") long id);
	
	@Query("select count(a) from Sponsor a")
	int findTotalSponsor();
	
	public List<Sponsor> findByPengguna(Pengguna byId);
}
