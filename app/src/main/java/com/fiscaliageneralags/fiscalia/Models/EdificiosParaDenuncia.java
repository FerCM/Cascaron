package com.fiscaliageneralags.fiscalia.Models;

/**
 * Created by ERodriguezF on 05/03/2018.
 * @author ERodriguezF
 * @version 1.18
 */
public class EdificiosParaDenuncia {

    public String Latitud;
    public String Longitud;
    public String Edificio;
    public String Horario_Denuncia;
    public int Cve_Mun;
    public String Telefono;
    public String Domicilio;

    public Double getLatitud(){
        return Double.parseDouble(Latitud);
    }

    public Double getLongitud(){
        return Double.parseDouble(Longitud);
    }

}
