package com.example.project.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project.model.Sponsor;

@Repository
public interface SponsorRepository extends CrudRepository<Sponsor,Long> {
	
	@Query(value="select a from Sponsor a where a.pengguna.id=:id")
//	@Query(value="SELECT b.nama_projek, a.jumlah_sponsor, a.tanggal_sponsor, b.status FROM sponsor a "
//			+ "join proyek b on a.id_proyek = b.id WHERE a.id_pengguna=?id", nativeQuery=true)
//	@Query(value="SELECT a.jumlah_sponsor, a.tanggal_sponsor FROM sponsor a id_pengguna=?id", nativeQuery=true)
	public List<Sponsor> findSponsorById(@Param("id")Long id);
	
	@Query("select a from Sponsor a where a.proyek.id=:id")
	List<Sponsor> findByProyek(@Param("id")long id);
	
	@Query(value="SELECT SUM(jumlah_sponsor) from sponsor WHERE id_proyek=:id",nativeQuery=true)
	double jumlahdana(@Param("id")long id);
}
