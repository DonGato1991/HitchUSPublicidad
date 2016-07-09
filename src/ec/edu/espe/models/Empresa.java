/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author homer
 */
@Entity
@Table(name = "empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e"),
    @NamedQuery(name = "Empresa.findByRuc", query = "SELECT e FROM Empresa e WHERE e.ruc = :ruc"),
    @NamedQuery(name = "Empresa.findByRazonSocial", query = "SELECT e FROM Empresa e WHERE e.razonSocial = :razonSocial"),
    @NamedQuery(name = "Empresa.findByEmail", query = "SELECT e FROM Empresa e WHERE e.email = :email"),
    @NamedQuery(name = "Empresa.findByTelefono", query = "SELECT e FROM Empresa e WHERE e.telefono = :telefono"),
    @NamedQuery(name = "Empresa.findByDireccion", query = "SELECT e FROM Empresa e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "Empresa.findByRepresentante", query = "SELECT e FROM Empresa e WHERE e.representante = :representante")})
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ruc")
    private String ruc;
    @Column(name = "razon_social")
    private String razonSocial;
    @Column(name = "email")
    private String email;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "representante")
    private String representante;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresa")
    private Collection<TargetEdad> targetEdadCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresa")
    private Collection<Campania> campaniaCollection;
    @OneToMany(mappedBy = "ruc")
    private Collection<FacturaEmpresa> facturaEmpresaCollection;

    public Empresa() {
    }

    public Empresa(String ruc) {
        this.ruc = ruc;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    @XmlTransient
    public Collection<TargetEdad> getTargetEdadCollection() {
        return targetEdadCollection;
    }

    public void setTargetEdadCollection(Collection<TargetEdad> targetEdadCollection) {
        this.targetEdadCollection = targetEdadCollection;
    }

    @XmlTransient
    public Collection<Campania> getCampaniaCollection() {
        return campaniaCollection;
    }

    public void setCampaniaCollection(Collection<Campania> campaniaCollection) {
        this.campaniaCollection = campaniaCollection;
    }

    @XmlTransient
    public Collection<FacturaEmpresa> getFacturaEmpresaCollection() {
        return facturaEmpresaCollection;
    }

    public void setFacturaEmpresaCollection(Collection<FacturaEmpresa> facturaEmpresaCollection) {
        this.facturaEmpresaCollection = facturaEmpresaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ruc != null ? ruc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.ruc == null && other.ruc != null) || (this.ruc != null && !this.ruc.equals(other.ruc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.models.Empresa[ ruc=" + ruc + " ]";
    }
    
}
