package com.fiscaliageneralags.fiscalia.Models;

import android.graphics.drawable.Drawable;

/**
 * Created by ERodriguezF on 18/12/2017.
 * @author ERodriguezF
 * @version 1.18
 */

public class NumeroEmergenciaItem{

    private Drawable icon;
    private String descripcion;
    private String numeroEmergencia;
    private String numero;

    public NumeroEmergenciaItem(Drawable icon, String descripcion, String numeroEmergencia, String numero) {
        this.icon = icon;
        this.descripcion = descripcion;
        this.numeroEmergencia = numeroEmergencia;
        this.numero = numero;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNumeroEmergencia() {
        return numeroEmergencia;
    }

    public String getNumeroParaLlamada() {
        return numero;
    }
}
