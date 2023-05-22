package com.fiscaliageneralags.fiscalia.Models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ERodriguezF on 27/12/2017.
 * @author ERodriguezF
 * @version 1.18
 */

public class MensajeAlFiscalItem implements Serializable {

    public enum Estado{
        EN_ESPERA,RESPONDIDA
    }
    public enum Tipo{
        SUGERENCIA, QUEJA
    }

    private String asunto;
    private String mensaje;
    private String respuesta;
    private Date fecha;



    private Estado estado;
    private Tipo tipo;

    public MensajeAlFiscalItem(String asunto, String mensaje, String respuesta, Date fecha, Estado estado, Tipo tipo) {
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.respuesta = respuesta;
        this.fecha = fecha;
        this.estado = estado;
        this.tipo = tipo;
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

    public String getHolderEstado() {
        return estado == Estado.EN_ESPERA ? "En Espera" : "Respondida" ;
    }

    public String getHolderTipo() { return tipo == Tipo.QUEJA ? "Queja" : "Sugerencia" ; }

    public Estado getEstado() {
        return estado;
    }

    public Tipo getTipo() {
        return tipo;
    }



    public String getHolderFecha() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault());
        return simpleDateFormat.format(fecha);
        //return fecha.toString();
    }
}
