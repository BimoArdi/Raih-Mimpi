package com.example.project.dao;

import java.util.List;

import com.example.project.model.Login;


public interface LoginDao {
	public Login getById(long id);
    public List<Login> select();
    public Login getLogin(String username,String password);
    public Login getbyEmail(String email);
    public boolean getExistUsername(String username);
    public void saveOrUpdate(Login l);
    public Login getPassword(String password);
    public boolean getUsername(String username);
}
