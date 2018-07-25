package com.example.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.Wilayah;

@Repository
public interface WilayahRepository extends CrudRepository<Wilayah,Long> {

}
