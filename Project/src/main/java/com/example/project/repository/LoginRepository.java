package com.example.project.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project.model.Login;

@Repository
public interface LoginRepository extends CrudRepository<Login,Long> {
	Login findByUsernameAndPassword(String username,String password);
	Login findByPassword(String password);
	
	@Query("select a.username from Login a where a.username =:username")
	String findByUsername(@Param("username") String username);
	
}
