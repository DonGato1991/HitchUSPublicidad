/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author homer
 */
@Entity
@Table(name = "factura_empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FacturaEmpresa.findAll", query = "SELECT f FROM FacturaEmpresa f"),
    @NamedQuery(name = "FacturaEmpresa.findByIdFactura", query = "SELECT f FROM FacturaEmpresa f WHERE f.idFactura = :idFactura"),
    @NamedQuery(name = "FacturaEmpresa.findByFechaEmision", query = "SELECT f FROM FacturaEmpresa f WHERE f.fechaEmision = :fechaEmision"),
    @NamedQuery(name = "FacturaEmpresa.findBySecuencial", query = "SELECT f FROM FacturaEmpresa f WHERE f.secuencial = :secuencial"),
    @NamedQuery(name = "FacturaEmpresa.findByDireccion", query = "SELECT f FROM FacturaEmpresa f WHERE f.direccion = :direccion"),
    @NamedQuery(name = "FacturaEmpresa.findByTelefono", query = "SELECT f FROM FacturaEmpresa f WHERE f.telefono = :telefono"),
    @NamedQuery(name = "FacturaEmpresa.findByValorTotal", query = "SELECT f FROM FacturaEmpresa f WHERE f.valorTotal = :valorTotal"),
    @NamedQuery(name = "FacturaEmpresa.findByPorcentajeIva", query = "SELECT f FROM FacturaEmpresa f WHERE f.porcentajeIva = :porcentajeIva"),
    @NamedQuery(name = "FacturaEmpresa.findBySubtotal", query = "SELECT f FROM FacturaEmpresa f WHERE f.subtotal = :subtotal")})
public class FacturaEmpresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_factura")
    private Integer idFactura;
    @Column(name = "fecha_emision")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEmision;
    @Column(name = "secuencial")
    private String secuencial;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    @Column(name = "porcentaje_iva")
    private BigDecimal porcentajeIva;
    @Column(name = "subtotal")
    private BigDecimal subtotal;
    @JoinColumn(name = "ruc", referencedColumnName = "ruc")
    @ManyToOne
    private Empresa ruc;

    public FacturaEmpresa() {
    }

    public FacturaEmpresa(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getSecuencial() {
        return secuencial;
    }

    public void setSecuencial(String secuencial) {
        this.secuencial = secuencial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getPorcentajeIva() {
        return porcentajeIva;
    }

    public void setPorcentajeIva(BigDecimal porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Empresa getRuc() {
        return ruc;
    }

    public void setRuc(Empresa ruc) {
        this.ruc = ruc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFactura != null ? idFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacturaEmpresa)) {
            return false;
        }
        FacturaEmpresa other = (FacturaEmpresa) object;
        if ((this.idFactura == null && other.idFactura != null) || (this.idFactura != null && !this.idFactura.equals(other.idFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.models.FacturaEmpresa[ idFactura=" + idFactura + " ]";
    }
    
}
