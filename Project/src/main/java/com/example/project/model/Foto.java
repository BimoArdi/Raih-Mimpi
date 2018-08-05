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
public class Foto extends Additional implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) //ga boleh null
    private String namaFoto;
    private byte[] foto;
    private boolean utamaProyek;
    private boolean utamaProgresProyek;
    
    public Foto() {
    	
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Foto)) {
            return false;
        }
        Foto other = (Foto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitas.Foto_Campaign[ id=" + id + " ]";
    }

    @ManyToOne
    private ProgresProyek progresProyek;
    
    @ManyToOne
    @JoinColumn(name ="IdProyek",referencedColumnName = "id"  ,nullable = false)
    private Proyek proyek;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaFoto() {
        return namaFoto;
    }

    public void setNamaFoto(String namaFoto) {
        this.namaFoto = namaFoto;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public ProgresProyek getProgresProyek() {
        return progresProyek;
    }

    public void setProgresProyek(ProgresProyek progresProyek) {
        this.progresProyek = progresProyek;
    }

    public Proyek getProyek() {
        return proyek;
    }

    public void setProyek(Proyek proyek) {
        this.proyek = proyek;
    }

	public boolean isUtamaProyek() {
		return utamaProyek;
	}

	public void setUtamaProyek(boolean utamaProyek) {
		this.utamaProyek = utamaProyek;
	}

	public boolean isUtamaProgresProyek() {
		return utamaProgresProyek;
	}

	public void setUtamaProgresProyek(boolean utamaProgresProyek) {
		this.utamaProgresProyek = utamaProgresProyek;
	}
    
}
