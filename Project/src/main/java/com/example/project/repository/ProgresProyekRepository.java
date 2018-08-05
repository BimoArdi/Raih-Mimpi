package com.example.project.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.ProgresProyek;
import com.example.project.model.Proyek;

@Repository
public interface ProgresProyekRepository extends CrudRepository<ProgresProyek,Long> {
	
	List<ProgresProyek> findByProyek (Proyek byId);
}
