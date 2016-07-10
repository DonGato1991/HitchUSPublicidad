/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.models;

import java.io.Serializable;

/**
 *
 * @author homer
 */
public class SegmentoDetalleCampaniaPK implements Serializable {

    private Integer idTargetEdad;
    private String ruc;
    private Integer secCampania;
    private Integer idElemento;
    private String tarRuc;

    public SegmentoDetalleCampaniaPK() {
    }

    public SegmentoDetalleCampaniaPK(String ruc, Integer secCampania, Integer idElemento, String tarRuc, Integer idTargetEdad) {
        this.ruc = ruc;
        this.secCampania = secCampania;
        this.idElemento = idElemento;
        this.tarRuc = tarRuc;
        this.idTargetEdad = idTargetEdad;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public Integer getSecCampania() {
        return secCampania;
    }

    public void setSecCampania(Integer secCampania) {
        this.secCampania = secCampania;
    }

    public Integer getIdElemento() {
        return idElemento;
    }

    public void setIdElemento(Integer idElemento) {
        this.idElemento = idElemento;
    }

    public String getTarRuc() {
        return tarRuc;
    }

    public void setTarRuc(String tarRuc) {
        this.tarRuc = tarRuc;
    }

    public Integer getIdTargetEdad() {
        return idTargetEdad;
    }

    public void setIdTargetEdad(Integer idTargetEdad) {
        this.idTargetEdad = idTargetEdad;
    }
}
