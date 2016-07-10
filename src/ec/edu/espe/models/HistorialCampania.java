/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author homer
 */
public class HistorialCampania implements Serializable{
    private Campania campania;
    private Integer idHistorialCampania;
    private Date fechaCompra;
    private BigInteger clics;
    private BigDecimal costoClic;
    private BigInteger despliegues;
    private BigDecimal costoDespliegue;

    public HistorialCampania() {
    }

    public HistorialCampania(Integer idHistorialCampania) {
        this.idHistorialCampania = idHistorialCampania;
    }

    public Integer getIdHistorialCampania() {
        return idHistorialCampania;
    }

    public void setIdHistorialCampania(Integer idHistorialCampania) {
        this.idHistorialCampania = idHistorialCampania;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public BigInteger getClics() {
        return clics;
    }

    public void setClics(BigInteger clics) {
        this.clics = clics;
    }

    public BigDecimal getCostoClic() {
        return costoClic;
    }

    public void setCostoClic(BigDecimal costoClic) {
        this.costoClic = costoClic;
    }

    public BigInteger getDespliegues() {
        return despliegues;
    }

    public void setDespliegues(BigInteger despliegues) {
        this.despliegues = despliegues;
    }

    public BigDecimal getCostoDespliegue() {
        return costoDespliegue;
    }

    public void setCostoDespliegue(BigDecimal costoDespliegue) {
        this.costoDespliegue = costoDespliegue;
    }

    public Campania getCampania() {
        return campania;
    }

    public void setCampania(Campania campania) {
        this.campania = campania;
    }
}
