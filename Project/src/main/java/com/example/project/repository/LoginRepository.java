package com.example.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.Login;

@Repository
public interface LoginRepository extends CrudRepository<Login,Long> {
	Login findByUsernameAndPassword(String username,String password);
	Login findByPassword(String password);
	Login findByconfirmationToken(String confirmationToken);
	Login findByEmail(String Email);
	Login findByconfirmationTokenAndUsername(String confirmationToken, String username);	
	Login findByUsername(String username);	
}
