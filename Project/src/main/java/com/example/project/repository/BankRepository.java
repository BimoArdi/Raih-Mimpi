package com.example.project.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.project.model.Bank;

public interface BankRepository extends CrudRepository<Bank, Long> {

}
