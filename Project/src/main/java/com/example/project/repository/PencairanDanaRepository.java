package com.example.project.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project.model.PencairanDana;

@Repository
public interface PencairanDanaRepository extends CrudRepository<PencairanDana, Long> {

	@Query("select sum(a.jumlahDana) from PencairanDana a where a.proyek.id=:id")
	float sumByProyek (@Param("id")long id);
}
