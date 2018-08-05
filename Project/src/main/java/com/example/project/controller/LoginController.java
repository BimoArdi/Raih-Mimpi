package com.example.project.controller;


import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.example.project.model.Kategori;
import com.example.project.model.Login;
import com.example.project.model.Pengguna;
import com.example.project.model.Proyek;
import com.example.project.services.KategoriServices;
import com.example.project.services.LoginServices;
import com.example.project.services.PenggunaServices;
import com.example.project.services.PeranServices;
import com.example.project.services.ProyekServices;
import com.example.project.services.SponsorServices;
import com.example.project.helper.EnkripHelper;


@Controller
@SessionAttributes("penggunaAktif")
public class LoginController {
	
	@Autowired
	LoginServices lg;
	
	@Autowired
	PenggunaServices pgs;
	
	@Autowired
	KategoriServices ks;
	
	@Autowired
	ProyekServices ps;
	
	@Autowired
	PeranServices prs;
	
	@Autowired
	SponsorServices ss;

	@RequestMapping(value="/verifikasiakun")
	public String verifikasiakun (ModelMap mm) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);
		return "verifikasi akun";
	}
	
	@RequestMapping(value="/verifikasiakun", method=RequestMethod.POST)
//	@ResponseBody
	public String postRegist( @ModelAttribute("Pengguna") Pengguna pg,@RequestParam("username1")String username,
			@RequestParam("email1")String email) {
		Login l = new Login();
		l.setUsername(username);
		l.setEmail(email);
		try {
			l.setConfirmationToken(EnkripHelper.hash256(email));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		l.setCreateBy("user");
		l.setKeterangan("akun user belum aktif");
		l.setStatus("Tidak Aktif");
		l.setPeran(prs.getPeran("User"));
		lg.saveOrUpdate(l);
		pg.setNama(l.getUsername());
		pg.setLogin(l);
		pg.setStatus("Tidak Aktif");
		pgs.saveOrUpdate(pg);
		return "redirect:verifikasiakun";
//		return "l";
	}
	
	@RequestMapping(value="/home",method=RequestMethod.POST)
	public ModelAndView getLogin(@RequestParam("username")String username, 
			@RequestParam("password")String pass,ModelMap mm) throws NoSuchAlgorithmException {
	ModelAndView mav= new ModelAndView();
	Login l = lg.getLogin(username,EnkripHelper.hash256(pass));
	if(l == null) {
		mav.setViewName("login");
	}else {
		mav.addObject("penggunaAktif",l);
		mav.setViewName("index-1"); 
	}
	List<Kategori> k = ks.getAll();
	List<Proyek> ls = ps.getByFitur();
	List<Proyek> ls1 = ps.getByLast();
	List<Proyek> ls2 = new ArrayList<>();
	int k1 = ps.getJumlahKategori(1L);
	int k2 = ps.getJumlahKategori(2L);
	int k3 = ps.getJumlahKategori(3L);
	int k4 = ps.getJumlahKategori(4L);
	int k5 = ps.getJumlahKategori(5L);
	int s = ss.getTotalSponsor();
	for(int i =0 ;i< ls1.size(); i++){
		ls2.add(ls1.get(i));
		if(i==2) {
			break;
		}
	}
	mm.put("k1",k1);
	mm.put("k2",k2);
	mm.put("k3",k3);
	mm.put("k4",k4);
	mm.put("k5",k5);
	mm.put("s",s);
	mm.put("listproyek1",ls2);
	mm.put("listproyek",ls);
	mm.put("listkategori",k);	return mav;
	}
	
	@RequestMapping(value="dashboard-password")
	public String dashboardPassword( Model m, ModelMap mm) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);
		return "Dashboard Password";
	}
		
	@RequestMapping(value="dashboard-password", method=RequestMethod.POST)
	public String ubahPassword(@ModelAttribute("penggunaAktif")Login login, @RequestParam("password")String password){
		try {
			login.setPassword(EnkripHelper.hash256(password));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		lg.saveOrUpdate(login); 
		return "redirect:dashboard-password";
	}
	
	@RequestMapping("/logout")
	public String goLogOut(HttpSession session,SessionStatus status,ModelMap mm) {
		status.setComplete();
		session.removeAttribute("penggunaAktif");
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);
		return "index";
	}
	
	// Process confirmation link
	@RequestMapping(value="/confirm", method = RequestMethod.GET)
	public String confirmRegistration(ModelMap mm,@RequestParam("code")String code) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);
		mm.put("token",code);
		Login l = lg.getConfirmationToken(code);
		if(l!=null) {
			l.setStatus("Aktif");
		}
		lg.saveOrUpdate(l);
		return "confirm";		
	}
	
	@RequestMapping(value="login")
	public String kelogin(ModelMap mm) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);
		return "login";
	}
	
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ModelAndView Login (ModelMap mm, @RequestParam("token")String token, @RequestParam("password")String password) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);
		Login l = lg.getConfirmationToken(token);
		try {
			l.setPassword(EnkripHelper.hash256(password));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		lg.saveOrUpdate(l);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping(value="forget-password")
	public String forgetPassword(ModelMap mm) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);
		return "Forget Password";
	}	
	
	@RequestMapping("/backtologin")
	public String gotoLogin(@RequestParam("email")String email) {
		Login l = lg.getbyEmail(email);
		lg.sendEmail(l,"Forgetten Password","To set new password, please copy this link below into your url:\n"+"localhost:8080/setpassword?code="+l.getConfirmationToken()+"&key="+l.getUsername());
		return "login";
	}
	
	@RequestMapping(value="/setpassword", method = RequestMethod.GET)
	public String setNewPassword(ModelMap mm,@RequestParam("code")String code, @RequestParam("key")String key) {
		List<Kategori> k = ks.getAll();
		mm.put("listkategori",k);
		mm.put("token",code);
		return "setpassword";		
	}
	
	// Process confirmation link
//	@RequestMapping(value="/confirm", method = RequestMethod.POST)
//	public String confirmRegistration(@RequestParam("password")String password) {
//					
//		// Find the user associated with the reset token
//		Login login = lg.getConfirmationToken(requestParams.get("token"));
//
//		// Set new password
//		try {
//			login.setPassword(EnkripHelper.hash256(requestParams.get("password")));
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
//
//		
//		// Set user to enabled
//		login.setStatus(true);
//		
//		// Save user
//		lg.saveOrUpdate(login);
//		
//		modelAndView.addObject("successMessage", "Your password has been set!");
//		return modelAndView;		
//	}

}
