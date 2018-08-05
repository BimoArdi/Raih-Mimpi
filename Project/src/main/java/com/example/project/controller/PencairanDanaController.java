package com.example.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.project.model.Kategori;
import com.example.project.model.Login;
import com.example.project.model.Pengguna;
//import com.example.project.model.Pengguna;
import com.example.project.model.PencairanDana;
import com.example.project.model.Proyek;
import com.example.project.services.KategoriServices;
import com.example.project.services.NotifikasiServices;
//import com.example.project.model.Proyek;
import com.example.project.services.PencairanDanaServices;
import com.example.project.services.PenggunaServices;
import com.example.project.services.ProyekServices;
import com.example.project.services.SponsorServices;

@Controller
@SessionAttributes("penggunaAktif")
public class PencairanDanaController {

	@Autowired
	PencairanDanaServices pds;
	
	@Autowired
	KategoriServices ks;
	
	@Autowired
	ProyekServices ps;
	
	@Autowired
	PenggunaServices pgs;
	
	@Autowired
	SponsorServices ss;

	@Autowired
	NotifikasiServices ns;

	@RequestMapping(value="/dashboard-pencairan")
	public String dpencairan(@ModelAttribute("penggunaAktif")Login l,ModelMap mm) {
		List<Kategori> k = ks.getAll();
		List<Proyek> proyek = ps.getTheUser(l.getPengguna().getId());
		mm.put("listproyek", proyek);
		mm.put("listkategori",k);
		return "Dashboard Pencairan";
	}
	
	@RequestMapping(value="/dashboard-pencairan", method=RequestMethod.POST)
	public String formInsertProyek (@ModelAttribute("penggunaAktif") Login l,@ModelAttribute("pencairanDana")PencairanDana pd, 
			@RequestParam("proyek")Proyek proyek) {
		Pengguna pe = pgs.getId(l.getPengguna().getId());
		Proyek proyek1 = ps.getById(proyek.getId());
		pd.setProyek(proyek);
		pd.setPengguna(pe);
		pd.setStatus("Request");
		pds.saveOrUpdate(pd);
		proyek1.setSisaDana((ss.getTotalSponsor(proyek1.getId())-pds.getByProyek(proyek1.getId())));
		ps.saveOrUpdate(proyek1);
		ns.savePencairanDana();
		return "redirect:dashboard-pencairan";
	}
}
