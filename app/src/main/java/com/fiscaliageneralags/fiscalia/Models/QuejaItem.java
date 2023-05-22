package com.fiscaliageneralags.fiscalia.Models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ERodriguezF on 03/01/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class QuejaItem implements Serializable {

    public enum Estado{
        EN_ESPERA,RESPONDIDA
    }

    private String asunto;
    private String mensaje;
    private String respuesta;
    private Date fecha;

    private Estado estado;
    private Funcionario funcionario;

    public QuejaItem(String asunto, String mensaje, String respuesta, Date fecha, Estado estado, Funcionario funcionario) {
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.respuesta = respuesta;
        this.fecha = fecha;
        this.estado = estado;
        this.funcionario = funcionario;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public String getHolderEstado() { return estado == Estado.EN_ESPERA ? "En Espera" : "Respondida" ; }

    public Estado getEstado() {
        return estado;
    }

    public String getHolderFecha() { return new SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault()).format(fecha); }

    public String getHolderFuncionario() { return funcionario.getNombreCompleto(); }
}
