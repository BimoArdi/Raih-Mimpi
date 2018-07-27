package com.example.project.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project.model.Pengguna;

@Repository
public interface PenggunaRepository extends CrudRepository<Pengguna,Long> {
	Pengguna findByNama(String nama);
	Pengguna findByTelephone(String telepon);
	@Query("select a from Pengguna a where a.login.id=:id")
	Pengguna findPhoto(@Param("id")Long id);
	
}
