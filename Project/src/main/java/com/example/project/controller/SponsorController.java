package com.example.project.controller;

import java.security.NoSuchAlgorithmException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.project.helper.EnkripHelper;
import com.example.project.model.Bank;
import com.example.project.model.Kategori;
import com.example.project.model.Login;
import com.example.project.model.Pengguna;
import com.example.project.model.Proyek;
import com.example.project.model.Sponsor;
import com.example.project.services.BankServices;
import com.example.project.services.FotoServices;
import com.example.project.services.KategoriServices;
import com.example.project.services.KomentarServices;
import com.example.project.services.LoginServices;
import com.example.project.services.PenggunaServices;
import com.example.project.services.PeranServices;
import com.example.project.services.ProgresProyekServices;
import com.example.project.services.ProyekServices;
import com.example.project.services.SponsorServices;
import com.example.project.services.StatusServices;

@Controller
@SessionAttributes(value= {"penggunaAktif", "proyek"}) 
public class SponsorController {
	
	@Autowired
	SponsorServices ss;
	
	@Autowired
	FotoServices fs;

	@Autowired
	LoginServices lg;
	
	@Autowired
	ProyekServices ps;
	
	@Autowired
	PenggunaServices pgs;

	@Autowired
	KategoriServices ks;

	@Autowired
	ProgresProyekServices pps;
	
	@Autowired
	KomentarServices kms;

	@Autowired
	StatusServices sts;

	@Autowired
	PeranServices prs;

	@Autowired
	BankServices bs;
	
	@RequestMapping(value="/dashboard-sponsor")
	public String dsponsor(ModelMap mm, @ModelAttribute("penggunaAktif") Login l) {
		List<Kategori> k = ks.getAll();
		List<Sponsor> ls = ss.getByPengguna(l.getPengguna().getId());
		mm.put("listsponsor", ls);
		mm.put("listkategori", k);
		return "Dashboard Sponsor";
	}
	
	@RequestMapping(value="/sponsor")
	public String sponsor(ModelMap mm,@ModelAttribute("Proyek")Proyek proyek1, Model m) {
		List<Kategori> k = ks.getAll();
		List<Bank> lb = bs.getAll(); 
		Proyek proyek = ps.getById(proyek1.getId());
		mm.put("proyek", proyek);
		mm.put("listbank",lb);
		m.addAttribute("proyek", proyek);
		mm.put("listkategori", k);
		return "Sponsor";
	}
	
	
	@RequestMapping(value="/detailsponsor")
	public String detailRekening(ModelMap mm, @ModelAttribute("Sponsor")Sponsor sponsor) {
		List<Kategori> k = ks.getAll();
		Sponsor s = ss.getById(sponsor.getId());
		mm.put("listkategori", k);
		mm.put("sponsor", s);
		return "Rekening Sponsor";
	}


	@RequestMapping(value="/detailsponsor", method=RequestMethod.POST)
//	@ResponseBody
	public String formInsertSponsor (@RequestParam("namap")String namaProyek,
			@RequestParam(value="user", required=false)String nama,@RequestParam("bank")Bank b,
			@RequestParam(value="anonim", required=false)String anonim,@RequestParam("jumlahSponsor")float jumlahSponsor,
			@RequestParam(value="username", required=false)String username,@RequestParam(value="email",required=false)String email) {
		Sponsor s = new Sponsor();
		s.setJumlahSponsor(jumlahSponsor);
		Login l = lg.getByUsername(nama); 
		if(anonim != null) {
			s.setAnonim(anonim);			
		}else {
			 
		}
		if (l != null) {
			Pengguna pengguna = pgs.getId(l.getPengguna().getId());
			s.setPengguna(pengguna);
			lg.sendEmail(l, "confirmasi pembayaran","harap mengirimkan bukti transfer ke email, jika dalam waktu 24 jam tidak dilakukan pengiriman, maka akan dianggap gagal atau tidak mensponsori proyek yang telah dipilih");
		}else if(l == null) {
			if(lg.findbyemail(email) == false) {
				Pengguna pengguna2 = new Pengguna();
				Login login = new Login();
				login.setUsername(username);
				login.setEmail(email);
				try {
					login.setConfirmationToken(EnkripHelper.hash256(email));
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}	
	
				login.setCreateBy("user");
				login.setKeterangan("akun user belum aktif");
				login.setStatus(sts.getByStatus("Tidak Aktif"));
				login.setPeran(prs.getPeran("User"));
				lg.saveOrUpdate(login);
				pengguna2.setLogin(login);
				pengguna2.setNama(login.getUsername());
				pengguna2.setStatus(sts.getByStatus("Tidak Aktif"));
				pgs.saveOrUpdate(pengguna2);
				s.setPengguna(pengguna2);
				lg.sendEmail(login,"confirmasi pembayaran","harap mengirimkan bukti transfer ke email, jika dalam waktu 24 jam tidak dilakukan pengiriman, maka akan dianggap gagal atau tidak mensponsori proyek yang telah dipilih");
			}else if(lg.findbyemail(email) == true) {
				Pengguna pengguna1 = pgs.getByLogin(lg.getbyEmail(email).getId());
				s.setPengguna(pengguna1);
				lg.sendEmail(lg.getbyEmail(email), "confirmasi pembayaran","harap mengirimkan bukti transfer ke email, jika dalam waktu 24 jam tidak dilakukan pengiriman, maka akan dianggap gagal atau tidak mensponsori proyek yang telah dipilih");
			}
		}
		Proyek proyek = ps.getByProyek(namaProyek);
		s.setProyek(proyek);
		s.setBank(b);
		s.setStatus(sts.getByStatus("Proses"));
		ss.saveOrUpdate(s);
		proyek.setTotalDana(ss.getTotalSponsor(proyek.getId()));
		ps.saveOrUpdate(proyek);
		return "redirect:/detailsponsor?id="+s.getId();
//		return listlogin.toString();
	}	
	
	@RequestMapping(value="/all-proyek", method=RequestMethod.POST)
	@ResponseBody
	public String keproyek(@RequestParam(value="anonim", required=false)String anonim, @ModelAttribute("penggunaAktif")Login l,
			@SessionAttribute("proyek")Proyek proyek,@RequestParam("bank")Bank bank, @RequestParam("jumlahSponsor")float jumlahSponsor) {
		Sponsor s = new Sponsor();
		if(anonim != null) {
			s.setAnonim(anonim);
		}else {
			s.setAnonim(null);
		}
		s.setJumlahSponsor(jumlahSponsor);
		s.setBank(bank);
		s.setPengguna(pgs.getId(l.getId()));
		s.setProyek(ps.getById(proyek.getId()));
		s.setStatus(sts.getByStatus("Proses"));
		ss.saveOrUpdate(s);
		proyek.setTotalDana(ss.getTotalSponsor(proyek.getId()));
		ps.saveOrUpdate(proyek);
//		return "Proyek-grid";
		return s.getId().toString();
	}
}
