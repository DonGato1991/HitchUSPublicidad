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
public class Elemento implements Serializable{

    private Integer idElemento;
    private String nombre;
    private String posicion;
    private String path;
    private String url;
    private String nombreImagen;
    private String ruc;
    private String pantalla;
    
    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getPantalla() {
        return pantalla;
    }

    public void setPantalla(String pantalla) {
        this.pantalla = pantalla;
    }

    public Elemento() {
    }

    public Elemento(Integer idElemento) {
        this.idElemento = idElemento;
    }

    public Elemento(Integer idElemento, String nombre, String posicion, String path) {
        this.idElemento = idElemento;
        this.nombre = nombre;
        this.posicion = posicion;
        this.path = path;
    }

    public Integer getIdElemento() {
        return idElemento;
    }

    public void setIdElemento(Integer idElemento) {
        this.idElemento = idElemento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
