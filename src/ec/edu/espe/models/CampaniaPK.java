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
public class CampaniaPK implements Serializable {

    private Integer secCampania;
    private String ruc;

    public CampaniaPK() {
    }

    public CampaniaPK(String ruc, Integer secCampania) {
        this.ruc = ruc;
        this.secCampania = secCampania;
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

}
