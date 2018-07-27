package com.example.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.dao.StatusDao;
import com.example.project.model.Status;
import com.example.project.repository.StatusRepository;

@Service
public class StatusServices implements StatusDao {
	@Autowired
	StatusRepository sr;
	
	@Override
	public Status getById(long id) {
		return sr.findById(id).get();
	}

	@Override
	public List<Status> getAll() {
		List<Status> ls = new ArrayList<>();
		sr.findAll().forEach(ls::add);
		return ls;
	}
	
	@Override
	public void saveOrUpdate(Status status) {
		sr.save(status);
	}

	@Override
	public Status getByStatus(String status) {
		return sr.findByStatus(status);
	}

	
}
