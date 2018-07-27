package com.example.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.project.services.KategoriServices;
import com.example.project.services.PenggunaServices;
import com.example.project.services.ProyekServices;
import com.example.project.model.Kategori;
import com.example.project.model.Login;
import com.example.project.model.Proyek;

@Controller
@SessionAttributes("penggunaAktif")
public class IndexController {
	
	@Autowired
	KategoriServices ks;
	
	@Autowired
	PenggunaServices pgs;

	@Autowired
	ProyekServices ps;
	
	@RequestMapping(value="/")
	public String Home(ModelMap mm) {
		List<Kategori> k = ks.getAll();
		List<Proyek> ls = ps.getByFitur();
		List<Proyek> ls1 = ps.getByLast();
		List<Proyek> ls2 = new ArrayList<>();
		for(int i =0 ;i< ls1.size(); i++){
			ls2.add(ls1.get(i));
			if(i==2) {
				break;
			}
		}
		mm.put("listproyek1",ls2);
		mm.put("listproyek",ls);
		mm.put("listkategori",k);
		return "index";
	}
	
	@RequestMapping(value="index1")
	public String Home1(ModelMap mm) {
		List<Kategori> k = ks.getAll();
		List<Proyek> ls = ps.getByFitur();
		List<Proyek> ls1 = ps.getByLast();
		List<Proyek> ls2 = new ArrayList<>();
		for(int i =0 ;i< ls1.size(); i++){
			ls2.add(ls1.get(i));
			if(i==2) {
				break;
			}
		}
		mm.put("listproyek1",ls2);
		mm.put("listproyek",ls);
		mm.put("listkategori",k);
		return "index-1";
	}

	
	
	@RequestMapping(value="index")
	public String HomeAfterLogin(ModelMap mm, @ModelAttribute("penggunaAktif")Login l) {
		List<Kategori> k = ks.getAll();
		List<Proyek> ls = ps.getByFitur(); 
		List<Proyek> ls1 = ps.getByLast();
		List<Proyek> ls2 = new ArrayList<>();
		for(int i =0 ;i< ls1.size(); i++){
			ls2.add(ls1.get(i));
			if(i==2) {
				break;
			}
		}
		mm.put("listproyek1",ls2);
		mm.put("listproyek",ls);
		mm.put("listkategori",k);
		return "index-1";
	}
	
	@RequestMapping(value="/about")
	public String aboutUs (ModelMap mm) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);
		return "about";
	}
	

	@RequestMapping(value="/contact-us")
	public String contactUs(ModelMap mm) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);
		return "contact-us";
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
	
	@RequestMapping(value="/overview")
	public String View (ModelMap mm) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);
		return "Dashboard Overview";
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
