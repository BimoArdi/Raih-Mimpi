package com.example.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.Bank;

@Repository
public interface BankRepository extends CrudRepository<Bank,Long> {

}
