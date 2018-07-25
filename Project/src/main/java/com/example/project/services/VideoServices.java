package com.example.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.dao.VideoDao;
import com.example.project.model.Video;
import com.example.project.repository.VideoRepository;

@Service
public class VideoServices implements VideoDao {
	@Autowired
	VideoRepository v;
	
	@Override
	public Video getById(long id) {
		return v.findById(id).get() ;
	}

	@Override
	public List<Video> getAll() {
		List<Video> lv = new ArrayList<>();
		v.findAll().forEach(lv::add);
		return lv;
	}
	
	@Override
	public void saveOrUpdate(Video vd) {
		v.save(vd);
	}
	
}
