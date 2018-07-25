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
public class Peran extends Additional implements Serializable {

	@OneToMany(mappedBy = "peran")
	@JsonIgnore
    private List<Login> login;
	
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String peran;
    
    public Peran () {
    	
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
        if (!(object instanceof Peran)) {
            return false;
        }
        Peran other = (Peran) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Peran[ id=" + id + " ]";
    }
	public List<Login> getLogin() {
		return login;
	}
	public void setLogin(List<Login> login) {
		this.login = login;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPeran() {
		return peran;
	}
	public void setPeran(String peran) {
		this.peran = peran;
	}

    
}
