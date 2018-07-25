package com.example.project.dao;

import java.util.List;

import com.example.project.model.Bank;

public interface BankDao {
 public Bank getbyID(long id );
 public List<Bank> getall();
}
