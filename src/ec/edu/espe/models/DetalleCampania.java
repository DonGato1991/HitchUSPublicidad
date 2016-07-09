/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.models;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author homer
 */
@Entity
@Table(name = "detalle_campania")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleCampania.findAll", query = "SELECT d FROM DetalleCampania d"),
    @NamedQuery(name = "DetalleCampania.findByRuc", query = "SELECT d FROM DetalleCampania d WHERE d.detalleCampaniaPK.ruc = :ruc"),
    @NamedQuery(name = "DetalleCampania.findBySecCampania", query = "SELECT d FROM DetalleCampania d WHERE d.detalleCampaniaPK.secCampania = :secCampania"),
    @NamedQuery(name = "DetalleCampania.findByIdElemento", query = "SELECT d FROM DetalleCampania d WHERE d.detalleCampaniaPK.idElemento = :idElemento"),
    @NamedQuery(name = "DetalleCampania.findByDespliegues", query = "SELECT d FROM DetalleCampania d WHERE d.despliegues = :despliegues"),
    @NamedQuery(name = "DetalleCampania.findByClics", query = "SELECT d FROM DetalleCampania d WHERE d.clics = :clics"),
    @NamedQuery(name = "DetalleCampania.findByModoFacturacion", query = "SELECT d FROM DetalleCampania d WHERE d.modoFacturacion = :modoFacturacion")})
public class DetalleCampania implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleCampaniaPK detalleCampaniaPK;
    @Column(name = "despliegues")
    private BigInteger despliegues;
    @Column(name = "clics")
    private BigInteger clics;
    @Column(name = "modo_facturacion")
    private String modoFacturacion;
    @JoinColumns({
        @JoinColumn(name = "ruc", referencedColumnName = "ruc", insertable = false, updatable = false),
        @JoinColumn(name = "sec_campania", referencedColumnName = "sec_campania", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Campania campania;
    @JoinColumn(name = "id_elemento", referencedColumnName = "id_elemento", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Elemento elemento;

    public DetalleCampania() {
    }

    public DetalleCampania(DetalleCampaniaPK detalleCampaniaPK) {
        this.detalleCampaniaPK = detalleCampaniaPK;
    }

    public DetalleCampania(String ruc, int secCampania, int idElemento) {
        this.detalleCampaniaPK = new DetalleCampaniaPK(ruc, secCampania, idElemento);
    }

    public DetalleCampaniaPK getDetalleCampaniaPK() {
        return detalleCampaniaPK;
    }

    public void setDetalleCampaniaPK(DetalleCampaniaPK detalleCampaniaPK) {
        this.detalleCampaniaPK = detalleCampaniaPK;
    }

    public BigInteger getDespliegues() {
        return despliegues;
    }

    public void setDespliegues(BigInteger despliegues) {
        this.despliegues = despliegues;
    }

    public BigInteger getClics() {
        return clics;
    }

    public void setClics(BigInteger clics) {
        this.clics = clics;
    }

    public String getModoFacturacion() {
        return modoFacturacion;
    }

    public void setModoFacturacion(String modoFacturacion) {
        this.modoFacturacion = modoFacturacion;
    }

    public Campania getCampania() {
        return campania;
    }

    public void setCampania(Campania campania) {
        this.campania = campania;
    }

    public Elemento getElemento() {
        return elemento;
    }

    public void setElemento(Elemento elemento) {
        this.elemento = elemento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleCampaniaPK != null ? detalleCampaniaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleCampania)) {
            return false;
        }
        DetalleCampania other = (DetalleCampania) object;
        if ((this.detalleCampaniaPK == null && other.detalleCampaniaPK != null) || (this.detalleCampaniaPK != null && !this.detalleCampaniaPK.equals(other.detalleCampaniaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.models.DetalleCampania[ detalleCampaniaPK=" + detalleCampaniaPK + " ]";
    }
    
}
