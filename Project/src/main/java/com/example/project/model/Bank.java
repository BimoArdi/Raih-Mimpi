package com.example.project.model;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 */
@Entity
public class Bank extends Additional implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String noRekening;
    
    private String namaBank;
    
    @OneToMany(mappedBy = "bank")
    @JsonIgnore
    private List<Sponsor> sponsor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNoRekening() {
		return noRekening;
	}

	public void setNoRekening(String noRekening) {
		this.noRekening = noRekening;
	}

	public String getNamaBank() {
		return namaBank;
	}

	public void setNamaBank(String namaBank) {
		this.namaBank = namaBank;
	}

	public List<Sponsor> getSponsor() {
		return sponsor;
	}

	public void setSponsor(List<Sponsor> sponsor) {
		this.sponsor = sponsor;
	}

}