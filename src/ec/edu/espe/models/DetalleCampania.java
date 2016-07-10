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
public class DetalleCampania implements Serializable{

    private DetalleCampaniaPK detalleCampaniaPK;
    private BigInteger despliegues;
    private BigInteger clics;
    private String modoFacturacion;
    private Campania campania;
    private Elemento elemento;
    public DetalleCampania() {
    }

    public DetalleCampania(DetalleCampaniaPK detalleCampaniaPK) {
        this.detalleCampaniaPK = detalleCampaniaPK;
    }

    public DetalleCampania(String ruc, Integer secCampania, Integer idElemento) {
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
}
