package com.example.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.project.services.KategoriServices;
import com.example.project.services.PenggunaServices;
import com.example.project.model.Kategori;
import com.example.project.model.Login;

@Controller
@SessionAttributes("penggunaAktif")
public class IndexController {
	
	@Autowired
	KategoriServices ks;
	
	@Autowired
	PenggunaServices pgs;
	
	@RequestMapping(value="/")
	public String Home(ModelMap mm) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);
		return "index";
	}
	
	@RequestMapping(value="index")
	public String HomeAfterLogin(ModelMap mm, @ModelAttribute("penggunaAktif")Login l) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);
		return "index-1";
	}
	
	@RequestMapping(value="/about")
	public String aboutUs (ModelMap mm) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);
		return "about";
	}
	
	@RequestMapping(value="/Overview")
	public String View (ModelMap mm) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);
		return "Dashboard Overview";
	}

	@RequestMapping(value="/404")
	public String Error (ModelMap mm) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);
		return "404";
	}
	

	@RequestMapping(value="/contact-us")
	public String contactUs(ModelMap mm) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);
		return "contact-us";
	}
	
	@RequestMapping(value="forget-password")
	public String forgetPassword(ModelMap mm) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);
		return "Forget Password";
	}
	
	@RequestMapping(value="terms-of-user")
	public String termsOfUser(ModelMap mm) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);
		return "Terms of User";
	}	
	
	@RequestMapping(value="/search-result")
	public String searchResult(ModelMap mm) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);	
		return "search-result";
	}
	
	@RequestMapping(value="/team-detail")
	public String teamDetail(ModelMap mm) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);	
		return "team-detail";
	}
		
	@RequestMapping(value="/team")
	public String teamGrid(ModelMap mm) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);	
		return "team-grid";
	}
	
	@RequestMapping(value="/rincian")
	public String rincian(ModelMap mm) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);	
		return "Rincian";
	}
}
