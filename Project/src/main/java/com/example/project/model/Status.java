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
public class Status extends Additional implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String status;
    
    @OneToMany(mappedBy = "status")
    @JsonIgnore
    private List<Sponsor> sponsor;

    @OneToMany(mappedBy = "status")
    @JsonIgnore
    private List<Proyek> proyek;
    
    @OneToMany(mappedBy = "status")
    @JsonIgnore
    private List<Login> login;

    @OneToMany(mappedBy = "status")
    @JsonIgnore
    private List<Pengguna> pengguna;

    @OneToMany(mappedBy = "status")
    @JsonIgnore
    private List<PencairanDana> pencairanDana;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Status)) {
            return false;
        }
        Status other = (Status) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitas.Status[ id=" + id + " ]";
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Sponsor> getSponsor() {
		return sponsor;
	}

	public void setSponsor(List<Sponsor> sponsor) {
		this.sponsor = sponsor;
	}

	public List<Proyek> getProyek() {
		return proyek;
	}

	public void setProyek(List<Proyek> proyek) {
		this.proyek = proyek;
	}

	public List<Login> getLogin() {
		return login;
	}

	public void setLogin(List<Login> login) {
		this.login = login;
	}

	public List<Pengguna> getPengguna() {
		return pengguna;
	}

	public void setPengguna(List<Pengguna> pengguna) {
		this.pengguna = pengguna;
	}

	public List<PencairanDana> getPencairanDana() {
		return pencairanDana;
	}

	public void setPencairanDana(List<PencairanDana> pencairanDana) {
		this.pencairanDana = pencairanDana;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    
}
    
