package com.example.project.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.Komentar;
import com.example.project.model.ProgresProyek;
import com.example.project.model.Proyek;

@Repository
public interface KomentarRepository extends CrudRepository<Komentar,Long> {
	
	List<Komentar> findByProyek(Proyek byId);
	
	List<Komentar> findByProgresProyek(ProgresProyek byId);
}

