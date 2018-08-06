package com.example.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.dao.BankDao;
import com.example.project.model.Bank;
import com.example.project.repository.BankRepository;

@Service
public class BankServices implements BankDao {
	@Autowired
	BankRepository bs;
	
	@Override
	public Bank getById(long id) {
		return bs.findById(id).get() ;
	}

	@Override
	public List<Bank> getAll() {
		List<Bank> lb = new ArrayList<>();
		bs.findAll().forEach(lb::add);
		return lb;
	}
	
	@Override
	public void saveOrUpdate(Bank b) {
		bs.save(b);
	}

}
