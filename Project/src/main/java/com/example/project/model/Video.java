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
 * @author Bimo Ardi Baskoro
 */
@Entity
public class Video extends Additional implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String linkVideo;

    public Video() {
    	
    }
    
    @ManyToOne
    @JoinColumn(name = "idProyek", referencedColumnName = "id", nullable = false)
    Proyek proyek;
    
    @ManyToOne
    @JoinColumn(name = "idProgresProyek", referencedColumnName = "id", nullable = true)
    ProgresProyek progresProyek;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Video)) {
            return false;
        }
        Video other = (Video) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitas.Video_Campaign[ id=" + id + " ]";
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getLinkVideo() {
		return linkVideo;
	}

	public void setLinkVideo(String linkVideo) {
		this.linkVideo = linkVideo;
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
