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
public class SegmentoDetalleCampania implements Serializable {

    private TargetEdad targetEdad;
    private SegmentoDetalleCampaniaPK segmentoDetalleCampaniaPK;
    private String horaInicio;
    private String horaFin;
    private String maximoHora;
    private String minimoHora;
    private DetalleCampania detalleCampania;

    public SegmentoDetalleCampania() {
    }

    public SegmentoDetalleCampania(SegmentoDetalleCampaniaPK segmentoDetalleCampaniaPK) {
        this.segmentoDetalleCampaniaPK = segmentoDetalleCampaniaPK;
    }

    public SegmentoDetalleCampania(String ruc, Integer secCampania, Integer idElemento, String tarRuc, Integer idTargetEdad) {
        this.segmentoDetalleCampaniaPK = new SegmentoDetalleCampaniaPK(ruc, secCampania, idElemento, tarRuc, idTargetEdad);
    }

    public SegmentoDetalleCampaniaPK getSegmentoDetalleCampaniaPK() {
        return segmentoDetalleCampaniaPK;
    }

    public void setSegmentoDetalleCampaniaPK(SegmentoDetalleCampaniaPK segmentoDetalleCampaniaPK) {
        this.segmentoDetalleCampaniaPK = segmentoDetalleCampaniaPK;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getMaximoHora() {
        return maximoHora;
    }

    public void setMaximoHora(String maximoHora) {
        this.maximoHora = maximoHora;
    }

    public String getMinimoHora() {
        return minimoHora;
    }

    public void setMinimoHora(String minimoHora) {
        this.minimoHora = minimoHora;
    }

    public DetalleCampania getDetalleCampania() {
        return detalleCampania;
    }

    public void setDetalleCampania(DetalleCampania detalleCampania) {
        this.detalleCampania = detalleCampania;
    }

    public TargetEdad getTargetEdad() {
        return targetEdad;
    }

    public void setTargetEdad(TargetEdad targetEdad) {
        this.targetEdad = targetEdad;
    }
}
