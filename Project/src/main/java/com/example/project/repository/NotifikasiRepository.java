package com.example.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.Notifikasi;

@Repository
public interface NotifikasiRepository extends CrudRepository<Notifikasi,Long> {
	
}
