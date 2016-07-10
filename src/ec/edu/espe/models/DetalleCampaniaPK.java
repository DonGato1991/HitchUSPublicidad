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
public class DetalleCampaniaPK implements Serializable {

    private Integer idElemento;
    private String ruc;
    private Integer secCampania;

    public DetalleCampaniaPK() {
    }

    public DetalleCampaniaPK(String ruc, Integer secCampania, Integer idElemento) {
        this.ruc = ruc;
        this.secCampania = secCampania;
        this.idElemento = idElemento;
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
}
