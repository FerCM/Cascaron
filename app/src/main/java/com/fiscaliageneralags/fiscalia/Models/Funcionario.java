package com.fiscaliageneralags.fiscalia.Models;

import java.io.Serializable;

/**
 * Created by ERodriguezF on 03/01/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class Funcionario implements Serializable{

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;

    public Funcionario(String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombreCompleto(){ return new StringBuilder().append(nombre).append(' ').append(apellidoPaterno).append(' ').append(apellidoMaterno).toString(); }

    @Override
    public String toString() {
        return this.getNombreCompleto();
    }
}
