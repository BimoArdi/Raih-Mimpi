package com.example.project.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnore;




/**
 *
 * @author Bimo Ardi Baskoro
 */
@Entity
public class Pengguna extends Additional implements Serializable {

    @OneToMany(mappedBy = "pengguna")
    @JsonIgnore
    private List<Proyek> proyek;

    @OneToMany(mappedBy = "pengguna")
    @JsonIgnore
    private List<PencairanDana> penggunaanDana;
    
    @OneToMany(mappedBy = "pengguna")
    @JsonIgnore
    private List<Sponsor> sponsor;

    @OneToMany(mappedBy = "pengguna")
    @JsonIgnore
    private List<Komentar> komentar;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   
    private Long id;
        
    
    @Column(length = 50)
    private String nama;
    
    @Column(length = 25)
    private String telephone;
    
    @Column(length = 3000)
    private String biografi;
    
    private byte [] ktp;
    
    private byte [] verifikasi;
    
    private String status;
    
    private byte [] fotoProfil;
    
    @PrePersist
    public void set() {
    	nama=login.getUsername();
    	telephone="-";
    	biografi="belum diisi";
        status = "Not Active";
    }
    
    
    @OneToOne
    @JoinColumn(name = "idLogin", referencedColumnName = "id",unique = true, nullable = false)        
    private Login login;
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pengguna)) {
            return false;
        }
        Pengguna other = (Pengguna) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitas.Pengguna[ id=" + id + " ]";
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public List<Komentar> getKomentar() {
        return komentar;
    }

    public void setKomentar(List<Komentar> komentar) {
        this.komentar = komentar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public byte[] getKtp() {
        return ktp;
    }

    public void setKtp(byte[] ktp) {
        this.ktp = ktp;
    }

    public byte[] getVerifikasi() {
        return verifikasi;
    }

    public void setVerifikasi(byte[] verifikasi) {
        this.verifikasi = verifikasi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Proyek> getProyek() {
        return proyek;
    }

    public void setProyek(List<Proyek> proyek) {
        this.proyek = proyek;
    }

    public List<Sponsor> getSponsor() {
        return sponsor;
    }

    public void setSponsor(List<Sponsor> sponsor) {
        this.sponsor = sponsor;
    }

	public String getBiografi() {
		return biografi;
	}

	public void setBiografi(String biografi) {
		this.biografi = biografi;
	}

	public byte [] getFotoProfil() {
		return fotoProfil;
	}

	public void setFotoProfil(byte[] fotoProfil) {
		this.fotoProfil = fotoProfil;
	}

	public List<PencairanDana> getPenggunaanDana() {
		return penggunaanDana;
	}

	public void setPenggunaanDana(List<PencairanDana> penggunaanDana) {
		this.penggunaanDana = penggunaanDana;
	}

	
}

