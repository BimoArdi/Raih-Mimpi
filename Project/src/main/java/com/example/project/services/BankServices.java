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
	BankRepository br;
	
	@Override
	public Bank getbyID(long id) {	
		return br.findById(id).get();
	}

	@Override
	public List<Bank> getall() {
		List<Bank> lb = new ArrayList<>();
		br.findAll().forEach(lb::add);
		return lb;
	}
	
	public void SaveOrUpdate(Bank b) {
		br.save(b);
		
	}


}
