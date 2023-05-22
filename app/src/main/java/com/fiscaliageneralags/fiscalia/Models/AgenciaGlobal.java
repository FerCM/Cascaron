package com.fiscaliageneralags.fiscalia.Models;

import com.fiscaliageneralags.fiscalia.Utils.StringFormatterUtil;


/**
 * Created by ERodriguezF on 26/02/2018.
 * @author ERodriguezF
 * @version 1.18
 */
public class AgenciaGlobal
{
    public String Nombre;

    public String Telefono;

    public int NumeroAgencias;

    public String Horario;

    public String Domicilio;

    public String getNombre(){ return StringFormatterUtil.convertStringToFUL(Nombre); }

    public String getHorario(){ return StringFormatterUtil.convertStringToFUL(Horario); }
}