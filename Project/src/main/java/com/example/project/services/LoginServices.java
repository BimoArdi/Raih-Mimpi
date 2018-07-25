package com.example.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.model.Login;
import com.example.project.repository.LoginRepository;
import com.example.project.component.EmailSender;
import com.example.project.dao.LoginDao;

@Service
public class LoginServices implements LoginDao {
	
	@Autowired
	LoginRepository lg ;

	@Autowired
	EmailSender emailSend;
	
	@Override
	public Login getById(long id) {
		return lg.findById(id).get();
	}

	@Override
	public List<Login> select() {
		List<Login> ll= new ArrayList<>();
		lg.findAll().forEach(ll::add);
		return ll;
	}

	@Override
	public Login getLogin(String username, String password) {
		return lg.findByUsernameAndPassword(username, password);
	}

	@Override
	public boolean getExistUsername(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Login getbyEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Login l) {
		lg.save(l);
	}
	
	@Override
	public Login getPassword(String password) {
		return lg.findByPassword(password);
	}
	
	@Override
	public boolean getUsername(String username) {
		return (lg.findByUsername(username))!= null;
	}
	
	public void saveWithEmail (Login l) {
		lg.save(l);
		emailSend.sendMessage(l.getEmail(),"Verifikasi Berhasil","selamat");
	}
}
