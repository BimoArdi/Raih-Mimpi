package com.example.project.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

/**
 *
 * @author Bimo Ardi Baskoro
 */
@Entity
public class Sponsor extends Additional implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    
    @Basic(optional = false) //Gak Boleh gak di Isi 
    @Column(nullable = false, length=50)//Gak Boleh null
    private float jumlahSponsor;
    private String anonim;
    private String namaSponsor;

    @PrePersist
    public void getSponsor() {
    	if(anonim == null) {
    		namaSponsor = pengguna.getNama();
    	}else {
    		namaSponsor = "anonim";
    	}
    }
    
    @ManyToOne
    @JoinColumn(name = "idPengguna", referencedColumnName = "id", nullable = false)
    Pengguna pengguna;
    
    @ManyToOne
    @JoinColumn(name = "idProyek", referencedColumnName = "id", nullable = false)
    Proyek proyek;
 
    @ManyToOne
    @JoinColumn(name = "idBank", referencedColumnName = "id", nullable = false)
    private Bank bank;

    @ManyToOne
    @JoinColumn(name = "idStatus", referencedColumnName = "id", nullable = false)
    private Status status;

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sponsor)) {
            return false;
        }
        Sponsor other = (Sponsor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public float getJumlahSponsor() {
		return jumlahSponsor;
	}


	public void setJumlahSponsor(float jumlahSponsor) {
		this.jumlahSponsor = jumlahSponsor;
	}


	public String getAnonim() {
		return anonim;
	}


	public void setAnonim(String anonim) {
		this.anonim = anonim;
	}

	public String getNamaSponsor() {
		return namaSponsor;
	}


	public void setNamaSponsor(String namaSponsor) {
		this.namaSponsor = namaSponsor;
	}


	public Bank getBank() {
		return bank;
	}


	public void setBank(Bank bank) {
		this.bank = bank;
	}


	public Pengguna getPengguna() {
		return pengguna;
	}


	public void setPengguna(Pengguna pengguna) {
		this.pengguna = pengguna;
	}


	public Proyek getProyek() {
		return proyek;
	}


	public void setProyek(Proyek proyek) {
		this.proyek = proyek;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}
}
