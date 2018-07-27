package com.example.project.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project.model.Foto;
import com.example.project.model.Kategori;
import com.example.project.model.Komentar;
import com.example.project.model.Login;
import com.example.project.model.ProgresProyek;
import com.example.project.model.Proyek;
import com.example.project.model.Video;
import com.example.project.services.FotoServices;
import com.example.project.services.KategoriServices;
import com.example.project.services.KomentarServices;
import com.example.project.services.PenggunaServices;
import com.example.project.services.ProgresProyekServices;
import com.example.project.services.ProyekServices;
import com.example.project.services.VideoServices;

@Controller
@SessionAttributes(value= {"penggunaAktif","proyek","progresProyek"})
public class ProgresProyekController {

	@Autowired
	ProgresProyekServices pps;
	
	@Autowired
	ProyekServices ps;

	@Autowired
	KategoriServices ks;
	
	@Autowired
	VideoServices vs;
	
	@Autowired
	FotoServices fs;

	@Autowired
	PenggunaServices pgs;
	
	@Autowired
	KomentarServices kms;
	
	@RequestMapping(value="/dashboard-update-proyek")
	public String dupdateProyek(@ModelAttribute("penggunaAktif")Login l, ModelMap mm) {
		List<Kategori> k = ks.getAll();
		List<Proyek> proyek = ps.getTheUser(l.getPengguna().getId());
		mm.put("listproyek", proyek);
		mm.put("listkategori",k);
		return "Dashboard UpdateProyek";
	}
	
	@RequestMapping(value="dashboard-update-proyek", method=RequestMethod.POST)
	public String formInsertProyek (@ModelAttribute("penggunaAktif")Login login, @RequestParam("judulProyek")String judulProyek,
			@RequestParam("deskripsiLengkap1")String deskripsiLengkap1, @RequestParam("proyek")Proyek proyek,
			@RequestParam("linkVideo")String lv, @RequestParam("foto")MultipartFile foto1,
			 @RequestParam("fototambahan")MultipartFile[] foto2) {
		ProgresProyek ppro = new ProgresProyek(); 
		ppro.setJudulProyek(judulProyek);
		ppro.setDeskripsiLengkap1(deskripsiLengkap1);
		ppro.setProyek(proyek);
		pps.saveOrUpdate(ppro);
		Video v = new Video();
		v.setLinkVideo(lv);
		v.setProyek(proyek);
		v.setProgresProyek(ppro);
		vs.saveOrUpdate(v);
		Foto f = new Foto();
		f.setProgresProyek(ppro);
		f.setProyek(proyek);
		try {
			byte[] picInBytes = foto1.getBytes();
			f.setFoto(picInBytes);
			f.setNamaFoto(foto1.getOriginalFilename());
			f.setUtamaProgresProyek(true);
		}catch(IOException e) {
			e.printStackTrace();
		}
		fs.saveOrUpdate(f);
		
		List<Foto> lf = new ArrayList<>();

		if (foto2 != null && foto2.length >0) {
    		for(int i =0 ;i< foto2.length; i++){
	            try {
	            	 Foto foto = new Foto();
	                 foto.setNamaFoto(foto2[i].getOriginalFilename());
	                 foto.setFoto(foto2[i].getBytes());
	                 foto.setProyek(proyek);
	                 foto.setProgresProyek(ppro);
	                 lf.add(foto);
	            } catch (Exception e) {
	            	e.printStackTrace();
	            }
    		}
		}	
		for(Foto photo : lf) {
			fs.saveOrUpdate(photo);
		}
		return "redirect:dashboard-update-proyek";
	}	

	@RequestMapping(value="/proyek-update")
	public String proyekUpdate(ModelMap mm, @ModelAttribute("pp")ProgresProyek pp, Model m) {
		List<Kategori> k = ks.getAll();
		ProgresProyek progresProyek = pps.getById(pp.getId());
		List<Foto> allfoto = fs.getTanpaUtamaProgresProyek(pp.getId());
		byte[] foto = pps.getById(pp.getId()).getFotoUtamaProgresProyek().getFoto();
		List<Komentar> lk = kms.getByProgresProyek(progresProyek.getId());
		List<Video> lv = vs.getByProgresProyek(progresProyek.getId());
		mm.put("listvideo", lv);
		mm.put("listkomentar", lk);
		mm.put("photo", foto);
		mm.put("listfoto", allfoto);
		mm.put("progresProyek", progresProyek);
		m.addAttribute("progresProyek", progresProyek);
		mm.put("listkategori",k);
		return "Proyek-Update";
	}
		
	
	
	@PostMapping(value="/proyek-update")
	public String postproyekUpdate(@SessionAttribute("progresProyek")ProgresProyek pp,@SessionAttribute("proyek")Proyek p,
			@ModelAttribute("Komentar") Komentar komentar, @ModelAttribute("penggunaAktif")Login l, ModelMap mm,
			RedirectAttributes ra) {
		ProgresProyek progresProyek = pps.getById(pp.getId());
		Proyek proyek = ps.getById(p.getId());
		List<Foto> allfoto = fs.getTanpaUtamaProgresProyek(pp.getId());
		byte[] foto = pps.getById(pp.getId()).getFotoUtamaProgresProyek().getFoto();
		List<Komentar> lk = kms.getByProgresProyek(pp.getId());
		List<Video> lv = vs.getByProgresProyek(progresProyek.getId());
		mm.put("listvideo", lv);
		mm.put("listkomentar", lk);
		mm.put("photo", foto);
		mm.put("listfoto", allfoto);
		mm.put("progresProyek", progresProyek);
		komentar.setPengguna(l.getPengguna());
		komentar.setProgresProyek(progresProyek);
		komentar.setProyek(proyek);
		kms.saveOrUpdate(komentar);
		return "redirect:proyek-update?id="+progresProyek.getId();
	}
		
}
