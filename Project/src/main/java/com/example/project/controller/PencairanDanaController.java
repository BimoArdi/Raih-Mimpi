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
//import com.example.project.model.Proyek;
import com.example.project.services.PencairanDanaServices;
import com.example.project.services.PenggunaServices;
import com.example.project.services.ProyekServices;

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

	@RequestMapping(value="/dashboard-pencairan")
	public String dpencairan(@ModelAttribute("penggunaAktif")Login l,ModelMap mm) {
		List<Kategori> k = ks.getAll();
		List<Proyek> proyek = ps.getTheUser(l.getPengguna().getId());
		mm.put("listproyek", proyek);
		mm.put("listkategori",k);
		return "Dashboard Pencairan";
	}

//	@RequestMapping(value="dashboard-pencairan", method=RequestMethod.POST)
//	public String formInsertProyek (@ModelAttribute("penggunaAktif") Login l,@RequestParam("namaPencair")String np,
//			@RequestParam("jumlahDana") double jd, @RequestParam("noRekening")String norek, @RequestParam("proyek")Proyek proyek) {
//		Pengguna pe = pgs.getId(l.getPengguna().getId());  
//		PencairanDana pd = new PencairanDana();
//		pd.setJumlahDana(jd);
//		pd.setNamaPencair(np);
//		pd.setNoRekening(norek);
//		pd.setProyek(proyek);
//		pd.setPengguna(pe);
//		pds.saveOrUpdate(pd);
//		return "redirect:dashboard-pencairan";
//	}
	
	@RequestMapping(value="/dashboard-pencairan", method=RequestMethod.POST)
	public String formInsertProyek (@ModelAttribute("penggunaAktif") Login l,@ModelAttribute("pencairanDana")PencairanDana pd, 
			@RequestParam("proyek")Proyek proyek) {
		Pengguna pe = pgs.getId(l.getPengguna().getId());  
		pd.setProyek(proyek);
		pd.setPengguna(pe);
		pds.saveOrUpdate(pd);
		return "redirect:dashboard-pencairan";
	}
}
