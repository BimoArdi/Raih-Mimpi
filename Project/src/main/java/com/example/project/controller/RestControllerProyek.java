package com.example.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.model.Foto;
import com.example.project.model.Pengguna;
import com.example.project.model.ProgresProyek;
import com.example.project.model.Proyek;
import com.example.project.model.Sponsor;
import com.example.project.services.BankServices;
import com.example.project.services.FotoServices;
import com.example.project.services.LoginServices;
import com.example.project.services.PenggunaServices;
import com.example.project.services.ProgresProyekServices;
import com.example.project.services.ProyekServices;
import com.example.project.services.SponsorServices;

@RestController
public class RestControllerProyek {

	@Autowired
	SponsorServices ss;

	@Autowired
	FotoServices fs;

	@Autowired
	ProyekServices ps;
	
	@Autowired
	PenggunaServices pgs;
	
	@Autowired
	ProgresProyekServices pps;
	
	@Autowired
	LoginServices lg;
	
	@Autowired
	BankServices bs;	
	
	@RequestMapping(value="sponsorrest")
	public List<Sponsor> getSponsorById(@RequestParam("id")long id){
		return ss.getByPengguna(id);
	}
	
	@RequestMapping(value="oneproyek") 
	public Proyek getTanggal(@RequestParam("id")long id) {
		return ps.getById(id);
	}
	
	@RequestMapping(value="penggunausername")
	public List<Pengguna> getUsernameAndPassword(){
		return pgs.getAll();
	}
	
	@RequestMapping(value="raihmimpi")
	public List<Proyek> getAllProyek(){
		return ps.getAll();
	}
	
	@RequestMapping(value="oneprogresproyek") // ga bisa
	public ProgresProyek getId (@RequestParam("id")long id) {
		return pps.getById(id);
	}
	
	@RequestMapping("API/isusernameexist")
	public boolean isUsernameExist(@RequestParam("username") String username) {
		return lg.getUsername(username);
	}
	
	@RequestMapping(value="oneproyeksisadana") 
	public float sisaDana(@RequestParam("id")long id) {
		return ps.getSisaDana(id);
	}
	
	@RequestMapping(value="kategori")
	public int kategori(@RequestParam("id")long id) {
		return ps.getJumlahKategori(id);
	}
	
	@RequestMapping(value="totalsponsor")
	public int sponsor() {
		return ss.getTotalSponsor();
	}
	
	@RequestMapping(value="rekeningbank")
	public String rekeningBank (@RequestParam("id")long id) {
		return bs.getById(id).getNoRekening();
	}
	
	@RequestMapping(value="searching")
	public List<Proyek> searching (@RequestParam("keyword")String keyword){
		return ps.getBySearch(keyword);
	}
	
	@RequestMapping(value="fotoproyek")
	public List<Foto> getFoto(){
		return fs.getAll();
	}
}
