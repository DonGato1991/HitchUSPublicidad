/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.models;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author homer
 */
public class Campania implements Serializable{

    private CampaniaPK campaniaPK;
    private String nombre;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;
    private EstadisticaCampania estadisticaCampania;
    public Campania() {
    }

    public Campania(CampaniaPK campaniaPK) {
        this.campaniaPK = campaniaPK;
    }

    public Campania(CampaniaPK campaniaPK, String nombre, String descripcion, Date fechaCreacion, Date fechaInicio, Date fechaFin, String estado) {
        this.campaniaPK = campaniaPK;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

    public Campania(String ruc, Integer secCampania) {
        this.campaniaPK = new CampaniaPK(ruc, secCampania);
    }

    public CampaniaPK getCampaniaPK() {
        return campaniaPK;
    }

    public void setCampaniaPK(CampaniaPK campaniaPK) {
        this.campaniaPK = campaniaPK;
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

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public EstadisticaCampania getEstadisticaCampania() {
        return estadisticaCampania;
    }

    public void setEstadisticaCampania(EstadisticaCampania estadisticaCampania) {
        this.estadisticaCampania = estadisticaCampania;
    }
}
