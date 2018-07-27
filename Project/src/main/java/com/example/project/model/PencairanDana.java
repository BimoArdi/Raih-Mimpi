package com.example.project.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Siti
 */
@Entity
public class PencairanDana extends Additional implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String namaPencair;
    private String noRekening;
    private float jumlahDana;
    private boolean status;
    
	public PencairanDana () {
    	
    }
    
    @ManyToOne
    @JoinColumn(name ="IdProyek",referencedColumnName = "id"  ,nullable = false)
    private Proyek proyek;
        
    @ManyToOne
    @JoinColumn(name ="IdPengguna",referencedColumnName = "id"  ,nullable = false)
    private Pengguna pengguna;
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PencairanDana)) {
            return false;
        }
        PencairanDana other = (PencairanDana) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.PenggunaanDana[ id=" + id + " ]";
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNamaPencair() {
		return namaPencair;
	}

	public void setNamaPencair(String namaPencair) {
		this.namaPencair = namaPencair;
	}

	public String getNoRekening() {
		return noRekening;
	}

	public void setNoRekening(String noRekening) {
		this.noRekening = noRekening;
	}

	public float getJumlahDana() {
		return jumlahDana;
	}

	public void setJumlahDana(float jumlahDana) {
		this.jumlahDana = jumlahDana;
	}

	public Proyek getProyek() {
		return proyek;
	}

	public void setProyek(Proyek proyek) {
		this.proyek = proyek;
	}
    
    public Pengguna getPengguna() {
		return pengguna;
	}

	public void setPengguna(Pengguna pengguna) {
		this.pengguna = pengguna;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
