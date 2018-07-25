package com.example.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project.model.Foto;

@Repository
public interface FotoRepository extends CrudRepository<Foto, Long>  {

	@Query("select a from Foto a where a.proyek.id=:id")
	Foto findPhoto(@Param("id")long id);
	
	@Query("select a.foto from Foto a where a.proyek.id=:id")
	List<Foto> findAllPhoto(@Param("id")long id);
	
	@Query("select a from Foto a where a.proyek.id=:id and a.utamaProyek=false and a.progresProyek.id=null")
	List<Foto> findTanpaUtama(@Param("id")long id);
	
	@Query("select a from Foto a where a.progresProyek.id =:id and a.utamaProgresProyek=false")
	List<Foto> findTanpaUtamaProgresProyek(@Param("id") long id);

}
