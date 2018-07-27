package com.example.project.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project.model.Komentar;

@Repository
public interface KomentarRepository extends CrudRepository<Komentar,Long> {
	
	@Query("select a from Komentar a where a.proyek.id=:id")
	List<Komentar> findByProyek(@Param("id")long id);
	
}

