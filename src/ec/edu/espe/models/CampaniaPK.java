/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author homer
 */
@Embeddable
public class CampaniaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ruc")
    private String ruc;
    @Basic(optional = false)
    @Column(name = "sec_campania")
    private int secCampania;

    public CampaniaPK() {
    }

    public CampaniaPK(String ruc, int secCampania) {
        this.ruc = ruc;
        this.secCampania = secCampania;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public int getSecCampania() {
        return secCampania;
    }

    public void setSecCampania(int secCampania) {
        this.secCampania = secCampania;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ruc != null ? ruc.hashCode() : 0);
        hash += (int) secCampania;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CampaniaPK)) {
            return false;
        }
        CampaniaPK other = (CampaniaPK) object;
        if ((this.ruc == null && other.ruc != null) || (this.ruc != null && !this.ruc.equals(other.ruc))) {
            return false;
        }
        if (this.secCampania != other.secCampania) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.models.CampaniaPK[ ruc=" + ruc + ", secCampania=" + secCampania + " ]";
    }
    
}
