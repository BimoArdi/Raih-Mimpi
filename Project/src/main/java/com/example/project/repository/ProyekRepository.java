package com.example.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project.model.Kategori;
import com.example.project.model.Pengguna;
import com.example.project.model.Proyek;
import com.example.project.model.Status;

@Repository
public interface ProyekRepository extends CrudRepository<Proyek,Long> {
	
	List<Proyek> findByKategoriAndStatus(Kategori byId, Status byStatus);	
	List<Proyek> findByPengguna (Pengguna byId);
	List<Proyek> findByStatus(Status byId);
	List<Proyek> findByFitur(boolean fitur);
	Proyek findByNamaProyek (String namaProyek);
	@Query("select a.sisaDana from Proyek a where a.id=:id")
	float findSisaDana(@Param("id")long id);
	
	@Query("select count(a.id) from Proyek a where a.kategori.id=:id")
	int findJumlahKategori(@Param("id")long id);
	
	@Query(value="SELECT * FROM proyek a ORDER BY a.create_date DESC", nativeQuery=true)
	List<Proyek> findByLast();
		
	@Query("select a from Proyek a where a.namaProyek || a.createDate || a.deskripsiLengkap ||"
			+ "a.deskripsiSingkat || a.link || a.tanggalBerakhir || a.targetDana || a.totalDana || "
			+ "a.kategori.namaKategori || a.pengguna.nama || a.wilayah.namaWilayah like %:keyword%")
	List<Proyek> findBySearch (@Param("keyword") String keyword);
}
