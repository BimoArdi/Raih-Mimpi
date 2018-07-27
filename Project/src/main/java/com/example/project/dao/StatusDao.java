package com.example.project.dao;

import java.util.List;

import com.example.project.model.Status;

public interface StatusDao {
	public Status getById(long id);
	public List<Status> getAll();
	public void saveOrUpdate(Status status);
	public Status getByStatus (String status);
}
