package com.example.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.PencairanDana;

@Repository
public interface PencairanDanaRepository extends CrudRepository<PencairanDana, Long> {

}
