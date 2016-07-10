/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author homer
 */
public class DetalleFactura implements Serializable{

    private Integer idDetalleFactura;
    private BigInteger cantidad;
    private String descripcion;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;
    private BigInteger codigoProducto;
    private BigDecimal valorDescuento;
    private FacturaEmpresa idFactura;
    public DetalleFactura() {
    }

    public DetalleFactura(Integer idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    public Integer getIdDetalleFactura() {
        return idDetalleFactura;
    }

    public void setIdDetalleFactura(Integer idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    public BigInteger getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigInteger getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(BigInteger codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public BigDecimal getValorDescuento() {
        return valorDescuento;
    }

    public void setValorDescuento(BigDecimal valorDescuento) {
        this.valorDescuento = valorDescuento;
    }

    public FacturaEmpresa getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(FacturaEmpresa idFactura) {
        this.idFactura = idFactura;
    }

}
