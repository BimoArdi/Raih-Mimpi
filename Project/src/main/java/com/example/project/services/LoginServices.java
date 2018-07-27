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
	EmailSender emailSender;
	
	@Autowired
	PenggunaServices ps;
	
	@Override
	public Login getById(long id) {
		return lg.findById(id).get();
	}

	@Override
	public List<Login> getAll() {
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
		return lg.findByEmail(email);
	}
	
	@Override
	public void saveOrUpdate(Login l) {
		lg.save(l);
		emailSender.sendMessage(l.getEmail(),"Registration Confirmation","To confirm your e-mail address, please copy this link below into your url:\n"+"localhost:8080/confirm?code="+l.getConfirmationToken());
	}
		
	public void sendEmail(Login l, String subject, String message) {
		emailSender.sendMessage(l.getEmail(),subject, message);
	}
	
	@Override
	public Login getPassword(String password) {
		return lg.findByPassword(password);
	}
	
	@Override
	public boolean getUsername(String username) {
		return (lg.findByUsername(username))!= null;
	}

	@Override
	public Login getConfirmationToken(String confirmationToken) {
		return lg.findByconfirmationToken(confirmationToken);
	}

	@Override
	public Login getByUsername(String username) {
		return lg.findByUsername(username);
	}
	
	public boolean findbyemail(String email) {
		if(lg.findByEmail(email)!=null)
			return true;
		else
			return false;
	}
	
}
