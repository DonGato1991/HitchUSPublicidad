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
public class TargetEdadPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ruc")
    private String ruc;
    @Basic(optional = false)
    @Column(name = "id_target_edad")
    private int idTargetEdad;

    public TargetEdadPK() {
    }

    public TargetEdadPK(String ruc, int idTargetEdad) {
        this.ruc = ruc;
        this.idTargetEdad = idTargetEdad;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public int getIdTargetEdad() {
        return idTargetEdad;
    }

    public void setIdTargetEdad(int idTargetEdad) {
        this.idTargetEdad = idTargetEdad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ruc != null ? ruc.hashCode() : 0);
        hash += (int) idTargetEdad;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TargetEdadPK)) {
            return false;
        }
        TargetEdadPK other = (TargetEdadPK) object;
        if ((this.ruc == null && other.ruc != null) || (this.ruc != null && !this.ruc.equals(other.ruc))) {
            return false;
        }
        if (this.idTargetEdad != other.idTargetEdad) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.models.TargetEdadPK[ ruc=" + ruc + ", idTargetEdad=" + idTargetEdad + " ]";
    }
    
}
