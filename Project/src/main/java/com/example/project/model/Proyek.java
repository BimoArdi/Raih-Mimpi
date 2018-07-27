package com.example.project.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Ulfa
 */

@Entity
public class Proyek extends Additional implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50,nullable = false)
    private String namaProyek;
    @Column(length = 500,nullable = false)
    private String deskripsiSingkat;
    @Column(length = 5000,nullable = false)
    private String deskripsiLengkap;
    @Column(length = 100,nullable = false)
    private String link;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date tanggalBerakhir;
    @Column(length = 30,nullable = false)
    private float targetDana;
    @Column(length = 30,nullable = false)
    private double komisiPersen;
    private double komisiFlat;
    private boolean fitur;
    private int suka;
    private float totalDana;
    private float sisaDana;

    @ManyToOne
    @JoinColumn(name = "idWilayah", referencedColumnName = "id", nullable = true)
    private Wilayah wilayah;
    
    @ManyToOne
    @JoinColumn(name = "idStatus", referencedColumnName = "id", nullable = false)
    private Status status;

    
    @ManyToOne
    @JoinColumn(name = "idPengguna", referencedColumnName = "id", nullable = true)
    private Pengguna pengguna;
    
    @ManyToOne
    @JoinColumn(name = "idKategori", referencedColumnName = "id", nullable = false)
    private Kategori kategori;
    
    @OneToMany(mappedBy = "proyek")
    @JsonIgnore
    private List<Video> video;

    @OneToMany(mappedBy = "proyek")
    @JsonIgnore
    private List<Sponsor> sponsor;

    @OneToMany(mappedBy = "proyek")
    @JsonIgnore
    private List<ProgresProyek> progresProyek;

    @OneToMany(mappedBy = "proyek")
    @JsonIgnore
    private List<Komentar> komentar;

    @OneToMany(mappedBy = "proyek")
    @JsonIgnore
    private List<Foto> foto;
    
    @OneToMany(mappedBy = "proyek")
    @JsonIgnore
    private List<PencairanDana> penggunaanDana;
    
    @JsonIgnore
    public Foto getFotoUtamaProyek() {
    	Foto tes = null;
		for(Foto f : this.foto) {
			if(f.isUtamaProyek()==true) {
				tes = f;
				break;
			}
		}	
		return tes;
	}

    @SuppressWarnings("null")
	@JsonIgnore
    public List<Foto> getFotoTanpaUtamaProyek(){
    	List<Foto> all = null;
    	for (Foto f :this.foto) {
    		if(f.isUtamaProyek() == false & f.isUtamaProgresProyek() == false & f.getProgresProyek() == null ) {
    			all.add(f);
    		}
    	}
    	return all;
    }
    
    @JsonIgnore
    public double getPersentaseDana() {
    	double persen = 0.0;
    	persen = (this.totalDana/this.targetDana)*100;
    	return persen;
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
        if (!(object instanceof Proyek)) {
            return false;
        }
        Proyek other = (Proyek) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Projek[ id=" + id + " ]";
    }

    
    public Proyek() {
    	
    }
    
    public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Kategori getKategori() {
		return kategori;
	}

	public void setKategori(Kategori kategori) {
		this.kategori = kategori;
	}

	public List<PencairanDana> getPenggunaanDana() {
		return penggunaanDana;
	}

	public void setPenggunaanDana(List<PencairanDana> penggunaanDana) {
		this.penggunaanDana = penggunaanDana;
	}

	public float getSisaDana() {
		return sisaDana;
	}

	public void setSisaDana(float sisaDana) {
		this.sisaDana = sisaDana;
	}

	@PrePersist
    public void set(){
        komisiPersen=0.05;
        fitur=false;
        suka = 0;
    }
	
    public Wilayah getWilayah() {
        return wilayah;
    }

    public void setWilayah(Wilayah wilayah) {
        this.wilayah = wilayah;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaProyek() {
        return namaProyek;
    }

    public void setNamaProyek(String namaProyek) {
        this.namaProyek = namaProyek;
    }

    public String getDeskripsiSingkat() {
        return deskripsiSingkat;
    }

    public void setDeskripsiSingkat(String deskripsiSingkat) {
        this.deskripsiSingkat = deskripsiSingkat;
    }

    public String getDeskripsiLengkap() {
        return deskripsiLengkap;
    }

    public void setDeskripsiLengkap(String deskripsiLengkap) {
        this.deskripsiLengkap = deskripsiLengkap;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getTanggalBerakhir() {
        return tanggalBerakhir;
    }

    public void setTanggalBerakhir(Date tanggalBerakhir) {
        this.tanggalBerakhir = tanggalBerakhir;
    }

    public float getTargetDana() {
        return targetDana;
    }

    public void setTargetDana(float targetDana) {
        this.targetDana = targetDana;
    }

    public double getKomisiPersen() {
        return komisiPersen;
    }

    public void setKomisiPersen(double komisiPersen) {
        this.komisiPersen = komisiPersen;
    }

    public double getKomisiFlat() {
        return komisiFlat;
    }

    public void setKomisiFlat(double komisiFlat) {
        this.komisiFlat = komisiFlat;
    }

    public boolean isFitur() {
        return fitur;
    }

    public void setFitur(boolean fitur) {
        this.fitur = fitur;
    }

    public int getSuka() {
        return suka;
    }

    public void setSuka(int suka) {
        this.suka = suka;
    }

	public double getTotalDana() {
        return totalDana;
    }

    public void setTotalDana(float totalDana) {
        this.totalDana = totalDana;
    }

    public List<Video> getVideo() {
        return video;
    }

    public void setVideo(List<Video> video) {
        this.video = video;
    }

    public List<Sponsor> getSponsor() {
        return sponsor;
    }

    public void setSponsor(List<Sponsor> sponsor) {
        this.sponsor = sponsor;
    }

    public List<ProgresProyek> getProgresProyek() {
        return progresProyek;
    }

    public void setProgresProyek(List<ProgresProyek> progresProyek) {
        this.progresProyek = progresProyek;
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
}
