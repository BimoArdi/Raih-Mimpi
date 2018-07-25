package com.example.project.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Bimo Ardi Baskoro
 */
@Entity
public class Komentar extends Additional implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String isiKomentar;
   
    @ManyToOne
    @JoinColumn(name ="IdPengguna",referencedColumnName = "id"  ,nullable = false)
    private Pengguna pengguna;
    
    @ManyToOne
    @JoinColumn(name ="IdProyek",referencedColumnName = "id"  ,nullable = false)  
    private Proyek proyek;
    
    @ManyToOne
    private ProgresProyek progresProyek;
   
    
    public Komentar() {
	
	}

//    @PrePersist
//    public void tanggalKomen(){
//        tanggalKomentar = new Date();
//    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Komentar)) {
            return false;
        }
        Komentar other = (Komentar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitas.Komentar_Campaign[ id=" + id + " ]";
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsiKomentar() {
		return isiKomentar;
	}

	public void setIsiKomentar(String isiKomentar) {
		this.isiKomentar = isiKomentar;
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

	public ProgresProyek getProgresProyek() {
		return progresProyek;
	}

	public void setProgresProyek(ProgresProyek progresProyek) {
		this.progresProyek = progresProyek;
	}

    
}
