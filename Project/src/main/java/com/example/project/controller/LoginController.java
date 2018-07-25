package com.example.project.controller;


import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.example.project.model.Kategori;
import com.example.project.model.Login;
import com.example.project.model.Pengguna;
import com.example.project.services.KategoriServices;
import com.example.project.services.LoginServices;
import com.example.project.services.PenggunaServices;
import com.example.project.helper.EnkripHelper;


@Controller
@SessionAttributes("penggunaAktif")
public class LoginController {
	
	@Autowired
	LoginServices lg;
	
	@Autowired
	PenggunaServices pgs;
	
	@Autowired
	KategoriServices ks;

//	@RequestMapping(value="index-1",method=RequestMethod.POST)
//	public ModelAndView getLogin(@ModelAttribute("Login") Login login) {
//		ModelAndView mav= new ModelAndView();
//		Login penggunaAktif = lg.getLogin(login.getUsername(), login.getPassword());
//		mav.addObject("penggunaAktif", penggunaAktif);
//		mav.setViewName("index-1");
//		return mav;
//	}
	
//	@RequestMapping(value="dashboard-profil", method=RequestMethod.GET)
//	public ModelAndView formLogin(@ModelAttribute("penggunaAktif") Login l, @ModelAttribute("Pengguna") Pengguna pg) {
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("pengguna",pg);
//		return mav;
//	}
	
	
	@RequestMapping(value="/login")
	public String Login (ModelMap mm) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String postRegist( @ModelAttribute("Pengguna") Pengguna pg,
			@RequestParam("password1")String password, @RequestParam("username1")String username,
			@RequestParam("email1")String email) {
		Login l = new Login();
		try {
			l.setPassword(EnkripHelper.hash256(password));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		l.setUsername(username);
		l.setEmail(email);
		lg.saveOrUpdate(l);
		pg.setLogin(l);
		pgs.saveOrUpdate(pg);
		return "redirect:login";
	}
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	public ModelAndView getLogin(@RequestParam("username")String username, 
			@RequestParam("password")String pass,ModelMap mm) throws NoSuchAlgorithmException {
	ModelAndView mav= new ModelAndView();
	Login l = lg.getLogin(username,EnkripHelper.hash256(pass)); // getlogin untuk username dan password
	if(l == null) {
		mav.setViewName("login"); //ke halaman login
	}else {
		mav.addObject("penggunaAktif",l); // l dari database, login dari form
		mav.setViewName("index-1"); //ke halaman index-1
	}
	List<Kategori> k = ks.getAll();
	mm.put("listkategori",k);
	return mav;
	}
	
	@RequestMapping(value="dashboard-password")
	public String dashboardPassword( Model m, ModelMap mm) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);
		return "Dashboard Password";
	}
	
//	@RequestMapping(value="ubahpassword", method=RequestMethod.GET)
//	public String keUbahPassword(){
////		return new ModelAndView("dashboard-password.html","penggunaAktif",l);		
//		return "dashboard-password.html";
//	}
	
	@RequestMapping(value="dashboard-password", method=RequestMethod.POST)
	public String ubahPassword(@ModelAttribute("penggunaAktif")Login login, @RequestParam("password")String password){
		try {
			login.setPassword(EnkripHelper.hash256(password));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		lg.saveOrUpdate(login); //kalau mau update pake modelattribute
		return "redirect:dashboard-password";
//		return login.getId().toString()+login.getPassword();
	}
	
	@RequestMapping("/logout")
	public String goLogOut(HttpSession session,SessionStatus status) {
		status.setComplete();
		session.removeAttribute("penggunaAktif");
		return "index";
	}
}
