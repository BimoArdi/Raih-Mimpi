/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.project.model;


import java.util.Date;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;

/**
 *
 * @author Bimo Ardi Baskoro
 */
@MappedSuperclass
public abstract class Additional {
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createDate;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date updateDate;
    
    private String createBy ;
    private String updateBy;
    private String keterangan;

    /**
     * @return the CreateDate
     */
    
    @PrePersist
    public void onCreate(){
        createDate= new Date();
       
    }
    @PreUpdate
    public void onUpdate(){
        updateDate= new Date();       
        
    }
    
    
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param CreateDate the CreateDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the UpdateDate
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param UpdateDate the UpdateDate to set
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return the CreateBy
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * @param CreateBy the CreateBy to set
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * @return the UpdateBy
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * @param UpdateBy the UpdateBy to set
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * @return the Keterangan
     */
    public String getKeterangan() {
        return keterangan;
    }

    /**
     * @param Keterangan the Keterangan to set
     */
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
    
}
