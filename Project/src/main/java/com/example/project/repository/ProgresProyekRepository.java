package com.example.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project.model.ProgresProyek;

@Repository
public interface ProgresProyekRepository extends CrudRepository<ProgresProyek,Long> {
	
	@Query("select a from ProgresProyek a where a.proyek.id=:id")
	List<ProgresProyek> findByProyek (@Param("id")long id);
}
