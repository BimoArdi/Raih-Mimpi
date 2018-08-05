package com.example.project.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.dao.NotifikasiDao;
import com.example.project.model.Notifikasi;
import com.example.project.repository.NotifikasiRepository;

@Service
public class NotifikasiServices implements NotifikasiDao {

	@Autowired
	NotifikasiRepository nr;

	@Override
	public void updatePengguna() {
		Notifikasi notifikasi = new Notifikasi();
		notifikasi.setTabelReferensi("Pengguna");
		notifikasi.setType("Update");
		notifikasi.setText("User meminta verifikasi akun pengguna");
		notifikasi.setDate(new Date());
		nr.save(notifikasi);
	}

	@Override
	public void saveProyek() {
		Notifikasi notifikasi = new Notifikasi();
		notifikasi.setTabelReferensi("Proyek");
		notifikasi.setType("New");
		notifikasi.setText("User meminta verifikasi proyek");
		notifikasi.setDate(new Date());
		nr.save(notifikasi);
	}

	@Override
	public void saveSponsor() {
		Notifikasi notifikasi = new Notifikasi();
		notifikasi.setTabelReferensi("Sponsor");
		notifikasi.setType("New");
		notifikasi.setText("User meminta verifikasi sponsor yang telah dilakukan user");
		notifikasi.setDate(new Date());
		nr.save(notifikasi);
	}

	@Override
	public void savePencairanDana() {
		Notifikasi notifikasi = new Notifikasi();
		notifikasi.setTabelReferensi("Pencairan Dana");
		notifikasi.setType("New");
		notifikasi.setText("User meminta verifikasi pencairan dana proyek");
		notifikasi.setDate(new Date());
		nr.save(notifikasi);		
	}
	
}
