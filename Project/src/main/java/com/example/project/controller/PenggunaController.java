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
import com.example.project.services.NotifikasiServices;
import com.example.project.services.PenggunaServices;
import com.example.project.services.ProyekServices;
import com.example.project.services.SponsorServices;

@Controller
@SessionAttributes("penggunaAktif")
public class PenggunaController {
	
	@Autowired
	PenggunaServices pgs;
	
	@Autowired
	LoginServices ls;

	@Autowired
	KategoriServices ks;
	
	@Autowired
	SponsorServices ss;

	@Autowired
	ProyekServices ps;

	@Autowired
	NotifikasiServices ns;

	@RequestMapping(value="dashboard-profil", method=RequestMethod.GET)
	public String profil(ModelMap mm) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);
		return "Dashboard Profil";
	}

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


	@RequestMapping(value="/dashboard-verifikasi")
	public String dverifikasi(ModelMap mm) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);
		return "Dashboard Verifikasi";
	}
	
	@RequestMapping(value="/dashboard-verifikasi", method=RequestMethod.POST)
	public String saveverifikasi(@ModelAttribute("penggunaAktif") Login login,@RequestParam("foto1")MultipartFile foto1, @RequestParam("foto2")MultipartFile foto2,RedirectAttributes ra ) {
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
		ns.updatePengguna();
		ra.addFlashAttribute("penggunaAktif",p.getLogin());
		return "redirect:dashboard-verifikasi";
	}
	
	@RequestMapping(value="/overview")
//	@ResponseBody
	public String View (ModelMap mm,@ModelAttribute("penggunaAktif")Login l) {
		List<Kategori> k = ks.getAll();
		int getRequestSponsor = ss.getByStatusandPengguna("Request", l.getId());
		int getBerhasilSponsor = ss.getByStatusandPengguna("Berhasil", l.getId());
		int getGagalSponsor = ss.getByStatusandPengguna("Gagal", l.getId());
		if(ss.getTotalSponsorbyPengguna(l.getId()) != 0.0) {
			float getTotalSponsorByPengguna = ss.getTotalSponsorbyPengguna(l.getId());			
			mm.put("TotalSponsor",getTotalSponsorByPengguna);

		}else {
			float getTotalSponsorByPengguna = 0;
			mm.put("TotalSponsor",getTotalSponsorByPengguna);
		}
		int getRequestProyek = ps.findTotalStatusandPengguna("Request", l.getId());
		int getAktifProyek = ps.findTotalStatusandPengguna("Approve", l.getId());
		int getTidakAktifProyek = ps.findTotalStatusandPengguna("Tidak Aktif", l.getId());
		int getGagalProyek = ps.findTotalStatusandPengguna("Gagal",l.getId());
		float getTotalSponsorbyPengguna1 = ss.getTotalByPenggunaAndProyek(l.getId());
		mm.put("listkategori",k);
		mm.put("Request",getRequestSponsor);
		mm.put("Berhasil",getBerhasilSponsor);
		mm.put("Gagal",getGagalSponsor);
		mm.put("Request1",getRequestProyek);
		mm.put("Aktif",getAktifProyek);
		mm.put("TidakAktif",getTidakAktifProyek);
		mm.put("Gagal1",getGagalProyek);
		mm.put("totalSponsorPengguna",getTotalSponsorbyPengguna1);
		return "Dashboard Overview";
	}

}

