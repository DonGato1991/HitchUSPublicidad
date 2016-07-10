/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.models;

import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @author homer
 */
public class EstadisticaCampania implements Serializable{
    private Campania campania;
    private EstadisticaCampaniaPK estadisticaCampaniaPK;
    private BigInteger despliegues;
    private BigInteger clics;

    public EstadisticaCampania() {
    }

    public EstadisticaCampania(EstadisticaCampaniaPK estadisticaCampaniaPK) {
        this.estadisticaCampaniaPK = estadisticaCampaniaPK;
    }

    public EstadisticaCampania(String ruc, Integer secCampania) {
        this.estadisticaCampaniaPK = new EstadisticaCampaniaPK(ruc, secCampania);
    }

    public EstadisticaCampaniaPK getEstadisticaCampaniaPK() {
        return estadisticaCampaniaPK;
    }

    public void setEstadisticaCampaniaPK(EstadisticaCampaniaPK estadisticaCampaniaPK) {
        this.estadisticaCampaniaPK = estadisticaCampaniaPK;
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

    public Campania getCampania() {
        return campania;
    }

    public void setCampania(Campania campania) {
        this.campania = campania;
    }

}
