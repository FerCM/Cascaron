package com.fiscaliageneralags.fiscalia.Models;

import com.fiscaliageneralags.fiscalia.Utils.StringFormatterUtil;


/**
 * Created by ERodriguezF on 06/03/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class AgenciaDeExpediente {

    public String Desc_age;
    public String no_telefonico;
    public String horario;
    public String Latitud;
    public String Longitud;
    public String Edificio;
    public String Domicilio;
    public int Cve_Mun;

    public Double getLatitud(){
        return Double.parseDouble(Latitud);
    }

    public Double getLongitud(){
        return Double.parseDouble(Longitud);
    }

    public String getNombre(){ return StringFormatterUtil.convertStringToFUL(Desc_age); }

    public String getHorario(){ return StringFormatterUtil.convertStringToFUL(horario); }


}
