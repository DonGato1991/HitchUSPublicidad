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
public class TargetEdad implements Serializable{

    private TargetEdadPK targetEdadPK;
    private String nombre;
    private Integer edadMinima;
    private Integer edadMaxima;
    private String genero;
    private String descripcion;
    public TargetEdad() {
    }

    public TargetEdad(TargetEdadPK targetEdadPK) {
        this.targetEdadPK = targetEdadPK;
    }

    public TargetEdad(TargetEdadPK targetEdadPK, String nombre, Integer edadMinima, Integer edadMaxima, String genero) {
        this.targetEdadPK = targetEdadPK;
        this.nombre = nombre;
        this.edadMinima = edadMinima;
        this.edadMaxima = edadMaxima;
        this.genero = genero;
    }

    public TargetEdad(String ruc, Integer idTargetEdad) {
        this.targetEdadPK = new TargetEdadPK(ruc, idTargetEdad);
    }

    public TargetEdadPK getTargetEdadPK() {
        return targetEdadPK;
    }

    public void setTargetEdadPK(TargetEdadPK targetEdadPK) {
        this.targetEdadPK = targetEdadPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(Integer edadMinima) {
        this.edadMinima = edadMinima;
    }

    public Integer getEdadMaxima() {
        return edadMaxima;
    }

    public void setEdadMaxima(Integer edadMaxima) {
        this.edadMaxima = edadMaxima;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
