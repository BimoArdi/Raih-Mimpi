package com.example.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.Kategori;

@Repository
public interface KategoriRepository extends CrudRepository<Kategori, Long> {
	Kategori findByNamaKategori(String namaKategori);
}
