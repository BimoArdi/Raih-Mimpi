package com.example.project.dao;

import java.util.List;

import com.example.project.model.Bank;

public interface BankDao {
	
	public Bank getById(long id);
	public List<Bank> getAll();
	public void saveOrUpdate(Bank b);

}
