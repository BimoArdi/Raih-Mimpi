package com.example.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project.model.Proyek;

@Repository
public interface ProyekRepository extends CrudRepository<Proyek,Long> {
	
	@Query("select a from Proyek a where a.kategori.id=:id and a.status=true")
	List<Proyek> findByKategori(@Param("id")long id1);
		
	@Query("select a from Proyek a where a.pengguna.id=:id and a.status=true")
	List<Proyek> findByUser(@Param("id")long id);
	
	@Query("select a from Proyek a where a.status=true")
	List<Proyek> findAllProyek();
	
		
}
