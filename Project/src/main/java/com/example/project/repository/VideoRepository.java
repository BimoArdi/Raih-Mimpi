package com.example.project.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.ProgresProyek;
import com.example.project.model.Proyek;
import com.example.project.model.Video;

@Repository
public interface VideoRepository extends CrudRepository<Video,Long> {

	List<Video> findByProyek(Proyek byId);
	
	List<Video> findByProgresProyek(ProgresProyek byId);
}
