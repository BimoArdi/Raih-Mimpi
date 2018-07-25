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
 *
 * @author Siti
 */
@Entity
public class Wilayah extends Additional implements Serializable {

    @OneToMany(mappedBy = "wilayah")
    @JsonIgnore
    private List<Proyek> proyek;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String namaWilayah;

    public Wilayah() {
    	
    }
    
    public List<Proyek> getProyek() {
		return proyek;
	}

	public void setProyek(List<Proyek> proyek) {
		this.proyek = proyek;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNamaWilayah() {
		return namaWilayah;
	}

	public void setNamaWilayah(String namaWilayah) {
		this.namaWilayah = namaWilayah;
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
        if (!(object instanceof Wilayah)) {
            return false;
        }
        Wilayah other = (Wilayah) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Wilayah[ id=" + id + " ]";
    }

    
}
