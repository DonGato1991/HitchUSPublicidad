/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
@Table(name = "target_edad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TargetEdad.findAll", query = "SELECT t FROM TargetEdad t"),
    @NamedQuery(name = "TargetEdad.findByRuc", query = "SELECT t FROM TargetEdad t WHERE t.targetEdadPK.ruc = :ruc"),
    @NamedQuery(name = "TargetEdad.findByIdTargetEdad", query = "SELECT t FROM TargetEdad t WHERE t.targetEdadPK.idTargetEdad = :idTargetEdad"),
    @NamedQuery(name = "TargetEdad.findByNombre", query = "SELECT t FROM TargetEdad t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TargetEdad.findByDescripcion", query = "SELECT t FROM TargetEdad t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TargetEdad.findByEdadMinima", query = "SELECT t FROM TargetEdad t WHERE t.edadMinima = :edadMinima"),
    @NamedQuery(name = "TargetEdad.findByEdadMaxima", query = "SELECT t FROM TargetEdad t WHERE t.edadMaxima = :edadMaxima"),
    @NamedQuery(name = "TargetEdad.findByGenero", query = "SELECT t FROM TargetEdad t WHERE t.genero = :genero")})
public class TargetEdad implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TargetEdadPK targetEdadPK;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "edad_minima")
    private int edadMinima;
    @Basic(optional = false)
    @Column(name = "edad_maxima")
    private int edadMaxima;
    @Basic(optional = false)
    @Column(name = "genero")
    private String genero;
    @JoinColumn(name = "ruc", referencedColumnName = "ruc", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empresa empresa;

    public TargetEdad() {
    }

    public TargetEdad(TargetEdadPK targetEdadPK) {
        this.targetEdadPK = targetEdadPK;
    }

    public TargetEdad(TargetEdadPK targetEdadPK, String nombre, int edadMinima, int edadMaxima, String genero) {
        this.targetEdadPK = targetEdadPK;
        this.nombre = nombre;
        this.edadMinima = edadMinima;
        this.edadMaxima = edadMaxima;
        this.genero = genero;
    }

    public TargetEdad(String ruc, int idTargetEdad) {
        this.targetEdadPK = new TargetEdadPK(ruc, idTargetEdad);
    }

    public TargetEdadPK getTargetEdadPK() {
        return targetEdadPK;
    }

    public void setTargetEdadPK(TargetEdadPK targetEdadPK) {
        this.targetEdadPK = targetEdadPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }

    public int getEdadMaxima() {
        return edadMaxima;
    }

    public void setEdadMaxima(int edadMaxima) {
        this.edadMaxima = edadMaxima;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (targetEdadPK != null ? targetEdadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TargetEdad)) {
            return false;
        }
        TargetEdad other = (TargetEdad) object;
        if ((this.targetEdadPK == null && other.targetEdadPK != null) || (this.targetEdadPK != null && !this.targetEdadPK.equals(other.targetEdadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.models.TargetEdad[ targetEdadPK=" + targetEdadPK + " ]";
    }
    
}
