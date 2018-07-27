package com.example.project.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Ulfa
 */
@Entity
public class ProgresProyek extends Additional implements Serializable {

    @OneToMany(mappedBy = "progresProyek")
    @JsonIgnore
    private List<Video> video;

    @OneToMany(mappedBy = "progresProyek")
    @JsonIgnore
    private List<Komentar> komentar;

    @OneToMany(mappedBy = "progresProyek")
    @JsonIgnore
    private List<Foto> foto;


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100,nullable = false)
    private String judulProyek;
    @Column(length = 1000,nullable = false)
    private String deskripsiLengkap1;
    @Column(length = 11)
    private int suka;

    public ProgresProyek () {
    	
    }
    
    @ManyToOne
    @JoinColumn(name = "idProyek", referencedColumnName = "id", nullable = true)
    Proyek proyek;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    public Foto getFotoUtamaProgresProyek() {
    	Foto tes = null;
		for(Foto f : this.foto) {
			if(f.isUtamaProgresProyek()==true) {
				tes = f;
				break;
			}
		}	
		return tes;
	}
    
    @SuppressWarnings("null")
    public List<Foto> getFotoTanpaUtamaProgresProyek(){
    	List<Foto> all = null;
    	for (Foto f :this.foto) {
    		if(f.isUtamaProgresProyek() == false & f.isUtamaProyek() == false & f.getProyek() == null) {
    			all.add(f);
    		}
    	}
    	return all;
    }
    

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgresProyek)) {
            return false;
        }
        ProgresProyek other = (ProgresProyek) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.UpdateCampaign[ id=" + id + " ]";
    }


	public List<Video> getVideo() {
		return video;
	}


	public void setVideo(List<Video> video) {
		this.video = video;
	}


	public List<Komentar> getKomentar() {
		return komentar;
	}


	public void setKomentar(List<Komentar> komentar) {
		this.komentar = komentar;
	}


	public List<Foto> getFoto() {
		return foto;
	}


	public void setFoto(List<Foto> foto) {
		this.foto = foto;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getJudulProyek() {
		return judulProyek;
	}


	public void setJudulProyek(String judulProyek) {
		this.judulProyek = judulProyek;
	}


	public String getDeskripsiLengkap1() {
		return deskripsiLengkap1;
	}


	public void setDeskripsiLengkap1(String deskripsiLengkap1) {
		this.deskripsiLengkap1 = deskripsiLengkap1;
	}


	public int getSuka() {
		return suka;
	}


	public void setSuka(int suka) {
		this.suka = suka;
	}


	public Proyek getProyek() {
		return proyek;
	}


	public void setProyek(Proyek proyek) {
		this.proyek = proyek;
	}

}
