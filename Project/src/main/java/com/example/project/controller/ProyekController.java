
package com.example.project.controller;

import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.example.project.model.Foto;
import com.example.project.model.Kategori;
import com.example.project.model.Login;
import com.example.project.model.Pengguna;
import com.example.project.model.ProgresProyek;
import com.example.project.model.Proyek;
import com.example.project.model.Sponsor;
import com.example.project.model.Video;
import com.example.project.model.Wilayah;
import com.example.project.services.FotoServices;
import com.example.project.services.KategoriServices;
import com.example.project.services.KomentarServices;
import com.example.project.services.PenggunaServices;
import com.example.project.services.ProgresProyekServices;
//import com.example.project.model.Video;
import com.example.project.services.ProyekServices;
import com.example.project.services.SponsorServices;
import com.example.project.services.VideoServices;
import com.example.project.services.WilayahServices;

@Controller
@SessionAttributes("penggunaAktif") //kalau udah ada session ga usah pake model di get
public class ProyekController {
	
	@Autowired
	ProyekServices ps;
	
	@Autowired
	PenggunaServices pgs;
	
	@Autowired
	KategoriServices ks;
	
	@Autowired
	WilayahServices ws;
	
	@Autowired
	VideoServices vs;

	@Autowired
	FotoServices fs;
	
	@Autowired
	KomentarServices kms;

	@Autowired
	SponsorServices ss;
	
	@Autowired
	ProgresProyekServices pps;
	
	@RequestMapping(value="/dashboard-proyek")
	public String dproyek(ModelMap mm) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori", k);
		return "Dashboard Proyek";
	}
	
	@RequestMapping(value="a")
	public String g(){
		return "proyek1";
	}
	
	@RequestMapping(value="/proyek")
	public String proyek (ModelMap m) {
		List<Wilayah> w = ws.getAll();		
		List<Kategori> k = ks.getAll();
		m.put("listkategori", k);
		m.put("listwilayah", w);
		return "Proyek";
	}
	
	@RequestMapping(value="/dashboard-proyek", method=RequestMethod.POST)
	public String formInsertProyek (@ModelAttribute("penggunaAktif") Login l,@RequestParam("namaProyek")String namaProyek,
			@RequestParam("targetDana") double targetDana, @RequestParam("deskripsiSingkat")String deskripsiSingkat,
			@RequestParam("deskripsiLengkap")String deskripsiLengkap, @RequestParam("link")String link,
			@RequestParam("kategori")Kategori kategori, @RequestParam("wilayah") Wilayah wilayah, 
			@RequestParam("linkVideo") String lv, @RequestParam("tanggalBerakhir")Date tg,
			@RequestParam("foto")MultipartFile foto1, @RequestParam("fototambahan")MultipartFile[] foto2) {
		Pengguna pe = pgs.getId(l.getPengguna().getId()); 
		Proyek p = new Proyek();
		p.setDeskripsiLengkap(deskripsiLengkap);
		p.setDeskripsiSingkat(deskripsiSingkat);
		p.setNamaProyek(namaProyek);
		p.setTargetDana(targetDana);
		p.setLink(link);
		p.setPengguna(pe);
		p.setTanggalBerakhir(tg);
		p.setKategori(kategori);
		p.setWilayah(wilayah);
		ps.saveOrUpdate(p);
		Video v = new Video();
		v.setProyek(p);
		v.setLinkVideo(lv);
		vs.saveOrUpdate(v);
		Foto f = new Foto();
		f.setProyek(p);
		try {
			byte[] picInBytes = foto1.getBytes();
			f.setFoto(picInBytes);
			f.setNamaFoto(foto1.getOriginalFilename());
			f.setUtamaProyek(true);
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
	                 foto.setProyek(p);
	                 lf.add(foto);
	            } catch (Exception e) {
	            	e.printStackTrace();
	            }
    		}
		}	
		for(Foto photo : lf) {
			fs.saveOrUpdate(photo);
		}
		
		return "redirect:dashboard-proyek";
	}
	
	
	@RequestMapping(value="/all-proyek")
	public String gridproyek(ModelMap mm,@ModelAttribute("Sponsor") Sponsor s) {
		List<Kategori> k = ks.getAll();
		List<Proyek> proyek2 = ps.getAllProyek();
		mm.put("listkategori",k);
		mm.put("listproyek", proyek2);
		return "Proyek-grid";
	}
		
	@RequestMapping(value="/by-kategori")
	public String byKategori (ModelMap mm, @ModelAttribute("kategori")Kategori kategori1) {
		List<Kategori> k = ks.getAll();
		Kategori kategori = ks.getById(kategori1.getId());
		List<Proyek> proyek = ps.getByKategori(kategori1.getId());
		mm.put("listproyek", proyek);
		mm.put("listkategori",k);
		mm.put("kategori",kategori);
		return "nama Proyek-grid";
	}
	
	//model atribute untuk ngambil dari html dari sebelumny ke link di value
	//model map put untuk ke html
	
	@RequestMapping(value="/proyek-detail")
	public String keproyekDetail(ModelMap mm, @ModelAttribute("proyek")Proyek proyek1) {
		List<Kategori> k = ks.getAll();
		Proyek proyek = ps.getById(proyek1.getId());
		List<Sponsor> ls = ss.getByProyek(proyek1.getId());
		List<ProgresProyek> lp = pps.getByProyek(proyek1.getId());
		byte[] foto = ps.getById(proyek1.getId()).getFotoUtamaProyek().getFoto();
		double jumlahDana = ((proyek.getTotalDana()/proyek.getTargetDana())*100);
		List<Foto> allfoto = fs.getTanpaUtamaProyek(proyek.getId());
		mm.put("listfoto", allfoto);
		mm.put("persentaseDana", jumlahDana);
		mm.put("photo", foto);
		mm.put("listprogresproyek", lp);
		mm.put("listsponsor", ls);
 		mm.put("proyek", proyek);
		mm.put("listkategori",k);
		return "Proyek-detail";
	}
			
	
	@RequestMapping(value="chat")
	public String keproyekDetailLogin(ModelMap mm,@ModelAttribute("proyek")Proyek proyek1) {
		List<Kategori> k = ks.getAll();
		Proyek proyek =ps.getById(proyek1.getId());
		mm.put("proyek", proyek);
		mm.put("listkategori",k);
		return "Proyek-detail - Login";
	}
	
//	@RequestMapping(value="chat", method=RequestMethod.POST)
//	public String postproyekDetailLogin(@ModelAttribute("proyek")Proyek proyek1,
//			@RequestParam("chat") String chat, @ModelAttribute("penggunaAktif")Login l) {
//		Pengguna pengguna = pgs.getId(l.getPengguna().getId());
//		Proyek proyek = ps.getById(proyek1.getId());
//		Komentar komentar = new Komentar();
//		komentar.setIsiKomentar(chat);
//		komentar.setPengguna(pengguna);
//		komentar.setProyek(proyek);
//		kms.saveOrUpdate(komentar);
//		return "redirect:proyek-detail-login.html";
//	}
}