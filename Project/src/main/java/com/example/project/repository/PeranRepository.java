package com.example.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.Peran;

@Repository
public interface PeranRepository extends CrudRepository<Peran,Long> {
	Peran findByPeran (String peran);
}
