package com.example.project.dao;

import java.util.List;

import com.example.project.model.Video;

public interface VideoDao {
	public Video getById(long id);
	public List<Video> getAll();
	public void saveOrUpdate(Video vd);

}
