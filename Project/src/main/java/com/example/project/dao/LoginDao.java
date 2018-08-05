package com.example.project.dao;

import java.util.List;

import com.example.project.model.Login;


public interface LoginDao {
	public Login getById(long id);
    public List<Login> getAll();
    public Login getLogin(String username,String password);
    public Login getbyEmail(String email);
    public boolean getEmail(String email);
    public void saveOrUpdate(Login l);
    public Login getPassword(String password);
    public boolean getUsername(String username);
    public Login getConfirmationToken(String confirmationToken);
    public Login getByUsername(String username);
}
