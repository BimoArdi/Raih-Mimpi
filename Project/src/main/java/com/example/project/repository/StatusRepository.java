package com.example.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.Status;

@Repository
public interface StatusRepository extends CrudRepository<Status,Long> {
	Status findByStatus(String status);

}
