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
public class DetalleCampaniaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ruc")
    private String ruc;
    @Basic(optional = false)
    @Column(name = "sec_campania")
    private int secCampania;
    @Basic(optional = false)
    @Column(name = "id_elemento")
    private int idElemento;

    public DetalleCampaniaPK() {
    }

    public DetalleCampaniaPK(String ruc, int secCampania, int idElemento) {
        this.ruc = ruc;
        this.secCampania = secCampania;
        this.idElemento = idElemento;
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

    public int getIdElemento() {
        return idElemento;
    }

    public void setIdElemento(int idElemento) {
        this.idElemento = idElemento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ruc != null ? ruc.hashCode() : 0);
        hash += (int) secCampania;
        hash += (int) idElemento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleCampaniaPK)) {
            return false;
        }
        DetalleCampaniaPK other = (DetalleCampaniaPK) object;
        if ((this.ruc == null && other.ruc != null) || (this.ruc != null && !this.ruc.equals(other.ruc))) {
            return false;
        }
        if (this.secCampania != other.secCampania) {
            return false;
        }
        if (this.idElemento != other.idElemento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.models.DetalleCampaniaPK[ ruc=" + ruc + ", secCampania=" + secCampania + ", idElemento=" + idElemento + " ]";
    }
    
}
