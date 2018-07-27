package com.example.project.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project.model.Kategori;
import com.example.project.model.Login;
import com.example.project.model.Pengguna;
import com.example.project.services.KategoriServices;
import com.example.project.services.LoginServices;
import com.example.project.services.PenggunaServices;

@Controller
@SessionAttributes("penggunaAktif")
public class PenggunaController {
	
	@Autowired
	PenggunaServices pgs;
	
	@Autowired
	LoginServices ls;

	@Autowired
	KategoriServices ks;
	
//	private static String fotoUpload="C:/Users/User/Documents/workspace-sts-3.9.4.RELEASE/Project/src/main/resources/static/images/";


	@RequestMapping(value="dashboard-profil", method=RequestMethod.GET)
	public String profil(ModelMap mm) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);
//		m.addAttribute("penggunaAktif",l);
		return "Dashboard Profil";
	}

	
//	@RequestMapping(value="profil", method=RequestMethod.GET)
//	public String formUpdateProfil() {		
//		return "Dashboard Profil";
//	}	
	
//	@RequestMapping(value="/dashboard-profil", method=RequestMethod.POST)
//	public String updateProfil(@ModelAttribute("penggunaAktif")Login login, @RequestParam("nama")String nama,
//			@RequestParam("telephone")String telephone, @RequestParam("biografi")String biografi,
//			@RequestParam("fotoProfil")MultipartFile fotoProfil,RedirectAttributes ra) {
//		Pengguna pg =pgs.getId((login.getPengguna().getId()));
//		pg.setNama(nama);
//		pg.setTelephone(telephone);
//		pg.setBiografi(biografi);
//		try {
//			byte[] picInBytes=fotoProfil.getBytes();
//			pg.setFotoProfil(picInBytes);
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
//		pgs.saveOrUpdate(pg);
//
////		ModelAndView mav = new ModelAndView();
//		//mav.addObject("penggunaAktif",pg.getLogin());
//		ra.addFlashAttribute("penggunaAktif",pg.getLogin());
//		return "redirect:dashboard-profil";
////		return pg.getId().toString();
//	}

	@RequestMapping(value="/dashboard-profil", method=RequestMethod.POST)
	public String updateProfil(@ModelAttribute("penggunaAktif")Login login, @RequestParam("nama")String nama,
			@RequestParam("telephone")String telephone, @RequestParam("biografi")String biografi,
			@RequestParam("fotoProfil")MultipartFile fotoProfil,RedirectAttributes ra) {
		Pengguna pg =pgs.getId((login.getPengguna().getId()));
		pg.setNama(nama);
		pg.setTelephone(telephone);
		pg.setBiografi(biografi);
		try {
			if(fotoProfil.isEmpty() == false) {
				byte[] picInBytes=fotoProfil.getBytes();
				pg.setFotoProfil(picInBytes);
			}else {
				
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		pgs.saveOrUpdate(pg);
		ra.addFlashAttribute("penggunaAktif",pg.getLogin());
		return "redirect:dashboard-profil";
	}

//	@RequestMapping(value="/dashboardfoto-profil", method=RequestMethod.POST)
//	public String updatePhotoProfil(@ModelAttribute("penggunaAktif")Login login,
//			@RequestParam("fotoProfil")MultipartFile fotoProfil,RedirectAttributes ra) {
//		Pengguna pg =pgs.getId((login.getPengguna().getId()));
//		try {
//			if(fotoProfil != null) {
//				byte[] picInBytes=fotoProfil.getBytes();
//				pg.setFotoProfil(picInBytes);
//			}
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
//		pgs.saveOrUpdate(pg);
//		ra.addFlashAttribute("penggunaAktif",pg.getLogin());
//		return "redirect:dashboard-profil";
//	}

	@RequestMapping(value="/dashboard-verifikasi")
	public String dverifikasi(ModelMap mm) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);
		return "Dashboard Verifikasi";
	}
	
	@RequestMapping(value="/dashboard-verifikasi", method=RequestMethod.POST)
	public String saveverifikasi(@ModelAttribute("penggunaAktif") Login login,@RequestParam("foto1")MultipartFile foto1, @RequestParam("foto2")MultipartFile foto2 ) {
		Pengguna p =pgs.getId(login.getPengguna().getId());
		try {
			byte[] picInBytes1 = foto1.getBytes();
			byte[] picInBytes2 = foto2.getBytes();
			p.setKtp(picInBytes1);
			p.setVerifikasi(picInBytes2);
		}catch(IOException e) {
			e.printStackTrace();
		}	
		pgs.saveOrUpdate(p);
		return "redirect:dashboard-verifikasi";
	}
}

