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
public class TargetEdadPK implements Serializable{

    private String ruc;
    private Integer idTargetEdad;

    public TargetEdadPK() {
    }

    public TargetEdadPK(String ruc, Integer idTargetEdad) {
        this.ruc = ruc;
        this.idTargetEdad = idTargetEdad;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public Integer getIdTargetEdad() {
        return idTargetEdad;
    }

    public void setIdTargetEdad(Integer idTargetEdad) {
        this.idTargetEdad = idTargetEdad;
    }

    @Override
    public String toString() {
        return ""+ruc + "-" + idTargetEdad;
    }
    
}
