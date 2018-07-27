package com.example.project.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.project.model.Bank;
import com.example.project.model.Kategori;
import com.example.project.model.Login;
import com.example.project.model.Pengguna;
import com.example.project.model.Proyek;
import com.example.project.model.Sponsor;
import com.example.project.services.BankServices;
import com.example.project.services.KategoriServices;
//import com.example.project.model.Sponsor;
import com.example.project.services.LoginServices;
import com.example.project.services.PenggunaServices;
import com.example.project.services.ProyekServices;
import com.example.project.services.SponsorServices;

@Controller
@SessionAttributes(value= {"penggunaAktif","proyek"})
public class SponsorController {

	List<Pengguna> lp = new ArrayList<>();
	
	@Autowired
	SponsorServices ss;
	
	@Autowired
	LoginServices lg;
	
	@Autowired
	ProyekServices ps;
	
	@Autowired
	PenggunaServices pgs;

	@Autowired
	KategoriServices ks;
	
	@Autowired
	BankServices bs;
	
	@RequestMapping(value="/dashboard-sponsor")
	public String dsponsor(ModelMap mm) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori", k);
		return "Dashboard Sponsor";
	}
	
	@RequestMapping(value="/sponsor")// get
	public String sponsor(ModelMap mm,@ModelAttribute("Proyek")Proyek proyek1, Model m) {
		List<Kategori> k = ks.getAll();
		List<Bank> b = bs.getall(); 
		Proyek proyek = ps.getById(proyek1.getId());
		m.addAttribute("proyek", proyek);
		mm.put("proyek", proyek);
		mm.put("listkategori", k);
		mm.put("listbank", b );
		return "Sponsor";
	}
	

//	@RequestMapping(value="sponsor.html", method=RequestMethod.POST)
//	public String sponsor(@ModelAttribute("penggunaAktif")Login l ,@RequestParam("pengguna")Pengguna pg, @RequestParam("proyek") Proyek pro, 
//			@RequestParam("jumlahSponsor")double js) {
//		Pengguna pengguna = p.getId(l.getPengguna().getId());
//		Sponsor s = new Sponsor();
//		s.setPengguna(pg);
//		s.setProyek(pro);
//		s.setPengguna(pengguna);
//		s.setJumlahSponsor(js);
//		return "Sponsor";
//	}

	@RequestMapping(value="/all-proyek", method=RequestMethod.POST)
	public String formInsertSponsor (@SessionAttribute("proyek")Proyek proyek1,
			@ModelAttribute("penggunaAktif")Login l,@ModelAttribute("bank")Bank bank1,
			@ModelAttribute ("Sponsor") Sponsor s) {
			Pengguna pengguna = pgs.getId(l.getPengguna().getId());
			Proyek proyek = ps.getById(proyek1.getId());
			Bank bank = bs.getbyID(bank1.getId());
			proyek.setTotalDana(ss.jumlahdana(proyek.getId()));
			ps.saveOrUpdate(proyek);
			s.setProyek(proyek);
			s.setPengguna(pengguna);
			s.setBank(bank1);
			ss.saveOrUpdate(s);
			return "redirect:all-proyek";
//		return login.getPengguna().getId().toString();
	}
	
//	@RequestMapping(value="dashboard-sponsor.html", method=RequestMethod.POST)
//	public ModelAndView getsponsor(@ModelAttribute("penggunaAktif")Login login) {
//		return new ModelAndView("Dashboard Sponsor","listsponsor",ss.getSponsorById(login.getPengguna().getId()));
//	}
	
}
