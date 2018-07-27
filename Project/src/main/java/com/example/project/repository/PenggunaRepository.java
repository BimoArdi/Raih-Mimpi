package com.example.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.Login;
import com.example.project.model.Pengguna;

@Repository
public interface PenggunaRepository extends CrudRepository<Pengguna,Long> {
	Pengguna findByNama(String nama);
	Pengguna findByTelephone(String telepon);
	Pengguna findByLogin(Login byId);
	
}
