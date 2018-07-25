package com.example.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.project.model.Login;
import com.example.project.model.Pengguna;
import com.example.project.model.ProgresProyek;
import com.example.project.model.Proyek;
//import com.example.project.model.Login;
import com.example.project.model.Sponsor;
import com.example.project.services.LoginServices;
import com.example.project.services.PenggunaServices;
import com.example.project.services.ProgresProyekServices;
import com.example.project.services.ProyekServices;
import com.example.project.services.SponsorServices;

@RestController
@SessionAttributes("penggunaAktif")
public class RestControllerProyek {

	@Autowired
	SponsorServices ss;
	
	@Autowired
	ProyekServices ps;
	
	@Autowired
	PenggunaServices pgs;
	
	@Autowired
	ProgresProyekServices pps;
	
	@Autowired
	LoginServices lg;
	
	@RequestMapping(value="sponsorrest")
	public List<Sponsor> getSponsorById(@ModelAttribute("penggunaAktif")Login l){
		return ss.getSponsorById(l.getPengguna().getId());
	}
	
	@RequestMapping(value="oneproyek")
	public Proyek getTanggal(@ModelAttribute("Proyek")com.example.project.model.Proyek p) {
		return ps.getById(p.getId());
	}
	
	@RequestMapping(value="penggunausername")
	public List<Pengguna> getUsernameAndPassword(){
		return pgs.getAll();
	}
	
	@RequestMapping(value="raihmimpi")
	public List<Proyek> getAllProyek(){
		return ps.getAll();
	}
	
	@RequestMapping(value="oneprogresproyek")
	public ProgresProyek getId (@ModelAttribute("ProgresProyek")ProgresProyek pp) {
		return pps.getById(pp.getId());
	}
	
	@RequestMapping("API/isusernameexist")
	public boolean isUsernameExist(@RequestParam("username") String username) {
		return lg.getUsername(username);
	}
	
	
}
